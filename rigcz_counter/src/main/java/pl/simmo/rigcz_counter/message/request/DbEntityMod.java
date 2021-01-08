package pl.simmo.rigcz_counter.message.request;

public class DbEntityMod {

    private Long id;

    private String name;

    private String craetedBy;

    private int rigczLevel;

    private String userAdd;

    private String userMinus;

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

    public String getUserAdd() {
        return userAdd;
    }

    public void setUserAdd(String userAdd) {
        this.userAdd = userAdd;
    }

    public String getUserMinus() {
        return userMinus;
    }

    public void setUserMinus(String userMinus) {
        this.userMinus = userMinus;
    }


    public int getRigczLevel() {
        return rigczLevel;
    }

    public void setRigczLevel(int rigczLevel) {
        this.rigczLevel = rigczLevel;
    }
}
