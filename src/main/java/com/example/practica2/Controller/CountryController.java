package com.example.practica2.Controller;

import com.example.practica2.Entity.Country;
import com.example.practica2.Entity.Region;
import com.example.practica2.Repository.CountryRepository;
import com.example.practica2.Repository.RegionRepository;
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
@RequestMapping(value = "/countries")
public class CountryController {

    @Autowired
    CountryRepository countryRepository;
    @Autowired
    RegionRepository regionRepository;

    @GetMapping("/list")
    public String listarPaises(Model model) {
        List<Country> listaPaises = countryRepository.findAll();
        model.addAttribute("lista", listaPaises);
        return "country/listar";
    }

    @GetMapping("/create")
    public  String crearPais(Model model) {
        List<Region> listaRegion = regionRepository.findAll();
        model.addAttribute("listaR", listaRegion);
        return "country/crear";
    }

    @PostMapping("/save")
    public String guardarPais(Country country) {

        countryRepository.save(country);
        return "redirect:/countries/list";
    }

    @GetMapping("/edit")
    public String editarPais(@RequestParam("id") String id,
                             Model model) {
        Optional<Country> opt = countryRepository.findById(id);
        if(opt.isPresent()) {
            Country country = opt.get();
            model.addAttribute("listaR", regionRepository.findAll());
            model.addAttribute("pais", country);
            return "country/editar";
        } else {
            return "redirect:/countries/list";
        }
    }

    @GetMapping("/delete")
    public String borrarPais(@RequestParam("id") String id) {
        Optional<Country> opt = countryRepository.findById(id);
        if(opt.isPresent()){
            countryRepository.deleteById(id);
        }
        return "redirect:/countries/list";
    }
}