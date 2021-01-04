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
    @SequenceGenerator(name = "generatorEn")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="generatorEn" )
    private long id;

    private String name;

    private int rigczLevel;
}
