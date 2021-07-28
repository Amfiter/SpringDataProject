package com.syncretis.SpringDataProject.repositories;

import com.syncretis.SpringDataProject.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByFirstName(@Param("name") String name);

}
