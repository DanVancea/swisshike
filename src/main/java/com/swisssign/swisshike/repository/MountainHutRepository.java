package com.swisssign.swisshike.repository;

import com.swisssign.swisshike.model.MountainHut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MountainHutRepository extends JpaRepository<MountainHut, Long> {
}
