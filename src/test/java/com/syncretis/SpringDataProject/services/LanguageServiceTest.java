package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.converters.LanguageConverter;
import com.syncretis.SpringDataProject.dto.DepartmentDTO;
import com.syncretis.SpringDataProject.dto.DocumentDTO;
import com.syncretis.SpringDataProject.dto.LanguageDTO;
import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.entities.Department;
import com.syncretis.SpringDataProject.entities.Language;
import com.syncretis.SpringDataProject.exceptions.LanguageException;
import com.syncretis.SpringDataProject.repositories.LanguageRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith({MockitoExtension.class})
class LanguageServiceTest {

    @Mock
    LanguageRepository languageRepository;
    @Mock
    LanguageConverter languageConverter;

    @InjectMocks
    LanguageService languageService;

    @Test
    @DisplayName("shouldReturnAllLanguages")
    void getLanguages() {
        //given
        List<Language> languageList = new ArrayList<>();
        Language language = new Language();
        language.setName("English");
        languageList.add(language);

        List<LanguageDTO> languageDTOList = new ArrayList<>();
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setName("English");
        languageDTOList.add(languageDTO);

        //when
        Mockito.when(languageRepository.findAll()).thenReturn(languageList);
        Mockito.when(languageConverter.entityToDto(languageList)).thenReturn(languageDTOList);
        List<LanguageDTO> actualLanguagesDto = languageService.getLanguages();

        //then
        Mockito.verify(languageRepository).findAll();
        Mockito.verify(languageConverter).entityToDto(languageList);
        assertThat(actualLanguagesDto).isEqualTo(languageDTOList);
    }

    @Test
    @DisplayName("shouldReturnLanguageById")
    void getLanguageById() {
        //given
        Language language = new Language();
        language.setName("English");

        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setName("English");

        //when
        Mockito.when(languageRepository.findById(1L)).thenReturn(Optional.of(language));
        Mockito.when(languageConverter.entityToDto(language)).thenReturn(languageDTO);
        LanguageDTO actualLanguageDto = languageService.getLanguages(1L);

        //then
        Mockito.verify(languageRepository).findById(1L);
        Mockito.verify(languageConverter).entityToDto(language);
        assertThat(actualLanguageDto).isEqualTo(languageDTO);
    }

    @Test
    @DisplayName("shouldThrowNotFoundErrorFindById")
    void getLanguageError() {
        //when
        Mockito.when(languageRepository.findById(1L)).thenThrow(new LanguageException(HttpStatus.NOT_FOUND));

        //then
        assertThatThrownBy(() -> languageService.getLanguages(1L))
                .isInstanceOf(LanguageException.class);
        Mockito.verify(languageRepository).findById(1L);
    }

    @Test
    @DisplayName("shouldThrowNotFoundErrorDeleteById")
    void deleteLanguageError() {
        //when
        Mockito.when(languageRepository.findById(1L)).thenThrow(new LanguageException(HttpStatus.NOT_FOUND));

        //then
        assertThatThrownBy(() -> languageService.deleteLanguage(1L))
                .isInstanceOf(LanguageException.class);
        Mockito.verify(languageRepository).findById(1L);
    }

    @Test
    @DisplayName("shouldSaveLanguage")
    void addNewLanguage() {
        //given
        Language language = new Language();
        language.setName("English");

        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setName("English");

        //when
        Mockito.when(languageConverter.dtoToEntity(languageDTO)).thenReturn(language);
        Mockito.when(languageRepository.save(language)).thenReturn(language);

        Language actualLanguage = languageService.addNewLanguages(languageDTO);

        //then
        Mockito.verify(languageConverter).dtoToEntity(languageDTO);
        Mockito.verify(languageRepository).save(language);
        assertThat(actualLanguage).isEqualTo(language);
    }

    @Test
    @DisplayName("ShouldDeleteLanguageById")
    void deleteLanguage() {
        //given
        Language language = new Language();
        language.setName("English");

        //when
        Mockito.when(languageRepository.findById(1L)).thenReturn(Optional.of(language));
        languageService.deleteLanguage(1L);

        //then
        Mockito.verify(languageRepository).findById(1L);
        Mockito.verify(languageRepository).deleteById(1L);
    }

