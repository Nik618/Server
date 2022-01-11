package com.example.server.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "prescribedtreatment")
public class Prescribedtreatment {
    @EmbeddedId
    private PrescribedtreatmentId id;

    public PrescribedtreatmentId getId() {
        return id;
    }

    public void setId(PrescribedtreatmentId id) {
        this.id = id;
    }
}