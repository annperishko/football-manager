package com.example.footballmanager.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(nullable = false, name = "name")
    private String teamName;
    @Column(nullable = false, name = "account")
    private Double account;
    @Column(nullable = false, name = "commission")
    private Integer commission;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE})
    private Set<Player> players = new HashSet<>();
}
