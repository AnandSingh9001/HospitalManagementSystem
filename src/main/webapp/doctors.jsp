<%@ page import="jakarta.persistence.*, java.util.*, com.hospital.model.Doctor" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Doctor List</title>
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
            background-color: #4CAF50;
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
    <h2>List of Doctors</h2>

    <%
        List<Doctor> doctors = null;
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("hospital_pu");
            em = emf.createEntityManager();
            TypedQuery<Doctor> query = em.createQuery("SELECT d FROM Doctor d", Doctor.class);
            doctors = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>

    <%
        if (doctors != null && !doctors.isEmpty()) {
    %>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Specialization</th>
            </tr>
            <%
                for (Doctor doc : doctors) {
            %>
                <tr>
                    <td><%= doc.getId() %></td>
                    <td><%= doc.getName() %></td>
                    <td><%= doc.getSpecialization() %></td>
                </tr>
            <%
                }
            %>
        </table>
    <%
        } else {
    %>
        <p class="no-data">No doctors found in the database.</p>
    <%
        }

        if (em != null) em.close();
        if (emf != null) emf.close();
    %>

</body>
</html>
