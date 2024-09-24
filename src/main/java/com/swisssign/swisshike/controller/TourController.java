package com.swisssign.swisshike.controller;

import com.swisssign.swisshike.model.Tour;
import com.swisssign.swisshike.service.TourService;

import java.util.Date;

@RestController
@RequestMapping("/com.swisssign.swisshike/tours")
public class TourController {

    @Autowired
    private TourService tourService;

    @PostMapping
    public ResponseEntity<Tour> createTour(@Requestbody Map<String, String> tourDetails) {
        String name = tourDetails.get("name");
        String difficulty = tourDetails.get("difficulty");
        Date startDate = tourDetails.get("startDate");
        Date endDate = tourDetails.get("endDate");
        Long hutId = Long.parseLong(tourDetails.get("hutId"));

        Tour createdTour = tourService.createTour(name, difficulty, startDate, endDate, hutId);
        return new ResponseEntity<>(createdTour, HttpStatus.CREATED);
    }

    @PostMapping("/{tourId}/hikers/{hikerId}")
    public ResponseEntity<Tour> assignHikerToTour(@PathVariable Long tourId, @PathVariable Long hikerId) {
        Tour updatedTour = tourService.assignHikerToTour(tourId, hikerId);
        return new ResponseEntity<>(updatedTour, HttpStatus.OK);
    }
}
