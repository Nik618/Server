package com.example.server.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "medicalcertificate")
public class Medicalcertificate {
    @EmbeddedId
    private MedicalcertificateId id;

    @Column(name = "dataBegin", nullable = false)
    private LocalDate dataBegin;

    @Column(name = "dataEnd", nullable = false)
    private LocalDate dataEnd;

    public LocalDate getDataEnd() {
        return dataEnd;
    }

    public void setDataEnd(LocalDate dataEnd) {
        this.dataEnd = dataEnd;
    }

    public LocalDate getDataBegin() {
        return dataBegin;
    }

    public void setDataBegin(LocalDate dataBegin) {
        this.dataBegin = dataBegin;
    }

    public MedicalcertificateId getId() {
        return id;
    }

    public void setId(MedicalcertificateId id) {
        this.id = id;
    }
}