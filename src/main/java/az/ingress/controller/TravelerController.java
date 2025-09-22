package az.ingress.controller;

import az.ingress.model.request.TravelerRequest;
import az.ingress.model.response.TravelerResponse;
import az.ingress.service.abstraction.TravelerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/v1/travelers")
public class TravelerController {

    private final TravelerService travelerService;

    @ResponseStatus(CREATED)
    @PostMapping
    public void create(@RequestBody @Valid TravelerRequest travelerRequest) {
        travelerService.create(travelerRequest);
    }

    @GetMapping("/{id}")
    public TravelerResponse getById(@PathVariable Long id) {
        return travelerService.getById(id);
    }

    @GetMapping
    public List<TravelerResponse> getAll() {
        return travelerService.getAll();
    }

    @GetMapping("/{tourId}")
    public List<TravelerResponse> getByTourId(@PathVariable Long tourId) {
        return travelerService.getByTourId(tourId);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        travelerService.delete(id);
    }
}
