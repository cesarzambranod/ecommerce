package productos;

import java.time.LocalDate;

public class Comida extends Producto {
    private LocalDate fechaVencimiento;

    public Comida(String nombre, double precio, int stock, LocalDate fechaVencimiento) {
        super(nombre, precio, stock);
        this.fechaVencimiento = fechaVencimiento;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Vence: %s", fechaVencimiento);
    }
}
