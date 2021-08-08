package com.syncretis.SpringDataProject.repositories;

import com.syncretis.SpringDataProject.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {
    Document getById(String id);

    Optional<Document> findByNumber(String number);
}
