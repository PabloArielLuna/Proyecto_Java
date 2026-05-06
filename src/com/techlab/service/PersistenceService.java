package com.techlab.service;

import com.techlab.model.Producto;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PersistenceService {
    private static final Path PATH_INV = Paths.get("inventario.csv");

    public void guardarInventarioCsv(List<Producto> productos) {
        List<String> lineas = new ArrayList<>();
        lineas.add("ID,Nombre,Precio,Stock");
        for (Producto p : productos) {
            lineas.add(p.getId() + "," + p.getNombre() + "," + p.getPrecio() + "," + p.getStock());
        }
        try {
            Files.write(PATH_INV, lineas);
        } catch (IOException e) {
            System.err.println("Error al guardar CSV: " + e.getMessage());
        }
    }
}