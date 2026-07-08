TechLab Backend API (Spring Boot)

API REST desarrollada con Java Spring Boot para el proyecto final del curso. Esta API gestiona el catálogo de productos, categorías, gestión de stock y procesamiento de pedidos.

Tecnologías Utilizadas

Java 17+

Spring Boot

Spring Data JPA (Hibernate)

MySQL

Maven

Funcionalidades Principales

Polimorfismo: Implementación de herencia en productos (Productos, Comida, Bebida) usando @Inheritance(strategy = InheritanceType.SINGLE_TABLE).

Gestión de Stock: Reducción automática de inventario al realizar un pedido.

REST API: Endpoints completos para gestión de productos y pedidos.

Manejo de Errores: Control de excepciones para stock insuficiente o productos no encontrados.

Configuración

Configura tu base de datos MySQL creando el esquema techlab_db.

Actualiza los datos de conexión en src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/techlab_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update


Ejecuta el proyecto desde IntelliJ IDEA o mediante Maven: ./mvnw spring-boot:run.

Endpoints Principales

GET /api/productos: Lista todos los productos.

POST /api/pedidos: Registra una nueva compra (descuenta stock).

GET /api/pedidos: Historial total de ventas (Panel Admin).
