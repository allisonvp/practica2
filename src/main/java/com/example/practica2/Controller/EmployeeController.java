package com.example.practica2.Controller;

import com.example.practica2.Entity.Department;
import com.example.practica2.Entity.Employee;
import com.example.practica2.Entity.Job;
import com.example.practica2.Entity.Region;
import com.example.practica2.Repository.DepartmentRepository;
import com.example.practica2.Repository.EmployeeRepository;
import com.example.practica2.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    JobRepository jobRepository;

    @GetMapping("/list")
    public String listarEmployees(Model model) {
        List<Employee> listaEmp = employeeRepository.findAll();
        model.addAttribute("lista", listaEmp);
        return "employee/listar";
    }

    @GetMapping("/create")
    public String crearEmp(Model model) {
        List<Job> listaJob = jobRepository.findAll();
        List<Department> listaDep = departmentRepository.findAll();
        List<Employee> listaMan = employeeRepository.findAll();
        model.addAttribute("listaJob", listaJob);
        model.addAttribute("listaDep", listaDep);
        model.addAttribute("listaMan", listaMan);
        return "employee/crear";
    }

    @PostMapping("/save")
    public String guardarEmp(Employee emp) {

        if (emp.getEmployeeid()==null) {
            List<Employee> listaEmp = employeeRepository.findAll(Sort.by("employeeid").descending());
            Employee emp_mayorId = listaEmp.get(0);
            String mayorId = emp_mayorId.getEmployeeid();
            //mayorId=206_AC
            String[] idSplit = mayorId.split("_");
            int mayoridNum = Integer.valueOf(idSplit[0]);
            String idNumstr=String.valueOf(mayoridNum+1);


            Department dep=emp.getDepartment();
            String dSN=dep.getDepartmentshortname();

            String idFinal= idNumstr +"_" +dSN;
            emp.setEmployeeid(idFinal);
        }

        employeeRepository.save(emp);
        return "redirect:/employee/list";
    }

    @GetMapping("/edit")
    public String editarEmp(@RequestParam("id") String id,
                            Model model,
                            RedirectAttributes attr){
        Optional<Employee> opt = employeeRepository.findById(id);
        if (opt.isPresent()) {
            Employee employee =opt.get();

            List<Job> listaJob = jobRepository.findAll();
            List<Department> listaDep = departmentRepository.findAll();
            List<Employee> listaMan = employeeRepository.findAll();
            model.addAttribute("listaJob", listaJob);
            model.addAttribute("listaDep", listaDep);
            model.addAttribute("listaMan", listaMan);
            model.addAttribute("employee", employee);

            if(employee.getManager()!=null) {
                return "employee/editar";
            } else {
                attr.addFlashAttribute("msg2","No se puede editar un empleado sin Jefe");
                return "redirect:/employee/list";
            }

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