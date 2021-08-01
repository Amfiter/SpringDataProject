package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.converters.LanguageConverter;
import com.syncretis.SpringDataProject.converters.PersonConverter;
import com.syncretis.SpringDataProject.dto.LanguageDTO;
import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.exceptions.LanguageException;
import com.syncretis.SpringDataProject.exceptions.PersonException;
import com.syncretis.SpringDataProject.models.Language;
import com.syncretis.SpringDataProject.models.Person;
import com.syncretis.SpringDataProject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private PersonConverter personConverter = new PersonConverter();

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> getPersons() {
        List<Person> listPerson = personRepository.findAll();
        List<PersonDTO> personDTOS = personConverter.entityToDto(listPerson);
        return personDTOS;
    }

    public PersonDTO getPersons(Long personId) {
        Person listPerson = personRepository.findById(personId).orElseThrow(() -> new PersonException(HttpStatus.NOT_FOUND));
        PersonDTO personDTOS = personConverter.entityToDto(listPerson);
        return personDTOS;
    }

    public void addNewPerson(PersonDTO personDTO) {
        Person person = personConverter.dtoToEntity(personDTO);
        personRepository.save(person);
    }

    public void deletePersons(Long id) {
        boolean personExists = personRepository.existsById(id);
        if (!personExists) {
            throw new IllegalStateException("person with id = " + id + " does't exist ");
        } else {
            personRepository.deleteById(id);
        }
    }

    public PersonDTO updatePerson(PersonDTO newPerson, Long id) {
        Person personEntity = personConverter.dtoToEntity(newPerson);
        PersonDTO personDTO = personConverter.entityToDto(personRepository.findById(id)
                .map(person -> {
                    person.setFirstName(personEntity.getFirstName());
                    person.setSecondName(personEntity.getSecondName());
                    person.setBirthday(personEntity.getBirthday());
                    person.setDepartment(personEntity.getDepartment());
                    person.setDocument(personEntity.getDocument());
                    person.setLanguageList(personEntity.getLanguageList());
                    return personRepository.save(person);
                })
                .orElseThrow(() -> new PersonException(HttpStatus.BAD_REQUEST)));
        return personDTO;
    }

}
