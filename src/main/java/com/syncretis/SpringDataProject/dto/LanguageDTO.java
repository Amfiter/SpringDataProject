package com.syncretis.SpringDataProject.dto;

import com.syncretis.SpringDataProject.models.Person;

import java.util.Set;

public class LanguageDTO {
    private Long id;
    private String name;

    public LanguageDTO() {
    }

    public LanguageDTO(String name) {
        this.name = name;
    }

    public LanguageDTO(String name, Set<Person> persons) {
        this.name = name;
    }

    public LanguageDTO(Long id, String name, Set<Person> persons) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
