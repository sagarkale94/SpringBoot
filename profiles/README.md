# Profiles

Spring Profiles allow you to create multiple environments (like dev, test, prod) with different configurations. This is super helpful for managing environment-specific settings like databases, logging levels, feature toggles, etc.

## 🚀 Step-by-Step: Creating Profiles in Spring Boot

```bash
src/main/resources/
├── application.properties
├── application-dev.properties
├── application-prod.properties
```

### 1. ✅ Define Profile-Specific Configuration Files

Spring Boot loads these automatically based on the active profile.

#### 🔧 application.properties (shared by all profiles)

```
app.name=MyApp
```

#### 🔧 application-dev.properties

```
spring.datasource.url=jdbc:h2:mem:devdb
app.mode=Development
```

#### 🔧 application-prod.properties

```
spring.datasource.url=jdbc:mysql://prod-db-server/mydb
app.mode=Production
```

You can also use .yml files if you prefer YAML format.

### 2. ✅ Activate a Profile

#### Option 1: In application.properties

```
spring.profiles.active=dev
```

#### Option 2: Via Command Line

```
java -jar myapp.jar --spring.profiles.active=prod
```

#### Option 3: In application.yml

```
spring:
  profiles:
    active: dev
```

#### 3. ✅ Use @Profile in Code

You can activate beans or configurations only for a specific profile.

```
@Component
@Profile("dev")
public class DevDataLoader implements CommandLineRunner {
    public void run(String... args) {
        System.out.println("Loading development data...");
    }
}
```

```
@Component
@Profile("prod")
public class ProdDataLoader implements CommandLineRunner {
    public void run(String... args) {
        System.out.println("Initializing production settings...");
    }
}
```

### 🔍 Optional: Use @Profile with @Configuration

```
@Configuration
@Profile("test")
public class TestConfig {
    // Beans specific to test
}
```

### 🧪 Check Active Profile at Runtime

```
@Autowired
private Environment environment;

public void printProfile() {
    System.out.println(Arrays.toString(environment.getActiveProfiles()));
}
```

## 🔍 Summary

| Feature                            | Description                               |
| ---------------------------------- | ----------------------------------------- |
| `application-{profile}.properties` | Profile-specific settings                 |
| `spring.profiles.active`           | Activates a profile                       |
| `@Profile("name")`                 | Loads beans/configs conditionally         |
| Use in `dev` / `prod` / `test`     | Configure DB URLs, logging, caching, etc. |

## Important distinction:

- Spring profiles (spring.profiles.active) control runtime behavior — which config files and beans get loaded.

- Maven profiles (-P) control build-time behavior — what gets included/excluded in the build.

### How to pass Spring Boot profile to your app at build time using Maven properties

### Step 1: Pass Spring profile as a Maven property during build

```
mvn clean package -Dspring.profiles.active=prod
```

### Step 2: Use Maven resource filtering to inject the profile value into your `application.properties`

#### 1. Enable resource filtering in your `pom.xml`:

```
<build>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <filtering>true</filtering>
        </resource>
    </resources>
</build>
```

#### 2. Create a property placeholder in `src/main/resources/application.properties`:

```
spring.profiles.active=@spring.profiles.active@
```

#### 3. Define a filtering property in `pom.xml` so Maven replaces `@spring.profiles.active@`:

```
<properties>
    <spring.profiles.active>default</spring.profiles.active>
</properties>

<build>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <filtering>true</filtering>
        </resource>
    </resources>
</build>
```

#### 4. When you build with:

```
mvn clean package -Dspring.profiles.active=prod
```

Maven replaces `@spring.profiles.active@` with prod in the generated `application.properties` inside the JAR.

### Step 3: Run your JAR as usual (no need to specify profile again):

```
java -jar target/myapp.jar
```

Your app will start with prod profile active because it’s set inside the `application.properties` via Maven filtering.

# Summary

| Step                      | Command / File                                    |
| ------------------------- | ------------------------------------------------- |
| Build with profile        | `mvn clean package -Dspring.profiles.active=prod` |
| Enable resource filtering | In `pom.xml`                                      |
| Placeholder in properties | `spring.profiles.active=@spring.profiles.active@` |
| Run JAR                   | `java -jar target/myapp.jar`                      |

## (Is step 2 Mandatory ) What actually happens?

### If you run the packaged JAR later by:

```
java -jar target/myapp.jar
```

The app will NOT know which profile to activate unless you explicitly tell it via:

- A profile set inside the JAR config (application.properties)
- Or by passing profile on the command line at runtime:

```
java -jar target/myapp.jar --spring.profiles.active=prod
```

### So:

- If you want to pick the profile at runtime, you just pass `--spring.profiles.active=prod` when running the JAR.
- If you want the profile baked into the config inside the JAR, you need **_Maven resource filtering_** or other build-time tricks.

## Spring Boot Profile Usage Summary

| Scenario                                   | What you need to do                                        |
| ------------------------------------------ | ---------------------------------------------------------- |
| Run with profile at runtime                | `java -jar target/myapp.jar --spring.profiles.active=prod` |
| Run with profile via Maven spring-boot:run | `mvn spring-boot:run -Dspring-boot.run.profiles=prod`      |
| Bake profile into JAR config at build time | Use Maven filtering (resource filtering in `pom.xml`)      |
