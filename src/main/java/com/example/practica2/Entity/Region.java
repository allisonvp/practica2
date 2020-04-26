package com.example.practica2.Entity;

import javax.persistence.*;

@Entity
@Table(name = "regions")
public class Region {

    @Id
    @Column(name="region_id")
    private int regionid;

    private String region_name;


    public int getRegionid() {
        return regionid;
    }

    public void setRegionid(int region_id) {
        this.regionid = region_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }
}
