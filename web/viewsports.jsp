<%--
  Created by IntelliJ IDEA.
  User: abrocken
  Date: 9/15/2017
  Time: 7:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.endicott.edu.ui.UiMessage" %>
<%@ page import="com.endicott.edu.models.*" %>
<%@ page import="com.endicott.edu.simulators.CollegeManager" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.endicott.edu.simulators.TutorialManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.endicott.edu.simulators.CoachManager" %>
<%@ page import="com.endicott.edu.simulators.SportManager" %>

<%--
  Created by IntelliJ IDEA.
  User: abrocken
  Date: 8/25/2017
  Time: 8:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>College World Sport</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="resources/style.css">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
          integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
            integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
            crossorigin="anonymous"></script>
</head>
<body>
<%
    //if addSportResultMsg is null, the sport was succesfully created. If not, there was a problem
    String addSportResultMsg = (String) request.getAttribute("addSportResultMsg");
    if (addSportResultMsg == null){
        addSportResultMsg = "success";
    }

    UiMessage msg = (UiMessage) request.getAttribute("message");
    if (msg == null) {
        msg = new UiMessage();
    }
    CollegeModel college = (CollegeModel) request.getAttribute("college");
    if (college == null) {
        college = new CollegeModel();
        msg.setMessage("Attribute for college missing.");
    }
    SportModel sport[] = (SportModel[]) request.getAttribute("sports");
    if (sport == null) {
        sport = new SportModel[0];  // This is really bad
        msg.setMessage(msg.getMessage() + " Attribute for sports missing.");
    }
    SportModel availableSports[] = (SportModel[]) request.getAttribute("availableSports");
    //need to change this later if col has all sports
    if (availableSports == null){
        msg.setMessage(msg.getMessage() + " Issue with getting available sports.");
    }
    NewsFeedItemModel news[] = (NewsFeedItemModel[]) request.getAttribute("news");
    if (news == null) {
        news = new NewsFeedItemModel[0];  // This is really bad
        msg.setMessage(msg.getMessage() + "Attribute for news missing.");
    }
    StudentModel students[] = (StudentModel[]) request.getAttribute("students");
    if (students == null) {
        students  = new StudentModel[0];  // This is really bad
        msg.setMessage(msg.getMessage() + " Attribute for students missing.");
    }
    NumberFormat numberFormatter = NumberFormat.getInstance();
    numberFormatter.setGroupingUsed(true);
    TutorialModel tip = TutorialManager.getCurrentTip("viewSports", college.getRunId());

    ArrayList<CoachModel> teamCoaches = CoachManager.getCollegeCoaches();
    if (teamCoaches == null)
        teamCoaches = new ArrayList<CoachModel>();
%>

<% if (!(addSportResultMsg == "success")) { %>
<script type="text/javascript">
    $(document).ready(function(){
        $("#addSportErrorModal").modal('show');
    });
</script>
<% } %>


