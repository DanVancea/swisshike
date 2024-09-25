package com.swisssign.swisshike.repository;

import com.swisssign.swisshike.model.Hiker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HikerRepository extends JpaRepository<Hiker, Long> {
    Hiker findAllByID(List<Long> hikerId);
}
