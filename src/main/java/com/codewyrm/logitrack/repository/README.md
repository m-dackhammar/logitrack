# Logitrack - A Spring Boot backend API
### What is Logitrack?
Logitrack is a very slim example of a clean Spring Boot backend API intended to show my understanding of backend architecture. There isn't anything "cool" going on with this project, it's just a demonstration of my skills. With that being said, I assume there are still lots of room for improvement.

### What does it contain?
LogiTrack is a mockup for a delivery company, it tracks vehicles, their drivers, and occasional maintenance tasks on the vehicles.

It's created to mimic a modern & robust backend following the principles of layered architecture structured accordingly:

**Domain Layer**: The data/entities we want to track and read - **Vehicle**, **Driver** and **MaintenanceTask**.

**Service Layer**: Business logic to manipulate the entities.

**Repository Layer**: Direct connection to DB, standard JPA.

**DTO Layer**: Create and Response, these objects represent data in transfer from client using the API without exposing domain entities.
  - **CreateDTO** is used to create new entities via API, they contain and carry vital data for Entity creation.
    - Handles constraints to ensure correct data format is sent to the backend. 
  - **ResponseDTO** is the readable data that is sent back from the API, exposing necessary data only.

**Controller Layer**: Exposes the API via REST

**GlobalExceptionHandler**: Ensures the correct status-code (4**) is sent when an exception is thrown by the service.

**Flyway Migrations**: The DB schema is set up via Flyway DB Migrations, under resources.
### How do I run it?
