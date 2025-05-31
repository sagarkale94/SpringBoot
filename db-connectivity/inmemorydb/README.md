# In Memory DB (H2 Database)

### Maven dependencies

In order to use in memory database (in this demo which is H2 database) we need following maven dependencies

```declarative
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```
### Configurations in application.properties file

```declarative
spring.datasource.url=jdbc:h2:mem:appdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=mydb
spring.datasource.password=password
spring.h2.console.enabled=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

| Property                                   | Purpose                                                  |
|--------------------------------------------|----------------------------------------------------------|
| `spring.datasource.url`                    | Connects to in-memory H2 database named `appdb`.         |
| `spring.datasource.driverClassName`        | Declares JDBC driver class (H2).                         |
| `spring.datasource.username` / `password`  | Credentials for the database.                            |
| `spring.h2.console.enabled`                | Enables the web-based database viewer.                   |
| `spring.jpa.database-platform`             | Informs Hibernate to use H2-specific SQL syntax.         |

### H2 Console

To open h2 console open the url in browser: http://localhost:8080/h2-console

![Image](https://github.com/user-attachments/assets/f8b17523-5ebc-4792-a223-dc76a75cf0ac)

![Image](https://github.com/user-attachments/assets/4f474d75-de27-4e16-8d6a-ce3cc2a83321)