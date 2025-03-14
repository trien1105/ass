<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/styleindex.css">
    <title>Đăng nhập</title>
    
</head>
<body>
    
    <div class="login-container">
        <h1>Đăng nhập</h1>
        <form action="login" method="post">
            <div class="input-group">
                <label for="email">Tài khoản</label>
                <input type="text" id="email" name="email" placeholder="Nhập email của bạn" required/>
            </div>

            <div class="input-group">
                <label for="matkhau">Mật khẩu</label>
                <input type="password" id="matkhau" name="matkhau" placeholder="Nhập mật khẩu" required/>
            </div>

            <button type="submit" class="btn-login">Đăng nhập</button>
        </form>
        <div>
            <p><a href="login?action=signup" style="font-size: 15px;text-decoration: none;">Đăng ký</a></p>
        </div>    
        

        <div class="error-message">${mess}</div>
    </div>
</body>
</html>
