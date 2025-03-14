<%-- 
    Document   : admin
    Created on : Mar 14, 2025, 5:51:14 PM
    Author     : PC
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.NumberFormat" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSP Page</title>
    <link href="css/admin.css" rel="stylesheet">
</head>
  

<body>
    <div style="display: flex; justify-content: center;margin: 20px" >
        <img  src="img/logof.jpg" style="border-radius: 50% ;width: 200px">
    </div>
    
     <nav>
         <div class="nav-left" ><a style="background: none;color:#ffffff; text-decoration: none; font-size: 18px; font-weight: bold;" href="home?action=home" class="active">PRJ-Group4</a></div>
        <div class="nav-center">
            <ul>
                
                <li><a href="home?action=products">Hàng Hóa</a></li>
                <li><a href="home?action=createnhanvien">Thêm nhân viên</a></li>
                <li><a href="#">Tạo sản phẩm</a></li>
            </ul>
        </div>
        <div class="nav-right dropdown">
            <a style="background: none;color:#ffffff; text-decoration: none; font-size: 18px; font-weight: bold;" href="#">Tài khoản</a>
            <div class="dropdown-content">
                <a href="home?action=information">Thông tin tài khoản</a>
                <a href="login?action=logout">Đăng xuất</a>
            </div>
        </div>
    </nav>
    
    <div>
        <h1> ${mess}</h1>
        <h1> ${thongbao}</h1>
        <h1> ${thongbao2}</h1>
        <h1> ${warning}</h1>
    </div>
    
    <%
        session.removeAttribute("warning");
        session.removeAttribute("mess");
    %>
    <c:if test="${param.action == 'products'}">
    <table border="1">
        <thead>
            <tr>
                <th>Tên sản phẩm</th>
                <th>Giá</th>
                <th>Số lượng</th>
                <th>HSD</th>
                <th>Mô tả</th>
                <th>Hình ảnh</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${products}">
                <tr>
                    <td>${p.tenmh}</td>
                    <td>${p.gia}</td>
                    <td>${p.soluongton}</td>
                    <td>${p.hsd}</td>
                    <td>${p.mota}</td>
                    <td><img src="${p.img}" style="width: 200px;height: 150px"/></td>
                    <td>
                        <a href="home?action=edit&id=${p.mamh}">
                            <img src="img/edit.jfif" width="50px"/>
                        </a>
                        <a href="home?action=delete&id=${p.mamh}">
                            <img src="img/del.jfif" width="50px"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </c:if>
</body>
</html>
