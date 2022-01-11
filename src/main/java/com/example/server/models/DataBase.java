package com.example.server.models;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.*;

public class DataBase {
    public Connection connection;
    public EntityManagerFactory emf;
    public EntityManager em;
    public EntityTransaction et;

    public DataBase() throws SQLException {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();

        DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
        String url = "jdbc:mysql://localhost:3306/mydbtest";
        String username = "root";
        String password = "root";
        connection = DriverManager.getConnection(url, username, password);
        if (!connection.isClosed()) System.out.println("connection successful!");
    }

    public void open() {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        et = em.getTransaction();
        et.begin();
    }

}
