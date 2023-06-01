package com.example.eksambackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Setter
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor

  @Entity
  @Table(name = "Sailboat")
  public class Sailboat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sailboat_id")
    private int id;


  @OneToMany(mappedBy = "sailboat", cascade = CascadeType.REMOVE)
  @JsonBackReference
  private List<RaceSeason> listOfRaceSeason;


  private String name;
  private String type;
  private int point;

  public Sailboat(String name, String type,int point) {
    this.name = name;
    this.type = type;
    this.point = point;

  }



}
