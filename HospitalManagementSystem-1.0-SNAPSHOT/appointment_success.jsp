<%
    Boolean success = (Boolean) request.getAttribute("success");
%>
<!DOCTYPE html>
<html>
<head><title>Appointment Status</title></head>
<body>
<% if (success != null && success) { %>
    <h2>? Appointment booked successfully!</h2>
<% } else { %>
    <h2>? Failed to book appointment. Try again.</h2>
<% } %>
</body>
</html>

