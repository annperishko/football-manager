package com.example.footballmanager.mapping;

import com.example.footballmanager.dto.response.TeamResponseDto;
import com.example.footballmanager.entities.Team;
import com.example.footballmanager.services.PlayerService;
import com.example.footballmanager.services.TeamService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamMapping {
    private final TeamService teamService;
    private final PlayerService playerService;

    public TeamMapping(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    public TeamResponseDto mapToTeamResponseDto(Team team){
        TeamResponseDto dto = new TeamResponseDto();
        dto.setId(team.getId());
        dto.setAccount(team.getAccount());
        dto.setCommission(team.getCommission());
        dto.setTeamName(team.getTeamName());

        List<String> playersNames = team.getPlayers()
                .stream()
                .map(playerService::findPlayerName)
                .collect(Collectors.toList());

        dto.setPlayersNames(playersNames);
        return dto;

    }
}
