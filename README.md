# 🌐 API REST: Foro con Spring Boot

## 📄 Descripción
**Foro Hub** es una API RESTful desarrollada con **Java** y **Spring Boot** para gestionar un foro en línea. Implementa un CRUD para usuarios y tópicos, con autenticación mediante **JWT tokens**.

---

## ✨ Funcionalidades

### 🔒 Seguridad
- Registro, inicio de sesión y rutas protegidas con JWT.

### 👥 Usuarios
- Crear, listar, actualizar y eliminar usuarios.

### 📝 Tópicos
- Crear, listar, actualizar y eliminar tópicos.

### ⚠️ Manejo de errores
- Validaciones claras con respuestas HTTP estándar.

---

## 📖 Endpoints destacados

- ➕ **POST** `/auth/login`: Login de un usuario.
- 👁️ **GET** `/topicos`: Listar topicos.
- ✏️ **PUT** `/topicos/{id}`: Actualizar un tópico.
- ❌ **DELETE** `/topicos/{id}`: Eliminar un topico.

---

## 🔧 Configuración

1. Configura el archivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   api.security.secret=clave_secreta_para_jwt

