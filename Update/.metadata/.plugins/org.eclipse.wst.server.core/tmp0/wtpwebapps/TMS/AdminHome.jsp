<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>E-Trains</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Optional: Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body style="background-color: #f4f6f9;">

<!-- Navigation Header -->
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

<!-- Welcome Section -->
<div class="container mt-5">
  <div class="card shadow-sm">
    <div class="card-body text-center">
      <h5 class="card-title">Hey, Admin!</h5>
      <p class="card-text">Welcome to our new NITRTC Website</p>
    </div>
  </div>
</div>

</body>
</html>
