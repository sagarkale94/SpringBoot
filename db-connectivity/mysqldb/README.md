# MySQL Connectivity

### Maven dependencies

In order to use mysql database we need following maven dependencies

```declarative
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```
### Configurations in application.properties file

```declarative
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=root

#Hibernate will automatically create or alter tables in the database
#to match your entity classes, without dropping existing data.
spring.jpa.hibernate.ddl-auto=update

# Enable logging of SQL queries in terminal
#spring.jpa.show-sql=true
# Format the logged SQL for readability
#spring.jpa.properties.hibernate.format_sql=true
```
