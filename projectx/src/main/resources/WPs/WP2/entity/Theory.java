package ch.zhaw.projectx.entity;

import ch.zhaw.projectx.serializer.TheorySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@JsonSerialize(using = TheorySerializer.class)
public class Theory extends Belief {

    private String theoryStatement;

    @OneToMany
    private List<Belief> beliefs;
}