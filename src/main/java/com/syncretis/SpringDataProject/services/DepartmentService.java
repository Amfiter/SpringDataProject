package com.syncretis.SpringDataProject.services;

import com.syncretis.SpringDataProject.models.Department;
import com.syncretis.SpringDataProject.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartments(Long departmentId) {
        return departmentRepository.findById(departmentId);
    }

    public void addNewDepartment(Department department) {
        System.out.println(department);
        departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        boolean exists = departmentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Department with id = " + id + " does not exists");
        }
        departmentRepository.deleteById(id);
    }

    public Department updateDepartment(Department newDepartment, Long id) {
        return departmentRepository.findById(id)
                .map(department -> {
                    department.setName(newDepartment.getName());
                    return departmentRepository.save(department);
                })
                .orElseThrow(() -> new IllegalStateException("Department with id =" + id + " does not exist"));
    }


}
