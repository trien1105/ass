<%-- 
    Document   : signup
    Created on : Mar 9, 2025, 7:00:58 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="./css/styleindex.css">
        <title>Đăng ký</title>
    </head>
    <body>
        <div class="login-container">
        <h1>Đăng ký</h1>
        <form action="signup" method="post">
            <div class="input-group">
                <label for="email">Tài khoản</label>
                <input type="text" id="email" name="email" placeholder="Nhập email của bạn" required/>
            </div>

            <div class="input-group">
                <label for="matkhau">Mật khẩu</label>
                <input type="password" id="matkhau" name="matkhau" placeholder="Nhập mật khẩu" required/>
            </div>
            <div class="input-group">
                <label for="name">Họ và tên</label>
                <input type="text" id="name" name="name" placeholder="Nhập họ và tên" required/>
            </div>
            
             <div class="input-group">
                <label for="number">Nhập số điện thoại</label>
                <input type="text" id="number" name="number" placeholder="Nhập số điện thoại" required/>
            </div>
            
            <button type="submit" class="btn-login">Đăng ký</button>
        </form>
           
        

        <div class="error-message">${mess}</div>
    </div>
    </body>
</html>
