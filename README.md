# Sistema de Gestión de Parqueadero

## Descripción

Aplicación web para gestionar entradas, salidas y ubicación de vehículos en un parqueadero. El sistema incluye una interfaz visual con JSP y Bootstrap, autenticación con Spring Security, control de permisos por roles, persistencia en MySQL y servicios REST documentados con Swagger/OpenAPI.

## Tecnologías usadas

- Java 21
- Spring Boot
- Maven
- Spring Web
- JSP
- Bootstrap
- Spring Data JPA
- MySQL
- Spring Security
- Lombok
- Swagger/OpenAPI

## Roles del sistema

- `ADMINISTRADOR`
- `ACOMODADOR`
- `CLIENTE`

## Permisos

- `ADMINISTRADOR`: crear, listar, ver, editar, eliminar vehículos y actualizar ubicación.
- `ACOMODADOR`: listar, ver y actualizar ubicación.
- `CLIENTE`: listar y ver únicamente.

## Entidades principales

- `Usuario`: representa los usuarios que inician sesión en el sistema.
- `Rol`: define los permisos asociados al usuario.
- `Vehiculo`: representa los vehículos registrados en el parqueadero.
- `TipoVehiculo`: clasifica los vehículos, por ejemplo Carro, Moto o Camioneta.

## Arquitectura del proyecto

El proyecto mantiene una estructura sencilla por capas:

- `controller`: contiene los controladores web JSP y los controladores REST bajo `/api`.
- `service`: contiene la lógica de negocio reutilizada por la interfaz web y los servicios REST.
- `repository`: contiene las interfaces de acceso a datos usando Spring Data JPA.
- `entity`: contiene las entidades JPA que se reflejan en la base de datos.
- `config`: contiene la configuración de seguridad, Swagger/OpenAPI y datos iniciales.

## Vistas JSP

Las vistas están ubicadas en `src/main/webapp/WEB-INF/views`.

- `login.jsp`: formulario personalizado de inicio de sesión.
- `vehiculos/listar.jsp`: listado de vehículos con botones según el rol del usuario.
- `vehiculos/formulario.jsp`: formulario para crear y editar vehículos.
- `vehiculos/ver.jsp`: detalle completo de un vehículo.
- `vehiculos/editar-ubicacion.jsp`: formulario para modificar únicamente la ubicación.
- `acceso-denegado.jsp`: página personalizada cuando el usuario no tiene permisos.

## Servicios REST

La aplicación conserva la interfaz JSP y agrega servicios REST paralelos bajo `/api` para cumplir el requisito de servicios web documentados.

### Vehículos

- `GET /api/vehiculos`: lista todos los vehículos.
- `GET /api/vehiculos/{id}`: consulta un vehículo por ID.
- `POST /api/vehiculos`: crea un vehículo.
- `PUT /api/vehiculos/{id}`: actualiza un vehículo.
- `PATCH /api/vehiculos/{id}/ubicacion`: actualiza únicamente la ubicación.
- `DELETE /api/vehiculos/{id}`: elimina un vehículo.

### Tipos de vehículo

- `GET /api/tipos-vehiculo`: lista los tipos de vehículo.
- `GET /api/tipos-vehiculo/{id}`: consulta un tipo de vehículo por ID.

## Swagger/OpenAPI

Swagger UI está disponible en:

```text
http://localhost:8080/swagger-ui/index.html
```

Para probar servicios protegidos desde Swagger, primero se debe iniciar sesión en la aplicación con un usuario válido. Después de iniciar sesión, abrir Swagger UI en el mismo navegador y usar la opción `Try it out`.

## Usuarios de prueba

Los usuarios iniciales son creados automáticamente por `DataInitializer` si no existen:

| Usuario | Contraseña | Rol |
| --- | --- | --- |
| `admin` | `admin123` | `ROLE_ADMINISTRADOR` |
| `acomodador` | `acomodador123` | `ROLE_ACOMODADOR` |
| `cliente` | `cliente123` | `ROLE_CLIENTE` |

## Configuración de MySQL

Base de datos usada por defecto:

```text
plantilla_parcial_web
```

Usuario configurado por defecto:

```text
root
```

La conexión se configura en:

```text
src/main/resources/application.properties
```

La contraseña de MySQL debe ajustarse según el equipo local:

```properties
spring.datasource.password=TU_PASSWORD_LOCAL
```

El proyecto usa `ddl-auto=update`, por lo que Hibernate crea o actualiza las tablas necesarias al iniciar la aplicación.

## Cómo ejecutar el proyecto

1. Importar el proyecto en Eclipse como `Maven Project`.
2. Verificar que MySQL esté iniciado.
3. Configurar usuario y contraseña en `src/main/resources/application.properties`.
4. Ejecutar la clase principal:

```text
PlantillaParcialWebApplication.java
```

5. Abrir en el navegador:

```text
http://localhost:8080
```

## Cómo probar

1. Abrir `http://localhost:8080/login`.
2. Iniciar sesión con `admin / admin123`.
3. Probar el CRUD completo de vehículos:
   - listar
   - ver detalle
   - crear
   - editar
   - eliminar
   - actualizar ubicación
4. Cerrar sesión e iniciar con `acomodador / acomodador123`.
5. Verificar que puede listar, ver y actualizar ubicación, pero no crear, editar completo ni eliminar.
6. Cerrar sesión e iniciar con `cliente / cliente123`.
7. Verificar que solo puede listar y ver vehículos.
8. Para probar servicios REST, iniciar sesión y abrir:

```text
http://localhost:8080/swagger-ui/index.html
```

## Seguridad

La seguridad se implementa con Spring Security:

- Autenticación mediante formulario personalizado.
- Autorización por roles.
- Usuarios cargados desde MySQL mediante `UserDetailsService`.
- Contraseñas cifradas con BCrypt.
- Página personalizada de acceso denegado.
- Rutas protegidas tanto para JSP como para servicios REST bajo `/api`.

## Datos iniciales

La clase `DataInitializer` crea datos iniciales si no existen:

- Roles:
  - `ROLE_ADMINISTRADOR`
  - `ROLE_ACOMODADOR`
  - `ROLE_CLIENTE`
- Usuarios de prueba.
- Tipos de vehículo:
  - Carro
  - Moto
  - Camioneta
- Vehículos de prueba.

Esto permite ejecutar el proyecto y probarlo sin insertar datos manualmente.
