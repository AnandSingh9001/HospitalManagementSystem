//package com.hospital.dao;
//
//import com.hospital.model.Patient;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//
//public class PatientDAO {
//
//    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hospital_db";
//    private static final String JDBC_USERNAME = "root";
//    private static final String JDBC_PASSWORD = "AnandSQL@123";
//
//    public boolean insertPatient(Patient patient) {
//        boolean inserted = false;
//
//        try {
//            // Connect to DB
//            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
//
//            // SQL query to insert patient
//            String sql = "INSERT INTO Patient (name, age, gender, disease) VALUES (?, ?, ?, ?)";
//            PreparedStatement ps = conn.prepareStatement(sql);
//
//            // Set parameters
//            ps.setString(1, patient.getName());
//            ps.setInt(2, patient.getAge());
//            ps.setString(3, patient.getGender());
//            ps.setString(4, patient.getDisease());
//
//            // Execute update
//            int rows = ps.executeUpdate();
//            if (rows > 0) {
//                inserted = true;
//            }
//
//            conn.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return inserted;
//    }
//}


//package com.hospital.dao;
//
//import com.hospital.model.Patient;
//import jakarta.persistence.*;
//
//public class PatientDAO {
//    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("hospital_pu");
//
//    public boolean addPatient(Patient patient) {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.persist(patient);
//            em.getTransaction().commit();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            em.close();
//        }
//    }
//}


package com.hospital.dao;

import com.hospital.model.Patient;
import jakarta.persistence.*;

public class PatientDAO {

    private static final EntityManagerFactory emf = 
        Persistence.createEntityManagerFactory("hospital_pu");

    public boolean addPatient(Patient patient) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        boolean isSuccess = false;

        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(patient);
            tx.commit();
            isSuccess = true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback(); // Rollback on failure
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

        return isSuccess;
    }
}






