package com.bk.demo.model;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class UserRecord {
    @Id
    private int id;
    private String name;
    private String email;

    //default conatructor
    public UserRecord() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Required
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
