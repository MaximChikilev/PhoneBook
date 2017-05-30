package com.phonebook.entity;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PhoneBookRecord {

    private Long id;
    private Long userid;
    @NotNull(message = "Surname cannot be empty")
    @Size(min = 4, message = "You mast enter min 4 character")
    private String surname;
    @NotNull(message = "Name cannot be empty")
    @Size(min = 4, message = "You mast enter min 4 character")
    private String name;
    @NotNull(message = "Patronymic cannot be empty")
    @Size(min = 4, message = "You mast enter min 4 character")
    private String patronymic;
    @Pattern(regexp = "^\\+?38\\(0([0-9]{2}\\)[0-9]{7})", message = "Phone number is not valid format")
    private String mobilePhone;
    private String homePhone;
    private String adress;
    @Email
    private String eMail;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

