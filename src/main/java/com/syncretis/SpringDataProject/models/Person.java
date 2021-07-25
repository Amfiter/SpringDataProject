package com.syncretis.SpringDataProject.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.StringJoiner;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    @NotNull
    private String firstName;
    @Column(name = "second_name")
    @NotNull
    private String secondName;
    @Column(name = "birthday")
    @NotNull
    private Date birthday;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    @NotNull
    private Department department;

    public Person() {
    }

    public Person(String firstName) {
        this.firstName = firstName;
    }

    public Person(Long id, String firstName, String secondName, Date birthday, Department department) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.department = department;
    }

    public Person(String firstName, String secondName, Date birthday, Department department) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.department = department;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("firstName='" + firstName + "'")
                .add("secondName='" + secondName + "'")
                .add("birthday=" + birthday)
                .add("department=" + department)
                .toString();
    }
}
