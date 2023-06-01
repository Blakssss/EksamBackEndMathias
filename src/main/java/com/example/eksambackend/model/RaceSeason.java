package com.example.eksambackend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RaceSeason {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "race_season")
  private int id;

  @ManyToOne // Many sailboats to one sailrace
  @JoinColumn(name = "sailboat_id", referencedColumnName = "sailboat_id") // Her kalder vi vores foreign key column "booking_id", og værdierne den lægger ind sætter vi til at være dem der findes i referenceColumnName = "booking_id" variablen inde fra Booking klassen.
  private Sailboat sailboat;

  @ManyToOne // Many sailraces to one sailboat
  @JoinColumn(name = "sailrace_id", referencedColumnName = "sailrace_id") // Her kalder vi vores foreign key column "treatment_id", og værdierne den lægger ind sætter vi til at være dem der findes i referenceColumnName = "treatment_id" variablen inde fra Treatment klassen.
  private SailRace sailRace;

}
