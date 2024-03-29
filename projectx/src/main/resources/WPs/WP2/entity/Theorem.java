package ch.zhaw.projectx.entity;

import ch.zhaw.projectx.serializer.TheoremSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonSerialize(using = TheoremSerializer.class)
public class Theorem extends Belief {

    @Column(columnDefinition="TEXT")
    private String proof;
}