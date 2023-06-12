package ch.zhaw.projectx.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Proof {
    private List<String> contents;
    private List<String> refs;
    private List<Integer> ref_ids;

}