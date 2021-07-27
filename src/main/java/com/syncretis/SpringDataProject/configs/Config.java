package com.syncretis.SpringDataProject.configs;

import com.syncretis.SpringDataProject.SpringDataProjectApplication;
import com.syncretis.SpringDataProject.models.Department;
import com.syncretis.SpringDataProject.models.Document;
import com.syncretis.SpringDataProject.models.Language;
import com.syncretis.SpringDataProject.models.Person;
import com.syncretis.SpringDataProject.repositories.DepartmentRepository;
import com.syncretis.SpringDataProject.repositories.DocumentRepository;
import com.syncretis.SpringDataProject.repositories.LanguageRepository;
import com.syncretis.SpringDataProject.repositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Configuration
public class Config {
    Logger log = Logger.getLogger(SpringDataProjectApplication.class.getName());

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository personRepository,
                                        DepartmentRepository departmentRepository,
                                        LanguageRepository languageRepository,
                                        DocumentRepository documentRepository
    ) {
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
                    List.of(defence, seversk, anything, knowledge)
            );

            Language english = new Language(
                    "English"
            );
            Language dutch = new Language(
                    "Dautch"
            );
            Language kyrgyz = new Language(
                    "Kyrgyz"
            );
            Language russian = new Language(
                    "Russian"
            );
            languageRepository.saveAll(
                    List.of(english, dutch, kyrgyz, russian)
            );
            Document diploma = new Document(
                    "1234567890",
                    new Date(122, 12, 4)
            );
            Document passport = new Document(
                    "0987654321",
                    new Date(123, 8, 28)
            );
            Document passport1 = new Document(
                    "123441234",
                    new Date(123, 8, 28)
            );
            Document passport2 = new Document(
                    "9876573342",
                    new Date(123, 8, 28)
            );
            Document newDoc = new Document(
                    "новый документ",
                    new Date(123, 8, 28)
            );
            documentRepository.saveAll(
                    List.of(diploma, passport, passport1, passport2, newDoc)
            );
            Person nastya = new Person(
                    "Nastya",
                    "Chernichenko",
                    new Date(96, 8, 4),
                    departmentRepository.getById(4L),
                    List.of(languageRepository.getById(1L), languageRepository.getById(2L)),
                    documentRepository.findAll().get(0)
            );
            Person serega = new Person(
                    "Serega",
                    "Filatov",
                    new Date(96, 8, 4),
                    departmentRepository.getById(2L),
                    List.of(english, dutch),
                    documentRepository.findAll().get(1)

            );
            Person nikita = new Person(
                    "Nikita",
                    "Zheksenov",
                    new Date(96, 8, 28),
                    departmentRepository.getById(1L),
                    List.of(kyrgyz),
                    documentRepository.findAll().get(2)
            );
            Person kostya = new Person(
                    "Kostya",
                    "Pestekhin",
                    new Date(78, 8, 28),
                    departmentRepository.getById(3L),
                    List.of(russian),
                    documentRepository.findAll().get(4)
            );

            personRepository.saveAll(
                    List.of(kostya, nikita, serega, nastya)
            );

//            Language english1 = languageRepository.findByName("English");
//            log.info(english1.getPersons().toString());
//            personRepository.deleteById(1L);
//            departmentRepository.deleteById(1L);
//
//            Optional<Person> personById = personRepository.findById(1L);
//            Optional<Department> departmentById = departmentRepository.findById(1L);
//             log.info(departmentById.toString());
//             log.info(personById.toString());
//            SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Date myDate = textFormat.parse("2023-09-05");
//            Department updateDepartment = departmentRepository.save(new Department(2L, "Department of anything2"));
//            Document updateDocument = documentRepository.save(new Document(
//                    "i snova zdravstvuy,snova kak dela2",
//                    myDate
//            ));
//
//            Person updatePerson = personRepository.save(
//                    new Person(3L,
//                            "Vladimir",
//                            "Shagalov",
//                            new java.sql.Date(System.currentTimeMillis()),
//                            updateDepartment,
//                            List.of(languageRepository.getById(1L)),
//                            documentRepository.findAll().get(5)
//                    ));
//            documentRepository.deleteById(documentRepository.findAll().get(4).getId());
//            personRepository.deleteById(1L);
//            log.info(documentRepository.findAll().get(4).toString());
//            log.info(updatePerson.toString());
//            log.info(documentRepository.findAll().get(1).getId());

        };

    }
}
