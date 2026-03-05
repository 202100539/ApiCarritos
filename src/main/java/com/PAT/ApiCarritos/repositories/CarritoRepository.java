package com.PAT.ApiCarritos.repositories;

import com.PAT.ApiCarritos.models.Carrito;
import com.PAT.ApiCarritos.models.LineaCarrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
//    el segundo tipo (Long) debe coincidir con el @Id de Carrito.
//    Como tu Carrito tiene @Id public Long idCarrito;, está bien.

    //Buscar la línea del carrito idCarrito que corresponde al artículo idArticulo
    Optional<LineaCarrito> findByCarrito_IdCarritoAndIdArticulo(Long idCarrito, Long idArticulo);
    List<LineaCarrito> findAllByCarrito_IdCarrito(Long idCarrito);
}