package az.ingress.dao.repository;

import az.ingress.dao.entity.DestinationEntity;
import az.ingress.dao.entity.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinationRepository extends JpaRepository<DestinationEntity, Long> {

    List<DestinationEntity> findAllByTour(TourEntity tour);
}
