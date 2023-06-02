package ch.zhaw.projectx.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainInfo {
    private String category;
    private String topLevelCategory;

    public DomainInfo(String category, String topLevelCategory) {
        this.category = category;
        this.topLevelCategory = topLevelCategory;
    }
}