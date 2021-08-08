package com.syncretis.SpringDataProject.dto;

import com.syncretis.SpringDataProject.util.Marker;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class PersonDTO {

    @Null(groups = Marker.OnCreate.class, message = "should be null")
    @NotNull(groups = Marker.OnUpdate.class, message = "should be not null")
    private Long id;

    @NotBlank(message = "should be not blank")
    @Pattern(regexp = "[A-Za-z ]*", message = "should only contain letters")
    private String firstName;

    @NotBlank(message = "should be not blank")
    @Pattern(regexp = "[A-Za-z ]*", message = "should only contain letters")
    private String secondName;

    @Past(message = "should not be in the future")
    private LocalDate birthday;

    @Valid
    private DocumentDTO document;

    @Valid
    private DepartmentDTO department;

    @Valid
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
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

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthday=" + birthday +
                ", document=" + document +
                ", department=" + department +
                ", languageList=" + languageList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(id, personDTO.id) && Objects.equals(firstName, personDTO.firstName) && Objects.equals(secondName, personDTO.secondName) && Objects.equals(birthday, personDTO.birthday) && Objects.equals(document, personDTO.document) && Objects.equals(department, personDTO.department) && Objects.equals(languageList, personDTO.languageList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, birthday, document, department, languageList);
    }
}
