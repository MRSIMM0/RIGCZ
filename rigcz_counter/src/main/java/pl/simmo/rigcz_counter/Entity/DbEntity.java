package pl.simmo.rigcz_counter.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@javax.persistence.Entity
@NoArgsConstructor
public class DbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int rigczLevel;

    private String craetedBy;

    @ManyToMany()
    private Set<User> UsersAdd;

    @ManyToMany()
    private Set<User> UsersMinus;

    public DbEntity(String name, int rigczLevel, String craetedBy, Set<User> usersAdd, Set<User> usersMinus) {
        this.name = name;
        this.rigczLevel = rigczLevel;
        this.craetedBy = craetedBy;
        UsersAdd = usersAdd;
        UsersMinus = usersMinus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRigczLevel() {
        return rigczLevel;
    }

    public void setRigczLevel(int rigczLevel) {
        this.rigczLevel = rigczLevel;
    }

    public String getCraetedBy() {
        return craetedBy;
    }

    public void setCraetedBy(String craetedBy) {
        this.craetedBy = craetedBy;
    }

    public Set<User> getUsersAdd() {
        return UsersAdd;
    }

    public void setUsersAdd(Set<User> usersAdd) {
        UsersAdd = usersAdd;
    }

    public Set<User> getUsersMinus() {
        return UsersMinus;
    }

    public void setUsersMinus(Set<User> usersMinus) {
        UsersMinus = usersMinus;
    }
}
