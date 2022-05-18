package com.example.footballmanager.repositories;

import com.example.footballmanager.entities.Player;
import com.example.footballmanager.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayerRepo extends JpaRepository<Player, Integer> {

    @Query("select p.name from Player p where p = :player")
    Optional<String> findPlayerName(Player player);

    @Query("select distinct p.name from Player p where p.team =: team order by p.name asc ")
    List<String> findAllNamesByTeam(Team team);

    @Query("select distinct p from Player p order by p.team.teamName asc, p.name asc ")
    List<Player> findAllSortedByTeam();
}
