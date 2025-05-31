# Versioning the API

API versioning is useful for backward compatibility. 

Spring Boot (with Spring MVC) supports API versioning at both the controller level and the method level, using :
 * URL paths
 * request headers
 * request parameters.
 * media type

This demo app covers both controller level and method level but using the **URL path**.

### Request header based

```declarative
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping(value = "", headers = "X-API-VERSION=1")
    public String getProductsV1() {
        return "Products v1";
    }

    @GetMapping(value = "", headers = "X-API-VERSION=2")
    public String getProductsV2() {
        return "Products v2";
    }
}
```
Call with header:
```X-API-VERSION: 1 or 2```

### Request parameter based
```declarative
@GetMapping(value = "", params = "version=1")
public String getProductsV1() {
    return "Products v1 (param)";
}

@GetMapping(value = "", params = "version=2")
public String getProductsV2() {
    return "Products v2 (param)";
}
```
Call with:
```declarative
/api/products?version=1
/api/products?version=2
```
### Versioning with produces (Media Type versioning)
```declarative
@GetMapping(value = "", produces = "application/vnd.api.v1+json")
public String getProductsV1() {
    return "Products v1 (media type)";
}

@GetMapping(value = "", produces = "application/vnd.api.v2+json")
public String getProductsV2() {
    return "Products v2 (media type)";
}
```
Set request header: ```Accept: application/vnd.api.v1+json```

### 📘 API Versioning Summary Table

| Versioning Type        | URL Example                            | Pros                         | Cons                          |
|------------------------|----------------------------------------|------------------------------|-------------------------------|
| Path Versioning        | `/api/v1/products`                     | ✅ Clear and easy            | ❌ URL can get messy          |
| Header Versioning      | `X-API-VERSION: 1`                     | ✅ Clean URLs                | ❌ Harder to test in browsers |
| Param Versioning       | `/api/products?version=1`              | ✅ Easy to test and implement| ❌ Less RESTful               |
| Media Type Versioning  | `Accept: application/vnd.api.v1+json` | ✅ RESTful, flexible         | ❌ More complex to configure  |
