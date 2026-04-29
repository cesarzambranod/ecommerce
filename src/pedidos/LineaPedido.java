package pedidos;

import productos.Producto;

public class LineaPedido {
    private final Producto producto;
    private int cantidad;
    private final double subtotal;

    public LineaPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = producto.getPrecio() * cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return String.format("  %s x%d (%.2f) = %.2f", producto.getNombre(), cantidad, producto.getPrecio(), subtotal);
    }
}
