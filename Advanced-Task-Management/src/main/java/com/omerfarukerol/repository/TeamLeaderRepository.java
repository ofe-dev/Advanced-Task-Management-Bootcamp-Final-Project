package com.omerfarukerol.repository;

import com.omerfarukerol.entities.TeamLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamLeaderRepository extends JpaRepository<TeamLeader, Long> {
} 