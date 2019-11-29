package be.thomasmore.userservice;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * This class inherits from the 'UserDetails' class, to easily include additional JWT claims.
 */
public class ExpandedUserDetails extends org.springframework.security.core.userdetails.User {
    private int id;
    private String firstName;

    public ExpandedUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public ExpandedUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
}
