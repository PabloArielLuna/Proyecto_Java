package com.techlab.backend.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemPedidoRequest {
    private Long productoId;
    private int cantidad;
}