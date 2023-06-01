package com.example.eksambackend.repository;

import com.example.eksambackend.model.RaceSeason;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RaceSeasonRepository extends JpaRepository<RaceSeason, Integer> {
   List<RaceSeason> findRaceSeasonBySailRaceId(int id);
}
