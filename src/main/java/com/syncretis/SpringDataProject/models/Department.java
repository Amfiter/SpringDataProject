package com.syncretis.SpringDataProject.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name",unique = true)
    @NotNull
    private String name;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "department",
            cascade = CascadeType.ALL)
    private List<Person> personList;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Department(Long id, String name) {
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

    @Override
    public String toString() {
        return new StringJoiner(", ", Department.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}

