package com.example.practica2.Entity;

import javax.persistence.*;

@Entity
@Table(name = "regions")
public class Region {

    @Id
    @Column(name="region_id")
    private int regionid;
    @Column(name = "region_name")
    private String regionname;


    public int getRegionid() {
        return regionid;
    }

    public void setRegionid(int region_id) {
        this.regionid = region_id;
    }

    public String getRegionname() {
        return regionname;
    }

    public void setRegionname(String region_name) {
        this.regionname = region_name;
    }
}
