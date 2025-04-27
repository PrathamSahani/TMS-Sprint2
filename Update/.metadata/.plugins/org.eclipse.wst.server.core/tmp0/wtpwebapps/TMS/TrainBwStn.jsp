<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trains Between Stations</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background: #f8f9fa; }
        .search-container { margin-top: 60px; }
        .form-control:focus { box-shadow: 0 0 0 0.2rem rgba(13, 110, 253, .25); }
        .animated-form { animation: fadeIn 1s ease-in-out; }
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to   { opacity: 1; transform: translateY(0); }
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">National Ticket Booking Spot</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/userhome">Home</a></li>
          <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/userviewtrainfwd">View Trains</a></li>
          <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/trainbwstnfwd">Trains Between Stations</a></li>
          <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/bookingdetails">Booking History</a></li>
          <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/fareenqfwd">Fare Enquiry</a></li>
          <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/useravailfwd">Seat Availability</a></li>
          <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/usersearchtrain">Search By TrainNo</a></li>
          <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/userhome">Profile</a></li>
        </ul>
        <div class="d-flex">
          <a class="btn btn-light" href="${pageContext.request.contextPath}/userlogout">Logout</a>
        </div>
      </div>
    </div>
  </nav>
<div class="container search-container">
    <div class="card shadow animated-form">
        <div class="card-header bg-primary text-white">
            <h5 class="mb-0">Find Trains Between Stations</h5>
        </div>
        <div class="card-body">
            <form action="trainbwstn" method="post">
                <div class="mb-3">
                    <label for="fromstation" class="form-label">From Station</label>
                    <input type="text" name="fromstation" id="fromstation" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="tostation" class="form-label">To Station</label>
                    <input type="text" name="tostation" id="tostation" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-success w-100">Search Train</button>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>