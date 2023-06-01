package com.example.eksambackend.config;


import com.example.eksambackend.model.RaceSeason;
import com.example.eksambackend.model.SailRace;
import com.example.eksambackend.model.Sailboat;
import com.example.eksambackend.repository.RaceSeasonRepository;
import com.example.eksambackend.repository.SailRaceRepository;
import com.example.eksambackend.repository.SailboatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

//@Configuration annotationen og ApplicationRunner klassen gør at run metoden køre når applikationen starter.
@Configuration
public class StartupDataConfig implements ApplicationRunner {

    // Indsæt autowired her for ønsket funktionalitet.
    @Autowired
    RaceSeasonRepository raceSeasonRepository;

    @Autowired
    SailboatRepository sailboatRepository;
    @Autowired
    SailRaceRepository sailRaceRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Sailboat s100 = new Sailboat("The Greatest","40fod", 2);
        makeASailBoatForAllRace(s100);
        createSailRaces();
        List<Sailboat> sailboats = createSailboats();
        for(Sailboat sailboat: sailboats){
            createRaceSeason(sailboat);
        }


    }
    public void makeASailBoatForAllRace(Sailboat sailboat){
        Sailboat theSailboat = sailboat;

        sailboatRepository.save(theSailboat);
        //Gem theSailboat fra vores parametre til vores repo.
        List<SailRace> allRaces = sailRaceRepository.findAll();
        //Gem en liste af alle vores Races.
        for (SailRace race: allRaces){
            //Gå gennem alle races fra vores liste
            RaceSeason raceSeason = new RaceSeason();
            //instantier en ny raceseason
            raceSeason.setSailboat(theSailboat);
            //Gem theSailboat til vores raceseason
            raceSeason.setSailRace(race);
            //Gem alle races til denne season
            raceSeasonRepository.save(raceSeason);
            //Og til sidst gem den nye instantieret raceseason, der nu har alle races og theSailboat
        }


    }

    private List<Sailboat> createSailboats() {
        Sailboat s1 = new Sailboat("Quickie","40fod", 2);
        Sailboat s2 = new Sailboat("Man o War","40fod", 3);
        Sailboat s3 = new Sailboat("Fly fish","40fod", 4);
        Sailboat s4 = new Sailboat("The Hound","25fod", 3);
        Sailboat s5 = new Sailboat("Rammstein","25fod", 4);
        Sailboat s6 = new Sailboat("Viking","25-40fod", 6);
        Sailboat s7 = new Sailboat("Crusader","25-40fod", 1);
        Sailboat s8 = new Sailboat("Honey Booboo","25-40fod", 4);

        sailboatRepository.save(s1);
        sailboatRepository.save(s2);
        sailboatRepository.save(s3);
        sailboatRepository.save(s4);
        sailboatRepository.save(s5);
        sailboatRepository.save(s6);
        sailboatRepository.save(s7);
        sailboatRepository.save(s8);

        List<Sailboat> sailboats = new ArrayList<>();
        sailboats.add(s1);
        sailboats.add(s2);
        sailboats.add(s3);
        sailboats.add(s4);
        sailboats.add(s5);
        sailboats.add(s6);
        sailboats.add(s7);
        sailboats.add(s8);

        return sailboats;
    }

    private void createSailRaces() {
        SailRace sr1 = new SailRace(LocalDate.of(2023, 5, 3), "Roskilde Verdensmesterskab");
        SailRace sr2 = new SailRace(LocalDate.of(2023, 5, 10), "Vejle Mesterskab");
        SailRace sr3 = new SailRace(LocalDate.of(2023, 5, 17), "KBH Mesterskab");
        SailRace sr4 = new SailRace(LocalDate.of(2023, 5, 24), "Nordsjællands Mester");
        SailRace sr5 = new SailRace(LocalDate.of(2023, 5, 31), "Aarhus Mesterskab");

        sailRaceRepository.save(sr1);
        sailRaceRepository.save(sr2);
        sailRaceRepository.save(sr3);
        sailRaceRepository.save(sr4);
        sailRaceRepository.save(sr5);

    }

    public void createRaceSeason(Sailboat sailBoat) {
        List<SailRace> allRaces = sailRaceRepository.findAll();
        boolean numberForSailBoats = sailBoat.getId() % 2 != 0;

        if (numberForSailBoats) {
            SailRace RoskildeVerdensmesterskab = allRaces.get(0);
            RaceSeason firstRaceSeason = new RaceSeason();
            firstRaceSeason.setSailboat(sailBoat);
            firstRaceSeason.setSailRace(RoskildeVerdensmesterskab);
            raceSeasonRepository.save(firstRaceSeason);

            SailRace KBHMesterskab = allRaces.get(2);
            RaceSeason secondRaceSeason = new RaceSeason();
            secondRaceSeason.setSailboat(sailBoat);
            secondRaceSeason.setSailRace(KBHMesterskab);
            raceSeasonRepository.save(secondRaceSeason);

        } else {
            SailRace AarhusMesterskab = allRaces.get(4);
            RaceSeason thirdRaceSeason = new RaceSeason();
            thirdRaceSeason.setSailboat(sailBoat);
            thirdRaceSeason.setSailRace(AarhusMesterskab);
            raceSeasonRepository.save(thirdRaceSeason);
        }
    }
}
