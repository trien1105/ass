<%-- 
    Document   : phancong
    Created on : Mar 15, 2025, 3:01:55 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&family=Open+Sans:ital,wght@0,300..800;1,300..800&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/stylephancong.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Phân Công Nhân Viên</h1>

            <td>
                
                <a href="nhanvien?action=taocalam">
                    <label>Thêm ca làm, ngày làm cho nhân viên</label>
                </a>
            </td>

    


    <div>${taocalamMessage}</div>
    <table border = "1">
        <thead>
            <tr>
                <th>ID Nhân Viên</th>
                <th>Họ Tên</th>
                <th>Ca</th>
                <th>Ngày Làm</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="pc" items="${danhsachphancong}">
                <tr>
                    <td>${pc.maNV}</td>
                    <td>${pc.hoTen}</td>
                    <td>${pc.tenCa}</td>
                    <td><fmt:formatDate value="${pc.ngayLam}" pattern="dd/MM/yyyy" /></td>                      
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <form action="nhanvien" method="get">
        <input type="submit" name="action" value="Return">
    </form>

</body>
</html>
