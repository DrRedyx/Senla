package com.example.Senla.Repository;

import java.util.List;

import com.example.Senla.Entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ilyas Nigamatullin
 */
@Repository
public interface CommentsRepo extends JpaRepository<Comments, Integer> {

  List<Comments> findByPersonId(int id);

  List<Comments> findByAdvertId(int id);
}
