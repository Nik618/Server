package com.example.server.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PrescribedtreatmentId implements Serializable {
    private static final long serialVersionUID = 2117625449999669356L;
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "diagnosis_id", nullable = false)
    private Integer diagnosisId;
    @Column(name = "treatment_id", nullable = false)
    private Integer treatmentId;
    @Column(name = "reception_id", nullable = false)
    private Integer receptionId;
    @Column(name = "reception_users_id", nullable = false)
    private Integer receptionUsersId;
    @Column(name = "reception_times_id", nullable = false)
    private Integer receptionTimesId;
    @Column(name = "reception_times_shedule_id", nullable = false)
    private Integer receptionTimesSheduleId;
    @Column(name = "reception_times_employees_id", nullable = false)
    private Integer receptionTimesEmployeesId;

    public Integer getReceptionTimesEmployeesId() {
        return receptionTimesEmployeesId;
    }

    public void setReceptionTimesEmployeesId(Integer receptionTimesEmployeesId) {
        this.receptionTimesEmployeesId = receptionTimesEmployeesId;
    }

    public Integer getReceptionTimesSheduleId() {
        return receptionTimesSheduleId;
    }

    public void setReceptionTimesSheduleId(Integer receptionTimesSheduleId) {
        this.receptionTimesSheduleId = receptionTimesSheduleId;
    }

    public Integer getReceptionTimesId() {
        return receptionTimesId;
    }

    public void setReceptionTimesId(Integer receptionTimesId) {
        this.receptionTimesId = receptionTimesId;
    }

    public Integer getReceptionUsersId() {
        return receptionUsersId;
    }

    public void setReceptionUsersId(Integer receptionUsersId) {
        this.receptionUsersId = receptionUsersId;
    }

    public Integer getReceptionId() {
        return receptionId;
    }

    public void setReceptionId(Integer receptionId) {
        this.receptionId = receptionId;
    }

    public Integer getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Integer treatmentId) {
        this.treatmentId = treatmentId;
    }

    public Integer getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(Integer diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(receptionUsersId, diagnosisId, receptionTimesSheduleId, receptionTimesId, receptionTimesEmployeesId, id, receptionId, treatmentId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrescribedtreatmentId entity = (PrescribedtreatmentId) o;
        return Objects.equals(this.receptionUsersId, entity.receptionUsersId) &&
                Objects.equals(this.diagnosisId, entity.diagnosisId) &&
                Objects.equals(this.receptionTimesSheduleId, entity.receptionTimesSheduleId) &&
                Objects.equals(this.receptionTimesId, entity.receptionTimesId) &&
                Objects.equals(this.receptionTimesEmployeesId, entity.receptionTimesEmployeesId) &&
                Objects.equals(this.id, entity.id) &&
                Objects.equals(this.receptionId, entity.receptionId) &&
                Objects.equals(this.treatmentId, entity.treatmentId);
    }
}