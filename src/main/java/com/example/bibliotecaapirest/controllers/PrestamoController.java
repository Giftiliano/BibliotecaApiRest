package com.example.bibliotecaapirest.controllers;



import com.example.bibliotecaapirest.entity.Prestamo;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.bibliotecaapirest.repositories.PrestamoRepository;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {
    PrestamoRepository repositorioPrestamos;

    public PrestamoController() {
    }

    @Autowired
    public PrestamoController(PrestamoRepository repositorioPrestamos) {
        this.repositorioPrestamos = repositorioPrestamos;
    }


    // GET --> SELECT *
    @GetMapping
    public ResponseEntity<List<Prestamo>> getPrestamos() {
        List<Prestamo> lista = repositorioPrestamos.findAll();
        System.out.println(lista);
        return ResponseEntity.ok(lista);
    }

    // GET BY ID --> SELECT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> getPrestamoById(@PathVariable Integer id) {
        Prestamo prestamo = repositorioPrestamos.findById(id).orElse(null);
        if (prestamo == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(prestamo);
    }

    // POST --> INSERT
    @PostMapping
    public ResponseEntity<Prestamo> addPrestamo(@Valid @RequestBody Prestamo prestamo) {
        Prestamo prestamoPersistido = repositorioPrestamos.save(prestamo);
        return ResponseEntity.ok().body(prestamoPersistido);
    }

    // PUT --> UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> updatePrestamo(@Valid @RequestBody Prestamo prestamo, @PathVariable Integer id) {
        if (!repositorioPrestamos.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        prestamo.setId(id);
        Prestamo prestamoActualizado = repositorioPrestamos.save(prestamo);
        return ResponseEntity.ok().body(prestamoActualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePrestamo(@PathVariable Integer id) {
        if (!repositorioPrestamos.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repositorioPrestamos.deleteById(id);
        String mensaje = "Préstamo con ID: " + id + " eliminado.";
        return ResponseEntity.ok().body(mensaje);
    }
}
