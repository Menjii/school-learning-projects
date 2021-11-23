package pl.pwsztar.mobilerestaurant.controllers;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pwsztar.mobilerestaurant.model.AddressDto;
import pl.pwsztar.mobilerestaurant.model.ERole;
import pl.pwsztar.mobilerestaurant.model.Role;
import pl.pwsztar.mobilerestaurant.model.User;
import pl.pwsztar.mobilerestaurant.payload.request.LoginRequest;
import pl.pwsztar.mobilerestaurant.payload.request.SignupRequest;
import pl.pwsztar.mobilerestaurant.payload.response.JwtResponse;
import pl.pwsztar.mobilerestaurant.payload.response.MessageResponse;
import pl.pwsztar.mobilerestaurant.repositories.AddressRepository;
import pl.pwsztar.mobilerestaurant.repositories.RoleRepository;
import pl.pwsztar.mobilerestaurant.repositories.UserRepository;
import pl.pwsztar.mobilerestaurant.security.jwt.JwtUtils;
import pl.pwsztar.mobilerestaurant.security.services.UserDetailsImpl;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  private AddressRepository addressRepository;

  @PostMapping("/updateUser")
  public ResponseEntity<?> authenticateUser(@RequestBody UserDetailsImpl user) {
    System.out.println(user.toString());
    AddressDto addressDto = addressRepository.save(user.getAddress());
    User updatedUser = userRepository.findById(user.getId()).get();
    updatedUser.setAddress(addressDto);
    updatedUser.setPhone(user.getPhone());
    updatedUser.setName(user.getName());
    updatedUser.setSurname(user.getSurname());
    return ResponseEntity.ok(userRepository.save(updatedUser));
  }

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
      .map(item -> item.getAuthority())
      .collect(Collectors.toList());

    System.out.println(userDetails.toString());

    return ResponseEntity.ok(new JwtResponse(jwt,
      userDetails.getId(),
      userDetails.getUsername(),
      userDetails.getEmail(),
      userDetails.getPhone(),
      userDetails.getName(),
      userDetails.getSurname(),
      userDetails.getAddress(),
      roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
      signUpRequest.getEmail(),
      encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            break;
          case "mod":
            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);

            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    user.setName("-");
    user.setPhone("-");
    AddressDto addressDto = new AddressDto();
    addressDto.setCity("-");
    addressDto.setHomeNumber("-");
    addressDto.setPostNumber("-");
    addressDto.setStreet("-");
    user.setAddress(addressRepository.save(addressDto));
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}