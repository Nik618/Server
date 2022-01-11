package com.example.server.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeeId implements Serializable {
    private static final long serialVersionUID = 1882439684567508737L;
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "department_id", nullable = false)
    private Integer departmentId;
    @Column(name = "users_id", nullable = false)
    private Integer usersId;

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, usersId, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeId entity = (EmployeeId) o;
        return Objects.equals(this.departmentId, entity.departmentId) &&
                Objects.equals(this.usersId, entity.usersId) &&
                Objects.equals(this.id, entity.id);
    }
}