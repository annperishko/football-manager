package com.example.footballmanager.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PlayerRequestDto {
    @NotBlank(message = "player name can`t be empty")
    private String name;
    @NotNull(message = "age can`t be null")
    private Integer age;
    @NotNull(message = "experience can`t be null")
    private Double experience;
    @NotBlank(message = "team name can`t be empty")
    private String teamName;
}
