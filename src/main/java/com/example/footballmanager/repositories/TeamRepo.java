package com.example.footballmanager.repositories;

import com.example.footballmanager.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeamRepo extends JpaRepository<Team, Integer> {

    Optional<Team> findTeamByTeamName(String teamName);

    @Query("select t.teamName from Team t where t = :team")
    Optional<String> findTeamName(Team team);
}
