package com.example.bibliotecaapirest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @Size(max = 20)
    @Column(name = "isbn", nullable = false, length = 20)
    @Pattern(regexp =  "\\d{3}-\\d{1,5}-\\d{1,7}-\\d{1,7}-\\d{1}" , message = "El ISBN debe seguir el formato adecuado")
    private String isbn;

    @Size(max = 200)
    @NotNull
    @Column(name = "titulo", nullable = false, length = 200)
    @NotBlank(message = "El titulo no puede estar vacio")
    @Pattern(regexp = "[A-Za-z0-9 ]{1,200}", message = "El título solo puede contener caracteres alfanuméricos")
    private String titulo;

    @Size(max = 100)
    @NotNull
    @Column(name = "autor", nullable = false, length = 100)
    @NotBlank(message = "El autor no puede estar vacio")
    @Pattern(regexp = "[A-Za-z0-9 ]{1,100}", message = "El autor solo puede contener caracteres alfanuméricos")
    private String autor;

    @OneToMany(mappedBy = "isbn")
    private Set<Ejemplar> ejemplars = new LinkedHashSet<>();

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Set<Ejemplar> getEjemplars() {
        return ejemplars;
    }

    public void setEjemplars(Set<Ejemplar> ejemplars) {
        this.ejemplars = ejemplars;
    }

    public Libro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
    }

    public Libro() {
    }
}