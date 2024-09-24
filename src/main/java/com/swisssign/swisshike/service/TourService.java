package com.swisssign.swisshike.service;

import com.swisssign.swisshike.model.Hiker;
import com.swisssign.swisshike.model.MountainHut;
import com.swisssign.swisshike.model.Tour;
import com.swisssign.swisshike.repository.HikerRepository;
import com.swisssign.swisshike.repository.MountainHutRepository;
import com.swisssign.swisshike.repository.TourRepository;

import java.util.Date;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private HikerRepository hikerRepository;

    @Autowired
    private MountainHutRepository mountainHutRepository;

    public Tour createTour(String name, String difficulty, Date startDate, Date endDate, long hutId, List<Long> hickerId) {
        MountainHut hut = mountainHutRepository.findById(hutId).orElseThrow(() -> new IllegalArgumentException("Mountain hut not found"));

        Tour tour = new Tour.TourBuilder()
                .name(name)
                .difficulty(difficulty)
                .startDate(startDate)
                .endDate(endDate)
                .hut(hut)
                .build();

        return tourRepository.save(tour);
    }

    public Tour assignHikerToTour(Long tourId, Long hikerId) {
        Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new ResourceNotFoundException("Tour not found"));
        Hiker Hiker = hikerRepository.findById(hikerId).orElseThrow(() -> new ResourceNotFoundException("Hiker not found"));

        if(!hiker.getExperienceLevel().equalsIgnoreCase(tour.getDifficulty())) {
            throw new InvalidOperationException(" Hiker experience level does not match tour difficulty");
        }
            tour.getHikers.add(hiker);
            return tourRepository.save(tour);
        }

}
