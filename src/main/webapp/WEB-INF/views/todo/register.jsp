<%--
  Created by IntelliJ IDEA.
  User: ict05-10
  Date: 2022-11-14
  Time: 오전 10:45
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="forrm" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>registe view</title>
</head>
<body>
 <form action="/todo/register" method="post">
     <div>
         Title: <input type="text" name="title">
     </div>
     <div>
         DueDate: <input type="date" name="dueDate" value="2022-11-14">
     </div>
     <div>
         Writer: <input type="text" name="writer">
     </div>
     <div>
         Finished: <input type="checkbox" name="finished">
     </div>
     <div>
         <button type="submit">Register</button>
     </div>
 </form>
</body>
</html>
