package com.syncretis.SpringDataProject.repositories;

import com.syncretis.SpringDataProject.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByName(@Param("name") String name);
}
