package com.example.server.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TimeId implements Serializable {
    private static final long serialVersionUID = 319029353387158085L;
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "shedule_id", nullable = false)
    private Integer sheduleId;
    @Column(name = "employees_id", nullable = false)
    private Integer employeesId;

    public Integer getEmployeesId() {
        return employeesId;
    }

    public void setEmployeesId(Integer employeesId) {
        this.employeesId = employeesId;
    }

    public Integer getSheduleId() {
        return sheduleId;
    }

    public void setSheduleId(Integer sheduleId) {
        this.sheduleId = sheduleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sheduleId, id, employeesId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeId entity = (TimeId) o;
        return Objects.equals(this.sheduleId, entity.sheduleId) &&
                Objects.equals(this.id, entity.id) &&
                Objects.equals(this.employeesId, entity.employeesId);
    }
}