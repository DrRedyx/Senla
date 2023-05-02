package com.example.Senla.Repository;

import java.util.Optional;

import com.example.Senla.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ilyas Nigamatullin
 */
@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

  Optional<Category> findByName(String name);
}
