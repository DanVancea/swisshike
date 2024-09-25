package com.swisssign.swisshike.repository;

import com.swisssign.swisshike.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

    Tour findAllById(Long tourID);
    Tour getHikers(Long hikerId);
}
