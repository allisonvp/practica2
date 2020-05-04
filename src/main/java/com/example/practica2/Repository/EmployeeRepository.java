package com.example.practica2.Repository;

import com.example.practica2.Dto.PromedioSalarioEmpleadosPorRegionDto;
import com.example.practica2.Entity.Department;
import com.example.practica2.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {

    @Query(value="SELECT avg(e.salary) as promediosalary, r.region_name as regionname\n" +
            "FROM employees e\n" +
            "INNER JOIN departments d ON e.department_id=d.department_id\n" +
            "INNER JOIN locations l ON d.location_id=l.location_id\n" +
            "INNER JOIN countries c ON c.country_id=l.country_id\n" +
            "INNER JOIN regions r ON r.region_id=c.region_id\n" +
            "WHERE r.region_name=?1 " +
            "group by r.region_id", nativeQuery=true)
    List<PromedioSalarioEmpleadosPorRegionDto> salarioPromedio(String region);

}
