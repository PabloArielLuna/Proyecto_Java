package com.techlab.service;
import com.techlab.model.Producto;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {
    private List<Producto> inventario = new ArrayList<>();

    // Cargo un par de cosas por defecto para no perder tiempo
    public ProductoService() {
        inventario.add(new Producto("Café Premium", 2500, 50));
        inventario.add(new Producto("Alfajor", 1200, 30));
    }

    public void agregar(Producto p) { inventario.add(p); }
    public List<Producto> getInventario() { return inventario; }

    public Producto buscarPorId(int id) {
        return inventario.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public Producto buscarPorNombre(String nombre) {
        return inventario.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .findFirst().orElse(null);
    }

    public boolean eliminar(int id) {
        return inventario.removeIf(p -> p.getId() == id);
    }
}