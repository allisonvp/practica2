package com.example.practica2.Controller;

import com.example.practica2.Entity.Department;
import com.example.practica2.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(name="/department")
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping(value = {"","/listar"})
    public String listaDepartments(Model model){

        List<Department> listaDepartments = departmentRepository.findAll();
        model.addAttribute("lista", listaDepartments);
        return "department/lista";
    }

    @GetMapping(value = "/nuevo")
    public String nuevoDepartment(){
        return "department/crear";
    }

    @PostMapping(value="/guardar")
    public String guardarDepartment(Department department, RedirectAttributes attr){
        if(department.getDepartmentid()==0){
            List<Department> list = departmentRepository.findAll(Sort.by("departmentid").descending());
            Department ultimoDepartment = list.get(0);
            department.setDepartmentid(ultimoDepartment.getDepartmentid() + 1);
            attr.addFlashAttribute("msg","Departamento creado exitosamente");
        }else{
            attr.addFlashAttribute("msg","Departamento " + department.getDepartmentname() + " actualizado exitosamente");
        }
        departmentRepository.save(department);
        return "redirect:/department";
    }

    @GetMapping("/editar")
    public String editarDepartment(@RequestParam("id") int id,
                                Model model){
        Optional<Department> opt = departmentRepository.findById(id);
        if(opt.isPresent()){
            Department departmentEditar = opt.get();
            model.addAttribute("departmentEditar", departmentEditar);
            return "department/editar";
        }else{
            return "redirect:/department";
        }

    }

    @GetMapping("/borrar")
    public String borrarDepartment(@RequestParam("id") int id){
        Optional<Department> opt = departmentRepository.findById(id);
        if (opt.isPresent()){
            departmentRepository.deleteById(id);
        }
        return "redirect:/department";

    }

    @PostMapping(value = "buscarDepartamento")
    public String buscarTransportista(@RequestParam("searchField") String searchField,
                                      Model model){
        List<Department> listaDepartments = departmentRepository.findByDepartmentname(searchField);
        model.addAttribute("lista", listaDepartments);
        model.addAttribute("searchField",searchField);
        return "department/lista";
    }



}
