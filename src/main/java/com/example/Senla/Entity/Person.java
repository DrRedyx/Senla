package com.example.Senla.Entity;

import java.util.Collection;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Ilyas Nigamatullin
 */
@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
public class Person implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "email")
  private String email;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "password")
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "person_role",
  joinColumns = @JoinColumn(name = "person_id"),
  inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roleSet;

  @OneToOne(mappedBy = "person", fetch = FetchType.EAGER)
  private Grade grade;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getRoleSet();
  }

  @Override
  public String getUsername() {
    return email;
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

  @Override
  public boolean isEnabled() {
    return true;
  }
}
