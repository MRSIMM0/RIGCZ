package pl.simmo.rigcz_counter.message.response;

import java.util.List;

public class UserDetails {
    private String name;
    private List<String> role;

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
}
