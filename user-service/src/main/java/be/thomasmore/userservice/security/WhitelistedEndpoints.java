package be.thomasmore.userservice.security;

/**
 * List of URL endpoints that don't require authentication/authorization (JWT) in order to be accessed.
 */
public class WhitelistedEndpoints {
    public static final String ADD_USER = "/users/add";
}
