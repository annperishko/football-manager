package com.example.footballmanager.repositories;

import com.example.footballmanager.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepo extends JpaRepository<Player, Integer> {

    @Query("select distinct p from Player p order by p.team.teamName asc, p.name asc ")
    List<Player> findAllSortedByTeam();
}
