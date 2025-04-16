package com.hospital.servlet;

import com.hospital.dao.DoctorDAO;
import com.hospital.model.Doctor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/viewDoctors")
public class DoctorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EntityManagerFactory emf;

    @Override
    public void init() throws ServletException {
        emf = Persistence.createEntityManagerFactory("hospital_pu");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();
        DoctorDAO dao = new DoctorDAO(em);
        List<Doctor> doctors = dao.getAllDoctors();

        request.setAttribute("doctorList", doctors);
        request.getRequestDispatcher("doctors.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        if (emf != null) {
            emf.close();
        }
    }
}
