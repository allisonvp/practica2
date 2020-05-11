package com.example.practica2.Controller;

import com.example.practica2.Entity.Country;
import com.example.practica2.Entity.Region;
import com.example.practica2.Repository.CountryRepository;
import com.example.practica2.Repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
    public  String crearPais(@ModelAttribute("pais") Country country,
                             Model model) {
        List<Region> listaRegion = regionRepository.findAll();
        model.addAttribute("listaR", listaRegion);
        return "country/crear";
    }

    @PostMapping("/save")
    public String guardarPais(@ModelAttribute("pais") @Valid Country country,
                              BindingResult bindingResult,
                              Model model,
                              RedirectAttributes attr) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listaR", regionRepository.findAll());
            return "country/crear";
        } else {
            attr.addFlashAttribute("msg", "País exitosamente " + (country.getCountryid()==null?"creado":"actualizado"));
            countryRepository.save(country);
            return "redirect:/countries/list";
        }
    }

    @GetMapping("/edit")
    public String editarPais(@ModelAttribute("pais") Country country,
                             @RequestParam("id") String id,
                             Model model) {
        Optional<Country> opt = countryRepository.findById(id);
        if(opt.isPresent()) {
            country = opt.get();
            model.addAttribute("listaR", regionRepository.findAll());
            model.addAttribute("pais", country);
            return "country/editar";
        } else {
            return "redirect:/countries/list";
        }
    }

    @GetMapping("/delete")
    public String borrarPais(@RequestParam("id") String id,
                             RedirectAttributes attr) {
        Optional<Country> opt = countryRepository.findById(id);
        if(opt.isPresent()){
            countryRepository.deleteById(id);
            attr.addFlashAttribute("msg", "País exitosamente eliminado");
        }
        return "redirect:/countries/list";
    }
}
