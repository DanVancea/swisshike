package com.swisssign.swisshike.repository;

import com.swisssign.swisshike.model.Hiker;

@Repository
public interface HikerRepository extends JpaRepository<Hiker, Long> {
}
