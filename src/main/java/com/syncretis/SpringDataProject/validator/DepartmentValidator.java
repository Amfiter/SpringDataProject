package com.syncretis.SpringDataProject.validator;

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
        if (departmentDto.getId() != null) {
            errors.rejectValue("id", "id.notEmpty", "Id should be blank");
        }
        if (departmentDto.getName() == null || departmentDto.getName().length() == 0) {
            errors.rejectValue("name", "name.empty", "Name should be not blank");
        } else if (!departmentDto.getName().matches("[a-zA-Z ]+")) {
            errors.rejectValue("name", "name.invalid", "Name should only contain letters");
        }
    }
}