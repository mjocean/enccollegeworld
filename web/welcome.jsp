<%@ page import="com.endicott.edu.ui.UiMessage" %>
<%@ page import="com.endicott.edu.models.CollegeModel" %><%--
  Created by IntelliJ IDEA.The user will need to add a minimum of three employees in order for a department to run efficiently.
  Date: 8/25/2017
  Time: 8:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Super College Simulator</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href = "resources/style.css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

<style>

  body {
    background: linear-gradient( rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.3) ), url("resources/images/largecampus.png");
    display: -ms-flexbox;
    display: flex;
    -ms-flex-align: center;
    align-items: center;
    padding-top: 40px;
    padding-bottom: 40px;
  }

  .jumbotron {
    background-color: rgba(238, 238, 238, .9);
    text-align: center;
  }

  .form-control {
    width: 50%;
    margin: 30px auto 0;
  }

  .btn {
    margin: 0 0.5%;
  }

</style>


</head>
<body>
<%
  UiMessage msg = (UiMessage) request.getAttribute("message");
  if (msg == null) {
      msg = new UiMessage();
      msg.setMessage("");
  }
  CollegeModel college = (CollegeModel) request.getAttribute("college");
  if (college == null) {
      college = new CollegeModel();
  }
%>
<p></p>
<p></p>
<div class="container">

  <form action="welcome" method="post">
  <div class="jumbotron">
    <h1>Endicott College World</h1>
    <p>A simulation of college life: students, buildings, sporting events, financials, unexpected events and more.</p>
    <div class="form-group">
      <input type="text" name="runId" class="form-control" id="runId" placeholder="Enter College Name">
    </div>
    <div class="container">
      <input type="submit" class="btn btn-info" name="button" value="Load College">
      <input type="submit" class="btn btn-info" name="button" value="Create College">
      <input type="submit" class="btn btn-danger" name="button" value="Delete College">
    </div>
  </div>

<div class="container">
  <!-- Display a message if defined -->
  <% if (msg.getMessage().length() > 0) { %>
  <div class="alert alert-danger">
    <strong>Info</strong> <%=msg.getMessage()%>
  </div>
  <% } %>

</form>
</div>
</body>
</html>
