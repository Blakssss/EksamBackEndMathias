package com.example.eksambackend.repository;

import com.example.eksambackend.model.RaceSeason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceSeasonRepository extends JpaRepository<RaceSeason, Integer> {
}
