package com.techlab.backend.service;

import com.techlab.backend.exceptions.StockInsuficienteException;
import com.techlab.backend.model.*;
import com.techlab.backend.repository.PedidoRepository;
import com.techlab.backend.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    // Inyectamos ambos repositorios porque necesitamos productos y pedidos en simultáneo
    public PedidoService(PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional // Si algo falla a mitad de la compra, esta anotación cancela todo para no romper el stock
    public Pedido crearPedido(PedidoRequest pedidoRequest) {
        Pedido pedido = new Pedido();
        pedido.setUsuarioId(pedidoRequest.getUsuarioId());

        // Recorremos los items que llegaron desde el JSON de React
        for (ItemPedidoRequest item : pedidoRequest.getItemsPedido()) {
            // 1. Buscamos el producto en MySQL
            Producto producto = productoRepository.findById(item.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + item.getProductoId()));

            // 2. Control de Stock (Lógica idéntica a la que tenías en tu TP viejo)
            if (producto.getStock() < item.getCantidad()) {
                throw new StockInsuficienteException("Solo hay " + producto.getStock() +
                        " unidades disponibles de " + producto.getNombre());
            }

            // 3. Reducimos el stock y actualizamos el producto en la BD
            producto.reducirStock(item.getCantidad());
            productoRepository.save(producto);

            // 4. Creamos la línea de detalle y la acoplamos al pedido
            LineaPedido linea = new LineaPedido(producto, item.getCantidad());
            pedido.agregarLinea(linea);
        }

        // 5. Guardamos el pedido final con todo su detalle en MySQL
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> obtenerHistorialPorUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }

    // Método para devolver el historial de TODAS las ventas (Panel de Admin)
    public List<Pedido> obtenerHistorial() {
        return pedidoRepository.findAll();
    }
}