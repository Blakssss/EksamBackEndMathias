package com.example.eksambackend.controller;

import com.example.eksambackend.model.RaceSeason;
import com.example.eksambackend.model.SailboatsInRaces;
import com.example.eksambackend.service.RaceSeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class RaceSeasonController {

  @Autowired
  RaceSeasonService raceSeasonService;


  @GetMapping("/raceseasons")
  public List<RaceSeason> getAllRaceSeasons() {
    return raceSeasonService.getAllRaceSeasons();
  }

  @GetMapping("/raceseason/{id}")
  public RaceSeason getRaceSeasonById(@PathVariable int id) {
    return raceSeasonService.getRaceSeasonById(id);
  }

  @GetMapping("/raceseason/sailboats/race/{raceId}")
  public List<SailboatsInRaces> getAllSailboatsInSailRace(@PathVariable int raceId) {
    return raceSeasonService.getAllSailboatsInRaces(raceId);
  }

}
