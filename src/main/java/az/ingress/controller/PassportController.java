package az.ingress.controller;

import az.ingress.model.request.PassportRequest;
import az.ingress.model.response.PassportResponse;
import az.ingress.service.abstraction.PassportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/passports")
public class PassportController {

    private final PassportService passportService;

    @ResponseStatus(CREATED)
    @PostMapping("/guides/{guideId}")
    public void create(@PathVariable Long guideId,
                       @RequestBody @Valid PassportRequest passportRequest) {
        passportService.create(guideId, passportRequest);
    }

    @GetMapping("/{id}")
    public PassportResponse getById(@PathVariable Long id) {
        return passportService.getById(id);
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody @Valid PassportRequest passportRequest) {
        passportService.update(id, passportRequest);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        passportService.delete(id);
    }
}