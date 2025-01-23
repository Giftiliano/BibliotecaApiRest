package com.example.bibliotecaapirest.repositories;

import com.example.bibliotecaapirest.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
}
