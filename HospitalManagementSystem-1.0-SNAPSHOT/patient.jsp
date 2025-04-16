<%@ page import="jakarta.persistence.*, java.util.*, com.hospital.model.Patient" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Patient List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        table {
            margin: 20px auto;
            width: 80%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px 15px;
            border: 1px solid #ccc;
            text-align: center;
        }

        th {
            background-color: #2196F3;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .no-data {
            text-align: center;
            color: red;
        }
    </style>
</head>
<body>
    <h2>List of Patients</h2>

    <%
        List<Patient> patients = null;
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("hospital_pu");
            em = emf.createEntityManager();
            TypedQuery<Patient> query = em.createQuery("SELECT p FROM Patient p", Patient.class);
            patients = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>

    <%
        if (patients != null && !patients.isEmpty()) {
    %>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
                <th>Gender</th>
            </tr>
            <%
                for (Patient p : patients) {
            %>
                <tr>
                    <td><%= p.getId() %></td>
                    <td><%= p.getName() %></td>
                    <td><%= p.getAge() %></td>
                    <td><%= p.getGender() %></td>
                </tr>
            <%
                }
            %>
        </table>
    <%
        } else {
    %>
        <p class="no-data">No patients found in the database.</p>
    <%
        }

        if (em != null) em.close();
        if (emf != null) emf.close();
    %>

</body>
</html>
