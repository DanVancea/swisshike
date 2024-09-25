package com.swisssign.swisshike.controller;

import com.swisssign.swisshike.model.Tour;
import com.swisssign.swisshike.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/com.swisssign.swisshike/tours")
public class TourController {

    @Autowired
    private TourService tourService;

    @PostMapping
    public ResponseEntity<Tour> createTour(@Requestbody Map<String, String> tourDetails) throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("E MM dd kk:mm:ss z yyyy");

        String name = tourDetails.get("name");
        String difficulty = tourDetails.get("difficulty");
        Date startDate = dateFormat.parse(tourDetails.get("startDate"));
        Date endDate = dateFormat.parse(tourDetails.get("endDate"));
        long hutId = Long.parseLong(tourDetails.get("hutId"));

        Tour createdTour = tourService.createTour(name, difficulty, startDate, endDate, hutId);
        return new ResponseEntity<>(createdTour, HttpStatus.CREATED);
    }

    @PostMapping("/{tourId}/hikers/{hikerId}")
    public ResponseEntity<Tour> assignHikerToTour(@PathVariable Long tourId, @PathVariable Long hikerId) {
        Tour updatedTour = tourService.assignHikerToTour(tourId, hikerId);
        return new ResponseEntity<>(updatedTour, HttpStatus.OK);
    }
}
