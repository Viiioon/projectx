package ch.zhaw.projectx.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Theory extends Belief {

    private String principle;

    @OneToMany(mappedBy = "theory", cascade = CascadeType.ALL)
    private List<Belief> beliefs;
}