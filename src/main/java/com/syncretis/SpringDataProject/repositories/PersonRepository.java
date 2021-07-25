package com.syncretis.SpringDataProject.repositories;

import com.syncretis.SpringDataProject.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
}
