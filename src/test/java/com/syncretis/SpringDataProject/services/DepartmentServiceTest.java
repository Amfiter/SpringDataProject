package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.converters.DepartmentConverter;
import com.syncretis.SpringDataProject.dto.DepartmentDTO;
import com.syncretis.SpringDataProject.dto.DocumentDTO;
import com.syncretis.SpringDataProject.dto.LanguageDTO;
import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.entities.Department;
import com.syncretis.SpringDataProject.entities.Document;
import com.syncretis.SpringDataProject.entities.Language;
import com.syncretis.SpringDataProject.entities.Person;
import com.syncretis.SpringDataProject.exceptions.DepartmentNotFoundException;
import com.syncretis.SpringDataProject.repositories.DepartmentRepository;
import com.syncretis.SpringDataProject.validator.DepartmentValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DepartmentServiceTest {

    @Mock
    DepartmentRepository departmentRepository;
    @Mock
    DepartmentConverter departmentConverter;
    @Spy
    DepartmentValidator departmentValidator;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void openMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("shouldReturnAllDepartments")
    void getDepartments() {
        //given
        List<Department> departments = new ArrayList<>();
        Department department = new Department();
        department.setName("Department of Hurt");
        Department department1 = new Department("Department of Love");
        department1.setName("Department of Love");

        departments.add(department);
        departments.add(department1);

        List<DepartmentDTO> departmentsDto = new ArrayList<>();
        DepartmentDTO departmentDto = new DepartmentDTO("Department of Hurt");
        departmentDto.setName("Department of Hurt");
        DepartmentDTO departmentDto1 = new DepartmentDTO("Department of Love");
        departmentDto1.setName("Department of Love");

        departmentsDto.add(departmentDto);
        departmentsDto.add(departmentDto1);

        //when
        Mockito.when(departmentRepository.findAll()).thenReturn(departments);
        Mockito.when(departmentConverter.entityToDto(departments)).thenReturn(departmentsDto);
        List<DepartmentDTO> actualDepartmentsDto = departmentService.getDepartments();

        //then
        Mockito.verify(departmentRepository).findAll();
        Mockito.verify(departmentConverter).entityToDto(departments);
        assertThat(actualDepartmentsDto).isEqualTo(departmentsDto);
    }

    @Test
    @DisplayName("shouldReturnDepartmentById")
    void getDepartmentById() {
        //given
        Department department = new Department();
        department.setName("Department of Hurt");

        DepartmentDTO departmentDto = new DepartmentDTO("Department of Hurt");
        departmentDto.setName("Department of Hurt");

        //when
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        Mockito.when(departmentConverter.entityToDto(department)).thenReturn(departmentDto);
        DepartmentDTO actualDepartmentsDto = departmentService.getDepartments(1L);

        //then
        Mockito.verify(departmentRepository).findById(1L);
        Mockito.verify(departmentConverter).entityToDto(department);
        assertThat(actualDepartmentsDto).isEqualTo(departmentDto);
    }

    @Test
    @DisplayName("shouldSaveDepartment")
    void addNewDepartment() {
        //given
        Department department = new Department();
        department.setName("DepartmentHurt");

        DepartmentDTO departmentDto = new DepartmentDTO();
        departmentDto.setName("DepartmentHurt");

        //when
        Mockito.when(departmentConverter.dtoToEntity(departmentDto)).thenReturn(department);
        Mockito.when(departmentRepository.save(department)).thenReturn(department);

        Department actualDepartment = departmentService.addNewDepartment(departmentDto);

        //then
        Mockito.verify(departmentRepository).save(department);
        Mockito.verify(departmentConverter).dtoToEntity(departmentDto);
        assertThat(actualDepartment).isEqualTo(department);
    }

    @Test
    @DisplayName("shouldDeleteDepartmentFindById")
    void deleteDepartment() {
        //given
        Department department = new Department();
        department.setName("Department of Hurt");

        //when
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        departmentService.deleteDepartment(1L);

        //then
        Mockito.verify(departmentRepository).findById(1L);
        Mockito.verify(departmentRepository).deleteById(1L);
    }

    @Test
    @DisplayName("shouldThrowNotFoundErrorFindById")
    void getDepartmentsError() throws DepartmentNotFoundException {
        //when
        Mockito.when(departmentRepository.findById(3L)).thenThrow(new DepartmentNotFoundException(HttpStatus.NOT_FOUND));

        //then
        assertThatThrownBy(() -> departmentService.getDepartments(3L))
                .isInstanceOf(DepartmentNotFoundException.class);
        Mockito.verify(departmentRepository).findById(3L);
    }

    @Test
    @DisplayName("shouldThrowNotFoundErrorDeleteById")
    void deleteDepartmentError() {
        //when
        Mockito.when(departmentRepository.findById(1L)).thenThrow(new DepartmentNotFoundException(HttpStatus.NOT_FOUND));

        //then
        assertThatThrownBy(() -> departmentService.deleteDepartment(1L))
                .isInstanceOf(DepartmentNotFoundException.class);
        Mockito.verify(departmentRepository).findById(1L);
    }

    @Test
    @DisplayName("shouldThrowNotFoundErrorUpdateById")
    void updateDepartmentError() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Department of hurt");
        //when
        Mockito.when(departmentRepository.findById(1L)).thenThrow(new DepartmentNotFoundException(HttpStatus.NOT_FOUND));

        //then
        assertThatThrownBy(() -> departmentService.updateDepartment(departmentDTO, 1L))
                .isInstanceOf(DepartmentNotFoundException.class);
        Mockito.verify(departmentRepository).findById(1L);
    }

    @Test
    @DisplayName("shouldReturnUpdateDepartment")
    void updateDepartment() {
        //given
        Department department = new Department();
        department.setName("Department of Hurt");

        DepartmentDTO departmentDto = new DepartmentDTO();
        departmentDto.setName("Department of Hurt");

        //when
        Mockito.when(departmentConverter.dtoToEntity(departmentDto)).thenReturn(department);
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        Mockito.when(departmentRepository.save(department)).thenReturn(department);

        Department actualDepartment = departmentService.updateDepartment(departmentDto, 1L);

        //then
        Mockito.verify(departmentRepository).save(department);
        Mockito.verify(departmentRepository).findById(1L);
        Mockito.verify(departmentConverter).dtoToEntity(departmentDto);
        assertThat(actualDepartment).isEqualTo(department);
    }

    @Test
    @DisplayName("shouldReturnErrorDepartmentsIdShouldBeBlank")
    public void shouldThrowExceptionValidateId() {
        //given
        DepartmentDTO departmentDto1 = new DepartmentDTO(1L, "Department");

        //then
        assertThatThrownBy(() -> departmentService.validate(departmentDto1)).hasMessage("Id should be blank\n");
    }

    @Test
    @DisplayName("shouldReturnErrorDepartmentsNameShouldBeNotBlank")
    public void shouldThrowExceptionValidateName() {
        //given
        DepartmentDTO departmentDto2 = new DepartmentDTO(null, "");

        //then
        assertThatThrownBy(() -> departmentService.validate(departmentDto2)).hasMessage("Name should be not blank\n");
    }

    @Test
    @DisplayName("shouldReturnErrorDepartmentsNameShouldContainLetter")
    public void shouldThrowExceptionValidateLetters() {
        //given
        DepartmentDTO departmentDto3 = new DepartmentDTO(null, "D3partment");

        //then
        assertThatThrownBy(() -> departmentService.validate(departmentDto3)).hasMessage("Name should only contain letters\n");
    }

    @Test
    @DisplayName("shouldReturnValidDepartment")
    public void shouldValidate() {
        DepartmentDTO departmentDto = new DepartmentDTO("Department");
        assertDoesNotThrow(() -> departmentService.validate(departmentDto));
    }

    @Test
    @DisplayName("checkAndReturnDepartmentIfIdIsNotNullAndIsPresent")
    void checkAndReturnDepartment() {
        //given
        Department department = new Department();
        department.setName("Department of Hurt");

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);
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
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        Department expected = departmentService.checkAndReturnDepartment(personDTO);
        //then

        assertThat(department).isEqualTo(expected);

    }

    @Test
    @DisplayName("checkAndReturnDepartmentIfIdIsNull")
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

        //when
        Mockito.when(departmentRepository.findById(1L)).thenThrow(new DepartmentNotFoundException(HttpStatus.NOT_FOUND));

        //then
        assertThatThrownBy(() -> departmentService.checkAndReturnDepartment(personDTO))
                .isInstanceOf(DepartmentNotFoundException.class);

    }
}