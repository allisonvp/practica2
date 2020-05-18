package com.example.practica2.Controller;

import com.example.practica2.Entity.Usuario;
import com.example.practica2.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/loginForm")
    public String loginForm() {
        return "login/form";
    }

    @GetMapping("/redirectByRole")
    public String redirectByRole(Authentication auth, HttpSession session) {
        String rol = "";
        for (GrantedAuthority role : auth.getAuthorities()) {
            rol = role.getAuthority();
            break;
        }

        String username = auth.getName();
        Usuario usuario = usuarioRepository.findByEmail(username);
        session.setAttribute("usuario", usuario);

        if (rol.equals("admin")) {
            return "redirect:/employee/";
        } else {
            return "redirect:/department/";
        }
    }

}
