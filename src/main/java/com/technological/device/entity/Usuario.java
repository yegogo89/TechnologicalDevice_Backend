package com.technological.device.entity;

import jakarta.persistence.*; //importa todas las anotaciones de JPA

@Entity //es una anotacion de JPA que le dice a Spring Boot que esta clase representa una tabla en MySQL
@Table(name = "usuarios")
public class Usuario {

    @Id // Maca este campo como llave primaria - identificador unico
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indica que el id se genere automaticamente usando el autoincremento de MySQL
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String cedula;

    @Column(nullable = false)
    private String contacto;

    @Column(nullable = false, unique = true)
    private String correoElectronico;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String rol;

    //constructor vacio
    public Usuario(){}

    //set y get de la clase (entidad) usuario
    public Long getId() { return id; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() { return rol; }

    public void setRol(String rol) { this.rol = rol; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cedula='" + cedula + '\'' +
                ", contacto='" + contacto + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
