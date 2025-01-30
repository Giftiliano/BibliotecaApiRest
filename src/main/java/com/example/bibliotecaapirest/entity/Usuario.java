package com.example.bibliotecaapirest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 15)
    @NotNull
    @Column(name = "dni", nullable = false, length = 15)
    @NotBlank(message = "El DNI no puede estar vacío")
    @Pattern(regexp = "\\d{8}[A-Za-z]", message = "El DNI no tiene un formato valido")
    private String dni;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    @NotBlank(message = "El nombre no puede estar vacío")
    @Pattern(regexp = "[A-Za-z0-9 ]{1,100}", message = "El nombre solo puede contener caracteres alfanuméricos")
    private String nombre;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    @NotBlank(message = "El email no puede estar vacio")
    @Pattern(regexp = "[A-Za-z0-9 ]{1,100} + @gmail.com", message = "El email solo puede acabar en gmail.com ")
    private String email;

    @Size(min = 4 , max =12 , message = "La contraseña tiene que tener entre 4 y 12 caracteres")
    @NotNull
    @Column(name = "password", nullable = false)
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

    @NotNull
    @Lob
    @Column(name = "tipo", nullable = false)
    @NotBlank(message = "El tipo de usuario no puede estar vacía")
    @Pattern(regexp = "(normal|administrador)", message = "El tipo solo puede ser 'normal' o 'administrador'")
    private String tipo;

    @Column(name = "penalizacion_hasta")
    private LocalDate penalizacionHasta;

    @OneToMany(mappedBy = "usuario")
    private Set<Prestamo> prestamos = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getPenalizacionHasta() {
        return penalizacionHasta;
    }

    public void setPenalizacionHasta(LocalDate penalizacionHasta) {
        this.penalizacionHasta = penalizacionHasta;
    }

    public Set<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Set<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public Usuario() {
    }

    public Usuario(String dni, String nombre, String email, String password, LocalDate penalizacionHasta, String tipo, Set<Prestamo> prestamos) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.penalizacionHasta = penalizacionHasta;
        this.tipo = tipo;
        this.prestamos = prestamos;
    }
}