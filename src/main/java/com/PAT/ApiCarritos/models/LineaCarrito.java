package com.PAT.ApiCarritos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class LineaCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @JsonIgnore //Para no mandarlo en el JSON
    @ManyToOne(optional = false)
    @JoinColumn(name = "idCarrito")
    public Carrito carrito;

    @Column(nullable = false)
    @NotNull
    public Long idArticulo;

    @Column(nullable = false)
    @NotNull
    @Positive
    public Double precioUnitario;

    @Column(nullable = false)
    @NotNull
    @Min(1)
    public Integer unidades;

    @Column(nullable = false)
    @NotNull
    @PositiveOrZero
    public Double costeLinea;

    public LineaCarrito() {}
}