package com.example.footballmanager.dto.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
@Data
public class TeamRequestDto {
    @NotBlank(message = "team name can`t be empty")
    private String teamName;
    @NotBlank(message = "account can`t be empty")
    private Double account;
    @Min(value = 0, message = "min % of transfer commission")
    @Max(value = 10, message = "max % of transfer commission")
    private Integer commission;

}
