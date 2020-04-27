package com.example.practica2.Controller;

import com.example.practica2.Entity.Employee;
import com.example.practica2.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/list")
    public String listarEmployees(Model model) {
        List<Employee> listaEmp = employeeRepository.findAll();
        model.addAttribute("lista", listaEmp);
        return "employee/listar";
    }

    @GetMapping("/create")
    public String crearEmp() {
        return "employee/crear";
    }

    @PostMapping("/save")
    public String guardarEmp(Employee emp) {
        employeeRepository.save(emp);
        return "redirect:/employee/list";
    }

    @GetMapping("/edit")
    public String editarEmp(@RequestParam("id") String id,
                               Model model){
        Optional<Employee> opt = employeeRepository.findById(id);
        if (opt.isPresent()) {
            Employee employee =opt.get();
            model.addAttribute("employee", employee);
            return "employee/editar";
        } else {
            return "redirect:/employee/list";
        }
    }

    @GetMapping("/delete")
    public String eliminarEmp(@RequestParam("id") String id) {
        Optional<Employee> opt = employeeRepository.findById(id);
        if (opt.isPresent()) {
            employeeRepository.deleteById(id);
        }
        return "redirect:/employee/list";
    }
}