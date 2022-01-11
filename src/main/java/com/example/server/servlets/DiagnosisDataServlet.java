package com.example.server.servlets;

import com.example.server.entities.Department;
import com.example.server.entities.Diagnosis;
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

@WebServlet(name = "diagnosisData", value = "/diagnosisData")
public class DiagnosisDataServlet extends HttpServlet {

    Query q;
    DataBase dataBase;

    public DiagnosisDataServlet() {

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
            "SELECT c FROM Diagnosis c"
        );
        List<Diagnosis> list = q.getResultList();
        for (Diagnosis diagnosis : list) {
            out.println(diagnosis.getName());
            out.println(diagnosis.getId());
        }
    }

    public void destroy() {
    }
}