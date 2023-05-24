package de.db.dto;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class SectionView {
    @Builder.Default
    private Set<String> sections = new HashSet<>();
}
