package com.techlab.backend.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long usuarioId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private List<LineaPedido> lineas = new ArrayList<>();

    private double total;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    // Dejamos este método para mantener la lógica de ir sumando subtotales
    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
        total += linea.getSubtotal();
    }

    public void confirmarPedido() {
        this.estado = EstadoPedido.ENTREGADO;
    }
}