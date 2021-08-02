package com.syncretis.SpringDataProject.converters;

import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.models.Department;
import com.syncretis.SpringDataProject.models.Document;
import com.syncretis.SpringDataProject.models.Person;
import com.syncretis.SpringDataProject.repositories.DepartmentRepository;
import com.syncretis.SpringDataProject.repositories.DocumentRepository;
import com.syncretis.SpringDataProject.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PersonConverter {

    @Autowired
    private DepartmentConverter departmentConverter;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private DocumentConverter documentConverter;
    @Autowired
    private LanguageConverter languageConverter;

    public PersonDTO entityToDto(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setSecondName(person.getSecondName());
        personDTO.setBirthday(person.getBirthday());
        personDTO.setDepartment(departmentConverter.entityToDto(person.getDepartment()));
        personDTO.setDocument(documentConverter.entityToDto(person.getDocument()));
        personDTO.setLanguageList(languageConverter.entityToDto(person.getLanguageList()));
        return personDTO;
    }

    public List<PersonDTO> entityToDto(List<Person> person) {
        return person.stream().map(personEntity -> entityToDto(personEntity)).collect(Collectors.toList());
    }

    public Person dtoToEntity(PersonDTO personDTO) {
        Person person = new Person();
        Department newDep;
        if(personDTO.getDepartment().getId() != null) {
            Optional<Department> optional = departmentRepository.findById(personDTO.getDepartment().getId());
            if(optional.isPresent()) {
                newDep = optional.get();
            } else {
                newDep = new Department();
            }
        } else {
            newDep = new Department();
        }
        newDep.setId(personDTO.getDepartment().getId());
        newDep.setName(personDTO.getDepartment().getName());

        Document newDoc;
        if(personDTO.getDocument().getId() != null) {
            Optional<Document> optional = documentRepository.findById(personDTO.getDocument().getId());
            if(optional.isPresent()) {
                newDoc = optional.get();
            } else {
                newDoc = new Document();
            }
        } else {
            newDoc = new Document();
        }
        newDoc.setId(personDTO.getDocument().getId());
        newDoc.setNumber(personDTO.getDocument().getNumber());


        person.setId(personDTO.getId());
        person.setFirstName(personDTO.getFirstName());
        person.setSecondName(personDTO.getSecondName());
        person.setBirthday(personDTO.getBirthday());
        person.setDepartment(newDep);
        person.setDocument(newDoc);
        person.setLanguageList(null);
        return person;
    }

    public List<Person> dtoToEntity(List<PersonDTO> personDTO) {
        return personDTO.stream().map(DTO -> dtoToEntity(DTO)).collect(Collectors.toList());
    }
}
