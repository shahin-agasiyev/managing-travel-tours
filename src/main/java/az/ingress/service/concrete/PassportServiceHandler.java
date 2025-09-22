package az.ingress.service.concrete;

import az.ingress.dao.entity.PassportEntity;
import az.ingress.dao.repository.PassportRepository;
import az.ingress.exception.AlreadyExistsException;
import az.ingress.exception.NotFoundException;
import az.ingress.model.request.PassportRequest;
import az.ingress.model.response.PassportResponse;
import az.ingress.service.abstraction.PassportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

import static az.ingress.exception.ErrorMessage.PASSPORT_ALREADY_HAS;
import static az.ingress.exception.ErrorMessage.PASSPORT_NOT_FOUND;
import static az.ingress.mapper.PassportMapper.PASSPORT_MAPPER;
import static java.time.LocalDate.now;

@Slf4j
@Service
@RequiredArgsConstructor
public class PassportServiceHandler implements PassportService {

    private final PassportRepository passportRepository;
    private final GuideServiceHandler guideServiceHandler;
    private final SecureRandom random = new SecureRandom();

    @Override
    public void create(Long guideId, PassportRequest passportRequest) {
        var guideEntity = guideServiceHandler.fetchGuideOrThrow(guideId);
        if (guideEntity.getPassport() != null) throw new AlreadyExistsException(PASSPORT_ALREADY_HAS.getMessage());
        var issueDate = now();
        var expiryDate = issueDate.plusYears(5);

        var passportEntity = PASSPORT_MAPPER.getPassportEntity(passportRequest, guideEntity, issueDate, expiryDate);
        passportEntity.setPassportNumber(generateUniquePassportNumber());
        passportRepository.save(passportEntity);
    }

    @Override
    public PassportResponse getById(Long id) {
        var passportEntity = fetchPassportOrThrow(id);
        return PASSPORT_MAPPER.mapPassportEntityToResponse(passportEntity);
    }

    @Override
    public void delete(Long id) {
        fetchPassportOrThrow(id);
        passportRepository.deleteById(id);
    }

    public PassportEntity fetchPassportOrThrow(Long id) {
        return passportRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("ActionLog.fetchGuideOrThrow.error: {}", id);
                    return new NotFoundException(PASSPORT_NOT_FOUND.getMessage(), id);
                });
    }

    private String generateUniquePassportNumber() {
        String number;
        do {
            int code = 10000000 + random.nextInt(90000000);
            number = "AZE-" + code;
        } while (passportRepository.existsByPassportNumber(number));
        return number;
    }
}
