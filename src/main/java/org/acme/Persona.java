package org.acme;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Persona extends PanacheEntity {
    public String nombre;
    public String apellido;
    public int edad;
    public String direccion;
}
