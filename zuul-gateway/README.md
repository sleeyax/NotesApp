# Zuul gateway
Proxy server that handles user authentication. 

All incoming requests will be validated and/or filtered before being forwared to internal microservices.
Almost all requests require an authorization header in the following format:

`Authorization: Bearer <jwt>`

This is the only service that's accessible to the public.

## Unauthorized requests
Below is a list of requests that don't require authorization.
### User login
`/auth/login` -> `user-service/login`

**method:** `POST`

**example body**:
```
{
	"email": "example@mail.com",
	"password": "password"
}
```

Returns a Json Web Token when the user credentials are correct.

### New user
`/auth/users/add` -> `user-service/users/add`

**method:** `POST`

**example body:**
```
{
	"firstName": "John",
	"lastName": "Doe",
	"email": "john.doe@mail.com",
	"password": "password"
}
```

Creates a new user