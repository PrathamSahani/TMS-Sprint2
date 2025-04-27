<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.tms.beans.TrainBean" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin View Trains</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="bg-light">
   <!-- Bootstrap Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">National Ticket Booking Spot</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarNav" aria-controls="navbarNav"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            
            <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="AdminHome.jsp">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="adminviewtrainfwd">View Trains</a></li>
                    <li class="nav-item"><a class="nav-link" href="adminsearchtrainfwd">Search By TrainNo</a></li>
                    <li class="nav-item"><a class="nav-link" href="addtrainfwd">Add Train</a></li>
                    <li class="nav-item"><a class="nav-link" href="cancletrainfwd">Delete Train</a></li>
                    <li class="nav-item"><a class="nav-link" href="updatetrain">Update Train Details</a></li>
                    <li class="nav-item"><a class="nav-link" href="adminlogout">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>

<div class="container mt-5">
	<h2 class="text-center mb-4">Running Trains</h2>

	<%
		List<TrainBean> trains = (List<TrainBean>) request.getAttribute("trains");
		if (trains != null && !trains.isEmpty()) {
	%>
		<table class="table table-bordered table-hover table-striped">
			<thead class="thead-dark">
				<tr>
					<th>Train Name</th>
					<th>Train Number</th>
					<th>From Station</th>
					<th>To Station</th>
					<th>Seats Available</th>
					<th>Fare (INR)</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<% for (TrainBean train : trains) { %>
				<tr>
					<td>
						<a href="viewadmin?trainNo=<%=train.getTr_no()%>&fromStn=<%=train.getFrom_stn()%>&toStn=<%=train.getTo_stn()%>">
							<%=train.getTr_name()%>
						</a>
					</td>
					<td><%=train.getTr_no()%></td>
					<td><%=train.getFrom_stn()%></td>
					<td><%=train.getTo_stn()%></td>
					<td><%=train.getSeats()%></td>
					<td><%=train.getFare()%> ₹</td>
					<td><a class="btn btn-primary btn-sm" href="adminupdatetrain?trainnumber=<%=train.getTr_no()%>">Update</a></td>
				</tr>
				<% } %>
			</tbody>
		</table>
	<%
		} else {
	%>
		<div class="alert alert-warning text-center" role="alert">
			No Running Trains
		</div>
	<%
		}
	%>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
