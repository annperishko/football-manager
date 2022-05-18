package com.example.footballmanager.services;

import com.example.footballmanager.dto.request.TeamRequestDto;
import com.example.footballmanager.dto.response.TeamResponseDto;
import com.example.footballmanager.entities.Team;
import com.example.footballmanager.exceptions.RecordNotFoundException;
import com.example.footballmanager.mapping.TeamMapping;
import com.example.footballmanager.repositories.TeamRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class TeamService {
    private final TeamRepo teamRepo;
    private final TeamMapping teamMapping;

    private static final String ERR_MSG = "Team not found";

    public TeamService(TeamRepo teamRepo, TeamMapping teamMapping) {
        this.teamRepo = teamRepo;
        this.teamMapping = teamMapping;
    }

    public TeamResponseDto getTeamInfo(Integer teamId) {
        return teamMapping.mapToTeamResponseDto(findById(teamId));
    }

    @Transactional
    public TeamResponseDto createTeam(TeamRequestDto teamRequestDto) {
        Team team = new Team();
        team.setTeamName(teamRequestDto.getTeamName());
        team.setAccount(teamRequestDto.getAccount());
        team.setCommission(teamRequestDto.getCommission());
        teamRepo.save(team);
        return teamMapping.mapToTeamResponseDto(team);

    }

    @Transactional
    public void deleteTeam(Integer teamId) {
        if (teamRepo.findById(teamId).isEmpty()) {
            throw new RecordNotFoundException(ERR_MSG);
        }
        teamRepo.deleteById(teamId);
    }

    @Transactional
    public TeamResponseDto updateTeamInfo(Integer teamId, TeamRequestDto teamRequestDto) {
        Team team = findById(teamId);
        team.setTeamName(teamRequestDto.getTeamName());
        team.setAccount(teamRequestDto.getAccount());
        team.setCommission(teamRequestDto.getCommission());

        return teamMapping.mapToTeamResponseDto(team);

    }

    public Team findTeamByName(String name) {
        return teamRepo.findTeamByTeamName(name)
                .orElseThrow(() -> new RecordNotFoundException(ERR_MSG));
    }

    public String findNameOfTeam(Team team) {
        return teamRepo.findTeamName(team)
                .orElseThrow(() -> new RecordNotFoundException(ERR_MSG));
    }

    public Team findById(Integer teamId) {
        return teamRepo.findById(teamId)
                .orElseThrow(() -> new RecordNotFoundException(ERR_MSG));
    }

    public List<TeamResponseDto> findAllTeams() {
        List<Team> teams = teamRepo.findAllTeams();
        return teams.stream()
                .map(teamMapping::mapToTeamResponseDto)
                .collect(Collectors.toList());
    }
}
