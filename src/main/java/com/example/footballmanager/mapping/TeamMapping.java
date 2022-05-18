package com.example.footballmanager.mapping;

import com.example.footballmanager.dto.response.TeamResponseDto;
import com.example.footballmanager.entities.Player;
import com.example.footballmanager.entities.Team;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamMapping {

    public TeamResponseDto mapToTeamResponseDto(Team team){
        TeamResponseDto dto = new TeamResponseDto();
        dto.setId(team.getId());
        dto.setAccount(team.getAccount());
        dto.setCommission(team.getCommission());
        dto.setTeamName(team.getTeamName());

        List<String> playersNames = team.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());

        dto.setPlayersNames(playersNames);
        return dto;

    }
}
