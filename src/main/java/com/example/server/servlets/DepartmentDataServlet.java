package com.example.server.servlets;

import com.example.server.entities.Department;
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

@WebServlet(name = "departmentData", value = "/departmentData")
public class DepartmentDataServlet extends HttpServlet {

    Query q;
    DataBase dataBase;

    public DepartmentDataServlet() {

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

        q = dataBase.em.createQuery(
            "SELECT c FROM Department c"
        );
        List<Department> list = q.getResultList();
        for (Department department : list) {
            out.println(department.getName());
            out.println(department.getId());
        }
    }

    public void destroy() {
    }
}