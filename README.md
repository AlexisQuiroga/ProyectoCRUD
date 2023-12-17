# ProyectoCRUD

# CRUD App en Java y MySQL

## Descripción

Esta aplicación Java implementa las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) utilizando una base de datos MySQL.

## Estructura del Proyecto

- **src/main/java/com/tuempresa/crudapp/models:** Contiene la clase `Registro` que representa el modelo de datos.
- **src/main/java/com/tuempresa/crudapp/dao:** Contiene la clase `RegistroDAO` que maneja el acceso a la base de datos.
- **src/main/java/com/tuempresa/crudapp/service:** Contiene la clase `RegistroService` que encapsula la lógica de negocio.
- **src/main/java/com/tuempresa/crudapp/util:** Contiene la clase `MySQLConexion` para gestionar la conexión a MySQL.

## Uso

1. Configurar los detalles de conexión a la base de datos en `MySQLConexion.java`.
2. Ejecutar la clase `Main.java` para probar las operaciones CRUD.

## Notas

- Asegúrate de tener el controlador JDBC de MySQL en tu proyecto.
- Adaptar según los requisitos específicos del proyecto.
