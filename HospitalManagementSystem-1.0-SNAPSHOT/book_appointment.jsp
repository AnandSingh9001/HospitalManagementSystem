<%@ page import="jakarta.persistence.*, java.util.*, com.hospital.model.Patient, com.hospital.model.Doctor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Book Appointment</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        form {
            width: 50%;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
        }

        label, select, input {
            display: block;
            width: 100%;
            margin-bottom: 15px;
        }

        input[type="submit"] {
            background-color: #28a745;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }

        .error {
            color: red;
            text-align: center;
        }
    </style>
</head>
<body>
<h2>Book an Appointment</h2>

<%
    EntityManagerFactory emf = null;
    EntityManager em = null;
    List<Doctor> doctors = null;
    List<Patient> patients = null;

    try {
        emf = Persistence.createEntityManagerFactory("hospital_pu");
        em = emf.createEntityManager();

        TypedQuery<Doctor> doctorQuery = em.createQuery("SELECT d FROM Doctor d", Doctor.class);
        doctors = doctorQuery.getResultList();

        TypedQuery<Patient> patientQuery = em.createQuery("SELECT p FROM Patient p", Patient.class);
        patients = patientQuery.getResultList();
    } catch (Exception e) {
        out.println("<p class='error'>Error loading data: " + e.getMessage() + "</p>");
    }
%>

<form method="post" action="addAppointment">
    <label for="patientId">Select Patient:</label>
    <select name="patientId" id="patientId" required>
        <option value="">-- Select Patient --</option>
        <%
            for (Patient p : patients) {
        %>
        <option value="<%= p.getId() %>"><%= p.getName() %> (ID: <%= p.getId() %>)</option>
        <%
            }
        %>
    </select>

    <label for="doctorId">Select Doctor:</label>
    <select name="doctorId" id="doctorId" required>
        <option value="">-- Select Doctor --</option>
        <%
            for (Doctor d : doctors) {
        %>
        <option value="<%= d.getId() %>"><%= d.getName() %> (ID: <%= d.getId() %>)</option>
        <%
            }
        %>
    </select>

    <label for="date">Date (YYYY-MM-DD):</label>
    <input type="date" name="date" required>

    <label for="time">Time (HH:MM):</label>
    <input type="time" name="time" required>

    <input type="submit" value="Book Appointment">
</form>

<%
    if (em != null) em.close();
    if (emf != null) emf.close();
%>

</body>
</html>
