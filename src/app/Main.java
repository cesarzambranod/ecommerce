package app;

import productos.Producto;
import productos.Bebida;
import productos.Comida;
import pedidos.LineaPedido;
import pedidos.Pedido;
import services.ProductoService;
import services.PedidoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final ProductoService productoService = new ProductoService();
    private static final PedidoService pedidoService = new PedidoService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            mostrarMenu();
            String opcion = scanner.nextLine().trim();

            try {
                switch (opcion) {
                    case "1" -> agregarProducto();
                    case "2" -> listarProductos();
                    case "3" -> buscarActualizarProducto();
                    case "4" -> eliminarProducto();
                    case "5" -> crearPedido();
                    case "6" -> listarPedidos();
                    case "7" -> {
                        System.out.println("Saliendo...");
                        running = false;
                    }
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú ---");
        System.out.println("1. Agregar producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Buscar/Actualizar producto");
        System.out.println("4. Eliminar producto");
        System.out.println("5. Crear pedido");
        System.out.println("6. Listar pedidos");
        System.out.println("7. Salir");
        System.out.print("Seleccione opción: ");
    }

    private static void agregarProducto() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Stock: ");
        int stock = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Tipo (1=Producto, 2=Bebida, 3=Comida): ");
        String tipo = scanner.nextLine().trim();

        Producto producto = switch (tipo) {
            case "2" -> {
                System.out.print("Volumen (L): ");
                double volumen = Double.parseDouble(scanner.nextLine().trim());
                yield new Bebida(nombre, precio, stock, volumen);
            }
            case "3" -> {
                System.out.print("Fecha de vencimiento (YYYY-MM-DD): ");
                LocalDate fecha = LocalDate.parse(scanner.nextLine().trim());
                yield new Comida(nombre, precio, stock, fecha);
            }
            default -> new Producto(nombre, precio, stock);
        };

        productoService.agregar(producto);
        System.out.println("Producto agregado con ID: " + producto.getId());
    }

    private static void listarProductos() {
        List<Producto> productos = productoService.listar();
        if (productos.isEmpty()) {
            System.out.println("No hay productos.");
        } else {
            productos.forEach(System.out::println);
        }
    }

    private static void buscarActualizarProducto() {
        System.out.print("ID del producto: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        Producto producto = productoService.buscarPorId(id);

        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println(producto);
        System.out.print("Nuevo precio (Enter para omitir): ");
        String precioStr = scanner.nextLine().trim();
        System.out.print("Nuevo stock (Enter para omitir): ");
        String stockStr = scanner.nextLine().trim();

        double nuevoPrecio = precioStr.isEmpty() ? producto.getPrecio() : Double.parseDouble(precioStr);
        int nuevoStock = stockStr.isEmpty() ? producto.getStock() : Integer.parseInt(stockStr);

        productoService.actualizar(id, nuevoPrecio, nuevoStock);
        System.out.println("Producto actualizado.");
    }

    private static void eliminarProducto() {
        System.out.print("ID del producto: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        if (productoService.eliminar(id)) {
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void crearPedido() {
        List<LineaPedido> lineas = new ArrayList<>();
        boolean agregarMas = true;

        while (agregarMas) {
            System.out.print("ID del producto: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            Producto producto = productoService.buscarPorId(id);

            if (producto == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            System.out.print("Cantidad: ");
            int cantidad = Integer.parseInt(scanner.nextLine().trim());

            LineaPedido linea = new LineaPedido(producto, cantidad);
            lineas.add(linea);

            System.out.print("¿Agregar otro producto? (s/n): ");
            agregarMas = scanner.nextLine().trim().equalsIgnoreCase("s");
        }

        if (lineas.isEmpty()) {
            System.out.println("No se creó el pedido.");
            return;
        }

        Pedido pedido = pedidoService.crearPedido(lineas);
        System.out.println("\n" + pedido);

        System.out.print("¿Confirmar pedido? (s/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
            pedidoService.confirmarPedido(pedido);
            System.out.println("Pedido confirmado. Stock actualizado.");
        } else {
            System.out.println("Pedido cancelado.");
        }
    }

    private static void listarPedidos() {
        List<Pedido> pedidos = pedidoService.listar();
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos.");
        } else {
            pedidos.forEach(System.out::println);
        }
    }
}
