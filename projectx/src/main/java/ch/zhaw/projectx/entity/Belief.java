package ch.zhaw.projectx.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Belief {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String parentStatement;

    @OneToMany(mappedBy = "belief", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Explanation> explanations;

}