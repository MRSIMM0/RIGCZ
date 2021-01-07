package pl.simmo.rigcz_counter.message.response;

import java.util.List;

public class UserDetails {
    private String name;
    private List<String> role;
    private String username;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
