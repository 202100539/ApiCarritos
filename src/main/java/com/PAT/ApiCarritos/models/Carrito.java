package com.PAT.ApiCarritos.models;

import jakarta.validation.constraints.*;

public record Carrito(

        @NotNull
        Long idCarrito,

        @NotNull
        Long idArticulo,

        @NotBlank
        String descripcion,

        @NotNull
        @Min(1)
        Integer unidades,

        @NotNull
        @Positive
        Double precioFinal

) {}

//SIN VALIDACIONES:
//public record Carrito (Long idCarrito, Long idArticulo, String descripcion, Integer unidades, Double precioFinal)
//{}
//JSON trabaja mejor con objetos
//Long para ids
//Integer para unidades
//Double para precio