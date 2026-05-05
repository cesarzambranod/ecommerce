package services;

import productos.Producto;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {
    private final List<Producto> productos;

    public ProductoService() {
        this.productos = new ArrayList<>();
    }

    public void agregar(Producto producto) {
        productos.add(producto);
    }

    public List<Producto> listar() {
        return new ArrayList<>(productos);
    }

    public Producto buscarPorId(int id) {
        return productos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Producto buscarPorNombre(String nombre) {
        return productos.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public boolean actualizar(int id, double nuevoPrecio, int nuevoStock) {
        Producto producto = buscarPorId(id);
        if (producto != null) {
            producto.setPrecio(nuevoPrecio);
            producto.setStock(nuevoStock);
            return true;
        }
        return false;
    }

    public boolean eliminar(int id) {
        Producto producto = buscarPorId(id);
        if (producto != null) {
            productos.remove(producto);
            return true;
        }
        return false;
    }
}
