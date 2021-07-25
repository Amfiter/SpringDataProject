/*
package com.syncretis.SpringDataProject.configs;

import com.syncretis.SpringDataProject.models.Department;
import com.syncretis.SpringDataProject.repositories.DepartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DepartmentConfig {

    @Bean
    CommandLineRunner commandLineRunner(DepartmentRepository departmentRepository) {
        return args -> {
            Department hurt = new Department(
                    "Department of hurt"
            );
            Department adgesia = new Department(
                    "Department of Adgesia"
            );
            departmentRepository.saveAll(
                    List.of(hurt, adgesia)
            );
        };
    }

}
*/
