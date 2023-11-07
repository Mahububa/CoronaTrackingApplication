package com.example.demo.controller;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LocationStates;
import com.example.demo.services.CoronaVirusDataServices;

@RestController
@RequestMapping("/api")
public class DataController {

    private final CoronaVirusDataServices dataServices;

    public DataController(CoronaVirusDataServices dataServices) {
        this.dataServices = dataServices;
    }

    @GetMapping("/covid19data")
    public List<LocationStates> getCovid19Data() {
        return dataServices.getAllstates();
    }
    @GetMapping("/country/{countryName}")
    public LocationStates getCountryByName(@PathVariable String countryName) {
        List<LocationStates> allStates = dataServices.getAllstates();
        return allStates.stream()
                .filter(state -> state.getCountry().equalsIgnoreCase(countryName))
                .findFirst()
                .orElse(null);
    }

    

    
    // Endpoint to find a particular country by ID
    @GetMapping("/country/id/{countryId}")
    public ResponseEntity<LocationStates> getCountryById(@PathVariable int countryId) {
        List<LocationStates> allStates = dataServices.getAllstates();
        LocationStates country = allStates.stream()
                .filter(state -> state.getCountryid() == countryId)
                .findFirst()
                .orElse(null);

        if (country != null) {
            return ResponseEntity.ok(country); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }


    @GetMapping("/topCountries/{count}")
    @ResponseBody
    public List<LocationStates> getTopCountriesForRandomNumber(@PathVariable int count) {
        List<LocationStates> allStates = dataServices.getAllstates();
        List<LocationStates> topCountries = allStates.stream()
                .sorted(Comparator.comparingInt(LocationStates::getLatestTotalDeaths).reversed())
                .limit(count)
                .collect(Collectors.toList());

        return topCountries;
    }


}
