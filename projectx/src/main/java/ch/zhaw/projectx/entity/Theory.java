package ch.zhaw.projectx.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Theory extends Belief {

    private String theory_statement;

    @OneToMany
    private List<Belief> beliefs;
}