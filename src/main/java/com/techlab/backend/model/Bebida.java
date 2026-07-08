package com.techlab.backend.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("BEBIDA")
@Getter
@Setter
@NoArgsConstructor
public class Bebida extends Producto {
    private boolean esAlcoholica;
    private int volumenMl;
}