package ch.zhaw.projectx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
public class Explanation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date dateExplained;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "belief_id", nullable = false)
    @JsonIgnore
    private Belief belief;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domain_id", nullable = false)
    private Domain domain;
}
