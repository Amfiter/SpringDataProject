package com.syncretis.SpringDataProject;

import com.syncretis.SpringDataProject.dto.DepartmentDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DepartmentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return DepartmentDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DepartmentDTO departmentDto = (DepartmentDTO) target;
        if (departmentDto.getName() == null || departmentDto.getName().length() == 0) {
            errors.rejectValue("name", "500");
        }
    }
}


