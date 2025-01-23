package com.example.bibliotecaapirest.controllers;

import com.example.bibliotecaapirest.entity.Ejemplar;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.bibliotecaapirest.repositories.EjemplarRepository;

import java.util.List;

@RestController
@RequestMapping("/ejemplares")
public class EjemplarController {
    EjemplarRepository repositorioEjemplares;

    public EjemplarController() {
    }

    @Autowired
    public EjemplarController(EjemplarRepository repositorioEjemplares) {
        this.repositorioEjemplares = repositorioEjemplares;
    }

    // GET --> SELECT *
    @GetMapping
    public ResponseEntity<List<Ejemplar>> getEjemplares() {
        List<Ejemplar> lista = repositorioEjemplares.findAll();
        System.out.println(lista);
        return ResponseEntity.ok(lista);
    }

    // GET BY ID --> SELECT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Ejemplar> getEjemplarById(@PathVariable Integer id) {
        Ejemplar ejemplar = repositorioEjemplares.findById(id).orElse(null);
        if (ejemplar == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ejemplar);
    }

    // POST --> INSERT
    @PostMapping
    public ResponseEntity<Ejemplar> addEjemplar(@Valid @RequestBody Ejemplar ejemplar) {
        Ejemplar ejemplarPersistido = repositorioEjemplares.save(ejemplar);
        return ResponseEntity.ok().body(ejemplarPersistido);
    }

    // PUT --> UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Ejemplar> updateEjemplar(@Valid @RequestBody Ejemplar ejemplar, @PathVariable Integer id) {
        if (!repositorioEjemplares.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ejemplar.setId(id);
        Ejemplar ejemplarActualizado = repositorioEjemplares.save(ejemplar);
        return ResponseEntity.ok().body(ejemplarActualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEjemplar(@PathVariable Integer id) {
        if (!repositorioEjemplares.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repositorioEjemplares.deleteById(id);
        String mensaje = "Ejemplar con ID: " + id + " eliminado.";
        return ResponseEntity.ok().body(mensaje);
    }
}

