package com.example.Senla.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ilyas Nigamatullin
 */
@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class Messages {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "message")
  private String message;

  @ManyToOne
  @JoinColumn(name = "create_person_id")
  private Person person;

  @Column(name = "create_at")
  private LocalDateTime createAt;

  @Column(name = "recipient_person_id")
  private Integer recipientPersonId;

  @Column(name = "is_read")
  private Boolean isRead;

}
