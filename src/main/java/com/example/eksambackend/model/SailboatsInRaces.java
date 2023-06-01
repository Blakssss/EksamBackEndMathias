package com.example.eksambackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SailboatsInRaces {

  private int raceId;

  private String raceName;

  private LocalDate raceDate;

  private int boatId;

  private String boatName;

  private String boatType;

  private int boatPoints;
}
