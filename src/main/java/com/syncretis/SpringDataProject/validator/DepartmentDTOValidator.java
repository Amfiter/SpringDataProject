package com.syncretis.SpringDataProject.validator;

import com.syncretis.SpringDataProject.dto.DepartmentDTO;
import com.syncretis.SpringDataProject.exceptions.DepartmentBadRequestException;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;

import java.util.Locale;

@Component
public class DepartmentDTOValidator {

    private final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    private final DepartmentValidator departmentValidator;

    public DepartmentDTOValidator(DepartmentValidator departmentValidator) {
        this.departmentValidator = departmentValidator;
    }

    public void dataBinderValidation(DepartmentDTO departmentDTO) {
        final DataBinder dataBinder = new DataBinder(departmentDTO);
        dataBinder.addValidators(departmentValidator);
        dataBinder.validate(departmentDTO);

        if (dataBinder.getBindingResult().hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ObjectError allError : dataBinder.getBindingResult().getAllErrors()) {
                errorMessage.append(messageSource.getMessage(allError, Locale.getDefault())).append('\n');
            }
            throw new DepartmentBadRequestException(errorMessage.toString());
        }
    }
}
