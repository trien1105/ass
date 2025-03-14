<%-- 
    Document   : nhanvien
    Created on : Mar 10, 2025, 3:02:09 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          <h1 style="color: green"> ${mess}</h1>
        <h1 style="color: red">${warning}</h1>
        
        <%
    session.removeAttribute("warning");
    %>

        
        <p>Vai trò: ${role}</p>
        
        
       
        <a href="login?action=logout" style="border: 1px solid black ; width: 100px;text-decoration: none;">Đăng xuất</a>
         
       
    </body>
</html>
