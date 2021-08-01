package com.syncretis.SpringDataProject.converters;

import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.models.Person;

import java.util.List;
import java.util.stream.Collectors;

public class PersonConverter {

    private DepartmentConverter departmentConverter = new DepartmentConverter();
    private DocumentConverter documentConverter = new DocumentConverter();
    private LanguageConverter languageConverter = new LanguageConverter();

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
        person.setDepartment(departmentConverter.dtoToEntity(personDTO.getDepartment()));
        person.setDocument(documentConverter.dtoToEntity(personDTO.getDocument()));
        person.setLanguageList(languageConverter.dtoToEntity(personDTO.getLanguageList()));
        return person;
    }

    public List<Person> dtoToEntity(List<PersonDTO> personDTO) {
        return personDTO.stream().map(DTO -> dtoToEntity(DTO)).collect(Collectors.toList());
    }
}
