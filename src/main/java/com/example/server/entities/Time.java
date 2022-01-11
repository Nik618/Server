package com.example.server.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "times")
public class Time {
    @EmbeddedId
    private TimeId id;

    public TimeId getId() {
        return id;
    }

    public void setId(TimeId id) {
        this.id = id;
    }
}