package com.techlab.service;
import com.techlab.model.Pedido;
import java.util.ArrayList;
import java.util.List;

public class PedidoService {
    private List<Pedido> historial = new ArrayList<>();

    public void registrarPedido(Pedido p) {
        historial.add(p);
    }

    public List<Pedido> getHistorial() {
        return historial;
    }
}