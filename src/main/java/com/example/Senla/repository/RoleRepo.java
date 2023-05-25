package com.example.Senla.repository;

import com.example.Senla.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ilyas Nigamatullin
 */
@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

  Role findByName(String name);
}
