# Sistema CRUD con Spring Boot y MySQL – ZonaFit

Este proyecto es un sistema CRUD (Crear, Leer, Actualizar y Eliminar) desarrollado con **Spring Boot**, **Java 21** y **MySQL**. Fue creado como práctica para aprender a construir aplicaciones Java con conexión a base de datos usando JPA y JDBC.

----

## 🚀 Tecnologías utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* MySQL 8
* JDBC Driver (MySQL Connector/J)
* Maven

---

## ⚙️ Funcionalidades

* Crear clientes
* Listar clientes
* Actualizar información de clientes
* Eliminar clientes

---

## 📂 Estructura del proyecto

```
src/main/java/gm/zona_fit
  ├── modelo        # Entidades (Cliente)
  ├── repositorio   # Repositorios JPA
  ├── servicio      # Lógica de negocio
  └── ZonaFitApplication.java
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
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
```

---

## ⚙️ Configuración

En el archivo `src/main/resources/application.properties` debes colocar tus credenciales de MySQL:

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
   git clone https://github.com/tuusuario/Sistema-CRUD-con-Spring-Boot-y-MySQL-ZonaFit.git
   ```
2. Importar en **IntelliJ IDEA** o **Eclipse** como proyecto Maven.
3. Crear la base de datos `zonafit` en MySQL.
4. Ejecutar la clase `ZonaFitApplication`.

---

## ✨ Autor

* **Juan Guillermo**

---
