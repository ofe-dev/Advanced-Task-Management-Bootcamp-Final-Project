package com.omerfarukerol.repository;

import com.omerfarukerol.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Modifying
    @Query("UPDATE Comment c SET c.isDeleted = true WHERE c.id = :commentId")
    void softDelete(Long commentId);
    
    @Query("SELECT c FROM Comment c WHERE c.id = :commentId AND c.isDeleted = false")
    Optional<Comment> findByIdAndNotDeleted(Long commentId);
} 