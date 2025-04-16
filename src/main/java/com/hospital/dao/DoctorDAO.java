package com.hospital.dao;

import com.hospital.model.Doctor;

import jakarta.persistence.EntityManager;
import java.util.List;

public class DoctorDAO {
    private EntityManager em;

    public DoctorDAO(EntityManager em) {
        this.em = em;
    }

    public List<Doctor> getAllDoctors() {
        return em.createQuery("SELECT d FROM Doctor d", Doctor.class).getResultList();
    }
}
