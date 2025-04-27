<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tms.beans.TrainBean" %>
<%
    TrainBean train = (TrainBean) request.getAttribute("train");
    String trainNo = (String) request.getAttribute("trainNo");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Train - Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
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

    <div class="container mt-4">
        <div class="card">
            <div class="card-header bg-primary text-white">
                Search Trains
            </div>
            <div class="card-body">
                <form action="adminsearchtrain" method="post" class="row g-3">
                    <div class="col-md-6">
                        <label for="trainnumber" class="form-label">Train Number</label>
                        <input type="text" name="trainnumber" id="trainnumber" class="form-control" required>
                    </div>
                    <div class="col-md-6 d-flex align-items-end">
                        <button type="submit" class="btn btn-success w-100">Search Train</button>
                    </div>
                </form>
            </div>
        </div>

        <% if (train != null) { %>
            <div class="card mt-4">
                <div class="card-header bg-success text-white">
                    Searched Train Details
                </div>
                <div class="card-body">
                    <table class="table table-bordered">
                        <tr><th>Train Name</th><td><%= train.getTr_name() %></td></tr>
                        <tr><th>Train Number</th><td><%= train.getTr_no() %></td></tr>
                        <tr><th>From Station</th><td><%= train.getFrom_stn() %></td></tr>
                        <tr><th>To Station</th><td><%= train.getTo_stn() %></td></tr>
                        <tr><th>Available Seats</th><td><%= train.getSeats() %></td></tr>
                        <tr><th>Fare (INR)</th><td><%= train.getFare() %> RS</td></tr>
                    </table>
                </div>
            </div>
        <% } else if (trainNo != null) { %>
            <div class="alert alert-warning mt-4" role="alert">
                Train No. <strong><%= trainNo %></strong> is not available!
            </div>
        <% } %>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
