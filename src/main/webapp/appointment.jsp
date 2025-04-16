<%@ page import="com.hospital.model.Patient" %>
<%@ page import="com.hospital.model.Doctor" %>
<%@ page import="jakarta.persistence.*" %>
<%@ page import="java.util.List" %>
<%
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hospital_pu");
    EntityManager em = emf.createEntityManager();

    List<Patient> patients = em.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();
    List<Doctor> doctors = em.createQuery("SELECT d FROM Doctor d", Doctor.class).getResultList();

    em.close();
    emf.close();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Book Appointment</title>
</head>
<body>
    <h2>Book a New Appointment</h2>
    <form action="addAppointment" method="post">
        <label>Select Patient:</label><br>
        <select name="patientId" required>
            <%
                for (Patient p : patients) {
            %>
                <option value="<%= p.getId() %>"><%= p.getName() %></option>
            <%
                }
            %>
        </select><br><br>

        <label>Select Doctor:</label><br>
        <select name="doctorId" required>
            <%
                for (Doctor d : doctors) {
            %>
                <option value="<%= d.getId() %>"><%= d.getName() %> - <%= d.getSpecialization() %></option>
            <%
                }
            %>
        </select><br><br>

        <label>Appointment Date:</label><br>
        <input type="date" name="date" required><br><br>

        <label>Appointment Time:</label><br>
        <input type="time" name="time" required><br><br>

        <input type="submit" value="Book Appointment">
    </form>
</body>
</html>

