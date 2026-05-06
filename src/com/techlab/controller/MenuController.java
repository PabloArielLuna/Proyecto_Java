package com.techlab.controller;

import com.techlab.exceptions.StockInsuficienteException;
import com.techlab.model.*;
import com.techlab.service.*;
import com.techlab.view.ConsoleView;

public class MenuController {
    private ConsoleView view;
    private ProductoService productoService;
    private PedidoService pedidoService;
    private PersistenceService persistenceService;

    public MenuController() {
        this.view = new ConsoleView();
        this.productoService = new ProductoService();
        this.pedidoService = new PedidoService();
        this.persistenceService = new PersistenceService();
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            int opcion = view.mostrarMenu();
            switch (opcion) {
                case 1 -> crearProducto();
                case 2 -> listarProductos();
                case 3 -> buscarActualizarProducto();
                case 4 -> eliminarProducto();
                case 5 -> crearPedido();
                case 6 -> listarPedidos();
                case 7 -> guardarDatos();
                case 0 -> {
                    view.mostrarMensaje("Saliendo. ¡Éxitos con el proyecto!");
                    salir = true;
                }
                default -> view.mostrarMensaje("Opción inválida.");
            }
        }
    }

    private void crearProducto() {
        int tipo = view.pedirInt("Tipo (1: Bebida, 2: Comida, 3: Genérico): ");
        String nombre = view.pedirString("Nombre del producto: ");

        // Validación moderna para Strings (Java 11+)
        if (nombre == null || nombre.isBlank()) {
            view.mostrarMensaje("ERROR: El nombre no puede estar vacío ni tener solo espacios.");
            return; // Cortamos la ejecución para no crear basura
        }

        double precio = view.pedirDouble("Precio: ");
        int stock = view.pedirInt("Stock inicial: ");

        if (precio > 0 && stock >= 0) {
            if (tipo == 1) {
                boolean alc = view.pedirString("¿Es alcohólica? (s/n): ").equalsIgnoreCase("s");
                productoService.agregar(new Bebida(nombre, precio, stock, alc));
            } else if (tipo == 2) {
                String venc = view.pedirString("Fecha de vencimiento: ");
                productoService.agregar(new Comida(nombre, precio, stock, venc));
            } else {
                productoService.agregar(new Producto(nombre, precio, stock));
            }
            view.mostrarMensaje("Producto agregado con éxito.");
        } else {
            view.mostrarMensaje("ERROR: Datos inválidos (precio o stock negativos).");
        }
    }

    private void listarProductos() {
        // Acá usamos isEmpty() porque es una Lista, y está perfecto.
        if (productoService.getInventario().isEmpty()) {
            view.mostrarMensaje("Inventario vacío.");
            return;
        }
        productoService.getInventario().forEach(p -> view.mostrarMensaje(p.toString()));
    }

    private void buscarActualizarProducto() {
        int id = view.pedirInt("Ingrese ID a buscar: ");
        Producto p = productoService.buscarPorId(id);

        if (p != null) {
            view.mostrarMensaje("Encontrado: " + p.toString());
            int opc = view.pedirInt("¿Actualizar? (1: Precio, 2: Stock, 0: Nada): ");

            if (opc == 1) {
                double nuevoPrecio = view.pedirDouble("Nuevo precio: ");
                if (nuevoPrecio >= 0) {
                    p.setPrecio(nuevoPrecio); // Acá seteamos el valor real
                    view.mostrarMensaje("Precio actualizado correctamente.");
                } else {
                    view.mostrarMensaje("ERROR: El precio no puede ser negativo.");
                }
            } else if (opc == 2) {
                int nuevoStock = view.pedirInt("Nuevo stock: ");
                if (nuevoStock >= 0) {
                    p.setStock(nuevoStock); // Acá seteamos el valor real
                    view.mostrarMensaje("Stock actualizado correctamente.");
                } else {
                    view.mostrarMensaje("ERROR: El stock no puede ser negativo.");
                }
            }
        } else {
            view.mostrarMensaje("Producto no encontrado.");
        }
    }

    private void eliminarProducto() {
        int id = view.pedirInt("Ingrese ID a eliminar: ");
        String conf = view.pedirString("¿Seguro que desea eliminar el ID " + id + "? (s/n): ");
        if (conf.equalsIgnoreCase("s")) {
            boolean borrado = productoService.eliminar(id);
            view.mostrarMensaje(borrado ? "Producto eliminado exitosamente." : "No se encontró el producto.");
        } else {
            view.mostrarMensaje("Operación cancelada.");
        }
    }

    private void crearPedido() {
        Pedido pedido = new Pedido();
        while (true) {
            listarProductos();
            int id = view.pedirInt("ID del producto (0 para cerrar pedido): ");
            if (id <= 0) break;

            Producto p = productoService.buscarPorId(id);
            if (p == null) {
                view.mostrarMensaje("Producto no encontrado.");
                continue;
            }

            int cant = view.pedirInt("Cantidad: ");
            try {
                if (cant > p.getStock()) {
                    throw new StockInsuficienteException("Solo hay " + p.getStock() + " unidades de " + p.getNombre());
                }
                p.reducirStock(cant);
                pedido.agregarLinea(new LineaPedido(p, cant));
                view.mostrarMensaje("Agregado al carrito.");
            } catch (StockInsuficienteException e) {
                view.mostrarMensaje("ERROR: " + e.getMessage());
            }
        }

        if (pedido.getTotal() > 0) {
            pedido.confirmarPedido();
            pedidoService.registrarPedido(pedido);
            view.mostrarMensaje("Pedido confirmado con éxito.");
            pedido.mostrarTicket();
        } else {
            view.mostrarMensaje("Pedido cancelado (vacío).");
        }
    }

    private void listarPedidos() {
        // Acá de vuelta usamos isEmpty() en la Lista de historial. Perfecto.
        if (pedidoService.getHistorial().isEmpty()) {
            view.mostrarMensaje("No hay pedidos registrados.");
            return;
        }
        pedidoService.getHistorial().forEach(Pedido::mostrarTicket);
    }

    private void guardarDatos() {
        persistenceService.guardarInventarioCsv(productoService.getInventario());
        view.mostrarMensaje("Datos guardados correctamente.");
    }
}