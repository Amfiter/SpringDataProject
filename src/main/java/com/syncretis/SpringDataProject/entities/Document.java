package com.syncretis.SpringDataProject.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expireDate;

    @OneToOne(mappedBy = "document",cascade = CascadeType.REMOVE)
    private Person person;

    public Document() {
    }

    public Document(String number, LocalDate expireDate) {
        this.number = number;
        this.expireDate = expireDate;
    }

    public Document(String number, LocalDate expireDate, Person person) {
        this.number = number;
        this.expireDate = expireDate;
        this.person = person;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public String getPerson() {
        return person.getSecondName();
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", expireDate=" + expireDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(id, document.id) && Objects.equals(number, document.number) && Objects.equals(expireDate, document.expireDate) && Objects.equals(person, document.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, expireDate, person);
    }
}
