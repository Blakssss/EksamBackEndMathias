package com.example.eksambackend.service;

import com.example.eksambackend.exception.ResourceNotFoundException;
import com.example.eksambackend.model.RaceSeason;
import com.example.eksambackend.model.SailboatsInRaces;
import com.example.eksambackend.repository.RaceSeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RaceSeasonService {
@Autowired
RaceSeasonRepository raceSeasonRepository;

public List<RaceSeason> getAllRaceSeasons() {
  return raceSeasonRepository.findAll();
}

  public RaceSeason getRaceSeasonById(int id) {
    return raceSeasonRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("Could not find a Race Season with the ID: " + id + " from the database"));
  }

}
