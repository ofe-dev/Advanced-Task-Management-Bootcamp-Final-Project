package com.omerfarukerol.repository;

import com.omerfarukerol.entities.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    
    @Query("SELECT a FROM Attachment a WHERE a.id = :id AND a.isDeleted = false")
    Optional<Attachment> findByIdAndNotDeleted(Long id);
    
    @Modifying
    @Query("UPDATE Attachment a SET a.isDeleted = true WHERE a.id = :id")
    void softDelete(Long id);
} 