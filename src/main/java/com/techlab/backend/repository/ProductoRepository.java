package com.techlab.backend.repository;

import com.techlab.backend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Al extender JpaRepository, automáticamente heredás métodos como:
    // save(), findAll(), findById(), deleteById()
}