package az.ingress.dao.repository;

import az.ingress.dao.entity.PassportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<PassportEntity, Long> {
    boolean existsByPassportNumber(String passportNumber);
}
