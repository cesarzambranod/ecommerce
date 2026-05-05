package productos;

public class Bebida extends Producto {
    private double volumen;

    public Bebida(String nombre, double precio, int stock, double volumen) {
        super(nombre, precio, stock);
        this.volumen = volumen;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Volumen: %.1fL", volumen);
    }
}
