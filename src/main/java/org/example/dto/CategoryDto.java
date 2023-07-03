package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryDto {
    private Integer id;
    private String name;
    private LocalDateTime createdDate;
    private Boolean visible = true;

    public CategoryDto(Integer id, String name, Boolean visible) {
        this.id = id;
        this.name = name;
        this.visible = visible;
    }
}
