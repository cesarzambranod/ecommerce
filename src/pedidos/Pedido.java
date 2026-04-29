package pedidos;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int counter = 1;
    private final int id;
    private final List<LineaPedido> lineas;
    private double total;

    public Pedido() {
        this.id = counter++;
        this.lineas = new ArrayList<>();
        this.total = 0;
    }

    public int getId() {
        return id;
    }

    public List<LineaPedido> getLineas() {
        return lineas;
    }

    public double getTotal() {
        return total;
    }

    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
        total += linea.getSubtotal();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Pedido #%d - Total: %.2f\n", id, total));
        for (LineaPedido linea : lineas) {
            sb.append("  ").append(linea).append("\n");
        }
        return sb.toString();
    }
}
