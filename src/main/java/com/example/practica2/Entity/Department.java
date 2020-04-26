package com.example.practica2.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="departments")
public class Department {

    @Id
    private int department_id;
    private String department_name;
    private String
}
