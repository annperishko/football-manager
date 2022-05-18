package com.example.footballmanager.repositories;

import com.example.footballmanager.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamRepo extends JpaRepository<Team, Integer> {

    Optional<Team> findTeamByTeamName(String teamName);

    @Query("select t.teamName from Team t where t = :team")
    Optional<String> findTeamName(Team team);

    @Query("select distinct t from Team t order by t.teamName asc")
    List<Team> findAllTeams();
}
