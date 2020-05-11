package com.example.practica2.Entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @Column(name = "country_id")
    private String countryid;
    @Size(max=40, message = "El nombre no puede tener más de 40 caracteres")
    @Column(name = "country_name")
    private String countryname;
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    public String getCountryid() {
        return countryid;
    }

    public void setCountryid(String countryid) {
        this.countryid = countryid;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String country_name) {
        this.countryname = country_name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
