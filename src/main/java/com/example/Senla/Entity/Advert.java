package com.example.Senla.Entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ilyas Nigamatullin
 */
@Entity
@Table(name = "advert")
@Getter
@Setter
@NoArgsConstructor
public class Advert {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private Integer price;

  @ManyToOne
  private Person person;

  @Column(name = "is_actual")
  private boolean isActual;

  @Column(name = "is_sale")
  private boolean isSale;

  @Column(name = "is_paid")
  private boolean isPaid;

  @OneToMany(mappedBy = "advert")
  private List<Comments> comments;

  @ManyToMany
  @JoinTable(name = "advert_category",
  joinColumns = @JoinColumn(name = "advert_id"),
  inverseJoinColumns = @JoinColumn(name = "category_id"))
  private Set<Category> categorySet;







}
