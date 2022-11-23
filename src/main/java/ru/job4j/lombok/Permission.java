package ru.job4j.lombok;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;

import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder(builderMethodName = "of")
public class Permission {
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    @Singular("rule")
    private List<String> rules;
}
