package com.example.bibliotecaapirest.repositories;

import com.example.bibliotecaapirest.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, String> {
}
