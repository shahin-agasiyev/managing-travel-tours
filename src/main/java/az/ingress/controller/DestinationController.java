package az.ingress.controller;

import az.ingress.model.request.DestinationRequest;
import az.ingress.model.response.DestinationResponse;
import az.ingress.service.abstraction.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/destinations")
public class DestinationController {

    private final DestinationService destinationService;

    @ResponseStatus(CREATED)
    @PostMapping("/tour/{tourId}")
    public void create(@PathVariable Long tourId,
                       @RequestBody DestinationRequest request) {
        destinationService.create(tourId, request);
    }

    @GetMapping("/{id}")
    public DestinationResponse getById(@PathVariable Long id) {
        return destinationService.getById(id);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        destinationService.delete(id);
    }

    @GetMapping("/tour/{tourId}")
    public List<DestinationResponse> getAllByTourId(@PathVariable Long tourId) {
        return destinationService.getAllByTourId(tourId);
    }
}
