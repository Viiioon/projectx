package ch.zhaw.projectx.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Dataset {
    private List<TheoremInfo> theorems;

}