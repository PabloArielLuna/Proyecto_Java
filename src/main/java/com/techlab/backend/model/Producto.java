package com.techlab.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_producto", discriminatorType = DiscriminatorType.STRING) // Magia del polimorfismo
@Getter
@Setter
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;

    @Min(value = 0, message = "El precio no puede ser negativo")
    private double precio;

    // Relación real con la tabla Categorías
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    // Atributo temporal solo para recibir el "categoriaId: 3" del JSON de React
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long categoriaId;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;

    public void reducirStock(int cantidad) {
        this.stock -= cantidad;
    }
}