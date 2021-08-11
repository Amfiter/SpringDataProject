package com.syncretis.SpringDataProject.converters;

import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.entities.Person;
import com.syncretis.SpringDataProject.services.DepartmentService;
import com.syncretis.SpringDataProject.services.DocumentService;
import com.syncretis.SpringDataProject.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonConverter {

    private final DepartmentService departmentService;
    private final DocumentService documentService;
    private final LanguageService languageService;

    private final DepartmentConverter departmentConverter;
    private final DocumentConverter documentConverter;
    private final LanguageConverter languageConverter;

    public PersonConverter(DepartmentConverter departmentConverter, DocumentConverter documentConverter, LanguageConverter languageConverter, DepartmentService departmentService, DocumentService documentService, LanguageService languageService) {
        this.departmentConverter = departmentConverter;
        this.documentConverter = documentConverter;
        this.languageConverter = languageConverter;
        this.departmentService = departmentService;
        this.documentService = documentService;
        this.languageService = languageService;
    }

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
        person.setId(personDTO.getId());
        person.setFirstName(personDTO.getFirstName());
        person.setSecondName(personDTO.getSecondName());
        person.setBirthday(personDTO.getBirthday());
        person.setDepartment(departmentService.checkAndReturnDepartment(personDTO));
        person.setDocument(documentService.checkAndReturnDocument(personDTO));
        person.setLanguageList(languageService.checkAndReturnLanguage(personDTO));
        return person;
    }

    public List<Person> dtoToEntity(List<PersonDTO> personDTO) {
        return personDTO.stream().map(DTO -> dtoToEntity(DTO)).collect(Collectors.toList());
    }
}
