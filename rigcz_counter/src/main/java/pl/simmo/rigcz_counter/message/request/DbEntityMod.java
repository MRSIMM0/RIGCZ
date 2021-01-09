package pl.simmo.rigcz_counter.message.request;

import java.util.Set;

public class DbEntityMod {

    private Long id;

    private String name;

    private String craetedBy;

    private int rigczLevel;

    private String updatingUser;

    private Set<String> userAdd;

    private Set<String> userMinus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCraetedBy() {
        return craetedBy;
    }

    public void setCraetedBy(String craetedBy) {
        this.craetedBy = craetedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Set<String> getUserAdd() {
        return userAdd;
    }

    public void setUserAdd(Set<String> userAdd) {
        this.userAdd = userAdd;
    }

    public Set<String> getUserMinus() {
        return userMinus;
    }

    public void setUserMinus(Set<String> userMinus) {
        this.userMinus = userMinus;
    }

    public int getRigczLevel() {
        return rigczLevel;
    }

    public void setRigczLevel(int rigczLevel) {
        this.rigczLevel = rigczLevel;
    }

    public String getUpdatingUser() {
        return updatingUser;
    }

    public void setUpdatingUser(String updatingUser) {
        this.updatingUser = updatingUser;
    }
}


