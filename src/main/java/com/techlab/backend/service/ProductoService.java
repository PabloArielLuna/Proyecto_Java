package com.techlab.backend.service;

import com.techlab.backend.model.Categoria;
import com.techlab.backend.model.Producto;
import com.techlab.backend.repository.CategoriaRepository;
import com.techlab.backend.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Producto guardar(Producto producto) {
        // Si desde React mandan "categoriaId": 3, buscamos esa categoría y la vinculamos al producto
        if (producto.getCategoriaId() != null) {
            Categoria cat = categoriaRepository.findById(producto.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + producto.getCategoriaId()));
            producto.setCategoria(cat);
        }
        return productoRepository.save(producto);
    }

    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    public Producto actualizar(Long id, Producto productoActualizado) {
        Producto p = buscarPorId(id);
        p.setNombre(productoActualizado.getNombre());
        p.setDescripcion(productoActualizado.getDescripcion());
        p.setPrecio(productoActualizado.getPrecio());
        p.setStock(productoActualizado.getStock());
        p.setImagenUrl(productoActualizado.getImagenUrl());

        if (productoActualizado.getCategoriaId() != null) {
            Categoria cat = categoriaRepository.findById(productoActualizado.getCategoriaId()).orElseThrow();
            p.setCategoria(cat);
        }

        return productoRepository.save(p);
    }

    public void eliminar(Long id) {
        Producto p = buscarPorId(id);
        productoRepository.delete(p);
    }
}