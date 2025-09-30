# Sistema CRUD con Spring Boot, MySQL y Swing – ZonaFit

Este proyecto es un sistema CRUD (Crear, Leer, Actualizar y Eliminar) desarrollado con **Spring Boot**, **Java 21**, **MySQL** y una **interfaz gráfica en Java Swing**.
Fue creado como práctica para aprender a construir aplicaciones de escritorio con conexión a base de datos usando **JPA** y **JDBC**.

---

## 🚀 Tecnologías utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* Swing (interfaz gráfica)
* MySQL 8
* JDBC Driver (MySQL Connector/J)
* Maven

---

## ⚙️ Funcionalidades

* Crear clientes
* Listar clientes
* Actualizar información de clientes
* Eliminar clientes
* Interfaz gráfica con tabla y formularios

---

## 📂 Estructura del proyecto

```
src/main/java/gm/zona_fit
  ├── gui           # Interfaz gráfica Swing (ZonaFitForma, ZonaFitSwing)
  ├── modelo        # Entidades (Cliente)
  ├── repositorio   # Repositorios JPA
  ├── servicio      # Lógica de negocio
  ├── ZonaFitApplication.java   # Backend Spring Boot
  └── ZonaFitSwing.java         # Lanzador interfaz gráfica
```

---

## 🗄️ Base de datos

El proyecto incluye el esquema y algunos datos de ejemplo en MySQL.

### Script SQL (`schema.sql` + `data.sql`)

```sql
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `membresia` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `membresia_UNIQUE` (`membresia`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `cliente` VALUES 
(1,'Gabriel','Florez',100),
(2,'Paola','Castillo',200),
(7,'Juan','Contreras',400);

UNLOCK TABLES;
```

---

## ⚙️ Configuración

En el archivo `src/main/resources/application.properties` debes colocar tus credenciales de MySQL (⚠️ no subir las reales a GitHub):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/zonafit
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

---

## ▶️ Ejecución

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/tuusuario/CRUD-SpringBoot-MySQL-Swing-ZonaFit.git
   ```
2. Importar en **IntelliJ IDEA** o **Eclipse** como proyecto Maven.
3. Crear la base de datos `zonafit` en MySQL.
4. Ejecutar la clase `ZonaFitApplication` para iniciar el backend.
5. Ejecutar la clase `ZonaFitSwing` para abrir la interfaz gráfica.

---

## ✨ Autor

* **Juan Guillermo**

