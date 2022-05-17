package com.example.footballmanager.services;

import com.example.footballmanager.dto.response.PlayerResponseDto;
import com.example.footballmanager.dto.response.TeamResponseDto;
import com.example.footballmanager.entities.Player;
import com.example.footballmanager.entities.Team;
import com.example.footballmanager.exceptions.RecordNotFoundException;
import com.example.footballmanager.mapping.TeamMapping;
import com.example.footballmanager.repositories.TeamRepo;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    private final TeamRepo teamRepo;
    private final TeamMapping teamMapping;

    private static final String ERR_MSG = "Team not found";

    public TeamService(TeamRepo teamRepo, TeamMapping teamMapping) {
        this.teamRepo = teamRepo;
        this.teamMapping = teamMapping;
    }

    public TeamResponseDto getTeamInfo(Integer teamId){
        return teamMapping.mapToTeamResponseDto(findById(teamId));
    }

    public Team findTeamByName(String name){
        return teamRepo.findTeamByTeamName(name)
                .orElseThrow(() -> new RecordNotFoundException(ERR_MSG));
    }

    public String findNameOfTeam(Team team){
        return teamRepo.findTeamName(team)
                .orElseThrow(() -> new RecordNotFoundException(ERR_MSG));
    }

    public Team findById(Integer teamId){
        return teamRepo.findById(teamId)
                .orElseThrow(() -> new RecordNotFoundException(ERR_MSG));
    }
}
