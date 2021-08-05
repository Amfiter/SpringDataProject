package com.syncretis.SpringDataProject.controllers;

import com.syncretis.SpringDataProject.dto.DepartmentDTO;
import com.syncretis.SpringDataProject.models.Department;
import com.syncretis.SpringDataProject.services.DepartmentService;
import com.syncretis.SpringDataProject.util.Marker;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

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
    public void createNewDepartment(@RequestBody DepartmentDTO departmentDTO) {
        departmentService.addNewDepartment(departmentDTO);
    }

    @DeleteMapping(path = "{id}")
    public void deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
    }

    @PutMapping(path = "{id}")
    public Department updateDepartment(@RequestBody DepartmentDTO departmentDTO, @PathVariable("id") Long id) {
        return departmentService.updateDepartment(departmentDTO, id);
    }


}
