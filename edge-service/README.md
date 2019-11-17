# edge service
Service that forwards requests to the appropriate internal microservices.
This service should only be called through the [Zuul gateway](../zuul-gateway).
 
## API
SwaggerUI should be available at: `http://localhost:8070/swagger-ui.html`

**Base-URL**: `/listings/`
# Search For User By Id
Get the details of the user with given userid

**URL** : `/users/search/userid={userid}`

**Method** : `GET`

**Auth required** : YES

## Success Response

**Code** : `200 OK`

**Content examples**

For a User with ID 15 on the local database
```json
{
    "id": 15,
    "firstName": "jos",
    "lastName": "peeters",
    "email": "jospeeters@example.com",
    "password": "1234"
}
```
# Add User
Adds a new user to the database.

**URL** : `/users/users/add/firstname={firstname}&lastname={lastname}&email={email}&password={password}`

**Method** : `GET`

**Auth required** : YES

## Success Response

**Code** : `200 OK`

---

