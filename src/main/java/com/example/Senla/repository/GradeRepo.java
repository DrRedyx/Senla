package com.example.Senla.repository;

import java.util.Optional;

import com.example.Senla.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ilyas Nigamatullin
 */
@Repository
public interface GradeRepo extends JpaRepository<Grade, Integer> {

  Optional<Grade> findByPersonId(int personId);

}
