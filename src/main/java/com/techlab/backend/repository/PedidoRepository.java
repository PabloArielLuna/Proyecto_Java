package com.techlab.backend.repository;

import com.techlab.backend.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Busca automáticamente todos los pedidos que coincidan con el usuarioId
    List<Pedido> findByUsuarioId(Long usuarioId);
}