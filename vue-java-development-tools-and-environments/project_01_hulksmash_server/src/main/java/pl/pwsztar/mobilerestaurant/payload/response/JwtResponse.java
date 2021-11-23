package pl.pwsztar.mobilerestaurant.payload.response;

import pl.pwsztar.mobilerestaurant.model.AddressDto;

import java.util.List;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String email;
  private String phone;
  private String name;
  private String surname;
  private AddressDto address;
  private List<String> roles;

  public JwtResponse(String accessToken, Long id, String username, String email, String phone, String name, String surname, AddressDto address, List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.email = email;
    this.phone = phone;
    this.name = name;
    this.surname = surname;
    this.roles = roles;
    this.address = address;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public Long getId() {
    return id;
  }

  public String getPhone() {
    return phone;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public AddressDto getAddress() {
    return address;
  }

  public void setAddress(AddressDto address) {
    this.address = address;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }
}