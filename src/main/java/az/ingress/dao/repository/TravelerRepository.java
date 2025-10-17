package az.ingress.dao.repository;

import az.ingress.dao.entity.TravelerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TravelerRepository extends JpaRepository<TravelerEntity, Long> {

    @Query("""
               SELECT CASE WHEN COUNT(tr) > 0 THEN true ELSE false END
               FROM TravelerEntity tr
               JOIN tr.tours t
               WHERE tr.id = :travelerId
                 AND t.startDate <= :endDate
                 AND t.endDate   >= :startDate
            """)
    boolean existsTravelerBusyInRange(Long travelerId,
                                      LocalDate startDate,
                                      LocalDate endDate);


    @Query("SELECT t FROM TravelerEntity t JOIN t.tours  tr WHERE tr.id =: tourId")
    List<TravelerEntity> findAllByTourId(Long tourId);
}
