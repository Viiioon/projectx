package ch.zhaw.projectx.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String areaOfStudy;

    @OneToMany(mappedBy = "domain", cascade = CascadeType.ALL)
    private List<Explanation> explanations;


}