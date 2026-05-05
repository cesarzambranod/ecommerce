package services;

import exceptions.StockInsuficienteException;
import pedidos.LineaPedido;
import pedidos.Pedido;
import productos.Producto;
import java.util.ArrayList;
import java.util.List;

public class PedidoService {
    private final List<Pedido> pedidos;

    public PedidoService() {
        this.pedidos = new ArrayList<>();
    }

    public Pedido crearPedido(List<LineaPedido> lineas) {
        Pedido pedido = new Pedido();
        for (LineaPedido linea : lineas) {
            validarStock(linea.getProducto(), linea.getCantidad());
            pedido.agregarLinea(linea);
        }
        pedidos.add(pedido);
        return pedido;
    }

    public void confirmarPedido(Pedido pedido) {
        for (LineaPedido linea : pedido.getLineas()) {
            Producto producto = linea.getProducto();
            producto.setStock(producto.getStock() - linea.getCantidad());
        }
    }

    public List<Pedido> listar() {
        return new ArrayList<>(pedidos);
    }

    private void validarStock(Producto producto, int cantidad) {
        if (producto.getStock() < cantidad) {
            throw new StockInsuficienteException(
                    String.format("Stock insuficiente para %s. Disponible: %d, solicitado: %d",
                            producto.getNombre(), producto.getStock(), cantidad));
        }
    }
}