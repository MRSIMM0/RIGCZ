package pl.simmo.rigcz_counter.message.response;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import pl.simmo.rigcz_counter.Entity.Role;

import java.util.Collection;
import java.util.Set;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private Set<String> authority;

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getAuthority() {
        return authority;
    }

    public void setAuthority(Set<String> Authority) {
        this.authority = Authority;
    }


    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }
}
