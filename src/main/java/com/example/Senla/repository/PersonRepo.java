package com.example.Senla.repository;

import java.util.List;
import java.util.Optional;

import com.example.Senla.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Ilyas Nigamatullin
 */
@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {

  Optional<Person> findByUsername(String email);

  @Query(value = "SELECT * FROM person", nativeQuery = true)
  List<Person> findAllBy();
}
