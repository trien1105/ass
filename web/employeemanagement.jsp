<%-- 
    Document   : employeemanagement
    Created on : Mar 14, 2025, 12:59:54 AM
    Author     : Dell
--%>


<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&family=Open+Sans:ital,wght@0,300..800;1,300..800&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/stylenhanvien.css"/>
    </head>
    <body>
        <h1>Welcome Admin to Employee Management</h1>

        <div>${errorMes}</div>
        <div>${delMessage}</div>
        <div>${errorEditMes}</div>
        <div>${editMessage}</div>
        <div>${addNVMess}</div>
        <div>          
            <a href="nhanvien?action=phancong">
                <label>Phân Công Ca Làm</label>
            </a>  
        </div>
        <div>
            <a href="nhanvien?action=create">
                <label>Add Employee</label>
            </a>
        </div>
        <table border ='1' class="employeetable">
            <thead class="head_table">
                <tr>
                    <th>ID Employee</th>
                    <th>Name</th>
                    <th>Role</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Starting Date</th>
                    <th>ID Admin</th>
                    <th>Edit</th>                   
                </tr>
            </thead>
            <tbody class="content_table">
                <c:forEach var="e" items="${list}">
                    <tr>
                        <td>${e.maNV}</td>
                        <td>${e.hoTen}</td>
                        <td>${e.chucVu}</td>
                        <td>${e.email}</td>
                        <td>${e.soDienThoai}</td>
                        <td><fmt:formatDate value="${e.ngayVaoLam}" pattern="dd/MM/yyyy" /></td>
                        <td>${e.maAdmin}</td>
                        <td>
                            <a href="nhanvien?action=edit&id=${e.maNV}">
                                <span class="material-icons">edit</span>
                            </a>                            
                        </td>                   
                    </tr>
                </c:forEach>                  
            </tbody>
        </table>
        
        <form action="nhanvien" method="get">
            <input type="submit" name="action" value="ReturnAdmin">
        </form>
        
    </body>
</html>
