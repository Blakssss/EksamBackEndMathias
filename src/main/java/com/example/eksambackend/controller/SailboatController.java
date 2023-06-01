package com.example.eksambackend.controller;


import com.example.eksambackend.model.Sailboat;
import com.example.eksambackend.service.SailboatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class SailboatController {

    @Autowired
    SailboatService sailboatService;
    @GetMapping("/sailboats")
    public List<Sailboat> getSailboats() {
        return sailboatService.getALlSailboat();
    }

    @GetMapping("/sailboat/{id}")
    public Sailboat getSailboatById(@PathVariable int id) {
        return sailboatService.getSailboatById(id);
    }

    @PostMapping("/sailboat")
    public ResponseEntity<Sailboat> addSailboat(@RequestBody Sailboat sailboat) {
        return sailboatService.addSailboat(sailboat);
    }

    @PutMapping("/sailboat")
    public ResponseEntity<Sailboat> updateSailboat(@RequestBody Sailboat sailboat) {
        return sailboatService.updateSailboat(sailboat);
    }

    @DeleteMapping("/sailboat/{id}")
    public ResponseEntity<Sailboat> deleteSailboat(@PathVariable int id) {
        return sailboatService.deleteSailboat(id);
    }
}