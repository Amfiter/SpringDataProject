package com.syncretis.SpringDataProject.repositories;

import com.syncretis.SpringDataProject.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
