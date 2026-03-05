package com.PAT.ApiCarritos.repositories;

import com.PAT.ApiCarritos.models.LineaCarrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LineaCarritoRepository extends JpaRepository<LineaCarrito, Long> {
    //Con el Extend, tenemos automáticamente los siguientes métodos: save()
    //findById()
    //findAll()
    //delete()

    Optional<LineaCarrito> findByCarrito_IdCarritoAndIdArticulo(Long idCarrito, Long idArticulo);

    List<LineaCarrito> findAllByCarrito_IdCarrito(Long idCarrito);
    // Busca la línea de un carrito concreto con un artículo concreto

    void deleteAllByCarrito_IdCarrito(Long idCarrito);
    //Para borrar un carrito
}