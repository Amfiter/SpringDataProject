package com.syncretis.SpringDataProject.repositories;

import com.syncretis.SpringDataProject.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language,Long> {
}

