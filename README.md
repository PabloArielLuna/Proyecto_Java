# ☕ TechLab API | Backend Spring Boot

API REST desarrollada con **Java Spring Boot** para la gestión del sistema de ventas de **TechLab**.

La aplicación administra productos, categorías, stock y pedidos, ofreciendo una arquitectura preparada para integrarse con un cliente React.

---

# 🚀 Tecnologías utilizadas

- ☕ Java 17
- 🌱 Spring Boot
- 🗄️ Spring Data JPA (Hibernate)
- 🐬 MySQL
- 📦 Maven

---

# ✨ Funcionalidades

## 📦 Gestión de Productos

- Alta de productos.
- Consulta de productos.
- Gestión de categorías.

---

## 🧬 Herencia y Polimorfismo

Implementación mediante:

```java
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
```

Modelos disponibles:

- Producto
- Comida
- Bebida

---

## 📉 Gestión de Stock

- Descuento automático al realizar una compra.
- Validación de stock disponible.
- Prevención de ventas sin inventario.

---

## 🛒 Gestión de Pedidos

- Registro de compras.
- Historial completo.
- Integración con el frontend React.

---

## ⚠️ Manejo de Errores

Control de excepciones para:

- Producto inexistente.
- Stock insuficiente.
- Solicitudes inválidas.

---

# ⚙️ Configuración

Crear una base de datos llamada:

```text
techlab_db
```

Editar el archivo:

```text
src/main/resources/application.properties
```

Configuración:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/techlab_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

---

# ▶️ Ejecución

Desde IntelliJ IDEA

o mediante Maven:

```bash
./mvnw spring-boot:run
```

---

# 🌐 Endpoints principales

| Método | Endpoint | Descripción |
|---------|----------|-------------|
| GET | `/api/productos` | Obtener todos los productos |
| POST | `/api/pedidos` | Registrar una compra |
| GET | `/api/pedidos` | Historial completo de ventas |

---

# 📁 Arquitectura

```
src
├── controller
├── service
├── repository
├── entity
├── dto
├── exception
└── config
```

---

# 👨‍💻 Autor Pablo Luna

**Pablo Ariel Luna**

Proyecto Final - Curso Full Stack Java
