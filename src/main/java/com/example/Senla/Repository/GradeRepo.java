package com.example.Senla.Repository;

import java.util.Optional;

import com.example.Senla.Entity.Grade;
import com.example.Senla.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ilyas Nigamatullin
 */
@Repository
public interface GradeRepo extends JpaRepository<Grade, Integer> {

  Optional<Grade> findByPersonId(int personId);

}
