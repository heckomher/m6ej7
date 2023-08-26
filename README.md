# Ejercicio7m6

This app was created with Bootify.io - tips on working with the code [can be found here](https://bootify.io/next-steps/).
Feel free to contact us for further questions.

## Development

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


Lombok must be supported by your IDE. For IntelliJ install the Lombok plugin and enable annotation processing -
[learn more](https://bootify.io/next-steps/spring-boot-with-lombok.html).

After starting the application it is accessible under `localhost:8080`.

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
