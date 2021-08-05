package com.syncretis.SpringDataProject.dto;

import com.syncretis.SpringDataProject.models.Person;
import com.syncretis.SpringDataProject.util.Marker;

import javax.validation.constraints.*;
import java.util.Date;

public class DocumentDTO {
    @Null(groups = Marker.OnCreate.class, message = "should be null")
    @NotNull(groups = Marker.OnUpdate.class, message = "should be not null")
    private String id;

    @NotBlank(message = "should be not blank")
    @DecimalMin(value = "1", message = "should only contain numbers")
    private String number;

    @Future(message = "should not be in the past")
    private Date expireDate;

    public DocumentDTO() {
    }

    public DocumentDTO(String number, Date expireDate) {
        this.number = number;
        this.expireDate = expireDate;
    }

    public DocumentDTO(String id, String number, Date expireDate, Person person) {
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

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
