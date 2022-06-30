package ru.pycak.todolistapp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.pycak.todolistapp.model.UserModel;

import javax.validation.constraints.NotBlank;

@Data
public class ChangeUserDTO {

    @NotBlank
    private final String name;

    @JsonCreator
    ChangeUserDTO(@JsonProperty String name) {
        this.name = name;
    }
}
