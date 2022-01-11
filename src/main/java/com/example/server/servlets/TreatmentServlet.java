package com.example.server.servlets;

import com.example.server.entities.*;
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
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "treatment", value = "/treatment")
public class TreatmentServlet extends HttpServlet {

    Query q;
    DataBase dataBase;

    public TreatmentServlet() {

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
        String diagnosisId = request.getParameter("diagnosisId");
        String treatmentId = request.getParameter("treatmentId");
        String receptionId = request.getParameter("receptionId");

        dataBase.open();

        Prescribedtreatment prescribedtreatment = new Prescribedtreatment();
        PrescribedtreatmentId prescribedtreatmentId = new PrescribedtreatmentId();
        prescribedtreatmentId.setDiagnosisId(Integer.parseInt(diagnosisId));
        prescribedtreatmentId.setReceptionId(Integer.parseInt(receptionId));
        prescribedtreatmentId.setTreatmentId(Integer.parseInt(treatmentId));

        q = dataBase.em.createQuery(
                "SELECT c FROM Reception c WHERE c.id.id = " + Integer.parseInt(receptionId)
        );
        List<Reception> listReception = q.getResultList();
        for (Reception reception : listReception) {
            prescribedtreatmentId.setReceptionTimesId(reception.getId().getTimesId());
            prescribedtreatmentId.setReceptionUsersId(reception.getId().getUsersId());
            prescribedtreatmentId.setReceptionTimesSheduleId(reception.getId().getTimesSheduleId());
            prescribedtreatmentId.setReceptionTimesEmployeesId(reception.getId().getTimesEmployeesId());
        }
        prescribedtreatment.setId(prescribedtreatmentId);
        dataBase.em.persist(prescribedtreatment);
        dataBase.et.commit();

        dataBase.em.close();
        dataBase.emf.close();
    }

    public void destroy() {
    }
}