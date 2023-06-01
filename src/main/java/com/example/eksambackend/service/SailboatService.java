package com.example.eksambackend.service;


import com.example.eksambackend.exception.ResourceAlreadyExistsException;
import com.example.eksambackend.exception.ResourceNotFoundException;
import com.example.eksambackend.model.Sailboat;
import com.example.eksambackend.repository.SailboatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SailboatService {

    @Autowired
    SailboatRepository sailboatRepository;

    // READ
    public List<Sailboat> getALlSailboat() {
        return sailboatRepository.findAll();
    }

    public Sailboat getSailboatById(int id) {
        return sailboatRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Sailboat with id: " + id + " does not exist and could therefore not find any Sailboat."));
    }


    // CREATE
    public ResponseEntity<Sailboat> addSailboat(Sailboat sailboat) {
        // Først tjekker vi om sailboat allerede eksistere, så vi ikke overrider den hvis den eksistere.
        boolean exists = sailboatRepository.existsById(sailboat.getId());
        if (exists) {
            // Hvis den existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
            throw new ResourceAlreadyExistsException("Sailboat with id: " + sailboat.getId() + " already exists and therefore can't be added.");
            // Add en exception der kan handle hvis man prøver at lave en sailboat uden et parti der eksistere. Brug resourcenotfoundexception.
        }



        // Hvis den IKKE allerede eksistere, så må vi adde den.
        Sailboat newSailboat = sailboatRepository.save(sailboat);
        return new ResponseEntity<>(newSailboat, HttpStatus.OK);
    }

    // UPDATE
    public ResponseEntity<Sailboat> updateSailboat(Sailboat sailboat) {
        // Først tjekker vi om sailboat allerede eksistere, så vi ikke overrider den hvis den eksistere.
        boolean exists = sailboatRepository.existsById(sailboat.getId());
        if (!exists) {
            // Hvis den ikke existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
            throw new ResourceNotFoundException("Sailboat with id: " + sailboat.getId() + " does not exist and therefore can't be updated");
        }
        // Hvis sailboat eksistere, tager vi den nye info fra vores requestbody og overwriter vores sailboat nu med den nye info, dvs vi saver oveni en allerede eksisterende sailboat, bare med ny info.
        Sailboat newSailboat = sailboatRepository.save(sailboat);
        return new ResponseEntity<>(newSailboat, HttpStatus.OK);
    }

    // DELETE
    public ResponseEntity<Sailboat> deleteSailboat(int id) {
        // Først tjekker vi om sailboat allerede eksistere, for vi kan jo ikke slette noget der ikke eksitere.
        boolean exists = sailboatRepository.existsById(id);
        if (!exists) {
            // Hvis den ikke existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
            throw new ResourceNotFoundException("sailboat with id: " + id + " does not exist and therefore can't be deleted");
        }
        Sailboat deletedSailboat = getSailboatById(id);
        sailboatRepository.deleteById(id);
        return new ResponseEntity<>(deletedSailboat, HttpStatus.OK); // Lav en log i console i frontenden der siger noget med ("Du har slettet denne bruger...)
    }
    
}
