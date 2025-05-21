# Spring Boot Demos

Welcome to the **Spring Boot Demos** repository! 🚀  
This repository contains a collection of self-contained Spring Boot projects that demonstrate key features of the Spring ecosystem.

## 🔧 Prerequisites
- Java 17+ 
- Maven 3.8+ or Gradle
- Spring Boot 3.x

## 📝 MVC

MVC stands for: 

- **Model** – Handles the data and business logic

- **View** – What the user sees (UI)

- **Controller** – Handles user input and coordinates between Model and View

![Image](https://github.com/user-attachments/assets/d1428599-8278-4019-bdd7-0029262ca5ce)

#### 📝 Example (Real-World Analogy)

**Task: Show user profile page**

- User clicks on “Profile”
- View sends a request to the Controller
- Controller asks the Model for user data
- Model fetches user info from database
- Controller sends that data to the View
- View renders the user profile page

## 📝 SPRING MVC

![Image](https://github.com/user-attachments/assets/a40a5bb8-bd1a-4e14-a285-deddb001abd6)

#### 🔄 Spring Boot Request-Response Flow (with DispatcherServlet & Filters)
##### ✅ Request Flow (Client → Server)
- Client makes HTTP request
- Request hits Servlet Container (e.g., Tomcat embedded in Spring Boot)
- Request passes through Servlet Filters (e.g., OncePerRequestFilter)
- DispatcherServlet (Spring's front controller) handles the request
- DispatcherServlet consults:
  - HandlerMapping → Determines which controller to call
  - HandlerAdapter → Executes the controller method
- Controller returns a response (e.g., a view name or @ResponseBody/JSON)

##### 🔁 Response Flow (Server → Client)
- Controller’s return value is sent back to the DispatcherServlet
- DispatcherServlet processes:
  - View Resolution (if needed)
  - Message conversion (e.g., converting an object to JSON using Jackson)
- Response goes back through the Servlet Filters (in reverse order)
- Servlet container sends final response back to the client

##### Concrete Flow (Server → Client → Server)

`[Client] ⇄ [Filter 1] ⇄ [Filter 2] ⇄ [DispatcherServlet] ⇄ [Controller]`

                                   `⇄ [DispatcherServlet] ⇄`

`[Client] ⇐ [Filter 2] ⇐ [Filter 1] ⇐`

#### 🕰️ In Classic Spring MVC Web Apps (Old-style WAR Deployment)

![Image](https://github.com/user-attachments/assets/dd9de40a-820e-4053-89bc-a5d6d5138b68)

##### 🔄 Request Flow (Client → Server)
- Client sends request
- Request hits Servlet Container (e.g., external Tomcat or WebLogic)
- Servlet Filters intercept the request first (e.g., logging, security)
- Request is forwarded to DispatcherServlet
- DispatcherServlet:
  - Finds controller via HandlerMapping
  - Invokes controller method

#### ⬅️ Response Flow (Controller → Dispatcher → View → Filters → Client)
- Controller returns a view name (e.g., "home" or "userList")
- DispatcherServlet calls:
  - A ViewResolver to map the view name to a JSP file or template
  - Then dispatches to that JSP (or template engine like Thymeleaf)
- The view renders the HTML (e.g., via JSP engine or Thymeleaf)
- Rendered output (HTML) is sent back to DispatcherServlet
- DispatcherServlet returns it through the Servlet Filters again (in reverse order)
- Servlet container returns response to client

##### ✅ So yes:
The response from the controller goes through DispatcherServlet → view → DispatcherServlet → filters → client
The view is rendered between controller and filters on the way back.
