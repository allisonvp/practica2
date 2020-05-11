package com.example.practica2.Controller;

import com.example.practica2.Dto.PromedioSalarioEmpleadosPorRegionDto;
import com.example.practica2.Entity.Region;
import com.example.practica2.Repository.EmployeeRepository;
import com.example.practica2.Repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/regions")
public class RegionController {

    @Autowired
    RegionRepository regionRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/list")
    public String listarRegiones(Model model) {
        List<Region> listaRegion = regionRepository.findAll();
        model.addAttribute("lista", listaRegion);
        return "region/listar";
    }

    @GetMapping("/create")
    public String crearRegion(@ModelAttribute("region") Region region) {
        return "region/crear";
    }

    @PostMapping("/save")
    public String guardarRegion(@ModelAttribute("region") @Valid Region region,
                                BindingResult bindingResult,
                                RedirectAttributes attr) {
        if (bindingResult.hasErrors()) {
            return "region/crear";
        } else if (region.getRegionid() == 0) {
            List<Region> listaRegion = regionRepository.findAll(Sort.by("regionid").descending());
            Region region_mayorId = listaRegion.get(0);
            int mayorId = region_mayorId.getRegionid();
            region.setRegionid(mayorId + 1);
            attr.addFlashAttribute("msg", "Región exitosamente " + (region.getRegionid() == 0?"creada":"actializada"));
            regionRepository.save(region);

        }
        return "redirect:/regions/list";
    }


    @GetMapping("/edit")
    public String editarRegion(@ModelAttribute("region") Region region,
                               @RequestParam("id") int id,
                               Model model) {
        Optional<Region> opt = regionRepository.findById(id);
        if (opt.isPresent()) {
            region = opt.get();
            model.addAttribute("region", region);
            return "/region/crear";
        } else {
            return "redirect:/regions/list";
        }
    }

    @GetMapping("/delete")
    public String eliminarRegion(@RequestParam("id") int id,
                                 RedirectAttributes attr) {
        Optional<Region> opt = regionRepository.findById(id);
        if (opt.isPresent()) {
            regionRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Región eliminada exitosamente");
        }
        return "redirect:/regions/list";
    }

    @PostMapping("/buscarRegion")
    public String buscarPorNombreRegion(@RequestParam("searchField") String sf,
                                        Model model) {
        List<Region> listaRegi = regionRepository.findByRegionname(sf);
        model.addAttribute("lista", listaRegi);
        return "region/listar";
    }

    @GetMapping("/promedio/{region}")
    public String promedioSalario(Model model,
                                  @PathVariable("region") String region,
                                  RedirectAttributes attr) {
        List<PromedioSalarioEmpleadosPorRegionDto> lista = employeeRepository.salarioPromedio(region);

        if (!lista.isEmpty()) {
            PromedioSalarioEmpleadosPorRegionDto prom = lista.get(0);
            model.addAttribute("salarioprom", prom);
        } else {
            attr.addFlashAttribute("msg2", "No hay reporte para regiones sin empleados");
            return "redirect:/regions/list";
        }

        return "region/promediosalario";
    }
}