package javemarket.dominio.repositorios;

import javemarket.dominio.entidades.Pedido;
import java.util.List;

public interface RepositorioPedido {

    // Crear un nuevo pedido
    void crearPedido(Pedido pedido);

    // Obtener todos los pedidos
    List<Pedido> obtenerPedidos();

    // Obtener un pedido por su ID
    Pedido obtenerPedidoPorId(int idPedido);

    // Obtener pedidos de un cliente específico
    List<Pedido> obtenerPedidosPorCliente(String cliente);

    // Eliminar un pedido por su ID
    void eliminarPedido(int idPedido);
}
