package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.converters.PersonConverter;
import com.syncretis.SpringDataProject.dto.DepartmentDTO;
import com.syncretis.SpringDataProject.dto.DocumentDTO;
import com.syncretis.SpringDataProject.dto.LanguageDTO;
import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.entities.Department;
import com.syncretis.SpringDataProject.entities.Document;
import com.syncretis.SpringDataProject.entities.Language;
import com.syncretis.SpringDataProject.entities.Person;
import com.syncretis.SpringDataProject.exceptions.DepartmentNotFoundException;
import com.syncretis.SpringDataProject.exceptions.PersonException;
import com.syncretis.SpringDataProject.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PersonServiceTest {

    @Mock
    PersonRepository personRepository;
    @Mock
    PersonConverter personConverter;

    @InjectMocks
    PersonService personService;

    @BeforeEach
    void openMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("shouldReturnAllPersons")
    void getPersons() {
        //given
        List<Person> people = new ArrayList<>();

        Department department = new Department();
        department.setName("Department of Hurt");

        Document document = new Document();
        document.setNumber("12341234");
        document.setExpireDate(LocalDate.of(2023, 1, 1));

        List<Language> languageList = new ArrayList<>();
        Language language = new Language();
        language.setName("English");
        languageList.add(language);

        Person person = new Person();
        person.setFirstName("Vladimir");
        person.setSecondName("Stavitskii");
        person.setDepartment(department);
        person.setDocument(document);
        person.setLanguageList(languageList);

        people.add(person);

        List<PersonDTO> peopleDto = new ArrayList<>();

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Department of Hurt");

        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setNumber("12341234");
        documentDTO.setExpireDate(LocalDate.of(2023, 1, 1));

        List<LanguageDTO> languageListDTO = new ArrayList<>();
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setName("English");
        languageListDTO.add(languageDTO);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName("Vladimir");
        personDTO.setSecondName("Stavitskii");
        personDTO.setDepartment(departmentDTO);
        personDTO.setDocument(documentDTO);
        personDTO.setLanguageList(languageListDTO);

        peopleDto.add(personDTO);
        //when
        Mockito.when(personRepository.findAll()).thenReturn(people);
        Mockito.when(personConverter.entityToDto(people)).thenReturn(peopleDto);

        List<PersonDTO> actualPersonsDto = personService.getPersons();
        //then
        Mockito.verify(personRepository).findAll();
        Mockito.verify(personConverter).entityToDto(people);
        assertThat(actualPersonsDto).isEqualTo(peopleDto);
    }

    @Test
    @DisplayName("shouldReturnPersonById")
    void testGetPersons() {
        //given
        Department department = new Department();
        department.setName("Department of Hurt");

        Document document = new Document();
        document.setNumber("12341234");
        document.setExpireDate(LocalDate.of(2023, 1, 1));

        List<Language> languageList = new ArrayList<>();
        Language language = new Language();
        language.setName("English");
        languageList.add(language);

        Person person = new Person();
        person.setFirstName("Vladimir");
        person.setSecondName("Stavitskii");
        person.setDepartment(department);
        person.setDocument(document);
        person.setLanguageList(languageList);

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Department of Hurt");

        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setNumber("12341234");
        documentDTO.setExpireDate(LocalDate.of(2023, 1, 1));

        List<LanguageDTO> languageListDTO = new ArrayList<>();
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setName("English");
        languageListDTO.add(languageDTO);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName("Vladimir");
        personDTO.setSecondName("Stavitskii");
        personDTO.setDepartment(departmentDTO);
        personDTO.setDocument(documentDTO);
        personDTO.setLanguageList(languageListDTO);

        //when
        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        Mockito.when(personConverter.entityToDto(person)).thenReturn(personDTO);
        PersonDTO actualPersonDto = personService.getPersons(1L);

        //then
        Mockito.verify(personRepository).findById(1L);
        Mockito.verify(personConverter).entityToDto(person);
        assertThat(actualPersonDto).isEqualTo(personDTO);
    }

    @Test
    @DisplayName("shouldSavePerson")
    void addNewPerson() {
        //given
        Department department = new Department();
        department.setName("Department of Hurt");

        Document document = new Document();
        document.setNumber("12341234");
        document.setExpireDate(LocalDate.of(2023, 1, 1));

        List<Language> languageList = new ArrayList<>();
        Language language = new Language();
        language.setName("English");
        languageList.add(language);

        Person person = new Person();
        person.setFirstName("Vladimir");
        person.setSecondName("Stavitskii");
        person.setDepartment(department);
        person.setDocument(document);
        person.setLanguageList(languageList);

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Department of Hurt");

        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setNumber("12341234");
        documentDTO.setExpireDate(LocalDate.of(2023, 1, 1));

        List<LanguageDTO> languageListDTO = new ArrayList<>();
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setName("English");
        languageListDTO.add(languageDTO);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName("Vladimir");
        personDTO.setSecondName("Stavitskii");
        personDTO.setDepartment(departmentDTO);
        personDTO.setDocument(documentDTO);
        personDTO.setLanguageList(languageListDTO);

        //when
        Mockito.when(personConverter.dtoToEntity(personDTO)).thenReturn(person);
        Mockito.when(personRepository.save(person)).thenReturn(person);

        Person actualPerson = personService.addNewPerson(personDTO);

        //then
        Mockito.verify(personRepository).save(person);
        Mockito.verify(personConverter).dtoToEntity(personDTO);
        assertThat(actualPerson).isEqualTo(person);
    }

    @Test
    @DisplayName("shouldDeletePersonFindById")
    void deletePersons() {
        //given
        Department department = new Department();
        department.setName("Department of Hurt");

        Document document = new Document();
        document.setNumber("12341234");
        document.setExpireDate(LocalDate.of(2023, 1, 1));

        List<Language> languageList = new ArrayList<>();
        Language language = new Language();
        language.setName("English");
        languageList.add(language);

        Person person = new Person();
        person.setFirstName("Vladimir");
        person.setSecondName("Stavitskii");
        person.setDepartment(department);
        person.setDocument(document);
        person.setLanguageList(languageList);

        //when
        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        personService.deletePersons(1L);

        //then
        Mockito.verify(personRepository).findById(1L);
        Mockito.verify(personRepository).deleteById(1L);
    }

    @Test
    @DisplayName("shouldReturnUpdatePerson")
    void updatePerson() {
        //given
        Department department = new Department();
        department.setName("Department of Hurt");

        Document document = new Document();
        document.setNumber("12341234");
        document.setExpireDate(LocalDate.of(2023, 1, 1));

        List<Language> languageList = new ArrayList<>();
        Language language = new Language();
        language.setName("English");
        languageList.add(language);

        Person person = new Person();
        person.setFirstName("Vladimir");
        person.setSecondName("Stavitskii");
        person.setDepartment(department);
        person.setDocument(document);
        person.setLanguageList(languageList);

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Department of Hurt");

        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setNumber("12341234");
        documentDTO.setExpireDate(LocalDate.of(2023, 1, 1));

        List<LanguageDTO> languageListDTO = new ArrayList<>();
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setName("English");
        languageListDTO.add(languageDTO);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName("Vladimir");
        personDTO.setSecondName("Stavitskii");
        personDTO.setDepartment(departmentDTO);
        personDTO.setDocument(documentDTO);
        personDTO.setLanguageList(languageListDTO);

        //when
        Mockito.when(personConverter.dtoToEntity(personDTO)).thenReturn(person);
        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        Mockito.when(personRepository.save(person)).thenReturn(person);

        Person actualDepartment = personService.updatePerson(personDTO, 1L);

        //then
        Mockito.verify(personRepository).save(person);
        Mockito.verify(personRepository).findById(1L);
        Mockito.verify(personConverter).dtoToEntity(personDTO);
        assertThat(actualDepartment).isEqualTo(person);
    }

    @Test
    @DisplayName("shouldThrowNotFoundErrorFindById")
    void getDepartmentsError() throws DepartmentNotFoundException {
        //when
        Mockito.when(personRepository.findById(3L)).thenThrow(new PersonException(HttpStatus.NOT_FOUND));

        //then
        assertThatThrownBy(() -> personService.getPersons(3L))
                .isInstanceOf(PersonException.class);
        Mockito.verify(personRepository).findById(3L);
    }

    @Test
    @DisplayName("shouldThrowNotFoundErrorDeleteById")
    void deleteDepartmentError() {
        //when
        Mockito.when(personRepository.findById(1L)).thenThrow(new PersonException(HttpStatus.NOT_FOUND));

        //then
        assertThatThrownBy(() -> personService.deletePersons(1L))
                .isInstanceOf(PersonException.class);
        Mockito.verify(personRepository).findById(1L);
    }

    @Test
    @DisplayName("shouldThrowNotFoundErrorUpdateById")
    void updateDepartmentError() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Department of Hurt");

        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setNumber("12341234");
        documentDTO.setExpireDate(LocalDate.of(2023, 1, 1));

        List<LanguageDTO> languageListDTO = new ArrayList<>();
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setName("English");
        languageListDTO.add(languageDTO);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName("Vladimir");
        personDTO.setSecondName("Stavitskii");
        personDTO.setDepartment(departmentDTO);
        personDTO.setDocument(documentDTO);
        personDTO.setLanguageList(languageListDTO);
        //when
        Mockito.when(personRepository.findById(1L)).thenThrow(new PersonException(HttpStatus.NOT_FOUND));

        //then
        assertThatThrownBy(() -> personService.updatePerson(personDTO, 1L))
                .isInstanceOf(PersonException.class);
        Mockito.verify(personRepository).findById(1L);
    }

}