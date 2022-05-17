package com.example.footballmanager.dto.request;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
public class PlayerRequestDto {
    @NotBlank(message = "team name can`t be empty")
    private String name;
    @NotBlank(message = "age can`t be empty")
    private Integer age;
    @NotBlank(message = "experience can`t be empty")
    private Double experience;

    private String teamName;
}
