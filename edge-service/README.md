# edge service

## API
**Base-URL**: `/listings/`
# Show Current User
Get the details of the currently Authenticated User.

**URL** : `/users/search/userid={userid}`

**Method** : `GET`

**Auth required** : YES

## Success Response

**Code** : `200 OK`

**Content examples**

For a User with ID 13 on the local database
```json
{
    "id": 15,
    "firstName": "jos",
    "lastName": "peeters",
    "email": "jospeeters@example.com",
    "password": "1234"
}
```
---

