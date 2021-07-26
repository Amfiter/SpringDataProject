package com.syncretis.SpringDataProject.configs;

import com.syncretis.SpringDataProject.SpringDataProjectApplication;
import com.syncretis.SpringDataProject.models.Department;
import com.syncretis.SpringDataProject.models.Person;
import com.syncretis.SpringDataProject.repositories.DepartmentRepository;
import com.syncretis.SpringDataProject.repositories.PersonRepository;
import org.hibernate.PersistentObjectException;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Configuration
public class Config {
    Logger log = Logger.getLogger(SpringDataProjectApplication.class.getName());

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository personRepository, DepartmentRepository departmentRepository) {
        return args -> {
            Department defence = new Department(
                    "Department of defence"
            );
            Department anything = new Department(
                    "Department of anything"
            );
            Department seversk = new Department(
                    "Department of seversk"
            );
            Department knowledge = new Department(
                    "Department of knowledge"
            );
            departmentRepository.saveAll(
                    List.of(defence,seversk,anything,knowledge)
            );

            Person nastya = new Person(
                    "Nastya",
                    "Chernichenko",
                    new Date(96,8,4),
                    departmentRepository.getById(4L)
            );
            Person serega = new Person(
                    "Serega",
                    "Filatov",
                    new Date(96,8,28),
                    departmentRepository.getById(2L)
            );
            Person nikita = new Person(
                    "Nikita",
                    "Zheksenov",
                    new Date(96,8,28),
                    departmentRepository.getById(1L)
            );
            Person kostya = new Person(
                    "Kostya",
                    "Pestekhin",
                    new Date(78,8,28),
                    departmentRepository.getById(3L)
            );

            personRepository.saveAll(
                    List.of(kostya,nikita,serega,nastya)
            );

//            personRepository.deleteById(1L);
//            departmentRepository.deleteById(1L);
//
//            Optional<Person> personById = personRepository.findById(1L);
//            Optional<Department> departmentById = departmentRepository.findById(1L);
//             log.info(departmentById.toString());
//             log.info(personById.toString());

//            Department updateDepartment = departmentRepository.save(new Department(2L,"Department of anything2"));
//            Person updatePerson = personRepository.save(new Person(3L,"Nikita","Zheksenov", new java.sql.Date(System.currentTimeMillis()),updateDepartment));
//            log.info(updatePerson.toString());
        };

    }
}