<form action="viewSports" method="post">

    <!-- Navigation Bar -->
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                    <li><a href="viewCollege"><%=college.getRunId()%>
                    </a></li>
                    <li><a href="viewStudent">Students</a></li>
                    <li><a href="viewBuilding">Buildings</a></li>
                    <li class="active"><a href="viewSports">Sports</a></li>
                    <li><a href="viewFaculty">Faculty</a></li>
                    <li><a href="viewGates">Objectives</a></li>
                    <li><a href="viewStore">Store</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="viewBalance">$<%=numberFormatter.format(college.getAvailableCash())%></a></li>
                    <li><a> <%=new SimpleDateFormat("MMM dd").format(CollegeManager.getCollegeDate(college.getRunId()))%> </a></li>
                    <li><a href="viewAdmin">Admin</a></li>
                    <li><a href="about.jsp">About</a></li>
                    <li><a href="welcome.jsp"><span class="glyphicon glyphicon-log-out"></span>Exit</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- If addSportResultMsg is not "success", there was an error adding a sport. Display the error modal -->
    <div class="modal" tabindex="-1" role="dialog" id="addSportErrorModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Can't Add Team:</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p><%=addSportResultMsg%></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <!--create array of sport teams -->
    <div class="container">
        <div class="jumbotron">
            <div class="row">
                <div class="col-md-2">
                    <img class="img-responsive" src="resources/images/stadium.png">
                </div>
                <div class="col-md-5">

                    <h2>Sports</h2>
                    <h3><%=sport.length%> sports</h3>
                </div>

                <!-- Tips -->
                <%if (tip != null){%>
                <div class="col-md-5">
                    <div class="well well-lg" style="background: #ffffff">
                        <%if (!tip.getImage().equals("")){%>
                        <img class="img-responsive" src="resources/images/<%=tip.getImage()%>">
                        <%}%>
                        <p><%=tip.getBody()%></p>
                    </div>
                    <input type="submit" class="btn btn-info" name="nextTip" value="Next Tip">
                    <input type="submit" class="btn btn-info" name="hideTips" value="Hide Tips">
                </div>
                <%}%>
                <%if (tip == null){%>
                <input type="submit" class="btn btn-info" name="showTips" value="Show Tips">
                <%}%>
            </div>
        </div>
    </div>

    <div>
        <table class="table table-condensed">
            <thread>
                <tr>
                    <th>Coaches</th>
                </tr>
            </thread>
            <tbody>
            <%
                for (int j = 0; j < teamCoaches.size(); j++) {
            %>
            <tr>
                <td><%=teamCoaches.get(j).getName()%>
                </td>
                <td>
                    <a href="#<%="coachDropdown"%>" class="btn btn-info" data-toggle="collapse">Details</a>
                    <div id="<%="coachDropdown"%>" class="collapse">
                        <div class="well well-sm">
                            Sport: <%=teamCoaches.get(j).getSportName()%><br>
                            Performance: <%=teamCoaches.get(j).getPerformance()%><br>
                            <br>
                        </div>
                    </div>
                    <label id="facultySalary" style="color: black"><%="Salary: $" + String.valueOf(teamCoaches.get(j).getSalary())%> </label>
                </td>
                <td>
                    <input type="submit" class="btn btn-info" name="<%="coachRaise" + j%>" value="Give Raise" style="text-decoration-color: #000099">
                    <input type="submit" class="btn btn-info" name="<%="removeCoach" + j%>" value="Fire Coach">
                    <%if(teamCoaches.get(j).getUnderPerforming()){%>
                        <label id="underPerformingCoaches"><%=SportManager.generateCoachUnderPerformingScenario(teamCoaches.get(j).getName())%></label>
                    <%}%>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>


        <!-- Display a message if defined -->
        <input type="hidden" name="runId" value="<%=college.getRunId()%>">
        <p></p>
        <div class="well well-sm">
            <table class="table table-condensed">
                <tbody>
                <h4>Sports Record</h4>
                    <thread>
                        <tr>
                          <th>Team</th>
                            <th>Details</th>
                            <th>Wins</th>
                            <th>Losses</th>
                            <th>Games Played</th>
                            <th>In Season</th>
                            <th>Division</th>
                        </tr>
                    </thread>
                    <%
                        for (int i = 0; i < sport.length; i++) {
                    %>

                    <tr>
                        <td><%=sport[i].getName()%> </td>
                        <td>
                            <a href="#<%=i%>" class="btn btn-info" data-toggle="collapse">Details</a>
                            <div id="<%=i%>" class="collapse">
                                <% for(int j = 0; j < students.length; j++){
                                    if(students[j].getTeam().equals(sport[i].getName())){ %>
                                        <%=students[j].getName()%><br/>
                                <%    }
                                }
                                    %>
                                <br/>Reputation:
                                <div class="progress">
                                    <div class="progress-bar progress-bar-success" role="progressbar"
                                         aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"
                                         style="width:<%=sport[i].getReputation()%>%">
                                        <%=sport[i].getReputation()%>%
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td><%=sport[i].getGamesWon()%> </td>
                        <td><%=sport[i].getGamesLost()%> </td>
                        <td><%=sport[i].getGamesWon() + sport[i].getGamesLost() + sport[i].getGamesTied()%> </td>
                        <td><% String isActive = "Yes";
                               if (sport[i].getIsActive() <= 0) isActive = "No"; %>
                            <%=isActive%>
                        </td>
                        <td><%=sport[i].getDivision()%></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>

        <!-- Add sport -->
        <div class="col-sm-4">
            <div class="well well-sm">
                <div class="form-group">
                    <label for="sportName">Select Sport to Add</label>
                    <select class="form-control" id="sportName" name="sportName">
                        <% for(int i = 0; i < availableSports.length; i++) { %>
                        <tr>
                            <option>$50,000 - <%= availableSports[i].getName()%></option>
                        </tr>
                        <% } %>
                    </select>
                </div>
                <input type="submit" class="btn btn-info" name="addSport" value="Add Sport">
            </div>
        </div>

        <!-- Sell sport -->
        <div class="col-sm-4">
            <div class="well well-sm">
                <div class="form-group">
                    <label for="sportName">Select Sport to sell</label>
                    <select class="form-control" id="sellSportName" name="sellSportName">
                        <% for(int i = 0; i < sport.length; i++) { %>
                        <tr>
                            <option><%= sport[i].getName()%></option>
                        </tr>
                        <% } %>
                    </select>
                </div>
                <input type="submit" class="btn btn-info" name="sellSportBtn" value="Sell Sport">
            </div>
        </div>

        <div class="row">
            <div class="col-sm-6">
                <div class="well well-sm">
                    <h3><p style="color: purple"><%=college.getRunId()%> Sport News</h3>
                    <div class="pre-scrollable">
                        <ul class="list-group">
                            <%
                                for (int i = news.length - 1; i >= 0; i--) {
                                    if (news[i].getNoteType() == NewsType.SPORTS_NEWS) {
                                        if(news[i].getNoteLevel() == NewsLevel.GOOD_NEWS){

                            %>
                            <li class="list-group-item">
                                <!-- change this to user up or down arrow depending on money -->
                                <span class="glyphicon glyphicon-thumbs-up" style="color:lawngreen"></span>
                                Day <%=news[i].getHour() / 24%> - <%=news[i].getMessage()%>
                            </li>
                            <%}
                            else if(news[i].getNoteLevel() == NewsLevel.BAD_NEWS){

                            %>
                            <li class="list-group-item">
                                <!-- change this to user up or down arrow depending on money -->
                                <span class="glyphicon glyphicon-thumbs-down"style="color:red"></span>
                                Day <%=news[i].getHour() / 24%> - <%=news[i].getMessage()%>
                            </li>
                            <% }}
                            } %>
                        </ul>
                    </div>
                </div>
            </div>

    </div>
    </div>
</form>
<div class="container">
    <div class="alert alert-success">
        <strong>Info</strong> <%=msg.getMessage()%>
    </div>
</div>
</div>
</body>
</html>

