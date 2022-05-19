package com.example.footballmanager.mapping;

import com.example.footballmanager.dto.response.PlayerResponseDto;
import com.example.footballmanager.entities.Player;
import com.example.footballmanager.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapping {

    public PlayerResponseDto mapToPlayerResponseDto(Player player) {
        PlayerResponseDto dto = new PlayerResponseDto();
        dto.setId(player.getId());
        dto.setAge(player.getAge());
        dto.setExperience(player.getExperience());
        dto.setName(player.getName());
        dto.setTeamName(player.getTeam().getTeamName());
        return dto;
    }
}
