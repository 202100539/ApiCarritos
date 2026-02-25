# Práctica 2 - API REST Carrito

## Descripción

Esta práctica consiste en el desarrollo de una API REST utilizando Spring Boot que permite realizar operaciones CRUD (Create, Read, Update, Delete) sobre el recurso **Carrito**, simulando un caso simplificado de e-commerce.

Cada carrito representa una compra en curso y contiene un único artículo.

---

## Modelo del recurso Carrito

El recurso `Carrito` está compuesto por los siguientes campos:

- `idCarrito`: Identificador único del carrito.
- `idArticulo`: Identificador del artículo.
- `descripcion`: Descripción del artículo.
- `unidades`: Número de unidades del artículo.
- `precioFinal`: Importe final del carrito.

Ejemplo de representación JSON:

```json
{
  "idCarrito": 1,
  "idArticulo": 10,
  "descripcion": "Producto test",
  "unidades": 2,
  "precioFinal": 20.0
}
```
## Endpoints implementados

| Método HTTP | Ruta | Body requerido | Descripción | Códigos de respuesta |
|------------|------|----------------|------------|----------------------|
| POST | `/carritos` | Carrito (JSON) | Crea un nuevo carrito | 201 Created, 400 Bad Request |
| GET | `/carritos` | — | Devuelve todos los carritos | 200 OK |
| GET | `/carritos/{id}` | — | Devuelve un carrito por su identificador | 200 OK, 404 Not Found |
| PUT | `/carritos/{id}` | Carrito (JSON) | Actualiza un carrito existente | 200 OK, 400 Bad Request, 404 Not Found |
| DELETE | `/carritos/{id}` | — | Elimina un carrito por su identificador | 204 No Content, 404 Not Found |

## Validaciones implementadas

Se han aplicado validaciones utilizando Jakarta Validation:
- **idCarrito** no puede ser nulo. 
- **idArticulo** no puede ser nulo. 
- **descripcion** no puede estar vacía. 
- **unidades** debe ser mayor o igual que 1. 
- **precioFinal** debe ser un valor positivo.

En caso de incumplirse alguna validación, la API devuelve automáticamente un:
_400 Bad Request_

## Códigos de estado HTTP utilizados

- **200 OK** → Operación realizada correctamente.
- **201 Created** → Recurso creado correctamente.
- **204 No Content** → Recurso eliminado correctamente.
- **400 Bad Request** → Error de validación en la petición.
- **404 Not Found** → El recurso solicitado no existe.
