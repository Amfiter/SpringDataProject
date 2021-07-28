package com.syncretis.SpringDataProject.repositories;

import com.syncretis.SpringDataProject.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {
    Document getById(String id);
}
