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
                
                <li class="dropdown"><a href="home?action=products">Hàng Hóa</a>
                 <div class="dropdown-content">
                <a href="home?action=products">Sản Phẩm</a>
                <a href="#">Thêm sẩn phẩm</a>
                <a href="#">Thêm chức năng</a>
               </div>
                </li>
               
                
                <li class="dropdown"><a href="#">Bán hàng</a>
                 <div class="dropdown-content">
                <a href="#">Thêm chức năng</a>
                <a href="#">Thêm chức năng</a>
                <a href="#">Thêm chức năng</a>
            </div>
                </li>
                
                <li class="dropdown"><a href="#">Đối tác</a>
                 <div class="dropdown-content">
                <a href="#">Khách hàng</a>
                <a href="#">Nhà Cung cấp</a>
            </div>
                </li>
                 <li class="dropdown"><a href="#">Báo cáo</a>
                 </li>
            </ul>
        </div>
         
         <%
            Object obj = request.getAttribute("role");
            int role = 0; // Giá trị mặc định nếu session chưa có idkh

            if (obj != null) {
                role = Integer.parseInt(obj.toString()); // Chuyển Object -> String -> int
            }
            session.setAttribute("idkh", role);
        %>
        
         
         
         
         
        <div class="nav-right dropdown">
            <a style="background: none;color:#ffffff; text-decoration: none; font-size: 18px; font-weight: bold;" href="#">Tài khoản</a>
            <div class="dropdown-content">
                <a href="home?action=information">Thông tin tài khoản</a>
                <a href="login?action=logout">Đăng xuất</a>
            </div>
        </div>
    </nav>
       
    </body>
</html>
