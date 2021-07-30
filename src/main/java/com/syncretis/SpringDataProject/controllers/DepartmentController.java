package com.syncretis.SpringDataProject.controllers;

import com.syncretis.SpringDataProject.models.Department;
import com.syncretis.SpringDataProject.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getDepartment() {
        return departmentService.getDepartments();
    }

    @GetMapping(path = "{id}")
    public Optional<Department> getDepartmentById(@PathVariable("id") Long id) {
        return departmentService.getDepartments(id);
    }

    @PostMapping
    public void createNewDepartment(@RequestBody Department department) {
        departmentService.addNewDepartment(department);
    }

    @DeleteMapping(path = "{id}")
    public void deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
    }

    @PutMapping(path = "{id}")
    public void updateDepartment(@RequestBody Department department, @PathVariable("id") Long id) {
        departmentService.updateDepartment(department, id);
    }


}
