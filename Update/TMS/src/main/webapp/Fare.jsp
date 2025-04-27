<%@ page language="java" contentType="text/html; charset=UTF-8" 
         pageEncoding="UTF-8"
         import="java.util.List, com.tms.beans.TrainBean" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Fare Enquiry — E-Trains</title>
    <!-- Bootstrap CSS -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-…"
      crossorigin="anonymous">
  </head>
  <body class="bg-light">

    <!-- NAVBAR -->
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
            <li class="nav-item"><a class="nav-link" href="userhome">Home</a></li>
            <li class="nav-item"><a class="nav-link" href="userviewtrainfwd">View Trains</a></li>
            <li class="nav-item"><a class="nav-link" href="trainbwstnfwd">Between Stations</a></li>
            <li class="nav-item"><a class="nav-link" href="bookingdetails">History</a></li>
            <li class="nav-item"><a class="nav-link active" href="fareenqfwd">Fare Enquiry</a></li>
            <li class="nav-item"><a class="nav-link" href="useravailfwd">Seat Availability</a></li>
            <li class="nav-item"><a class="nav-link" href="usersearchtrain">Search By TrainNo</a></li>
            <li class="nav-item"><a class="nav-link" href="userprofile">Profile</a></li>
            <li class="nav-item"><a class="nav-link" href="userlogout">Logout</a></li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="container my-5">

      <!-- Input Form Card -->
      <div class="card shadow-sm mb-4">
        <div class="card-header bg-primary text-white">
          <h5 class="mb-0">Fare Enquiry for Trains Between Stations</h5>
        </div>
        <div class="card-body">
          <form action="fareenq" method="post" class="row g-3">
            <div class="col-md-6">
              <label for="fromstation" class="form-label">From Station</label>
              <input type="text" class="form-control" id="fromstation" name="fromstation" required>
            </div>
            <div class="col-md-6">
              <label for="tostation" class="form-label">To Station</label>
              <input type="text" class="form-control" id="tostation" name="tostation" required>
            </div>
            <div class="col-12 text-end">
              <button type="submit" class="btn btn-primary">Get Fare</button>
            </div>
          </form>
        </div>
      </div>

      <% 
         // Pull in whatever the servlet set
         List<TrainBean> trains = (List<TrainBean>) request.getAttribute("trains");
         String fromStation = (String) request.getAttribute("fromStation");
         String toStation   = (String) request.getAttribute("toStation");

         if (trains != null) {
      %>

      <!-- Results Card -->
      <div class="card shadow-sm">
        <div class="card-header">
          <h5 class="mb-0">Fare Results</h5>
        </div>
        <div class="card-body">
          <% if (!trains.isEmpty()) { %>
            <p class="lead">
              Fare for trains between 
              <strong><%= fromStation %></strong> and 
              <strong><%= toStation %></strong>:
            </p>
            <div class="table-responsive">
              <table class="table table-striped table-hover align-middle">
                <thead class="table-light">
                  <tr>
                    <th>Train Name</th>
                    <th>Train No</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Time</th>
                    <th>Seats</th>
                    <th>Fare (₹)</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>
                  <% for (TrainBean train : trains) {
                       int hr  = (int)(Math.random()*24);
                       int min = (int)(Math.random()*60);
                       String time = String.format("%02d:%02d", hr, min);
                  %>
                  <tr>
                    <td><%= train.getTr_name() %></td>
                    <td><%= train.getTr_no()   %></td>
                    <td><%= train.getFrom_stn() %></td>
                    <td><%= train.getTo_stn()   %></td>
                    <td><%= time                %></td>
                    <td><%= train.getSeats()    %></td>
                    <td><%= train.getFare()     %></td>
                    <td>
                      <a 
                        class="btn btn-sm btn-success"
                        href="booktrainbyref?trainNo=<%=train.getTr_no()%>
                             &amp;fromStn=<%=train.getFrom_stn()%>
                             &amp;toStn=<%=train.getTo_stn()%>">
                        Book Now
                      </a>
                    </td>
                  </tr>
                  <% } %>
                </tbody>
              </table>
            </div>
          <% } else { %>
            <div class="alert alert-warning">
              No trains found between 
              <strong><%= fromStation %></strong> and 
              <strong><%= toStation   %></strong>.
            </div>
          <% } %>
        </div>
      </div>

      <% } %>

    </div>

    <!-- Bootstrap JS (Bundle includes Popper) -->
    <script 
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-…"
      crossorigin="anonymous">
    </script>
  </body>
</html>
