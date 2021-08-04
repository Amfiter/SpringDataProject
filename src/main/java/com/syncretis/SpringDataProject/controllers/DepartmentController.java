package com.syncretis.SpringDataProject.controllers;

import com.syncretis.SpringDataProject.DepartmentValidator;
import com.syncretis.SpringDataProject.Marker;
import com.syncretis.SpringDataProject.dto.DepartmentDTO;
import com.syncretis.SpringDataProject.models.Department;
import com.syncretis.SpringDataProject.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.Assert;
import org.springframework.validation.DataBinder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Validated
@RestController
@RequestMapping(path = "api/departments")
public class DepartmentController {
    private static final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    static {
        messageSource.setBasename("message");
    }

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentValidator departmentValidator;

    @GetMapping
    public List<DepartmentDTO> getDepartment() {
        return departmentService.getDepartments();
    }

    @GetMapping(path = "{id}")
    public DepartmentDTO getDepartmentById(@PathVariable("id") Long id) {
        return departmentService.getDepartments(id);
    }

    @PostMapping
    @Validated({Marker.OnCreate.class})
    public void createNewDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        /*final DataBinder dataBinder = new DataBinder(departmentDTO);
        dataBinder.addValidators(departmentValidator);
        dataBinder.validate(departmentDTO);

        if (dataBinder.getBindingResult().hasErrors()) {
            dataBinder.getBindingResult().getAllErrors().stream().
                    forEach(e -> System.out.println(messageSource
                            .getMessage(e, Locale.getDefault())));
        }*/
        departmentService.addNewDepartment(departmentDTO);
    }

    @DeleteMapping(path = "{id}")
    public void deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
    }

    @Validated({Marker.OnUpdate.class})
    @PutMapping(path = "{id}")
    public Department updateDepartment(@Valid @RequestBody DepartmentDTO departmentDTO, @PathVariable("id") Long id) {
        return departmentService.updateDepartment(departmentDTO, id);
    }


}
