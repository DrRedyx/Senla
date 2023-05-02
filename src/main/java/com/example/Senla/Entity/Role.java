package com.example.Senla.Entity;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Ilyas Nigamatullin
 */
@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
public class Role implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;

  @ManyToMany(mappedBy = "roleSet")
  private Set<Person> personSet;

  @Override
  public String getAuthority() {
    return getName();
  }
}
