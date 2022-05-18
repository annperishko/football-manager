package com.example.footballmanager.mapping;

import com.example.footballmanager.dto.response.PlayerResponseDto;
import com.example.footballmanager.entities.Player;
import com.example.footballmanager.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerMapping {
    private final TeamService teamService;

    public PlayerResponseDto mapToPlayerResponseDto(Player player) {
        PlayerResponseDto dto = new PlayerResponseDto();
        dto.setId(player.getId());
        dto.setAge(player.getAge());
        dto.setExperience(player.getExperience());
        dto.setName(player.getName());
        dto.setTeamName(teamService.findNameOfTeam(player.getTeam()));
        return dto;
    }
}
