package com.PAT.ApiCarritos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity // Se convierte en una tabla en BD
public class Carrito{

        @Id
        @Column(nullable = false)
        public Long idCarrito;

        @Column(nullable = false)
        @NotNull
        public Long idUsuario;

        @Column(nullable = false)
        @NotBlank
        public String correoUsuario;

        @Column(nullable = false)
        @NotNull
        @PositiveOrZero
        public Double totalPrecio;

        public Carrito() {
                // constructor vacío obligatorio para JPA
        }
        public Carrito(Long idCarrito, Long idUsuario, String correoUsuario, Double totalPrecio){
                this.idCarrito = idCarrito;
                this.idUsuario = idUsuario;
                this.correoUsuario = correoUsuario;
                this.totalPrecio = totalPrecio;
        }
}

//        @NotNull
//        Long idCarrito,
//
//        @NotNull
//        Long idArticulo,
//
//        @NotBlank
//        String descripcion,
//
//        @NotNull
//        @Min(1)
//        Integer unidades,
//
//        @NotNull
//        @Positive
//        Double precioFinal



//SIN VALIDACIONES:
//public record Carrito (Long idCarrito, Long idArticulo, String descripcion, Integer unidades, Double precioFinal)
//{}
//JSON trabaja mejor con objetos
//Long para ids
//Integer para unidades
//Double para precio