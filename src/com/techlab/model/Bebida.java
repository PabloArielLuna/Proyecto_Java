package com.techlab.model;

public class Bebida extends Producto {
    private boolean esAlcoholica;

    public Bebida(String nombre, double precio, int stock, boolean esAlcoholica) {
        super(nombre, precio, stock);
        this.esAlcoholica = esAlcoholica;
    }

    @Override
    public String toString() {
        return super.toString() + " | " + (esAlcoholica ? "Bebida Alcohólica" : "Bebida Sin Alcohol");
    }
}