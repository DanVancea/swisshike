package com.swisssign.swisshike.repository;

import com.swisssign.swisshike.model.MountainHut;
import org.springframework.data.jpa.repositort.JpaRepository;

@Repository
public interface MountainHutRepository extends JpaRepository<MountainHut, Long> {
}
