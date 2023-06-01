package com.example.eksambackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Sailrace")
public class SailRace {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "sailrace_id")
  private int id;

  private LocalDate date;

  private String name;

  @OneToMany(mappedBy = "sailRace", cascade = CascadeType.REMOVE)
  @JsonBackReference
  private List<RaceSeason> listOfRaceSeason;

  public SailRace(LocalDate date, String name) {
    this.date = date;
    this.name = name;

  }


}
