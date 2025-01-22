🌐 API REST: Foro con Spring Boot
📄 Descripción
Foro Hub es una API RESTful creada con Java y Spring Boot para gestionar un foro en línea. Implementa un CRUD para usuarios y tópicos, con autenticación mediante JWT tokens.

✨ Funcionalidades
🔒 Seguridad: Registro, inicio de sesión y rutas protegidas con JWT.
👥 Usuarios: Crear, listar, actualizar y eliminar.
📝 Tópicos: Crear, listar, actualizar y eliminar.
⚠️ Errores: Validaciones claras con respuestas HTTP estándar.
📖 Endpoints destacados
➕ POST /auth/register: Registro de un usuario.
👁️ GET /usuarios: Listar usuarios.
✏️ PUT /topicos/{id}: Actualizar un tópico.
❌ DELETE /usuarios/{id}: Eliminar un usuario.
🔧 Configuración
Configura application.properties:
properties
Copiar
Editar
spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos  
spring.datasource.username=tu_usuario  
spring.datasource.password=tu_contraseña  
api.security.secret=clave_secreta_para_jwt  
Ejecuta las migraciones con Flyway.
Compila y ejecuta.
