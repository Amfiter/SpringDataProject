package com.syncretis.SpringDataProject.validator;

import com.syncretis.SpringDataProject.dto.DepartmentDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;

@ExtendWith(MockitoExtension.class)
class DepartmentValidatorTest {

    DepartmentValidator departmentValidator = new DepartmentValidator();
    @Mock
    private Errors bindException;

    @Test
    @DisplayName("shouldThrowExceptionWhenNameShouldBeNotNull")
    public void error1() {
        //given
        DepartmentDTO departmentDTO = new DepartmentDTO();
        //when
        departmentValidator.validate(departmentDTO, bindException);
        //then
        Mockito.verify(bindException).rejectValue("name", "name.empty", "Name should be not blank");
        Mockito.verify(bindException, Mockito.times(0)).rejectValue("id", "id.notEmpty", "Id should be blank");
        Mockito.verify(bindException, Mockito.times(0)).rejectValue("name", "name.invalid", "Name should only contain letters");
    }

    @Test
    @DisplayName("shouldThrowExceptionWhenIdShouldBeNull")
    public void error2() {
        //given
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);
        departmentDTO.setName("Department");
        //when
        departmentValidator.validate(departmentDTO, bindException);
        //then
        Mockito.verify(bindException).rejectValue("id", "id.notEmpty", "Id should be blank");
        Mockito.verify(bindException, Mockito.times(0)).rejectValue("name", "name.empty", "Name should be not blank");
        Mockito.verify(bindException, Mockito.times(0)).rejectValue("name", "name.invalid", "Name should only contain letters");
    }

    @Test
    @DisplayName("shouldThrowExceptionWhenNameShouldContainOnlyLetter")
    public void error3() {
        //given
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("D3partment");
        //when
        departmentValidator.validate(departmentDTO, bindException);
        //then
        Mockito.verify(bindException).rejectValue("name", "name.invalid", "Name should only contain letters");
        Mockito.verify(bindException, Mockito.times(0)).rejectValue("name", "name.empty", "Name should be not blank");
        Mockito.verify(bindException, Mockito.times(0)).rejectValue("id", "id.notEmpty", "Id should be blank");
    }

    @Test
    @DisplayName("validDepartmentShouldSuccessValidation ")
    public void error4() {
        //given
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Department");
        //when
        departmentValidator.validate(departmentDTO, bindException);
        //then
        Mockito.verify(bindException, Mockito.times(0)).rejectValue("name", "name.invalid", "Name should only contain letters");
        Mockito.verify(bindException, Mockito.times(0)).rejectValue("name", "name.empty", "Name should be not blank");
        Mockito.verify(bindException, Mockito.times(0)).rejectValue("id", "id.notEmpty", "Id should be blank");
    }

}