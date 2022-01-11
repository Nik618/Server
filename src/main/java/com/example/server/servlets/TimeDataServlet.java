package com.example.server.servlets;

import com.example.server.entities.*;
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

@WebServlet(name = "timeData", value = "/timeData")
public class TimeDataServlet extends HttpServlet {

    Query q;
    DataBase dataBase;

    public TimeDataServlet() {

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
        String t1 = request.getParameter("t1");
        String day = request.getParameter("day");

        q = dataBase.em.createQuery(
                //"SELECT c FROM Shedule c WHERE c.id = " + reception.getId().getTimesSheduleId()
                "SELECT c FROM Shedule c WHERE c.id IN (SELECT b.id.sheduleId FROM Time b WHERE b.id.employeesId = " + Integer.parseInt(t1) + ") and c.day LIKE :day and c.id NOT IN (SELECT a.id.timesSheduleId FROM Reception a WHERE a.status LIKE :status)"
        );
        q.setParameter("day", day);
        q.setParameter("status", "0");

        List<Shedule> listShedule = q.getResultList();
        for (Shedule shedule : listShedule) {
            out.println(shedule.getTimeBegin() + " " + shedule.getTimeEnd());
            out.println(shedule.getId());
        }
    }

    public void destroy() {
    }
}