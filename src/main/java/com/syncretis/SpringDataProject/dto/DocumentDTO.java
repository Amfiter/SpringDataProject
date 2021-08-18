package com.syncretis.SpringDataProject.dto;

import com.syncretis.SpringDataProject.entities.Person;
import com.syncretis.SpringDataProject.util.Marker;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

public class DocumentDTO {

    @Null(groups = Marker.OnCreate.class, message = "should be null")
    @NotNull(groups = Marker.OnUpdate.class, message = "should be not null")
    private String id;

    @NotBlank(message = "should be not blank")
    @DecimalMin(value = "1", message = "should only contain numbers")
    private String number;

    @Future(message = "should not be in the past")
    private LocalDate expireDate;

    public DocumentDTO() {
    }

    public DocumentDTO(String number, LocalDate expireDate) {
        this.number = number;
        this.expireDate = expireDate;
    }

    public DocumentDTO(String id, String number, LocalDate expireDate, Person person) {
        this.id = id;
        this.number = number;
        this.expireDate = expireDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "DocumentDTO{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", expireDate=" + expireDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentDTO that = (DocumentDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(number, that.number) && Objects.equals(expireDate, that.expireDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, expireDate);
    }
}
