package ch.zhaw.projectx.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Belief {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String beliefStatement;

    private String validationStatus;

    @OneToMany(mappedBy = "belief", cascade = CascadeType.ALL)
    private List<Explanation> explanations;

    @ManyToOne
    @JoinColumn(name = "theory_id")
    private Theory theory;
}