# :shopping_cart: Ecommerce - Sistema de Gestion de Productos y Pedidos

> Aplicacion de consola en Java para gestionar un pequeno sistema de ecommerce: administracion de productos (bebidas, comidas) y procesamiento de pedidos.

---

## :rocket: Requisitos

- :coffee: **Java 11+** installed

---

## :cd: Compilacion y Ejecucion

```bash
# Compilar
cd ecommerce && javac -d bin src/**/*.java src/*.java

# Ejecutar
cd ecommerce && java -cp bin App
```

---

## :construction_worker: Arquitectura

Arquitectura multicapa:

```plantuml
                    ┌─────────────────────────────────────────────────────────┐
                    │                    PRESENTACION                          │
                    │                      Main.java                          │
                    │              (menu e interaccion con usuario)            │
                    └─────────────────────────────────────────────────────────┘
                                        │  │
                                        ▼  │
                    ┌─────────────────────────────────────────────────────────┐
                    │                     SERVICIOS                            │
                    │         ProductoService      │     PedidoService         │
                    │    (logica de negocio)      │   (logica de negocio)     │
                    └─────────────────────────────────────────────────────────┘
                                        │  │
                                        ▼  │
                    ┌─────────────────────────────────────────────────────────┐
                    │                      MODELOS                             │
                    │   Producto  │  Bebida  │  Comida  │  Pedido  │ LineaPedido
                    └─────────────────────────────────────────────────────────┘
                                        │  │
                                        ▼  │
                    ┌─────────────────────────────────────────────────────────┐
                    │                    EXCEPCIONES                           │
                    │              StockInsuficienteException                  │
                    └─────────────────────────────────────────────────────────┘
```

---

## :uml: Diagrama de Clases

```plantuml
                    ┌─────────────────────────────────┐
                    │         «abstract»               │
                    │          Producto               │
                    ├─────────────────────────────────┤
                    │ - id: int                       │
                    │ - nombre: String                │
                    │ - precio: double                │
                    │ - stock: int                     │
                    ├─────────────────────────────────┤
                    │ + getId(): int                  │
                    │ + getNombre(): String           │
                    │ + getPrecio(): double            │
                    │ + getStock(): int               │
                    │ + setStock(int): void           │
                    │ + «abstract» toString()        │
                    └───────────────┬─────────────────┘
                                    │
                        ┌───────────┴───────────┐
                        │                       │
                        ▼                       ▼
                ┌───────────────┐       ┌───────────────┐
                │    Bebida     │       │    Comida     │
                └───────────────┘       └───────────────┘

                ┌──────────────────────────────────────────────────────────┐
                │                         Pedido                             │
                ├──────────────────────────────────────────────────────────┤
                │ - id: int                                                │
                │ - lineas: List<LineaPedido>                              │
                │ - fecha: LocalDateTime                                   │
                ├──────────────────────────────────────────────────────────┤
                │ + agregarLinea(producto, cantidad): void                │
                │ + calcularTotal(): double                               │
                │ + toString(): String                                    │
                └──────────────────────────────────────────────────────────┘

                ┌──────────────────────────────────────────────────────────┐
                │                      LineaPedido                          │
                ├──────────────────────────────────────────────────────────┤
                │ - producto: Producto                                      │
                │ - cantidad: int                                         │
                ├──────────────────────────────────────────────────────────┤
                │ + getProducto(): Producto                                │
                │ + getCantidad(): int                                    │
                │ + getSubtotal(): double                                  │
                └──────────────────────────────────────────────────────────┘
```

---

## :arrows_counterclockwise: Flujo de Pedidos

```plantuml
    ┌──────────────┐    ┌──────────────┐    ┌──────────────┐
    │    Menu      │───▶│  Seleccionar │───▶│   Ingresar    │
    │   Principal  │    │   opcion 5   │    │  ID producto │
    └──────────────┘    └──────────────┘    └──────────────┘
                                              │
                                              ▼
                                     ┌──────────────┐
                                     │   Ingresar    │
                                     │   cantidad    │
                                     └──────────────┘
                                              │
                                              ▼
                             ┌────────────────────────────────┐
                             │     ¿Stock suficiente?         │
                             └────────────────────────────────┘
                                    │              │
                                   NO             YES
                                    │              │
                                    ▼              ▼
                          ┌──────────────┐  ┌──────────────┐
                          │   Mostrar    │  │   Mostrar     │
                          │    error     │  │   subtotal    │
                          └──────────────┘  └──────────────┘
                                                    │
                                                    ▼
                                           ┌──────────────┐
                                           │  ¿Agregar    │
                                           │  mas items?  │◀────+
                                           └──────────────┘     │
                                                 │   │            │
                                                SI  NO           │
                                                 │   │            │
                                                 └───┘            │
                                              (loop)              │
                                                    │            │
                                                    ▼            │
                                          ┌──────────────┐       │
                                          │   Confirmar   │       │
                                          │    pedido      │       │
                                          └──────────────┘       │
                                                    │            │
                                                    ▼            │
                                          ┌──────────────┐       │
                                          │  Decrementar  │──────┘
                                          │    stock      │
                                          └──────────────┘
```

---

## :file_folder: Estructura de Carpetas

```plantuml
ecommerce/
├── bin/                         📁 Archivos .class compilados
├── src/
│   ├── App.java                🚪 Punto de entrada
│   ├── productos/
│   │    ├── Producto.java       📦 Clase abstracta
│   │    ├── Bebida.java        🍹 Producto bebida
│   │    └── Comida.java        🍔 Producto comida
│   ├── pedidos/
│   │    ├── Pedido.java         📋 Cabecera del pedido
│   │    └── LineaPedido.java    📝 Linea de pedido
│   ├── services/
│   │    ├── ProductoService.java    🔧 CRUD productos
│   │    └── PedidoService.java      🔧 Logica pedidos
│   ├── exceptions/
│   │    └── StockInsuficienteException.java  ⚠️ Excepcion
│   └── app/
│        └── Main.java           🖥️ Menu de consola
└── README.md
```

---

## :clipboard: Menu de Consola

```plantuml
  ┌────────────────────────────────────────┐
  │           MENU PRINCIPAL               │
  ├────────────────────────────────────────┤
  │  1️⃣  Agregar producto                 │
  │  2️⃣  Listar productos                 │
  │  3️⃣  Buscar/Actualizar producto       │
  │  4️⃣  Eliminar producto                │
  │  5️⃣  Crear pedido                      │
  │  6️⃣  Listar pedidos                    │
  │  7️⃣  Salir                             │
  └────────────────────────────────────────┘
```

---

## :bookmark_tabs: Reglas de Negocio

| Regla | Descripcion |
| ----- | ----------- |
| :package: **Stock** | No puede ser negativo. Si un pedido requiere mas stock del disponible, se lanza `StockInsuficienteException`. |
| :dollar: **Precio** | Debe ser mayor a 0. |
| :shopping_cart: **Pedido** | Al confirmarse, decrementa el stock de cada producto involucrado. |
| :chart: **Total** | Se calcula como la suma de `(precio * cantidad)` de todas las lineas. |

---
