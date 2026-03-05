package com.PAT.ApiCarritos.controllers;

import com.PAT.ApiCarritos.models.Carrito;
import com.PAT.ApiCarritos.models.LineaCarrito;
import com.PAT.ApiCarritos.services.CarritoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carritos")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    // Crear carrito (el cliente manda Carrito con idCarrito, idUsuario, correoUsuario)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Carrito crearCarrito(@RequestBody Carrito carrito) {
        return carritoService.crearCarrito(carrito.idCarrito, carrito.idUsuario, carrito.correoUsuario);
    }

    @GetMapping("/{idCarrito}")
    public Carrito obtenerCarrito(@PathVariable Long idCarrito) {
        return carritoService.obtenerCarrito(idCarrito);
    }

    // ✅ NUEVO: añadir línea (el cliente manda idArticulo, precioUnitario, unidades)
    @PostMapping("/{idCarrito}/lineas")
    @ResponseStatus(HttpStatus.CREATED)
    public LineaCarrito anadirLinea(@PathVariable Long idCarrito,
                                    @RequestBody LineaCarrito linea) {

        return carritoService.anadirLinea(idCarrito, linea.idArticulo, linea.precioUnitario, linea.unidades);
    }

    // ✅ NUEVO: borrar línea
    @DeleteMapping("/{idCarrito}/lineas/{idArticulo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarLinea(@PathVariable Long idCarrito,
                            @PathVariable Long idArticulo) {

        carritoService.borrarLinea(idCarrito, idArticulo);
    }
    @GetMapping
    public List<Carrito> listarCarritos() {
        return carritoService.listarCarritos();
    }
    @DeleteMapping("/{idCarrito}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarCarrito(@PathVariable Long idCarrito) {
        carritoService.borrarCarrito(idCarrito);
    }
}