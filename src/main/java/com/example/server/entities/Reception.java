package com.example.server.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "reception")
public class Reception {
    @EmbeddedId
    private ReceptionId id;

    @Column(name = "status", nullable = false, length = 45)
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ReceptionId getId() {
        return id;
    }

    public void setId(ReceptionId id) {
        this.id = id;
    }
}