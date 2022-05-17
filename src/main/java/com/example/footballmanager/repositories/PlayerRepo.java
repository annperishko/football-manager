package com.example.footballmanager.repositories;

import com.example.footballmanager.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlayerRepo extends JpaRepository<Player, Integer> {

    @Query("select p.name from Player p where p = :player")
    Optional<String> findPlayerName(Player player);

}
