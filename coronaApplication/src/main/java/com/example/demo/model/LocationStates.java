package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LocationStates")
public class LocationStates {
	 private static int countryidCounter = 1;
    @Id
   
    private int countryid;
    private String state;
    @Column
    private String country;
    @Column
    private int latestTotalDeaths;
    @Column
    private int differFromPrevay;

    public LocationStates() {
       this.countryid = countryidCounter++; 
    }

    public int getCountryid() {
        return countryid;
    }

    public void setCountryid(int countryid) {
        this.countryid = countryid;
    }

    public int getDifferFromPrevay() {
        return differFromPrevay;
    }

    public void setDifferFromPrevay(int differFromPrevay) {
        this.differFromPrevay = differFromPrevay;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalDeaths() {
        return latestTotalDeaths;
    }

    public void setLatestTotalDeaths(int latestTotalDeaths) {
        this.latestTotalDeaths = latestTotalDeaths;
    }

    @Override
    public String toString() {
        return "LocationStates [countryid=" + countryid + ", state=" + state + ", country=" + country
                + ", latestTotalDeaths=" + latestTotalDeaths + ", differFromPrevay=" + differFromPrevay + "]";
    }
}
