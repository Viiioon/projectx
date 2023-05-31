package ch.zhaw.projectx.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Theorem extends Belief {

    private String proofStatus;
}