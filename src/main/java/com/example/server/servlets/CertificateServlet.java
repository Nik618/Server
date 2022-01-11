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
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "certificate", value = "/certificate")
public class CertificateServlet extends HttpServlet {

    Query q;
    DataBase dataBase;

    public CertificateServlet() {

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
        String diagnos = request.getParameter("diagnos");
        String begin = request.getParameter("begin");
        String end = request.getParameter("end");
        String receptionId = request.getParameter("receptionId");

        dataBase.open();

        if (diagnos != null) {
            Medicalcertificate medicalcertificate = new Medicalcertificate();
            LocalDate beginInstant = LocalDate.parse(begin);
            LocalDate endInstant = LocalDate.parse(end);
            medicalcertificate.setDataBegin(beginInstant);
            medicalcertificate.setDataEnd(endInstant);

            MedicalcertificateId medicalcertificateId = new MedicalcertificateId();

            q = dataBase.em.createQuery(
                    "SELECT c FROM Diagnosis c WHERE c.name LIKE :diagnos"
            );
            q.setParameter("diagnos", diagnos);
            List<Diagnosis> listDiagnosis = q.getResultList();
            for (Diagnosis diagnosis : listDiagnosis) {
                medicalcertificateId.setDiagnosisId(diagnosis.getId());
            }

            medicalcertificateId.setReceptionId(Integer.parseInt(receptionId));

            q = dataBase.em.createQuery(
                    "SELECT c FROM Reception c WHERE c.id.id = " + Integer.parseInt(receptionId)
            );
            List<Reception> listReception = q.getResultList();
            for (Reception reception : listReception) {
                medicalcertificateId.setReceptionTimesId(reception.getId().getTimesId());
                medicalcertificateId.setReceptionUsersId(reception.getId().getUsersId());
                medicalcertificateId.setReceptionTimesSheduleId(reception.getId().getTimesSheduleId());
                medicalcertificateId.setReceptionTimesEmployeesId(reception.getId().getTimesEmployeesId());
            }
            medicalcertificate.setId(medicalcertificateId);
            dataBase.em.persist(medicalcertificate);
            dataBase.et.commit();
        } else {
            q = dataBase.em.createQuery(
                    "SELECT c FROM Medicalcertificate c WHERE c.id.receptionId = " + Integer.parseInt(receptionId)
            );
            List<Medicalcertificate> listMedicalcertificate = q.getResultList();
            for (Medicalcertificate medicalcertificate : listMedicalcertificate) {
                //out.println(medicalcertificate.getId());
                out.println(medicalcertificate.getDataBegin());
                out.println(medicalcertificate.getDataEnd());
                q = dataBase.em.createQuery(
                        "SELECT c FROM Diagnosis c WHERE c.id = " + medicalcertificate.getId().getDiagnosisId()
                );
                List<Diagnosis> listDiagnosis = q.getResultList();
                for (Diagnosis diagnosis : listDiagnosis) {
                    out.println(diagnosis.getName());
                }
            }
        }

        dataBase.em.close();
        dataBase.emf.close();
    }

    public void destroy() {
    }
}