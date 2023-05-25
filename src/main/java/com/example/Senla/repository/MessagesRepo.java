package com.example.Senla.repository;

import java.util.List;

import com.example.Senla.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Ilyas Nigamatullin
 */
@Repository
public interface MessagesRepo extends JpaRepository<Messages, Integer> {

  @Query(value = "SELECT * FROM messages "
                 + "WHERE (create_person_id = ?1 "
                 + "AND recipient_person_id = ?2) "
                 + "OR (create_person_id = ?2 "
                 + "AND recipient_person_id = ?1) "
                 + "ORDER BY create_at", nativeQuery = true)
  List<Messages> findChat(int createPersonId, int recipientPersonId);

  @Query(value = "SELECT * FROM messages "
                 + "WHERE recipient_person_id = ? "
                 + "AND is_read = false "
                 + "ORDER BY create_at", nativeQuery = true)
  List<Messages> findAllMyNewMessages(int id);
}
