package com.example.practica2.Entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name= "employees")
public class Employee {
    @Id
    @Column(name="employee_id")
    @Max(value = 6)
    private String employeeid;
    @Column(name="first_name")
    @Size(max = 20, message = "El nombre no puede tener más de 20 caracteres")
    private String firstname;
    @Column(nullable = false, name="last_name")
    @Size(max = 25, message = "El apellido no puede tener más de 25 caracteres")
    private String lastname;
    @Column(nullable = false)
    @Size(max = 25, message = "El correo no puede tener más de 25 caracteres")
    private String email;
    private String phone_number;

    @Column(nullable = false)
    private LocalDate hire_date;


    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
    private BigDecimal salary;
    private BigDecimal commission_pct;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;


    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employee_id) {
        this.employeeid = employee_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String first_name) {
        this.firstname = first_name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String last_name) {
        this.lastname = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public LocalDate getHire_date() {
        return hire_date;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getCommission_pct() {
        return commission_pct;
    }

    public void setCommission_pct(BigDecimal commission_pct) {
        this.commission_pct = commission_pct;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
