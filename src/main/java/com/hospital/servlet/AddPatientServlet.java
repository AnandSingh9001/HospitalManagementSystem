//package com.hospital.servlet;
//
////import com.mycompany.hospitalmanagementsystem.servlets.*;
//import com.hospital.dao.PatientDAO;
//import com.hospital.model.Patient;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@WebServlet("/addPatient")
//public class AddPatientServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        // Get form data from request
//        String name = request.getParameter("name");
//        int age = Integer.parseInt(request.getParameter("age"));
//        String gender = request.getParameter("gender");
//        String disease = request.getParameter("disease");
//
//        // Create a new Patient object
//        Patient patient = new Patient();
//        patient.setName(name);
//        patient.setAge(age);
//        patient.setGender(gender);
//        patient.setDisease(disease);
//
//        // Insert patient using DAO
//        PatientDAO dao = new PatientDAO();
//        boolean result = dao.insertPatient(patient);
//
//        // Respond to client
//        if (result) {
//            response.getWriter().println("<h2>Patient added successfully!</h2>");
//        } else {
//            response.getWriter().println("<h2>Error adding patient. Please try again.</h2>");
//        }
//    }
//}




package com.hospital.servlet;

import com.hospital.dao.PatientDAO;
import com.hospital.model.Patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/AddPatientServlet")
public class AddPatientServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String disease = request.getParameter("disease");
        String contact = request.getParameter("contact");

        Patient patient = new Patient();
        patient.setName(name);
        patient.setAge(age);
        patient.setGender(gender);
        patient.setDisease(disease);
        patient.setContact(contact);

        PatientDAO patientDAO = new PatientDAO();
        boolean success = patientDAO.addPatient(patient);

        if (success) {
            response.sendRedirect("index.html"); // or show a success page
        } else {
            response.getWriter().println("Failed to add patient.");
        }
    }
}

