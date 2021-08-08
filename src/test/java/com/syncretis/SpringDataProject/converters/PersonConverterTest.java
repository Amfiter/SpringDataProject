package com.syncretis.SpringDataProject.converters;

import com.syncretis.SpringDataProject.dto.DepartmentDTO;
import com.syncretis.SpringDataProject.dto.DocumentDTO;
import com.syncretis.SpringDataProject.dto.LanguageDTO;
import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.entities.Department;
import com.syncretis.SpringDataProject.entities.Document;
import com.syncretis.SpringDataProject.entities.Language;
import com.syncretis.SpringDataProject.entities.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PersonConverterTest {

    private final PersonConverter personConverter = new PersonConverter();

    @Test
    @DisplayName("shouldConvertEntityToDto")
    void entityToDto() {
        //given
        Department department = new Department();
        department.setId(1L);
        department.setName("Department of test");

        Document document = new Document();
        document.setId("2c91948d7b101e2e017b102165920000");
        document.setNumber("12341234");

        List<Language> languageList = new ArrayList<>();

        Language english = new Language();
        english.setId(1L);
        english.setName("English");

        Language dutch = new Language();
        dutch.setId(2L);
        dutch.setName("Dutch");

        languageList.add(english);
        languageList.add(dutch);

        Person person = new Person();
        person.setId(12L);
        person.setFirstName("Vladimir");
        person.setSecondName("Stavitskii");
        person.setBirthday(LocalDate.of(1997,4,30));
        person.setDepartment(department);
        person.setDocument(document);
        person.setLanguageList(languageList);

        //when
        PersonDTO actual  = personConverter.entityToDto(person);

        //then
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);
        departmentDTO.setName("Department of test");

        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId("2c91948d7b101e2e017b102165920000");
        documentDTO.setNumber("12341234");

        List<LanguageDTO> languageListDTO = new ArrayList<>();

        LanguageDTO englishDTO = new LanguageDTO();
        englishDTO.setId(1L);
        englishDTO.setName("English");

        LanguageDTO dutchDTO = new LanguageDTO();
        dutchDTO.setId(2L);
        dutchDTO.setName("Dutch");

        languageListDTO.add(englishDTO);
        languageListDTO.add(dutchDTO);

        PersonDTO expected = new PersonDTO();
        expected.setId(12L);
        expected.setFirstName("Vladimir");
        expected.setSecondName("Stavitskii");
        expected.setBirthday(LocalDate.of(1997,4,30));
        expected.setDepartment(departmentDTO);
        expected.setDocument(documentDTO);
        expected.setLanguageList(languageListDTO);

//        Mockito.verify(departmentConverter,Mockito.times(1));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("shouldConvertDtoToEntity")
    void dtoToEntity() {

    }

    @Test
    @DisplayName("shouldConvertEntityListToDtoList")
    void entityListToDtoList() {
    }

    @Test
    @DisplayName("shouldConvertDtoListToEntityList")
    void dtoListToEntityList() {
    }
}