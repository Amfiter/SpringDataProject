package com.syncretis.SpringDataProject.repositories;

import com.syncretis.SpringDataProject.models.Department;
import com.syncretis.SpringDataProject.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    List<Department> findByName(@Param("name") String name);
}