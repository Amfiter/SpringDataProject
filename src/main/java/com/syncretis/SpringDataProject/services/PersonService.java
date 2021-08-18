package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.converters.PersonConverter;
import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.entities.Person;
import com.syncretis.SpringDataProject.exceptions.PersonException;
import com.syncretis.SpringDataProject.repositories.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;

    public PersonService(PersonConverter personConverter, PersonRepository personRepository) {
        this.personConverter = personConverter;
        this.personRepository = personRepository;
    }

    public List<PersonDTO> getPersons() {
        List<Person> personList = personRepository.findAll();
        List<PersonDTO> personDTOList = personConverter.entityToDto(personList);
        return personDTOList;
    }

    public PersonDTO getPersons(Long personId) {
        Person listPerson = personRepository.findById(personId).orElseThrow(() -> new PersonException(HttpStatus.NOT_FOUND));
        PersonDTO personDTOS = personConverter.entityToDto(listPerson);
        return personDTOS;
    }

    public Person addNewPerson(PersonDTO personDTO) {
        Person person = personConverter.dtoToEntity(personDTO);
        return personRepository.save(person);
    }

    public void deletePersons(Long id) {
        personRepository.findById(id).orElseThrow(() -> new PersonException(HttpStatus.NOT_FOUND));
        personRepository.deleteById(id);
    }

    public Person updatePerson(PersonDTO newPerson, Long id) {
        Person personEntity = personConverter.dtoToEntity(newPerson);
        Optional<Person> optionalPerson = personRepository.findById(id);
        Person person = optionalPerson.orElseThrow(() -> new PersonException(HttpStatus.NOT_FOUND));
        person.setFirstName(personEntity.getFirstName());
        person.setSecondName(personEntity.getSecondName());
        person.setBirthday(personEntity.getBirthday());
        person.setDepartment(personEntity.getDepartment());
        person.setDocument(personEntity.getDocument());
        person.setLanguageList(personEntity.getLanguageList());
        return personRepository.save(person);
    }
}
