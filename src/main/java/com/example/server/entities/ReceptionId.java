package com.example.server.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReceptionId implements Serializable {
    private static final long serialVersionUID = -2815549217898437540L;
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "users_id", nullable = false)
    private Integer usersId;
    @Column(name = "times_id", nullable = false)
    private Integer timesId;
    @Column(name = "times_shedule_id", nullable = false)
    private Integer timesSheduleId;
    @Column(name = "times_employees_id", nullable = false)
    private Integer timesEmployeesId;

    public Integer getTimesEmployeesId() {
        return timesEmployeesId;
    }

    public void setTimesEmployeesId(Integer timesEmployeesId) {
        this.timesEmployeesId = timesEmployeesId;
    }

    public Integer getTimesSheduleId() {
        return timesSheduleId;
    }

    public void setTimesSheduleId(Integer timesSheduleId) {
        this.timesSheduleId = timesSheduleId;
    }

    public Integer getTimesId() {
        return timesId;
    }

    public void setTimesId(Integer timesId) {
        this.timesId = timesId;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timesEmployeesId, usersId, id, timesId, timesSheduleId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceptionId entity = (ReceptionId) o;
        return Objects.equals(this.timesEmployeesId, entity.timesEmployeesId) &&
                Objects.equals(this.usersId, entity.usersId) &&
                Objects.equals(this.id, entity.id) &&
                Objects.equals(this.timesId, entity.timesId) &&
                Objects.equals(this.timesSheduleId, entity.timesSheduleId);
    }
}