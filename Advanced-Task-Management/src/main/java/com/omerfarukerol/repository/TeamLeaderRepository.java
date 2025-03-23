package com.omerfarukerol.repository;

import com.omerfarukerol.entities.TeamLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamLeaderRepository extends JpaRepository<TeamLeader, Long> {
    Optional<TeamLeader> findByUsername(String username);
} 