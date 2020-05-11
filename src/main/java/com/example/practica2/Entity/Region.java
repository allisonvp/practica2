package com.example.practica2.Entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "regions")
public class Region {

    @Id
    @Column(name="region_id")
    private int regionid;
    @Size(max=25, message = "El nombre no puede tener más de 25 caracteres")
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
