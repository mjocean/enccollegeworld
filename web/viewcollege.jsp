<%@ page import="com.endicott.edu.models.ui.UiMessage" %>
<%@ page import="com.endicott.edu.models.models.CollegeModel" %>
<%@ page import="com.endicott.edu.models.models.DormitoriesModel" %>
<%@ page import="com.endicott.edu.models.models.DormitoryModel" %><%--
  Created by IntelliJ IDEA.
  User: abrocken
  Date: 8/25/2017
  Time: 8:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Enc College World</title>
<link rel="stylesheet" href = "resources/style.css">
</head>
<body>
<%
  UiMessage msg = (UiMessage) request.getAttribute("message");
  if (msg == null) {
    msg = new UiMessage();
  }
  CollegeModel college = (CollegeModel) request.getAttribute("college");
  if (college == null) {
    college = new CollegeModel();
    msg.setMessage("Attribute for college missing.");
  }
  DormitoryModel dorms[] = (DormitoryModel[]) request.getAttribute("dorms");
  if (dorms == null) {
      dorms = new DormitoryModel[0];  // This is really bad
      msg.setMessage(msg.getMessage() + "Attribute for dorms missing.");
  }
%>
<h1>College <%=college.getRunId()%>
</h1>
<!-- Display a message if defined -->
<h2><%=msg.getMessage()%>
</h2>
<form action="updateCollege" method="post">
  <input type="hidden" name="runid" value="<%=college.getRunId()%>">
  <table align="center" bgcolor="#DDDDFF" border="1" width="40%">
    <tr>
      <td>Day</td>
      <td><%=college.getCurrentDay()%>
      </td>
    </tr>
    <tr>
      <td>Funding</td>
      <td>$<%=college.getAvailableCash()%>
      </td>
    </tr>
    <tr>
      <td>Number of Dorms</td>
      <td><%=dorms.length%>
      </td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <input type="submit" name="nextDayButton" value="Next Day">
        <input type="submit" name="refreshButton" value="Refresh">
      </td>
    </tr>
  </table>
</form>
</body>
</html>