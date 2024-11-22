package javemarket.dominio.repositorios;

import javemarket.dominio.entidades.Producto;
import java.util.List;

public interface RepositorioProducto {

    // Crear un nuevo producto
    void crearProducto(Producto producto);

    // Obtener todos los productos
    List<Producto> obtenerProductos();

    // Obtener un producto por su ID
    Producto obtenerProductoPorId(int idProducto);

    // Actualizar un producto (por ejemplo, actualizar unidades o precio)
    void actualizarProducto(Producto producto);

    // Eliminar un producto por su ID
    void eliminarProducto(int idProducto);
}

