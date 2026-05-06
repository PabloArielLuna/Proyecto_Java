# Changelog / Bitácora de Desarrollo

## [Versión 2.0.0] - Implementación de Arquitectura MVC y Persistencia
- **Refactorización Completa:** Se reestructuró el proyecto utilizando el patrón MVC (Model-View-Controller) para separar la vista (consola) de la lógica de negocio y los datos.
- **Persistencia (CSV):** Se integró la librería `java.nio.file` para guardar los datos del inventario en un archivo físico (`inventario.csv`).
- **Validaciones Modernas:** Se reemplazó el uso clásico de `trim().isEmpty()` por el método `isBlank()` (introducido en Java 11+) para una validación de Strings más eficiente.
- **Herencia y Polimorfismo:** Se implementaron las clases `Bebida` y `Comida` extendiendo de la clase base `Producto`.
- **Manejo de Errores:** Se profundizó en el control de excepciones (`NumberFormatException` y `StockInsuficienteException`) para evitar cierres inesperados de la aplicación.