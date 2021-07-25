package com.syncretis.SpringDataProject.configs;

import com.syncretis.SpringDataProject.models.Department;
import com.syncretis.SpringDataProject.models.Person;
import com.syncretis.SpringDataProject.repositories.DepartmentRepository;
import com.syncretis.SpringDataProject.repositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PersonConfig {

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository repository){
        return args -> {
            Person nastya = new Person(
                    1l,
                    "Nastya",
                    "Chernichenko",
                    new java.sql.Date(96,8,4),
                    new Department(1L,"Department of hurt")
            );
            Person yuliya = new Person(
                    2l,
                    "Serega",
                    "Filatov",
                    new java.sql.Date(96,8,28),
                    new Department(2L,"Department of POSHEL NAHUI KAZEL EBANII <3")
            );
            repository.saveAll(
                    List.of(nastya,yuliya)
            );
        };
    }
//    @Bean
//    CommandLineRunner commandLineRunner(DepartmentRepository departmentRepository) {
//        return args -> {
//            Department hurt = new Department(
//                    "Department of hurt"
//            );
//            Department adgesia = new Department(
//                    "Department of Adgesia"
//            );
//            departmentRepository.saveAll(
//                    List.of(hurt, adgesia)
//            );
//        };
//    }
}
