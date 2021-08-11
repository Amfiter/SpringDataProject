package com.syncretis.SpringDataProject.converters;

import com.syncretis.SpringDataProject.dto.DepartmentDTO;
import com.syncretis.SpringDataProject.dto.DocumentDTO;
import com.syncretis.SpringDataProject.dto.LanguageDTO;
import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.entities.Department;
import com.syncretis.SpringDataProject.entities.Document;
import com.syncretis.SpringDataProject.entities.Language;
import com.syncretis.SpringDataProject.entities.Person;
import com.syncretis.SpringDataProject.services.DepartmentService;
import com.syncretis.SpringDataProject.services.DocumentService;
import com.syncretis.SpringDataProject.services.LanguageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;

class PersonConverterTest {
    @Mock
    DepartmentConverter departmentConverter ;
    @Mock
    DocumentConverter documentConverter;
    @Mock
    LanguageConverter languageConverter;
    @Mock
    DepartmentService departmentService;
    @Mock
    DocumentService documentService;
    @Mock
    LanguageService languageService;

    @InjectMocks
    private PersonConverter personConverter;

    @BeforeEach
    void openMocks(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("shouldConvertEntityToDto")
    void entityToDto() {
        //given
        List<Language> languageList = new ArrayList<>();

        Person person = new Person();
        person.setId(12L);
        person.setFirstName("Vladimir");
        person.setSecondName("Stavitskii");
        person.setBirthday(LocalDate.of(1997,4,30));
        person.setDepartment(new Department());
        person.setDocument(new Document());
        person.setLanguageList(languageList);

        //when
        DepartmentDTO departmentDTO  = new DepartmentDTO();
        departmentDTO.setName("Department of death");

        DocumentDTO documentDTO  = new DocumentDTO();
        documentDTO.setNumber("123456789");
        documentDTO.setExpireDate(LocalDate.of(2020,2,5));

        List<LanguageDTO> languagesDTOS = new ArrayList<>();
        LanguageDTO english = new LanguageDTO();
        english.setName("English");
        languagesDTOS.add(english);

        Mockito.when(departmentConverter.entityToDto(any(Department.class))).thenReturn(departmentDTO);
        Mockito.when(documentConverter.entityToDto(any(Document.class))).thenReturn(documentDTO);
        Mockito.when(languageConverter.entityToDto(languageList)).thenReturn(languagesDTOS);
        PersonDTO actual  = personConverter.entityToDto(person);

        //then
        PersonDTO expected = new PersonDTO();
        expected.setId(12L);
        expected.setFirstName("Vladimir");
        expected.setSecondName("Stavitskii");
        expected.setBirthday(LocalDate.of(1997,4,30));
        expected.setDepartment(departmentDTO);
        expected.setDocument(documentDTO);
        expected.setLanguageList(languagesDTOS);

        Mockito.verify(departmentConverter,times(1)).entityToDto(new Department());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("shouldConvertDtoToEntity")
    void dtoToEntity() {
        //given
        List<LanguageDTO> languageList = new ArrayList<>();

        PersonDTO person = new PersonDTO();
        person.setId(12L);
        person.setFirstName("Vladimir");
        person.setSecondName("Stavitskii");
        person.setBirthday(LocalDate.of(1997,4,30));
        person.setDepartment(new DepartmentDTO());
        person.setDocument(new DocumentDTO());
        person.setLanguageList(languageList);

        //when
        Department department  = new Department();
        department.setName("Department of death");

        Document document  = new Document();
        document.setNumber("123456789");
        document.setExpireDate(LocalDate.of(2020,2,5));

        List<Language> languages = new ArrayList<>();
        Language english = new Language();
        english.setName("English");
        languages.add(english);

        Mockito.when(departmentService.checkAndReturnDepartment(any(PersonDTO.class))).thenReturn(department);
        Mockito.when(documentService.checkAndReturnDocument(any(PersonDTO.class))).thenReturn(document);
        Mockito.when(languageService.checkAndReturnLanguage(any(PersonDTO.class))).thenReturn(languages);
        Person actual  = personConverter.dtoToEntity(person);

        //then
        Person expected = new Person();
        expected.setId(12L);
        expected.setFirstName("Vladimir");
        expected.setSecondName("Stavitskii");
        expected.setBirthday(LocalDate.of(1997,4,30));
        expected.setDepartment(department);
        expected.setDocument(document);
        expected.setLanguageList(languages);

        Mockito.verify(departmentService,times(1)).checkAndReturnDepartment(person);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("shouldConvertEntityListToDtoList")
    void entityListToDtoList() {
        //given
        List<PersonDTO> personListDTO = new ArrayList<>();
        List<LanguageDTO> languageList = new ArrayList<>();

        PersonDTO person = new PersonDTO();
        person.setId(12L);
        person.setFirstName("Vladimir");
        person.setSecondName("Stavitskii");
        person.setBirthday(LocalDate.of(1997,4,30));
        person.setDepartment(new DepartmentDTO());
        person.setDocument(new DocumentDTO());
        person.setLanguageList(languageList);

        personListDTO.add(person);

        //when
        Department department  = new Department();
        department.setName("Department of death");

        Document document  = new Document();
        document.setNumber("123456789");
        document.setExpireDate(LocalDate.of(2020,2,5));

        List<Language> languages = new ArrayList<>();
        Language english = new Language();
        english.setName("English");
        languages.add(english);

        Mockito.when(departmentService.checkAndReturnDepartment(any(PersonDTO.class))).thenReturn(department);
        Mockito.when(documentService.checkAndReturnDocument(any(PersonDTO.class))).thenReturn(document);
        Mockito.when(languageService.checkAndReturnLanguage(any(PersonDTO.class))).thenReturn(languages);
        List<Person> actual  = personConverter.dtoToEntity(personListDTO);

        //then
        List<Person> expectedList = new ArrayList<>();

        Person expected = new Person();
        expected.setId(12L);
        expected.setFirstName("Vladimir");
        expected.setSecondName("Stavitskii");
        expected.setBirthday(LocalDate.of(1997,4,30));
        expected.setDepartment(department);
        expected.setDocument(document);
        expected.setLanguageList(languages);

        expectedList.add(expected);

        Mockito.verify(departmentService,times(1)).checkAndReturnDepartment(person);

        assertThat(actual).isEqualTo(expectedList);
    }

    @Test
    @DisplayName("shouldConvertDtoListToEntityList")
    void dtoListToEntityList() {
        //given
        List<PersonDTO> personDTOList = new ArrayList<>();
        List<LanguageDTO> languageList = new ArrayList<>();

        PersonDTO person = new PersonDTO();
        person.setId(12L);
        person.setFirstName("Vladimir");
        person.setSecondName("Stavitskii");
        person.setBirthday(LocalDate.of(1997,4,30));
        person.setDepartment(new DepartmentDTO());
        person.setDocument(new DocumentDTO());
        person.setLanguageList(languageList);

        personDTOList.add(person);

        //when
        Department department  = new Department();
        department.setName("Department of death");

        Document document  = new Document();
        document.setNumber("123456789");
        document.setExpireDate(LocalDate.of(2020,2,5));

        List<Language> languages = new ArrayList<>();
        Language english = new Language();
        english.setName("English");
        languages.add(english);

        Mockito.when(departmentService.checkAndReturnDepartment(any(PersonDTO.class))).thenReturn(department);
        Mockito.when(documentService.checkAndReturnDocument(any(PersonDTO.class))).thenReturn(document);
        Mockito.when(languageService.checkAndReturnLanguage(any(PersonDTO.class))).thenReturn(languages);
        List<Person> actual  = personConverter.dtoToEntity(personDTOList);

        //then
        List<Person> expectedList = new ArrayList<>();
        Person expected = new Person();
        expected.setId(12L);
        expected.setFirstName("Vladimir");
        expected.setSecondName("Stavitskii");
        expected.setBirthday(LocalDate.of(1997,4,30));
        expected.setDepartment(department);
        expected.setDocument(document);
        expected.setLanguageList(languages);

        expectedList.add(expected);

        Mockito.verify(departmentService,times(1)).checkAndReturnDepartment(person);

        assertThat(actual).isEqualTo(expectedList);
    }


}