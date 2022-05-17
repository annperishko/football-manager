package com.example.footballmanager.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class TeamResponseDto {
    private Integer id;
    private String teamName;
    private Double account;
    private Integer commission;
    private List<String> playersNames;
}
