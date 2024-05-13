package com.task.goalphakids;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class InfoModel implements Serializable {
    @PrimaryKey
    private double id;
    private String name;
    private String email;


    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public InfoModel(double id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;

    }
}
