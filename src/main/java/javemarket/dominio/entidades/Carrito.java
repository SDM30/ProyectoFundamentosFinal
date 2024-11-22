package javemarket.dominio.entidades;

import java.util.HashMap;
import java.util.Map;

public class Carrito {

    private Map<Producto, Integer> productos; // Producto -> Unidades compradas
    private double total; // Total acumulado

    public Carrito() {
        this.productos = new HashMap<>();
        this.total = 0.0;
    }

    // Método para agregar un producto al carrito
    public boolean agregarProducto(Producto producto, int unidades) {
        if (producto.getUnidades() < unidades) {
            System.out.println("No hay suficiente stock para el producto: " + producto.getNombre());
            return false;
        }

        // Si ya existe, sumamos las unidades, de lo contrario añadimos el producto
        productos.put(producto, productos.getOrDefault(producto, 0) + unidades);

        // Recalcular total
        recalcularTotal();
        return true;
    }

    // Método para eliminar un producto del carrito
    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
        recalcularTotal();
    }

    // Método para recalcular el total
    public void recalcularTotal() {
        total = 0.0;
        for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
            total += entry.getKey().getPrecio() * entry.getValue();
        }
    }

    // Método para obtener el total del carrito
    public double getTotal() {
        return total;
    }

    // Método para obtener los productos del carrito
    public Map<Producto, Integer> getProductos() {
        return productos;
    }

    // Limpiar el carrito
    public void limpiarCarrito() {
        productos.clear();
        total = 0.0;
    }
}
