package com.techlab.backend.controller;

import com.techlab.backend.model.Pedido;
import com.techlab.backend.model.PedidoRequest;
import com.techlab.backend.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // 1) Realizar Pedido
    @PostMapping("/api/pedidos")
    public ResponseEntity<Pedido> realizarPedido(@RequestBody PedidoRequest pedidoRequest) {
        Pedido nuevoPedido = pedidoService.crearPedido(pedidoRequest);
        return ResponseEntity.ok(nuevoPedido);
    }

    // 2) Consultar Historial de un Usuario Específico
    @GetMapping("/api/usuarios/{usuarioId}/pedidos")
    public List<Pedido> obtenerHistorial(@PathVariable Long usuarioId) {
        return pedidoService.obtenerHistorialPorUsuario(usuarioId);
    }

    // 3) Panel de Administración (Ver TODOS los pedidos del negocio)
    @GetMapping("/api/pedidos")
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoService.obtenerHistorial();
    }
}