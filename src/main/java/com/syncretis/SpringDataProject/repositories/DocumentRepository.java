package com.syncretis.SpringDataProject.repositories;

import com.syncretis.SpringDataProject.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document,String> {
    Document getById(String id);
}
