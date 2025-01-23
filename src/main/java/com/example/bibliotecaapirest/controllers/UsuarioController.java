package com.example.bibliotecaapirest.controllers;


import com.example.bibliotecaapirest.entity.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.bibliotecaapirest.repositories.UsuarioRepository;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    UsuarioRepository repositorioUsuarios;

    public UsuarioController() {
    }

    @Autowired
    public UsuarioController(UsuarioRepository repositorioUsuarios) {
        this.repositorioUsuarios = repositorioUsuarios;
    }

    // GET --> SELECT *
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        List<Usuario> lista = repositorioUsuarios.findAll();
        System.out.println(lista);
        return ResponseEntity.ok(lista);
    }

    // GET BY ID --> SELECT BY ID
    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        Usuario usuario = repositorioUsuarios.findById(id).orElse(null);
        if (usuario == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuario);
    }

    // POST --> INSERT
    @PostMapping
    public ResponseEntity<Usuario> addUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario usuarioPersistido = repositorioUsuarios.save(usuario);
        return ResponseEntity.ok().body(usuarioPersistido);
    }

    // PUT --> UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@Valid @RequestBody Usuario usuario, @PathVariable Integer id) {
        if (!repositorioUsuarios.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuario.setId(id);
        Usuario usuarioActualizado = repositorioUsuarios.save(usuario);
        return ResponseEntity.ok().body(usuarioActualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Integer id) {
        if (!repositorioUsuarios.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repositorioUsuarios.deleteById(id);
        String mensaje = "Usuario con ID: " + id + " eliminado.";
        return ResponseEntity.ok().body(mensaje);
    }
}
