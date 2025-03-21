<%-- 
    Document   : taocalam
    Created on : Mar 15, 2025, 7:25:29 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/stylephancong.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Tạo Ca Làm Và Ngày Làm</h1>
        <div>${taocalamMessage}</div>
        <form action="nhanvien" method="post">
            
            <input type="hidden" name="action" value="taocalam"> 
            
            
            <label>ID Nhân Viên:</label>
            <input type="text" pattern="[0-9]+" name="id" required title= "ID Phải là số"/> <br/>



            <label>Ca Làm</label>
            <select name="calam">
                <option value="1">Ca Sáng</option>
                <option value="2">Ca Chiều</option>
            </select> 
            <br/>
            <label>Ngày Làm</label>
            <input type="date" name="ngaylam"> <br/>
            
            <input type="submit" value="Đăng Kí">
            


        </form>
        
        <form action="nhanvien" method="get">
            <input type="submit" name="action" value="returnphancong">
        </form>
        
        <table border = '1'>
            <thead>
                <tr>
                    <th>ID Nhân Viên</td>
                    <th> Họ Tên</td>
                    <th>Chức Vụ</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="nv" items="${listnv}">
                    <tr>
                        <td>${nv.maNV}</td>
                        <td>${nv.hoTen}</td>
                        <td>${nv.chucVu}</td>
                    </tr>
                </c:forEach>
            </tbody>
            
        </table>
    </body>
</html>
