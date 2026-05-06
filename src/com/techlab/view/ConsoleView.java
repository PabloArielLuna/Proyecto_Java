package com.techlab.view;
import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GESTIÓN TECHLAB ===");
        System.out.println("1) Agregar Producto");
        System.out.println("2) Listar Productos");
        System.out.println("3) Buscar/Actualizar Producto");
        System.out.println("4) Eliminar Producto");
        System.out.println("5) Crear Pedido");
        System.out.println("6) Listar Pedidos Realizados");
        System.out.println("7) Guardar Datos en CSV");
        System.out.println("0) Salir");
        System.out.print("Elige una opción: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void mostrarMensaje(String msj) { System.out.println(">> " + msj); }

    public String pedirString(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public int pedirInt(String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            mostrarMensaje("ERROR: Ingresa un número entero válido.");
            return -1;
        }
    }

    public double pedirDouble(String mensaje) {
        System.out.print(mensaje);
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            mostrarMensaje("ERROR: Ingresa un número decimal válido.");
            return -1.0;
        }
    }
}