package az.ingress.controller;

import az.ingress.model.request.TourRequest;
import az.ingress.model.response.TourResponse;
import az.ingress.service.abstraction.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/tours")
public class TourController {

    private final TourService tourService;

    @ResponseStatus(CREATED)
    @PostMapping
    public void create(@RequestBody @Valid TourRequest tourRequest) {
        tourService.create(tourRequest);
    }

    @GetMapping("/{id}")
    public TourResponse getById(@PathVariable Long id) {
        return tourService.getById(id);
    }

    @GetMapping
    public List<TourResponse> getAllTours() {
        return tourService.getAllTours();
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody @Valid TourRequest tourRequest) {
        tourService.update(id, tourRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void delete(@PathVariable Long id) {
        tourService.delete(id);
    }

    @GetMapping("/guides/{guideId}")
    public List<TourResponse> getAllToursByGuideId(@PathVariable Long guideId) {
        return tourService.getAllToursByGuideId(guideId);
    }

    @ResponseStatus(OK)
    @PatchMapping("/{tourId}/guides/{guideId}")
    public void assignGuideToTour(@PathVariable Long tourId,
                                  @PathVariable Long guideId) {
        tourService.assignGuideToTour(tourId, guideId);
    }

    @ResponseStatus(OK)
    @PatchMapping("/{tourId}/travelers/{travelerId}")
    public void assignTravelerToTour(@PathVariable Long tourId,
                                     @PathVariable Long travelerId) {
        tourService.assignTravelerToTour(tourId, travelerId);
    }
}