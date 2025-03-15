<%-- 
    Document   : admin
    Created on : Mar 14, 2025, 5:51:14 PM
    Author     : PC
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
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
            <div class="nav-left" ><a style="background: none;color:#ffffff; text-decoration: none; font-size: 18px; font-weight: bold;"
                                      href="home?action=home" class="active">PRJ-Group4</a></div>
            <div class="nav-center">
                <ul>

                    <li><a href="home?action=payproducts">Sản phẩm</a></li>
                    <li><a href="home?action=order">Giỏ Hàng</a></li>
                    <li><a href="home?action=payed">Mặt Hàng Đã mua </a> </li>
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
             ${idkh}
        </div>
        <%
            Object obj = request.getAttribute("idkh");
            int idkh = 0; // Giá trị mặc định nếu session chưa có idkh

            if (obj != null) {
                idkh = Integer.parseInt(obj.toString()); // Chuyển Object -> String -> int
            }
            session.setAttribute("idkh", idkh);
        %>
        <%= idkh%>
        <%
            session.removeAttribute("warning");
            session.removeAttribute("mess");
        %>

       



        <c:if test="${param.action != 'information'&& param.action != 'payproducts'}">
            <table border="1">
                <h1>Sản Phần Bán chạy</h1>
                <thead>
                    <tr>
                        <th>Tên sản phẩm</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>HSD</th>
                        <th>Mô tả</th>
                        <th>Hình ảnh</th>
                        <th>action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${spbanchay}">
                        <tr>
                            <td>${p.tenmh}</td>
                            <td>${p.gia}</td>
                            <td>${p.soluongton}</td>
                            <td>${p.hsd}</td>
                            <td>${p.mota}</td>
                            <td><img src="${p.img}" style="width: 200px;height: 150px"/></td>
                            <td>
                                
                                
                                
                                <a href="productdetail?action=buy&id=${p.mamh}" >
                                    select
                                </a>

                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>


        <c:if test="${param.action == 'payproducts'}">
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
                                
                                <a href="productdetail?action=buy&id=${p.mamh}">
                                    select
                                </a>

                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>







    </body>
</html>
