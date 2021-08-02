package com.syncretis.SpringDataProject.dto;

import com.syncretis.SpringDataProject.models.Department;
import com.syncretis.SpringDataProject.models.Document;
import com.syncretis.SpringDataProject.models.Language;

import java.util.Date;
import java.util.List;

public class PersonDTO {
    private Long id;
    private String firstName;
    private String secondName;
    private Date birthday;
    private DocumentDTO document;
    private DepartmentDTO department;
    private List<LanguageDTO> languageList;

    public PersonDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public DocumentDTO getDocument() {
        return document;
    }

    public void setDocument(DocumentDTO document) {
        this.document = document;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDTO department) {
        this.department = department;
    }

    public List<LanguageDTO> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<LanguageDTO> languageList) {
        this.languageList = languageList;
    }
}
