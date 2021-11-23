package pl.pwsztar.mobilerestaurant.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.pwsztar.mobilerestaurant.model.AddressDto;
import pl.pwsztar.mobilerestaurant.model.User;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;
	private AddressDto address;

	private String email;
	private String phone;
	private String name;
	private String surname;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String username, AddressDto address, String email, String phone, String name, String surname, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.address = address;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getId(), 
				user.getUsername(),
        user.getAddress(),
				user.getEmail(),
				user.getPhone(),
				user.getName(),
				user.getSurname(),
				user.getPassword(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

  public AddressDto getAddress() {
    return address;
  }



  public String getPhone() {
    return phone;
  }

  @Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  @Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

  @Override
  public String toString() {
    return "UserDetailsImpl{" +
      "id=" + id +
      ", username='" + username + '\'' +
      ", address=" + address.toString() +
      ", email='" + email + '\'' +
      ", phone='" + phone + '\'' +
      ", name='" + name + '\'' +
      ", surname='" + surname + '\'' +
      ", password='" + password + '\'' +
      ", authorities=" + authorities +
      '}';
  }
}
