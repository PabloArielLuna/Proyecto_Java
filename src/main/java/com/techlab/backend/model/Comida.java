package com.techlab.backend.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("COMIDA")
@Getter
@Setter
@NoArgsConstructor
public class Comida extends Producto {
    private String fechaVencimiento;
    private boolean aptoCelíacos;
}