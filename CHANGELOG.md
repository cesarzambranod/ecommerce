# :scroll: Changelog

> Todos los cambios notables de este proyecto seran documentados en este archivo.

---

## :bookmark: [1.0.0] - 2026-05-01

### :heavy_plus_sign: Agregado

| Componente | Descripcion |
|------------|-------------|
| :package: **Producto** | Clase abstracta como base para productos |
| :beer: **Bebida** | Subclase especializada para bebidas |
| :hamburger: **Comida** | Subclase especializada para comidas |
| :clipboard: **Pedido** | Clase con lista de lineas de pedido |
| :pencil: **LineaPedido** | Representa items individuales en un pedido |
| :wrench: **ProductoService** | Logica CRUD para productos |
| :wrench: **PedidoService** | Logica de pedidos y validacion de stock |
| :warning: **StockInsuficienteException** | Excepcion custom para errores de inventario |
| :computer: **Main.java** | Menu de consola interactivo |
| :door: **App.java** | Punto de entrada de la aplicacion |

---

### :memo: Proceso de desarrollo

Bueno, te cuento como fue esto paso a paso porque esta bueno dejar registrado por donde fui pasando.

#### :one: Estructura inicial

Lo primero fue armar la estructura de carpetas. Necesitaba separar bien las cosas para que no sea un bolonchi todo junto. Pense en Java con un enfoque por capas: **modelos**, **servicios**, **excepciones** y la **interfaz de usuario**.

#### :two: Los modelos

Despues empece con los modelos basicos. `Producto` fue el primero, con sus atributos de id, nombre, precio y stock. Inmediatamente me di cuenta que iba a necesitar subclases porque hay bebidas y comidas que quizas tengan caracteristicas distintas, entonces hice `Bebida` y `Comida` que heredan de `Producto`.

#### :three: El tema de los pedidos

Un pedido no es solo una cosa, tiene varias lineas, cada una con un producto y una cantidad. Entonces hice `LineaPedido` y `Pedido` que las agrupa. El pedido tiene que calcular el total, eso fue lo primero que le agregue.

#### :four: Logica de negocio

Para la logica de negocio arme los servicios. `ProductoService` para dar de alta, baja, modificar y buscar productos. `PedidoService` para crear pedidos, validar que haya stock suficiente, restar del inventario y demas.

#### :five: Excepciones

El tema del stock me parecio importante manejarlo bien. Si alguien quiere comprar mas de lo que hay, que explote nicely con una excepcion custom `StockInsuficienteException` en vez de un error feo de Java.

#### :six: Interfaz de usuario

Para la interfaz de usuario hice un menu de consola en `Main.java`. Nada fancy, pero funcional. Muestra las opciones 1 a 7 y va delegando a los servicios correspondientes. **Ninguna logica de negocio en el main**, todo al servicio.

#### :seven: Documentacion

Despues el usuario me pidio mejorar el README. Le agregue iconos para que se vea mas copado, un diagrama de clases porque siempre viene bien visualmente, el flujo de como funciona un pedido desde que entra al menu hasta que se confirma, la estructura de carpetas y las reglas de negocio. Quedo bastante mas presentable que el template de VS Code que venia antes.

---

### :rocket: Tecnologias usadas

| | |
|:---:|:---|
| :coffee:| **Java 11+** |
| :computer: | **VS Code** con extensiones para Java |
| :gear: | Compilacion manual con `javac` y ejecucion con `java` |
