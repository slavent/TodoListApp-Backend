package ru.pycak.todolistapp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateTaskCommentDTO {

    @NotBlank
    private final String text;

    @JsonCreator
    public CreateTaskCommentDTO(@JsonProperty String text) {
        this.text = text;
    }
}
