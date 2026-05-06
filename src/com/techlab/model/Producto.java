package com.techlab.model;

public class Producto {
    protected static int contadorId = 1;
    protected int id;
    protected String nombre;
    protected double precio;
    protected int stock;

    public Producto(String nombre, double precio, int stock) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    // Setters que faltaban para poder editar el producto desde el Menú
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setStock(int stock) { this.stock = stock; }

    public void reducirStock(int cantidad) { this.stock -= cantidad; }

    @Override
    public String toString() {
        return String.format("[%d] %s - $%.2f | Stock: %d", id, nombre, precio, stock);
    }
}