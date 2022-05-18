package com.example.footballmanager.controllers;

import com.example.footballmanager.dto.request.PlayerRequestDto;
import com.example.footballmanager.dto.response.PlayerResponseDto;
import com.example.footballmanager.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerResponseDto> getPlayerById(@PathVariable int playerId) {
        return ResponseEntity.ok(playerService.getPlayerInfo(playerId));
    }

    @PostMapping
    public ResponseEntity<PlayerResponseDto> createNewPlayer(@Valid @RequestBody PlayerRequestDto playerRequestDto) {
        return ResponseEntity.ok(playerService.createNewPlayer(playerRequestDto));
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<Void> deletePlayerById(@PathVariable int playerId) {
        playerService.deletePlayerById(playerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<PlayerResponseDto>> getAll() {
        return ResponseEntity.ok(playerService.findAll());
    }

    @PutMapping("/{playerId}/{purchaserId}")
    public ResponseEntity<PlayerResponseDto> transferPlayer(@PathVariable int playerId,
                                                            @PathVariable int purchaserId) {
        return ResponseEntity.ok(playerService.transferPlayerToAnotherTeam(playerId, purchaserId));
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<PlayerResponseDto> updateTeamInfo(@PathVariable int playerId,
                                                            @Valid @RequestBody PlayerRequestDto playerRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.updatePlayerInfo(playerId, playerRequestDto));
    }

}
