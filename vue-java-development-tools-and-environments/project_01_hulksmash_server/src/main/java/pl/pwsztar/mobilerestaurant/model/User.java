package pl.pwsztar.mobilerestaurant.model;

import com.sun.istack.Nullable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users", schema = "restauracja",
  uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "email")
  })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @Nullable
  @Size(max = 50)
  private String phone;

  @Nullable
  @Size(max = 50)
  private String name;

  @Nullable
  @Size(max = 50)
  private String surname;

  @NotBlank
  @Size(max = 120)
  private String password;

  @OneToOne()
  @Nullable
  @JoinColumn(name = "id_adresu", referencedColumnName = "id_adresu")
  private AddressDto address;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(	name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }



  public String getSurname() {
    return surname;
  }

  public void setSurname(String surmane) {
    this.surname = surmane;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public AddressDto getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setAddress(AddressDto address) {
    this.address = address;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
