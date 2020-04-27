package com.example.practica2.Repository;

import com.example.practica2.Entity.Department;
import com.example.practica2.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {

}
