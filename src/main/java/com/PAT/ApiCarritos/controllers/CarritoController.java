package com.PAT.ApiCarritos.controllers;


import com.PAT.ApiCarritos.models.Carrito;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/carritos") //Todos los métodos de esta clase empiezan por /carritos.
public class CarritoController {
    //Aquí metemos los metodos POST-GET-PUT-DELETE (Para hacer CRUD)

    //Para cuando hagamos un GET, tenemos que tener los carritos guardados en algun sitio para poder buscarlos, eliminarlos etc.
    //Los guardamos con valor idCarrito y clave objeto Carrito
    private Map<Long, Carrito> baseDatos = new HashMap<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Carrito crea(@Valid @RequestBody Carrito carrito) //Con este argumento Spring convierte el JSON del body en un objeto Carrito.
    {
        baseDatos.put(carrito.idCarrito(), carrito);
        return carrito;
    }

    //Hacemos 2 gets, uno para obtener todos los carritos y otro para obtener detalles de un carrito si le pasamos su id
    @GetMapping
    public Collection<Carrito> obtenerCarritos(){
        return baseDatos.values(); // Me devuelve la clave y el valor
    }

    @GetMapping("/{id}") //Lo ponemos entre llaves porque el numero cambia -> No es fijo
    public Carrito obtenerIdCarrito(@PathVariable Long id){
        //Si no existe, lanzamos excepcion
        if (!baseDatos.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return baseDatos.get(id);

    }

    @PutMapping("/{id}")
    public Carrito actualizarCarrito(@PathVariable Long id, @Valid @RequestBody Carrito carritoActualizado){ //requestBody para coger el JSON

        //Comprobamos que el carrito exista
        if (!baseDatos.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        baseDatos.put(id,carritoActualizado);
        return carritoActualizado;
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarCarrito(@PathVariable Long id){
        baseDatos.remove(id);

    }



}
