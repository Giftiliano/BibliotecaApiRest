package com.example.bibliotecaapirest.repositories;

import com.example.bibliotecaapirest.entity.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjemplarRepository extends JpaRepository<Ejemplar, Integer> {
}
