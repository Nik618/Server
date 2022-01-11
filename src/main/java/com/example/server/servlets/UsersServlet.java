package com.example.server.servlets;

import com.example.server.entities.Employee;
import com.example.server.entities.EmployeeId;
import com.example.server.entities.Reception;
import com.example.server.entities.User;
import com.example.server.models.DataBase;

import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "dataemp", value = "/dataemp")
public class UsersServlet extends HttpServlet {
    DataBase dataBase;

    public UsersServlet() {
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
        String deleteIndex = request.getParameter("deleteIndex");
        String t1 = request.getParameter("t1");
        String t2 = request.getParameter("t2");
        String t3 = request.getParameter("t3");
        String t4 = request.getParameter("t4");
        String t5 = request.getParameter("t5");
        String t6 = request.getParameter("t6");
        String t7 = request.getParameter("t7");

        dataBase.open();

        if ((t1 != null) && (t2 != null) && (t3 != null) && (t4 != null) && (t5 != null) && (t6 != null) && (t7 != null)) {
            User user = new User();
            user.setLogin(t1);
            user.setPassword(t2);
            user.setPassport(t3);
            user.setBirth(t4);
            user.setFirstname(t5);
            user.setLastname(t6);
            user.setRole("2");
            dataBase.em.persist(user);
            dataBase.et.commit();
            dataBase.et.begin();
            Employee employee = new Employee();
            EmployeeId employeeId = new EmployeeId();
            employeeId.setUsersId(user.getId());
            employeeId.setDepartmentId(Integer.parseInt(t7));
            employee.setId(employeeId);
            dataBase.em.persist(employee);
            dataBase.et.commit();
        }

        if (deleteIndex != null) {
            User user = dataBase.em.find(User.class, Integer.parseInt(deleteIndex));
            Query q;
            q = dataBase.em.createQuery(
                    "SELECT c FROM Employee c WHERE c.id.usersId = " + user.getId()
            );
            List<Employee> employees = q.getResultList();
            for (Employee employee : employees) {
                dataBase.em.remove(employee);
                dataBase.et.commit();
            }
            dataBase.em.remove(user);
            dataBase.et.commit();
        }

        Query q;
        q = dataBase.em.createQuery(
                "SELECT c FROM User c"
        );
        List<User> list = q.getResultList();
        for (User user : list) {
            out.println(user.getLogin());
            out.println(user.getPassword());
            out.println(user.getPassport());
            out.println(user.getBirth());
            out.println(user.getFirstname());
            out.println(user.getLastname());
            out.println(user.getRole());
            out.println(user.getId());
        }

        dataBase.em.close();
        dataBase.emf.close();
    }

    public void destroy() {
    }
}