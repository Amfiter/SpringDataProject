package com.syncretis.SpringDataProject.dto;

import com.syncretis.SpringDataProject.models.Department;
import com.syncretis.SpringDataProject.models.Document;
import com.syncretis.SpringDataProject.models.Language;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class PersonDTO {
    private Long id;
    private String firstName;
    private String secondName;
    private Date birthday;
    private Document document;
    private Department department;
    private List<Language> languageList;

    public PersonDTO() {
    }

    public PersonDTO(String firstName, String secondName, Date birthday, Department department, List<Language> languageList, Document document) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.department = department;
        this.languageList = languageList;
        this.document = document;
    }

    public PersonDTO(Long id, String firstName, String secondName, Date birthday, Department department, List<Language> languageList, Document document) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.department = department;
        this.languageList = languageList;
        this.document = document;
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

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
    }
}
