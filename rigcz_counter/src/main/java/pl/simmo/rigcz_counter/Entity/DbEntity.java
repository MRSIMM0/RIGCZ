package pl.simmo.rigcz_counter.Entity;

import lombok.*;

import javax.persistence.*;

@javax.persistence.Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DbEntity {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int rigczLevel;
}
