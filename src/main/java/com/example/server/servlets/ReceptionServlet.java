package com.example.server.servlets;

import com.example.server.entities.*;
import com.example.server.models.DataBase;

import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.QueryHint;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "reception", value = "/reception")
public class ReceptionServlet extends HttpServlet {

    Query q;
    DataBase dataBase;

    public ReceptionServlet() {

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
        String id = request.getParameter("id");
        String t1 = request.getParameter("t1");
        String t2 = request.getParameter("t2");
        String userId = request.getParameter("userId");
        String status = request.getParameter("status");

        dataBase.open();

        if (id != null) {
            q = dataBase.em.createQuery(
                    "SELECT c FROM Reception c WHERE c.id.id = " + Integer.parseInt(id)
            );
            List<Reception> listReception = q.getResultList();
            Reception reception = new Reception();
            for (Reception reception1 : listReception)
                reception = reception1;
            reception.setStatus("1");
            dataBase.em.merge(reception);
            dataBase.et.commit();
        }

        if (t2 != null) {
            Reception reception = new Reception();
            ReceptionId receptionId = new ReceptionId();
            receptionId.setUsersId(Integer.parseInt(userId));
            q = dataBase.em.createQuery(
                    "SELECT c FROM Time c WHERE c.id.employeesId = " + Integer.parseInt(t1) + " AND c.id.sheduleId = " + Integer.parseInt(t2)
            );
            List<Time> listTime = q.getResultList();
            for (Time time1 : listTime) {
                receptionId.setTimesId(time1.getId().getId());
                receptionId.setTimesSheduleId(time1.getId().getSheduleId());
                receptionId.setTimesEmployeesId(time1.getId().getEmployeesId());
            }

            reception.setId(receptionId);
            reception.setStatus("0");
            dataBase.em.persist(reception);
            dataBase.et.commit();
        }
        if (t1 == null || t2 != null) {
            if (Objects.equals(status, "0"))
                q = dataBase.em.createQuery(
                        "SELECT c FROM Reception c WHERE c.id.usersId = " + Integer.parseInt(userId) + " AND c.status = " + 0
                ); else
                    q = dataBase.em.createQuery(
                            "SELECT c FROM Reception c WHERE c.id.usersId = " + Integer.parseInt(userId) + " AND c.status = " + 1
                    );

            List<Reception> listReception = q.getResultList();
            for (Reception reception1 : listReception) {
                out.println(reception1.getId().getId());
                q = dataBase.em.createQuery(
                        "SELECT c FROM Employee c WHERE c.id.id = " + reception1.getId().getTimesEmployeesId()
                );
                List<Employee> listEmployee = q.getResultList();
                for (Employee employee : listEmployee) {
                    q = dataBase.em.createQuery(
                            "SELECT c FROM User c WHERE c.id = " + employee.getId().getUsersId()
                    );
                    List<User> listUsers = q.getResultList();
                    for (User user : listUsers) {
                        out.println(user.getFirstname() + " " + user.getLastname());
                    }
                }
                q = dataBase.em.createQuery(
                        "SELECT c FROM Time c WHERE c.id.id = " + reception1.getId().getTimesId()
                );
                List<Time> listTime1 = q.getResultList();
                for (Time time1 : listTime1) {
                    q = dataBase.em.createQuery(
                            "SELECT c FROM Shedule c WHERE c.id = " + time1.getId().getSheduleId()
                    );
                    List<Shedule> listShedule1 = q.getResultList();
                    for (Shedule shedule1 : listShedule1) {
                        out.println(shedule1.getDay());
                        out.println(shedule1.getTimeBegin());
                        out.println(shedule1.getTimeEnd());
                    }
                }
            }
        } else {


            if (Objects.equals(status, "0"))
                q = dataBase.em.createQuery(
                        "SELECT c FROM Reception c WHERE c.id.timesEmployeesId = " + Integer.parseInt(t1) + " AND c.status = " + 0
                ); else
                    q = dataBase.em.createQuery(
                            "SELECT c FROM Reception c WHERE c.id.timesEmployeesId = " + Integer.parseInt(t1) + " AND c.status = " + 1
                    );


            List<Reception> listReception = q.getResultList();
            for (Reception reception1 : listReception) {
                out.println(reception1.getId().getId());
                q = dataBase.em.createQuery(
                        "SELECT c FROM User c WHERE c.id = " + reception1.getId().getUsersId()
                );
                List<User> listUsers = q.getResultList();
                for (User user : listUsers) {
                    out.println(user.getFirstname() + " " + user.getLastname());
                }
                q = dataBase.em.createQuery(
                        "SELECT c FROM Time c WHERE c.id.id = " + reception1.getId().getTimesId()
                );
                List<Time> listTime1 = q.getResultList();
                for (Time time1 : listTime1) {
                    q = dataBase.em.createQuery(
                            "SELECT c FROM Shedule c WHERE c.id = " + time1.getId().getSheduleId()
                    );
                    List<Shedule> listShedule1 = q.getResultList();
                    for (Shedule shedule1 : listShedule1) {
                        out.println(shedule1.getDay());
                        out.println(shedule1.getTimeBegin());
                        out.println(shedule1.getTimeEnd());
                    }
                }
            }


        }
        dataBase.em.close();
        dataBase.emf.close();
    }

    public void destroy() {
    }
}