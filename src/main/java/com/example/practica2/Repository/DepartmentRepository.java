package com.example.practica2.Repository;

import com.example.practica2.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    List<Department> buscarPorDepartmentname(String departmentname);
}
