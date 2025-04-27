<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>E-Trains - User Home</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-..." crossorigin="anonymous">
  <!-- Animate.css -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" integrity="sha512-b7gO+X..." crossorigin="anonymous" referrerpolicy="no-referrer" />
  <!-- Optional custom CSS -->
  <link rel="stylesheet" href="UserHome_Css.css">
</head>
<body class="bg-light">
  <!-- Navbar -->
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

  <!-- Main Content -->
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card shadow-sm animate__animated animate__zoomIn">
          <div class="card-header bg-primary text-white">
            <h5 class="mb-0">Trains Enquiry</h5>
          </div>
          <div class="card-body">
            <form action="searchtrainservlet" method="post" class="row g-3">
              <div class="col-12">
                <label for="trainnumber" class="form-label">Train Number</label>
                <input type="text" class="form-control" id="trainnumber" name="trainnumber" placeholder="Enter train number" required>
              </div>
              <div class="col-12 text-end">
                <button type="submit" class="btn btn-primary btn-lg">Search Train</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap JS Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-..." crossorigin="anonymous"></script>
</body>
</html>