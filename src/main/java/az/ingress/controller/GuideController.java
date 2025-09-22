package az.ingress.controller;

import az.ingress.model.request.GuideRequest;
import az.ingress.model.response.GuideResponse;
import az.ingress.service.abstraction.GuideService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/guides")
public class GuideController {

    private final GuideService guideService;

    @ResponseStatus(CREATED)
    @PostMapping
    public void create(@RequestBody @Valid GuideRequest guideRequest) {
        guideService.create(guideRequest);
    }

    @GetMapping("/{id}")
    public GuideResponse getById(@PathVariable Long id) {
        return guideService.getById(id);
    }

    @GetMapping
    public List<GuideResponse> getAllGuides() {
        return guideService.getAllGuides();
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        guideService.delete(id);
    }

    @GetMapping("/available")
    public List<GuideResponse> getAvailableGuides(
            @RequestParam @DateTimeFormat(iso = DATE) LocalDate startDate, //TODO: haven't request param validation
            @RequestParam @DateTimeFormat(iso = DATE) LocalDate endDate) {
        return guideService.getAvailableGuides(startDate, endDate);
    }
}
