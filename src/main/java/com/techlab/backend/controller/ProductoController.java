package com.techlab.backend.controller;

import com.techlab.backend.model.Producto;
import com.techlab.backend.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> listarTodos() {
        return productoService.obtenerTodos();
    }

    @PostMapping
    public Producto agregar(@RequestBody Producto producto) {
        return productoService.guardar(producto);
    }

    // Buscar un producto específico (GET /api/productos/5)
    @GetMapping("/{id}")
    public Producto buscarPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id);
    }

    // Actualizar un producto (PUT /api/productos/5)
    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.actualizar(id, producto);
    }

    // Eliminar un producto (DELETE /api/productos/5)
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return "Producto eliminado correctamente.";
    }
}