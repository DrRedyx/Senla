package com.example.Senla.Repository;

import java.util.List;
import java.util.Optional;

import com.example.Senla.Entity.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Ilyas Nigamatullin
 */
@Repository
public interface AdvertRepo extends JpaRepository<Advert, Integer> {

  Optional<Advert> findById(int id);

  @Query(value = "SELECT a FROM Advert a "
                 + "JOIN a.person p"
                 + " LEFT JOIN p.grade g "
                 + "WHERE a.isActual = true "
                 + "ORDER BY a.isPaid DESC, g.averageGrade desc")
  List<Advert> getAllActualAdverts();

  @Query(value = "SELECT * FROM Advert a WHERE a.person_id = ?1 AND a.is_sale = true",
      nativeQuery = true)
  List<Advert> getAllMySaleAdvert(int id);

  List<Advert> getAllByPersonId(int id);

  List<Advert> getAdvertsByTitleContains(String title);

}
