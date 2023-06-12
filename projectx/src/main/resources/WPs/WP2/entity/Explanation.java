package ch.zhaw.projectx.entity;

import ch.zhaw.projectx.serializer.ExplanationSerializer;
import ch.zhaw.projectx.serializer.TheoremSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@JsonSerialize(using = ExplanationSerializer.class)
public class Explanation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateExplained;

    @ManyToOne
    @JoinColumn(name = "belief_id", nullable = false)
    private Belief belief;

    @ManyToOne
    @JoinColumn(name = "domain_id", nullable = false)
    private Domain domain;
}
