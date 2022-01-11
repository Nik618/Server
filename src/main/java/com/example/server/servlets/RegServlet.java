package com.example.server.servlets;

import com.example.server.entities.User;
import com.example.server.models.DataBase;

import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "registration", value = "/registration")
public class RegServlet extends HttpServlet {
    DataBase dataBase;

    public RegServlet() {
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
        String t2 = request.getParameter("t2");
        String t3 = request.getParameter("t3");
        String t4 = request.getParameter("t4");
        String t5 = request.getParameter("t5");
        String t6 = request.getParameter("t6");

        dataBase.open();

        User user = new User();
        user.setLogin(t1);
        user.setPassword(t2);
        user.setPassport(t3);
        user.setBirth(t4);
        user.setFirstname(t5);
        user.setLastname(t6);
        user.setRole("1");

        dataBase.em.persist(user);
        dataBase.et.commit();

        dataBase.em.close();
        dataBase.emf.close();
        out.println("1");
    }

    public void destroy() {
    }
}