package com.example.demo.controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.LocationStates;
import com.example.demo.services.CoronaVirusDataServices;

@Controller
public class ChartController {

    @Autowired
    private CoronaVirusDataServices dvss;

    @GetMapping("/country/{countryName}")
    public String getCountryByName(@PathVariable String countryName, Model model) {
        List<LocationStates> allStates = dvss.getAllstates();
        LocationStates countryData = allStates.stream()
                .filter(state -> state.getCountry().equalsIgnoreCase(countryName))
                .findFirst()
                .orElse(null);

        model.addAttribute("LocationStates", Arrays.asList(countryData));
        model.addAttribute("countryName", countryName);

        return "chart"; 
    }
    @GetMapping("/country/id/{countryId}")
    public String getCountryById(@PathVariable("countryId") int countryId, Model model) {
        List<LocationStates> allStates = dvss.getAllstates();
        LocationStates country = allStates.stream()
                .filter(state -> state.getCountryid() == countryId)
                .findFirst()
                .orElse(null);

        if (country != null) {
            model.addAttribute("LocationStates", Arrays.asList(country));
            model.addAttribute("countryId", countryId);
            return "chart";
        } else {
            return "country_not_found"; 
        }
    }
    @GetMapping("/topCountries/{count}")
    public String getTopNCountries(@PathVariable int count, Model model) {
        List<LocationStates> topCountries = dvss.getAllstates().stream()
                .sorted(Comparator.comparingInt(LocationStates::getLatestTotalDeaths).reversed())
                .limit(count)
                .collect(Collectors.toList());

        model.addAttribute("LocationStates", topCountries);
        return "chart";
    }
}

