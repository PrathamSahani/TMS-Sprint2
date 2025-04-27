<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tms.beans.TrainBean" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>National Ticket Booking Spot - Update Train</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

  <!-- Navbar -->
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
          <li class="nav-item"><a class="nav-link active" aria-current="page" href="updatetrain">Update Train Details</a></li>
          <li class="nav-item"><a class="nav-link" href="adminlogout">Logout</a></li>
        </ul>
      </div>
    </div>
  </nav>

  <div class="container my-5">
    <%
      TrainBean train = (TrainBean) request.getAttribute("train");
      String message = (String) request.getAttribute("message");
      if (train == null) {
    %>
      <!-- Search form -->
      <div class="card shadow-sm">
        <div class="card-header bg-primary text-white">
          <h5 class="mb-0">Search Train to Update</h5>
        </div>
        <div class="card-body">
          <form action="adminupdatetrain" method="post" class="row g-3">
            <div class="col-md-8">
              <label for="trainnumber" class="form-label">Train Number</label>
              <input type="text" id="trainnumber" name="trainnumber" class="form-control" required>
            </div>
            <div class="col-md-4 align-self-end">
              <button type="submit" class="btn btn-primary w-100">Search</button>
            </div>
          </form>
          <c:if test="${not empty message}">
            <div class="alert alert-warning mt-3">${message}</div>
          </c:if>
        </div>
      </div>
    <%
      } else {
    %>
      <!-- Update form -->
      <div class="card shadow-sm">
        <div class="card-header bg-success text-white">
          <h5 class="mb-0">Update Train Schedule</h5>
        </div>
        <div class="card-body">
          <form action="updatetrainschedule" method="post" class="row g-3">
            <div class="col-md-6">
              <label class="form-label">Train No</label>
              <input type="text" name="trainno" class="form-control" value="<%= train.getTr_no() %>" readonly>
            </div>
            <div class="col-md-6">
              <label class="form-label">Train Name</label>
              <input type="text" name="trainname" class="form-control" value="<%= train.getTr_name() %>">
            </div>
            <div class="col-md-6">
              <label class="form-label">From Station</label>
              <input type="text" name="fromstation" class="form-control" value="<%= train.getFrom_stn() %>">
            </div>
            <div class="col-md-6">
              <label class="form-label">To Station</label>
              <input type="text" name="tostation" class="form-control" value="<%= train.getTo_stn() %>">
            </div>
            <div class="col-md-6">
              <label class="form-label">Available Seats</label>
              <input type="number" name="available" class="form-control" value="<%= train.getSeats() %>">
            </div>
            <div class="col-md-6">
              <label class="form-label">Fare (INR)</label>
              <input type="text" name="fare" class="form-control" value="<%= train.getFare() %>">
            </div>
            <div class="col-12">
              <button type="submit" class="btn btn-success">Update Train Schedule</button>
            </div>
          </form>
        </div>
      </div>
    <%
      }
    %>
  </div>

  <!-- Bootstrap JS (Popper + Bootstrap) -->
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
