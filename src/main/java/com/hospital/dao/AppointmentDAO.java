//package com.hospital.dao;
//
//import com.hospital.model.Appointment;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;

//public class AppointmentDAO {
//    private EntityManagerFactory emf;
//
//    public AppointmentDAO() {
//        emf = Persistence.createEntityManagerFactory("hospital_pu");
//    }
//
//    public boolean insertAppointment(Appointment appointment) {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.persist(appointment);
//            em.getTransaction().commit();
//            return true;
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//            e.printStackTrace();
//            return false;
//        } finally {
//            em.close();
//        }
//    }
//}
//



//package com.hospital.dao;
//
//import com.hospital.model.Appointment;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//
//public class AppointmentDAO {
//    private EntityManagerFactory emf;
//
//    // Constructor to accept existing EntityManagerFactory
//    public AppointmentDAO(EntityManagerFactory emf) {
//        this.emf = emf;
//    }
//
//    // Insert a new appointment into the database
//    public boolean insertAppointment(Appointment appointment) {
//        EntityManager em = null;
//        try {
//            em = emf.createEntityManager();
//            em.getTransaction().begin();
//            em.persist(appointment);
//            em.getTransaction().commit();
//            System.out.println("Appointment inserted successfully.");
//            return true;
//        } catch (Exception e) {
//            if (em != null && em.getTransaction().isActive()) {
//                em.getTransaction().rollback();
//            }
//            System.err.println("Insert failed: " + e);
//            e.printStackTrace();
//            return false;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//}


package com.hospital.dao;

import com.hospital.model.Appointment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class AppointmentDAO {
    private EntityManagerFactory emf;

    public AppointmentDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public boolean insertAppointment(Appointment appointment) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            em.persist(appointment);

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace(); // 👈 This will show exact error in Tomcat log
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}

