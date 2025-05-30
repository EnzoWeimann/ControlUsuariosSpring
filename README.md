# People Management 

This project is a web application developed with Spring Boot for people management (CRUD). It includes role authentication (USER and ADMIN), session management, internationalization, password encryption, and Javadoc documentation.

## 🧰 Technologies used

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Thymeleaf
- MySQL
- Maven
- Bootstrap 5

## ✅ Requirements

- Java JDK 17 or higher
- Maven 3.8+
- MySQL Server

## ⚙️ Database Configuration

1. Create a database in MySQL called `control_usuarios`.
2. Create the tables `persona`, `usuario`, and `rol` according to the entity structure (`Persona`, `Usuario`, `Rol`).
3. Set up the file `application.properties` o `application.yml`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/control_usuarios?useSSL=false
spring.datasource.username=your_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

## ▶️ Project execution
*1.* Clone the repository with bash:
```properties
git clone https://github.com/EnzoWeimann/People-Management-with-Spring.git
cd People-Management-with-Spring
```

*2.* Compile and run with bash:
```properties
./mvnw spring-boot:run
```

*3.* Access from the browser:
```properties
http://localhost:8080
```
 - Default User: ``admin``
 - Password: ``1234`` (see src -> main -> java -> com.enzow.PeopleManagement -> uil -> EncryptPassword)

*Hardcoded users can be added from the database for testing.*
*Dynamic creation of users and passwords will be added in a future version.*

## 🔐 Roles y permisos
| Rol   | Access               |
| ----- |----------------------|
| ADMIN | Create, edit, delete |
| USER  | Only view the list   |

## 📝 Language
The application has two buttons in the footer to choose the language, which can be English or Spanish (default option).
