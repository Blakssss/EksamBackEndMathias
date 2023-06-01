package com.example.eksambackend.controller;

import com.example.eksambackend.model.SailRace;
import com.example.eksambackend.model.Sailboat;
import com.example.eksambackend.service.SailRaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class SailRaceController {

  @Autowired
  SailRaceService sailRaceService;

  @GetMapping("/sailraces")
  public List<SailRace> getAllSailRaces() {
    return sailRaceService.getAllSailRaces();
  }

  @GetMapping("/sailrace/{id}")
  public SailRace getSailRaceById(@PathVariable int id) {
    return sailRaceService.getSailRacesById(id);
  }

  @PostMapping("/sailrace")
  public ResponseEntity<SailRace> addSailRace(@RequestBody SailRace sailRace) {
    return sailRaceService.addSailRace(sailRace);
  }

  @PutMapping("/sailrace")
  public ResponseEntity<SailRace> updateSailRace(@RequestBody SailRace sailRace) {
    return sailRaceService.updateSailRace(sailRace);
  }

  @DeleteMapping("/sailrace/{id}")
  public ResponseEntity<SailRace> deleteSailRace(@PathVariable int id) {
    return sailRaceService.deleteSailRace(id);
  }
}
