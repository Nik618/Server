package com.example.server.servlets;

import com.example.server.entities.Department;
import com.example.server.entities.Diagnosis;
import com.example.server.entities.Prescribedtreatment;
import com.example.server.entities.Treatment;
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

@WebServlet(name = "treatmentData", value = "/treatmentData")
public class TreatmentDataServlet extends HttpServlet {

    Query q;
    DataBase dataBase;

    public TreatmentDataServlet() {

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
        String receptionId = request.getParameter("receptionId");

        if (receptionId == null) {
            q = dataBase.em.createQuery(
                    "SELECT c FROM Treatment c"
            );
            List<Treatment> list = q.getResultList();
            for (Treatment treatment : list) {
                out.println(treatment.getName());
                out.println(treatment.getId());
            }
        } else {

            q = dataBase.em.createQuery(
                    "SELECT c FROM Prescribedtreatment c WHERE c.id.receptionId = " + Integer.parseInt(receptionId)
            );
            List<Prescribedtreatment> listPrescribedtreatment = q.getResultList();
            for (Prescribedtreatment prescribedtreatment : listPrescribedtreatment) {
                q = dataBase.em.createQuery(
                        "SELECT c FROM Treatment c WHERE c.id = " + prescribedtreatment.getId().getTreatmentId()
                );
                List<Treatment> listTreatment = q.getResultList();
                for (Treatment treatment : listTreatment) {
                    out.println(treatment.getDescription());
                }
            }
        }
    }

    public void destroy() {
    }
}