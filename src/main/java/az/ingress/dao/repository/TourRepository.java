package az.ingress.dao.repository;

import az.ingress.dao.entity.TourEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourRepository extends JpaRepository<TourEntity, Long> {

    @Query("SELECT t FROM TourEntity t JOIN t.guides g WHERE g.id = :guideId")
    List<TourEntity> findAllByGuidesId(Long guideId);

    @EntityGraph(attributePaths = {"destinations"})
    List<TourEntity> findAll();
}
