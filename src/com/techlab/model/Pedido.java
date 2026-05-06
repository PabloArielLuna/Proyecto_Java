package com.techlab.model;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contadorId = 1;
    private int id;
    private List<LineaPedido> lineas;
    private double total;
    private EstadoPedido estado;

    public Pedido() {
        this.id = contadorId++;
        this.lineas = new ArrayList<>();
        this.total = 0.0;
        this.estado = EstadoPedido.PROCESANDO;
    }

    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
        total += linea.getSubtotal();
    }

    public void confirmarPedido() { this.estado = EstadoPedido.ENTREGADO; }

    public void mostrarTicket() {
        System.out.println("\n--- PEDIDO #" + id + " [" + estado + "] ---");
        for (LineaPedido lp : lineas) { System.out.println("- " + lp.toString()); }
        System.out.println("TOTAL: $" + total);
        System.out.println("---------------------------------");
    }

    public double getTotal() {
        return this.total;
    }
}