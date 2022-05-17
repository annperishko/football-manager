package com.example.footballmanager.services;

import com.example.footballmanager.dto.request.PlayerRequestDto;
import com.example.footballmanager.dto.response.PlayerResponseDto;
import com.example.footballmanager.entities.Player;
import com.example.footballmanager.entities.Team;
import com.example.footballmanager.exceptions.RecordNotFoundException;
import com.example.footballmanager.mapping.PlayerMapping;
import com.example.footballmanager.repositories.PlayerRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PlayerService {
    private final PlayerRepo playerRepo;
    private final TeamService teamService;
    private final PlayerMapping playerMapping;

    private static final String ERR_MSG = "Player not found";

    public PlayerService(PlayerRepo playerRepo, TeamService teamService, PlayerMapping playerMapping) {
        this.playerRepo = playerRepo;
        this.teamService = teamService;
        this.playerMapping = playerMapping;
    }

    public PlayerResponseDto getPlayerInfo(Integer playerId){
        return playerMapping.mapToPlayerResponseDto(findById(playerId));
    }

    @Transactional
    public PlayerResponseDto createNewPlayer(PlayerRequestDto playerRequestDto){
        Player player = new Player();
        player.setName(playerRequestDto.getName());
        player.setAge(playerRequestDto.getAge());
        player.setExperience(playerRequestDto.getExperience());
        player.setTeam(teamService.findTeamByName(playerRequestDto.getTeamName()));

        playerRepo.save(player);
        return playerMapping.mapToPlayerResponseDto(player);

    }

    @Transactional
    public void removePlayerById(Integer playerId){
        playerRepo.deleteById(playerId);
    }

    public String findPlayerName(Player player){
        return playerRepo.findPlayerName(player)
                .orElseThrow(() -> new RecordNotFoundException(ERR_MSG));
    }

    public PlayerResponseDto transferPlayerToAnotherTeam(Integer playerId, Integer teamPurchaserId){
        Player player = findById(playerId);
        Team purchaser = teamService.findById(teamPurchaserId);

        Team seller = player.getTeam();
        Double fullPrice = countFullPrice(player, seller);

        player.setTeam(purchaser);
        seller.setAccount(seller.getAccount() + fullPrice);
        purchaser.setAccount(purchaser.getAccount() - fullPrice);

        return playerMapping.mapToPlayerResponseDto(player);

    }

    public Player findById(Integer playerId){
        return playerRepo.findById(playerId)
                .orElseThrow(() -> new RecordNotFoundException(ERR_MSG));
    }

    private Double countFullPrice(Player player, Team teamSeller){
        Double transferPrice = (player.getExperience() * 100000) / player.getAge();
        Double commissionPrice = (transferPrice / 100) * teamSeller.getCommission();
        return transferPrice + commissionPrice;
    }
}
