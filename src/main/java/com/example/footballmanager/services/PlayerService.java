package com.example.footballmanager.services;

import com.example.footballmanager.dto.request.PlayerRequestDto;
import com.example.footballmanager.dto.response.PlayerResponseDto;
import com.example.footballmanager.entities.Player;
import com.example.footballmanager.entities.Team;
import com.example.footballmanager.exceptions.ForbiddenModificationException;
import com.example.footballmanager.exceptions.RecordNotFoundException;
import com.example.footballmanager.mapping.PlayerMapping;
import com.example.footballmanager.repositories.PlayerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepo playerRepo;
    private final TeamService teamService;
    private final PlayerMapping playerMapping;

    private static final String ERR_MSG = "Player not found";
    private static final String ERR_MSG_FORBIDDEN = "Team can`t be changed";

    public PlayerResponseDto getPlayerInfo(Integer playerId) {
        return playerMapping.mapToPlayerResponseDto(findById(playerId));
    }

    public List<PlayerResponseDto> findAll() {
        List<Player> allPlayers = playerRepo.findAllSortedByTeam();
        return allPlayers.stream()
                .map(playerMapping::mapToPlayerResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public PlayerResponseDto createNewPlayer(PlayerRequestDto playerRequestDto) {
        Player player = new Player();
        player.setName(playerRequestDto.getName());
        player.setAge(playerRequestDto.getAge());
        player.setExperience(playerRequestDto.getExperience());
        player.setTeam(teamService.findTeamByName(playerRequestDto.getTeamName()));

        playerRepo.save(player);
        return playerMapping.mapToPlayerResponseDto(player);

    }

    @Transactional
    public void deletePlayerById(Integer playerId) {
        playerRepo.deleteById(playerId);
    }

    @Transactional
    public PlayerResponseDto transferPlayerToAnotherTeam(Integer playerId, Integer teamPurchaserId) {
        Player player = findById(playerId);
        Team purchaser = teamService.findById(teamPurchaserId);

        Team seller = player.getTeam();
        Double fullPrice = countFullPrice(player, seller);

        player.setTeam(purchaser);
        seller.setAccount(seller.getAccount() + fullPrice);
        purchaser.setAccount(purchaser.getAccount() - fullPrice);

        return playerMapping.mapToPlayerResponseDto(player);

    }

    @Transactional
    public PlayerResponseDto updatePlayerInfo(Integer playerId, PlayerRequestDto playerRequestDto) {
        Player player = findById(playerId);
        if (!playerRequestDto.getTeamName().equals(teamService.findNameOfTeam(player.getTeam()))) {
            throw new ForbiddenModificationException(ERR_MSG_FORBIDDEN);
        }
        player.setName(playerRequestDto.getName());
        player.setExperience(playerRequestDto.getExperience());
        player.setAge(playerRequestDto.getAge());

        return playerMapping.mapToPlayerResponseDto(player);

    }

    public Player findById(Integer playerId) {
        return playerRepo.findById(playerId)
                .orElseThrow(() -> new RecordNotFoundException(ERR_MSG));
    }

    private Double countFullPrice(Player player, Team teamSeller) {
        Double transferPrice = (player.getExperience() * 100000) / player.getAge();
        Double commissionPrice = (transferPrice / 100) * teamSeller.getCommission();
        return transferPrice + commissionPrice;
    }

}