    @Test
    @DisplayName("shouldReturnUpdateLanguageIfIsExist")
    void updateLanguage1() {
        //given
        Language language = new Language();
        language.setName("Language");

        LanguageDTO languageDto = new LanguageDTO();
        languageDto.setName("Language");

        //when
        Mockito.when(languageConverter.dtoToEntity(languageDto)).thenReturn(language);
        Mockito.when(languageRepository.existsById(1L)).thenReturn(true);
        Mockito.when(languageRepository.findById(1L)).thenReturn(Optional.of(language));
        Mockito.when(languageRepository.save(language)).thenReturn(language);

        Language actualLanguage = languageService.updateLanguage(languageDto, 1L);

        //then
        Mockito.verify(languageRepository).save(language);
        Mockito.verify(languageRepository).findById(1L);
        Mockito.verify(languageConverter).dtoToEntity(languageDto);
        assertThat(actualLanguage).isEqualTo(language);
    }

    @Test
    @DisplayName("shouldReturnUpdateLanguageIfIsNotExist")
    void updateLanguage2() {
        //given
        Language language = new Language();
        language.setName("Language");

        LanguageDTO languageDto = new LanguageDTO();
        languageDto.setName("Language");

        //when
        Mockito.when(languageConverter.dtoToEntity(languageDto)).thenReturn(language);
        Mockito.when(languageRepository.existsById(1L)).thenReturn(false);

        //then
        assertThatThrownBy(() -> languageService.updateLanguage(languageDto, 1L)).isExactlyInstanceOf(LanguageException.class);
    }

    @Test
    @DisplayName("checkAndReturnLanguageIfIdIsNotNullAndIsPresent")
    void checkAndReturnDepartment() {
        //given
        List<Language> languageList = new ArrayList<>();
        Language language = new Language();
        language.setName("English");
        languageList.add(language);

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Department of Hurt");

        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setNumber("12341234");
        documentDTO.setExpireDate(LocalDate.of(2023, 1, 1));

        List<LanguageDTO> languageListDTO = new ArrayList<>();
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setId(1L);
        languageDTO.setName("English");
        languageListDTO.add(languageDTO);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName("Vladimir");
        personDTO.setSecondName("Stavitskii");
        personDTO.setDepartment(departmentDTO);
        personDTO.setDocument(documentDTO);
        personDTO.setLanguageList(languageListDTO);

        //when
        Mockito.when(languageRepository.findById(1L)).thenReturn(Optional.of(language));
        List<Language> expected = languageService.checkAndReturnLanguage(personDTO);

        //then
        assertThat(languageList).isEqualTo(expected);

    }

    @Test
    @DisplayName("checkAndReturnLanguageIfIdIsNull")
    void checkAndReturnDepartmentError() {
        //given
        Department department = new Department();
        department.setName("Department of Hurt");

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

        //then
        assertThatThrownBy(() -> languageService.checkAndReturnLanguage(personDTO))
                .isInstanceOf(LanguageException.class);

    }

    @Test
    @DisplayName("checkAndReturnLanguageIfIdIsNotNullAndIsNotPresent")
    void checkAndReturnDepartmentNotPresent() {
        //given
        List<Language> languageList = new ArrayList<>();
        Language language = new Language();
        languageList.add(language);

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Department of Hurt");

        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setNumber("12341234");
        documentDTO.setExpireDate(LocalDate.of(2023, 1, 1));

        List<LanguageDTO> languageListDTO = new ArrayList<>();
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setId(1L);
        languageDTO.setName("English");
        languageListDTO.add(languageDTO);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName("Vladimir");
        personDTO.setSecondName("Stavitskii");
        personDTO.setDepartment(departmentDTO);
        personDTO.setDocument(documentDTO);
        personDTO.setLanguageList(languageListDTO);

        //when
        Mockito.when(languageRepository.findById(1L)).thenReturn(Optional.empty());
        List<Language> expected = languageService.checkAndReturnLanguage(personDTO);

        //then
        assertThat(languageList).isEqualTo(expected);

    }
}