# Sprint Módulo 6

Esta aplicación fue creada usando Java 17, Spring Boot 3.1.2, Maven 4.0.0, Thymeleaf 3.1.1, Bootstrap 5.1.3 y MySQL 8.0.33, Spring Security 6.1.2


## Despliegue

### Base de Datos
Actualiza tu conexión local o remota a la base de datos dentro de `application.yml`  o define las siguientes variables de entorno:
Los registros iniciales insertos en la base de datos se cargan al compilar la aplicación en src/main/resources/sql/script.sql
```
DB_HOST= tu conexión, ejemplo localhost:3306
DB_USER= tu usuario, ejemplo root ;
DB_PASS= tu contraseña;
DB_SCHEMA= por defecto es: sprint7_sala3;
```

Si lo estás ejecutando en local, asegúrate que el IDE detecta todos los archivos en **codificación UTF-8**.


Se hace uso intensivo de la biblioteca Lombok, asegúrate de tener instalada la extensión en tu IDE.
### Usuarios por defecto
Los usuarios por defecto son:


| Usuario | Clave  | Perfil         |
|---------|--------|----------------|
| admin   | 123456 | Administrativo |
| carito  | 123456 | Cliente        |
| julio   | 123456 | Profesional    |


## Consideraciones de seguridad

Las contraseñas se están almacenando dentro de la base de datos en texto plano, por lo que se recomienda añadir sistemas de cifrado si pretende usar esta aplicación en producción.


## **A continuación se describen las funcionalidades:**

### 1.- Login o Acceso de usuarios:

Los usuarios puede iniciar sesión utilizando un nombre de usuario y contraseña.
Se admite el acceso para tres roles de usuario: Administativo, Cliente y Profesional , cada uno con sus acceso según su tipo de usuario.

### 2.- Página de Inicio del portal:

Una vez autenticado el usuario puede acceder a la página de inicio de portal.
La página de inicio ofrece diferentes opciones y enlaces dependiendo del rol de usuario.

### 3.- Formulario de Contacto:

Solo el usuario de tipo Cliente tiene acceso al formulario de Contacto para realizar consultas.
El formulario permite registrar datos de contacto.

### 4.- Formulario de Creación de Capacitaciones:

Solo el cliente tiene acceso al formulario para Crear Capacitación.
Se proporciona un listado de capacitaciones registradas.

### 5.- Formulario de Creación y Edición de Usuarios.

Solo el usuario de tipo Administrativo tiene acceso al formulario para crear y editar usuarios en el sistema.
Se ofrece un listado de los usuarios registrados de todos los tipos.

### 6.- Formulario de Registro de Visitas a Clientes:

Solo el usuario de tipo Profesional puede acceder a un formulario para agregar y listar las visitas realizadas a cada cliente.
El formulario permite registrar y actualizar las visitas realizadas a los clientes.

### 7.- Formulario de Creación y Edición de Pagos:

Solo el usuario de tipo Administrativo tiene acceso a un formulario para crear y editar pagos.
Se ofrece un listado de los pagos realizados.

### 8.- Formulario de Registro y Edición de Asistencia a Capacitaciones:

Solo el usuario Cliente puede acceder al formulario para registrar y editar los asistentes a una capacitación.
Se proporciona un listado de asistentes de acuerdo a la capacitación.

### 9.- Formulario de Registro de Check List de las visitas realizadas por los profesionales:

Solo el usuario de tipo profesional puede acceder al formulario para registrar y ver el listado de chequeos o items evaluados 
de las visitas relacionadas.

# ////////////////////////////////////////////////////////////////////////

# **AUTORES:**

### Victor Rivas.

### Héctor Komori.

### Alan Vera.

### Joaquín De la Huerta.
