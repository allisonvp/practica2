package com.example.practica2.Controller;

import com.example.practica2.Entity.Region;
import com.example.practica2.Repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/regions")
public class RegionController {

    @Autowired
    RegionRepository regionRepository;

    @GetMapping("/list")
    public String listarRegiones(Model model) {
        List<Region> listaRegion = regionRepository.findAll();
        model.addAttribute("lista", listaRegion);
        return "region/listar";
    }

    @GetMapping("/create")
    public String crearRegion() {
        return "region/crear";
    }

    @PostMapping("/save")
    public String guardarRegion(Region region) {
        if (region.getRegionid()==0){
            List<Region> listaRegion = regionRepository.findAll(Sort.by("regionid").descending());
            Region region_mayorId = listaRegion.get(0);
            int mayorId = region_mayorId.getRegionid();
            region.setRegionid(mayorId + 1);
        }

        regionRepository.save(region);
        return "redirect:/regions/list";
    }

    @GetMapping("/edit")
    public String editarRegion(@RequestParam("id") int id,
                               Model model){
        Optional<Region> opt = regionRepository.findById(id);
        if (opt.isPresent()) {
            Region region =opt.get();
            model.addAttribute("region", region);
            return "/region/editar";
        } else {
            return "redirect:/regions/list";
        }
    }

    @GetMapping("/delete")
    public String eliminarRegion(@RequestParam("id") int id) {
        Optional<Region> opt = regionRepository.findById(id);
        if (opt.isPresent()) {
            regionRepository.deleteById(id);
        }
        return "redirect:/regions/list";
    }
}
