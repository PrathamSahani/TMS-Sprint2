<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>E-Trains</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="background-color: #f4f6f9;">

<!-- Header -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">National Ticket Booking Spot</a>
    <div class="d-flex">
      <a class="btn btn-light me-2" href="UserLogin.jsp">Login as User</a>
      <a class="btn btn-light me-2" href="UserRegister.jsp">New User Register</a>
      <a class="btn btn-light" href="AdminLogin.jsp">Login as Admin</a>
    </div>
  </div>
</nav>

<!-- Admin Login Title -->
<div class="container mt-5">
  <div class="alert alert-success text-center fs-5 fw-bold">
    Admin Login
  </div>

  <!-- Login Form -->
  <div class="card shadow-sm">
    <div class="card-body">
      <form action="adminlogin" method="post">
        <div class="mb-3">
          <label for="uname" class="form-label">Username</label>
          <input type="text" class="form-control" id="uname" name="uname" placeholder="Enter Your EmailId">
        </div>
        <div class="mb-3">
          <label for="pword" class="form-label">Password</label>
          <input type="password" class="form-control" id="pword" name="pword">
        </div>
        <button type="submit" class="btn btn-primary w-100">LOGIN</button>
      </form>
    </div>
  </div>
</div>

<!-- Bootstrap JS (optional but recommended for responsive features) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
