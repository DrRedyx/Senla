package com.example.Senla.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ilyas Nigamatullin
 */
@Entity
@Table(name = "grade")
@Getter
@Setter
@NoArgsConstructor
public class Grade {

  @Id
  private Integer id;

  @Column(name = "grade")
  private Integer grade;

  @Column(name = "count")
  private Integer count;

  @Column(name = "average_grade")
  private Integer averageGrade;

  @OneToOne
  @MapsId
  @JoinColumn(name = "person_id", referencedColumnName = "id")
  private Person person;
}
