package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.converters.DepartmentConverter;
import com.syncretis.SpringDataProject.dto.DepartmentDTO;
import com.syncretis.SpringDataProject.dto.PersonDTO;
import com.syncretis.SpringDataProject.exceptions.DepartmentBadRequestException;
import com.syncretis.SpringDataProject.exceptions.DepartmentNotFoundException;
import com.syncretis.SpringDataProject.models.Department;
import com.syncretis.SpringDataProject.repositories.DepartmentRepository;
import com.syncretis.SpringDataProject.validator.DepartmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class DepartmentService {
    private final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentConverter departmentConverter;

    @Autowired
    private DepartmentValidator departmentValidator;

    public List<DepartmentDTO> getDepartments() {
        List<Department> listDepartment = departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOS = departmentConverter.entityToDto(listDepartment);
        return departmentDTOS;
    }

    public DepartmentDTO getDepartments(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException(HttpStatus.NOT_FOUND));
        DepartmentDTO departmentDTO = departmentConverter.entityToDto(department);
        return departmentDTO;
    }

    public void addNewDepartment(DepartmentDTO departmentDTO) {
        validate(departmentDTO);
        Department department = departmentConverter.dtoToEntity(departmentDTO);
        departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new DepartmentNotFoundException(HttpStatus.NOT_FOUND);
        }
        departmentRepository.deleteById(id);
    }

    public Department updateDepartment(DepartmentDTO newDepartment, Long id) {
        validate(newDepartment);
        Department departmentEntity = departmentConverter.dtoToEntity(newDepartment);
        return departmentRepository.findById(id)
                .map(department -> {
                    department.setName(departmentEntity.getName());
                    return departmentRepository.save(department);
                })
                .orElseThrow(() -> new DepartmentNotFoundException(HttpStatus.NOT_FOUND));
    }

    public Department checkAndReturnDepartment(PersonDTO personDTO) {
        Department department;
        if (personDTO.getDepartment().getId() != null) {
            Optional<Department> optional = departmentRepository.findById(personDTO.getDepartment().getId());
            System.out.println("нашел optional departmentById" + optional);
            if (optional.isPresent()) {
                department = optional.get();
            } else {
                department = new Department();
            }
        } else {
            throw new DepartmentNotFoundException(HttpStatus.FORBIDDEN);
        }
        return department;
    }

    private void validate(DepartmentDTO departmentDTO) {
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
