package ch.zhaw.projectx.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Explanation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateExplained;

    @ManyToOne
    @JoinColumn(name = "belief_id", nullable = false)
    @JsonIgnore
    private Belief belief;

    @ManyToOne
    @JoinColumn(name = "domain_id", nullable = false)
    @JsonIgnore
    private Domain domain;
}
