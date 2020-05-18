package com.example.practica2.Repository;

import com.example.practica2.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Usuario findByEmail(String email);

}

