package ch.zhaw.projectx.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheoremInfo {
    private int id;
    private String type;
    private String label;
    private String title;
    private List<String> categories;
    private List<String> contents;
    private List<String> refs;
    private List<Integer> ref_ids;
    private List<Proof> proofs;
    private List<String> toplevel_categories;
    private List<String> recursive_categories;

}