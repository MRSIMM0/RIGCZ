package pl.simmo.rigcz_counter.Entity;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;

@Entity

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    @NaturalId
    @Column(length = 60)
    private RoleName name;

    public Role(){}

    public Role(RoleName name){
        this.name=name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
