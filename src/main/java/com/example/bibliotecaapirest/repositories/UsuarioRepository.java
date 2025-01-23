package com.example.bibliotecaapirest.repositories;

import com.example.bibliotecaapirest.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
