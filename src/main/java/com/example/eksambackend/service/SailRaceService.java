package com.example.eksambackend.service;

import com.example.eksambackend.exception.ResourceAlreadyExistsException;
import com.example.eksambackend.exception.ResourceNotFoundException;
import com.example.eksambackend.model.SailRace;
import com.example.eksambackend.model.Sailboat;
import com.example.eksambackend.repository.SailRaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SailRaceService {

  @Autowired
  SailRaceRepository sailRaceRepository;

  // READ
  public List<SailRace> getAllSailRaces() {
    return sailRaceRepository.findAll();
  }

  public SailRace getSailRacesById(int id) {
    return sailRaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find a Sail Race with the ID: " + id + " from the database"));
  }

  // CREATE
  public ResponseEntity<SailRace> addSailRace(SailRace sailRace) {
    // Først tjekker vi om sailboat allerede eksistere, så vi ikke overrider den hvis den eksistere.
    boolean exists = sailRaceRepository.existsById(sailRace.getId());
    if (exists) {
      // Hvis den existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
      throw new ResourceAlreadyExistsException("Sail Race with id: " + sailRace.getId() + " already exists and therefore can't be added.");
      // Add en exception der kan handle hvis man prøver at lave en sailboat uden et parti der eksistere. Brug resourcenotfoundexception.
    }

    // Hvis den IKKE allerede eksistere, så må vi adde den.
    SailRace newSailRace = sailRaceRepository.save(sailRace);
    return new ResponseEntity<>(newSailRace, HttpStatus.OK);
  }

  // UPDATE
  public ResponseEntity<SailRace> updateSailRace(SailRace sailRace) {
    // Først tjekker vi om sailboat allerede eksistere, så vi ikke overrider den hvis den eksistere.
    boolean exists = sailRaceRepository.existsById(sailRace.getId());
    if (!exists) {
      // Hvis den ikke existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
      throw new ResourceNotFoundException("Sail Race with id: " + sailRace.getId() + " does not exist and therefore can't be updated");
    }
    // Hvis Sail Race eksistere, tager vi den nye info fra vores requestbody og overwriter vores sailboat nu med den nye info, dvs vi saver oveni en allerede eksisterende sailboat, bare med ny info.
    SailRace newSailRace = sailRaceRepository.save(sailRace);
    return new ResponseEntity<>(newSailRace, HttpStatus.OK);
  }

  // DELETE
  public ResponseEntity<SailRace> deleteSailRace(int id) {
    // Først tjekker vi om sailrace allerede eksistere, for vi kan jo ikke slette noget der ikke eksitere.
    boolean exists = sailRaceRepository.existsById(id);
    if (!exists) {
      // Hvis den ikke existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
      throw new ResourceNotFoundException("sailrace with id: " + id + " does not exist and therefore can't be deleted");
    }
    SailRace deletedSailRace = getSailRacesById(id);
    sailRaceRepository.deleteById(id);
    return new ResponseEntity<>(deletedSailRace, HttpStatus.OK);
  }

}
