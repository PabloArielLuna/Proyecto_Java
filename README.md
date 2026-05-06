# Sistema de Gestión de Inventario y Pedidos - TechLab

Aplicación backend desarrollada en Java para la gestión de productos y procesamiento de pedidos. El sistema está estructurado bajo el patrón de diseño MVC (Model-View-Controller) para separar responsabilidades y garantizar la escalabilidad del código.

## Características Principales
- **Arquitectura MVC:** Separación lógica en paquetes (`controller`, `model`, `view`, `service`, `exceptions`).
- **Programación Orientada a Objetos (POO):** Uso de herencia, encapsulamiento y polimorfismo con clases abstractas (`Producto`) y subclases (`Bebida`, `Comida`).
- **Persistencia de Datos:** Guardado automático del inventario en un archivo `inventario.csv` utilizando `java.nio.file`.
- **Validaciones Robustas:** Implementación de excepciones personalizadas (`StockInsuficienteException`) y validación de cadenas modernas (`isBlank()`).
- **Gestión de Colecciones:** Uso de `ArrayList` y `List` para el manejo dinámico de memoria.

## Tecnologías Utilizadas
- Java SE (JDK 17+)
- IntelliJ IDEA Community Edition

## Cómo ejecutar el proyecto
1. Clonar el repositorio.
2. Abrir el proyecto en IntelliJ IDEA o cualquier IDE compatible con Java.
3. Ejecutar la clase `Main.java` ubicada en la raíz del paquete `com.techlab`.
