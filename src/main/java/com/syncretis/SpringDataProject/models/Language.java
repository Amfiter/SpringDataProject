package com.syncretis.SpringDataProject.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(targetEntity = Person.class)
    private Set<Person> persons;

    public Language() {
    }

    public Language(String name) {
        this.name = name;
    }

    public Language(String name, Set<Person> persons) {
        this.name = name;
        this.persons = persons;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", persons=" + persons +
                '}';
    }
}
