package com.example.Senla.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ilyas Nigamatullin
 */
@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
public class Comments {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "comment")
  private String comment;

  @Column(name = "create_at")
  private LocalDateTime createAt;

  @ManyToOne
  @JoinColumn(name = "advert_id")
  private Advert advert;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person person;
}
