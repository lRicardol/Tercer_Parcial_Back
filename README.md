# Tercer_Parcial_Back

### Estudiante:
- **Nombre:** *[Ricardo Ayala Garzon]*
- **Grupo:** *CVDS - Tercer Tercio*

---

## Descripción

Este backend implementa el sistema de gestión de citas médicas para la clínica **ECI Salud Vital**. Permite:
- Visualizar especialidades médicas.
- Programar citas con validación.
- Consultar historial de citas por correo.
- Cancelar citas.
- Guardar todo en MongoDB y exponer API REST desplegada en Azure.

---

## Tecnologías

- Java 17
- Spring Boot 3.2.0
- MongoDB
- Swagger
- Azure App Service
- JUnit + Mockito

---

## Arquitectura

### estructura del parcial
![Screenshot 2025-05-23 153427.png](imagenes%2FScreenshot%202025-05-23%20153427.png)

## Despliege en Azure
![Screenshot 2025-05-23 161713.png](imagenes%2FScreenshot%202025-05-23%20161713.png)
![img.png](img.png)
![Screenshot 2025-05-23 161945.png](imagenes%2FScreenshot%202025-05-23%20161945.png)
```plaintext
[React Frontend] ---> [REST API - Spring Boot] ---> [MongoDB Atlas]
```

## Ejecutar el Proyecto

Se implementaron pruebas unitarias usando JUnit y Mockito para asegurar el correcto funcionamiento del codigo
![Screenshot 2025-05-23 163743.png](imagenes%2FScreenshot%202025-05-23%20163743.png)
```
./mvnw test
```

## Endpoints REST principales
Método	Endpoint	Descripción
GET	/especialidades	Lista todas las especialidades
POST	/citas	Crea una nueva cita médica
GET	/citas/{correo}	Citas asociadas a un paciente
GET	/citas/{correo}/estado/{estado}	Historial filtrado por estado
PUT	/citas/{id}/cancelar	Cancela una cita específica
