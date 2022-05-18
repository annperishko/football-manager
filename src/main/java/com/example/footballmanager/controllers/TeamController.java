package com.example.footballmanager.controllers;

import com.example.footballmanager.dto.request.TeamRequestDto;
import com.example.footballmanager.dto.response.TeamResponseDto;
import com.example.footballmanager.services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamResponseDto> getTeamById(@PathVariable int teamId){
        return ResponseEntity.ok(teamService.getTeamInfo(teamId));
    }

    @PostMapping
    public ResponseEntity<TeamResponseDto> createNewTeam(@Valid @RequestBody TeamRequestDto teamRequestDto){
        return ResponseEntity.ok(teamService.createTeam(teamRequestDto));
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeamById(@PathVariable int teamId){
        teamService.deleteTeam(teamId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<TeamResponseDto>> getAll(){
        return ResponseEntity.ok(teamService.findAllTeams());
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<TeamResponseDto> updateTeamInfo(@PathVariable int teamId,
                                                          @RequestBody TeamRequestDto teamRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(teamService.updateTeamInfo(teamId, teamRequestDto));
    }
}
