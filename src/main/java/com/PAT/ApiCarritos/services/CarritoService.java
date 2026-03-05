package com.PAT.ApiCarritos.services;

import com.PAT.ApiCarritos.models.*;
import com.PAT.ApiCarritos.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoService {

    //Estructura básica del service sin meter métodos todavia

    private final CarritoRepository carritoRepository; //guardar carritos, buscar carrito, borrar carritos
    private final LineaCarritoRepository lineaRepository;

    public CarritoService(CarritoRepository carritoRepository,
                          LineaCarritoRepository lineaRepository) {

        this.carritoRepository = carritoRepository;
        this.lineaRepository = lineaRepository;
    }

    //Metemos métodos
    public Carrito crearCarrito(Long idCarrito, Long idUsuario, String correoUsuario) {

        // 1) comprobar que no existe ya
        if (carritoRepository.existsById(idCarrito)) { //existsById es un método de JPA ya hecho.
            throw new RuntimeException("Ya existe un carrito con idCarrito=" + idCarrito);
        }

        // 2) crear y guardar
        Carrito c = new Carrito();
        c.idCarrito = idCarrito;
        c.idUsuario = idUsuario;
        c.correoUsuario = correoUsuario;
        c.totalPrecio = 0.0;

        return carritoRepository.save(c);
    }

    public Carrito obtenerCarrito(Long idCarrito) {
        return carritoRepository.findById(idCarrito)
                .orElseThrow(() -> new RuntimeException("No existe carrito " + idCarrito));
    }

    public void recalcularTotal(Long idCarrito) {

        Carrito carrito = obtenerCarrito(idCarrito);

        List<LineaCarrito> lineas = lineaRepository.findAllByCarrito_IdCarrito(idCarrito);

        double total = 0.0;
        for (LineaCarrito l : lineas) {
            total += l.costeLinea;
        }

        carrito.totalPrecio = total;
        carritoRepository.save(carrito);
    }

    //POST:
    public LineaCarrito anadirLinea(Long idCarrito, Long idArticulo, Double precioUnitario, Integer unidades) {

        Carrito carrito = obtenerCarrito(idCarrito);

        // ¿Ya existe línea con ese artículo? (depende de cómo lo quieras)
        // Yo lo dejo simple: si existe, sumo unidades
        var existente = lineaRepository.findByCarrito_IdCarritoAndIdArticulo(idCarrito, idArticulo);

        if (existente.isPresent()) {
            LineaCarrito l = existente.get();
            l.unidades = l.unidades + unidades;
            l.precioUnitario = precioUnitario; // o no lo tocas, según criterio
            l.costeLinea = l.precioUnitario * l.unidades;
            LineaCarrito guardada = lineaRepository.save(l);

            recalcularTotal(idCarrito);
            return guardada;
        }

        // si no existe, creamos una nueva línea
        LineaCarrito nueva = new LineaCarrito();
        nueva.carrito = carrito;
        nueva.idArticulo = idArticulo;
        nueva.precioUnitario = precioUnitario;
        nueva.unidades = unidades;
        nueva.costeLinea = precioUnitario * unidades;

        LineaCarrito guardada = lineaRepository.save(nueva);

        recalcularTotal(idCarrito);
        return guardada;
    }

    //Delete
    public void borrarLinea(Long idCarrito, Long idArticulo) {

        LineaCarrito linea = lineaRepository
                .findByCarrito_IdCarritoAndIdArticulo(idCarrito, idArticulo)
                .orElseThrow(() -> new RuntimeException("No existe esa línea"));

        lineaRepository.delete(linea);

        recalcularTotal(idCarrito);
    }

    public List<Carrito> listarCarritos() {
        return carritoRepository.findAll();
    }
    public void borrarCarrito(Long idCarrito) {

        if (!carritoRepository.existsById(idCarrito)) {
            throw new RuntimeException("No existe carrito " + idCarrito);
        }

        lineaRepository.deleteAllByCarrito_IdCarrito(idCarrito);
        carritoRepository.deleteById(idCarrito);
    }

}