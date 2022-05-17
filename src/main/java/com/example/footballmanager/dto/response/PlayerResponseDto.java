package com.example.footballmanager.dto.response;

import lombok.Data;

@Data
public class PlayerResponseDto {
    private Integer id;
    private String name;
    private Integer age;
    private Double experience;
    private String teamName;
}
