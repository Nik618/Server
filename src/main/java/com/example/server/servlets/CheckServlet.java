package com.example.server.servlets;

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
import java.sql.*;
import java.util.List;

@WebServlet(name = "check", value = "/check")
public class CheckServlet extends HttpServlet {
    DataBase dataBase;

    public CheckServlet() {

    }

    public void init() {
        try {
            dataBase = new DataBase();
        } catch (SQLException e) {
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                String url = "jdbc:mysql://localhost:3306";
                String username = "root";
                String password = "root";
                Connection connection = DriverManager.getConnection(url, username, password);
                if (!connection.isClosed()) System.out.println("connection successful!");
            } catch ( SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String t1 = request.getParameter("t1");
        String t2 = request.getParameter("t2");
        System.out.println("!");
        Query q;
        q = dataBase.em.createQuery(
                "SELECT c FROM Employee c LEFT JOIN User a ON c.id.usersId = a.id WHERE a.login LIKE :t1 and a.password LIKE :t2"
        );
        q.setParameter("t1", t1);
        q.setParameter("t2", t2);
        List<Employee> list = q.getResultList();
        if (!list.isEmpty()) {
            out.println('2');
            out.println(list.get(0).getId().getId());
        }
        if (list.isEmpty()) {
            q = dataBase.em.createQuery(
                    "SELECT c FROM User c WHERE c.login LIKE :t1 and c.password LIKE :t2"
            );
            q.setParameter("t1", t1);
            q.setParameter("t2", t2);
            List<User> listUser = q.getResultList();
            if (!listUser.isEmpty()) {
                out.println('1');
                out.println(listUser.get(0).getId());
                out.println(listUser.get(0).getRole());
            }
        }
        //out.println(list.get(0).getRole());

    }

    public void destroy() {
    }
}