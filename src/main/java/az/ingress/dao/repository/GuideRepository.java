package az.ingress.dao.repository;

import az.ingress.dao.entity.GuideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface GuideRepository extends JpaRepository<GuideEntity, Long> {

    @Query("""
            SELECT g
            FROM GuideEntity g
            WHERE g.id NOT IN (
                 SELECT g2.id
                 FROM GuideEntity g2
                 JOIN g2.tours t
                 WHERE t.startDate < :endDate
                   AND t.endDate   > :startDate
            )
            """)
    List<GuideEntity> findAllAvailableGuides(LocalDate startDate, LocalDate endDate);

    @Query("""
               SELECT CASE WHEN COUNT(g) > 0 THEN true ELSE false END
               FROM GuideEntity g
               JOIN g.tours t
               WHERE g.id = :guideId
                 AND t.startDate <= :endDate
                 AND t.endDate   >= :startDate
            """)
    boolean existsGuideBusyInRange(Long guideId,
                                   LocalDate startDate,
                                   LocalDate endDate);
}
