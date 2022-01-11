package com.example.server.servlets;

import com.example.server.entities.Department;
import com.example.server.entities.Employee;
import com.example.server.entities.User;
import com.example.server.models.DataBase;

import javax.persistence.Query;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "employeesData", value = "/employeesData")
public class EmployeeDataServlet extends HttpServlet {

    Query q;
    DataBase dataBase;

    public EmployeeDataServlet() {

    }

    public void init() {
        try {
            dataBase = new DataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String department = request.getParameter("department");

        q = dataBase.em.createQuery(
                "SELECT c FROM Department c WHERE c.name LIKE :department"
        );
        q.setParameter("department", department);
        List<Department> listDepartment = q.getResultList();

        for (Department department1 : listDepartment) {
            q = dataBase.em.createQuery(
                    "SELECT c FROM Employee c WHERE c.id.departmentId = " + department1.getId()
            );
        }

        List<Employee> listEmployee = q.getResultList();
        for (Employee employee : listEmployee) {
            q = dataBase.em.createQuery(
                    "SELECT c FROM User c WHERE c.id = " + employee.getId().getId()
            );
            List<User> listUser = q.getResultList();
            for (User user : listUser) {
                out.println(user.getFirstname() + " " + user.getLastname());
                out.println(user.getId());
            }
        }
    }

    public void destroy() {
    }
}