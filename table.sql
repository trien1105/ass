﻿
create database QuanLyBanHang

-- Bảng Admin
CREATE TABLE Admin (
    MaAdmin INT PRIMARY KEY IDENTITY(1,1),
    HoTen NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) UNIQUE
);

-- Bảng Nhân viên
CREATE TABLE NhanVien (
    MaNV INT PRIMARY KEY IDENTITY(1,1),
    HoTen NVARCHAR(100) NOT NULL,
    ChucVu NVARCHAR(50),
    Email NVARCHAR(100) UNIQUE,
    SoDienThoai NVARCHAR(15),
    NgayVaoLam DATE,
    MaAdmin INT,
    FOREIGN KEY (MaAdmin) REFERENCES Admin(MaAdmin)
);

-- Bảng Ca làm (Đưa lên trước PhanCong)
CREATE TABLE CaLam (
    MaCa INT PRIMARY KEY IDENTITY(1,1),
    TenCa NVARCHAR(50) NOT NULL,
    GioBatDau TIME NOT NULL,
    GioKetThuc TIME NOT NULL
);

-- Bảng Phân công
CREATE TABLE PhanCong (
    MaPhanCong INT PRIMARY KEY IDENTITY(1,1),
    MaNV INT,
    MaCa INT,
    NgayLam DATE NOT NULL,
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    FOREIGN KEY (MaCa) REFERENCES CaLam(MaCa)
);

-- Bảng Loại mặt hàng
CREATE TABLE LoaiMatHang (
    MaLoai INT PRIMARY KEY IDENTITY(1,1),
    TenLoai NVARCHAR(100) NOT NULL
);

-- Bảng Nhà cung cấp mặt hàng (Đưa lên trước MatHang)
CREATE TABLE NhaCungCapMatHang (
    MaNCC INT PRIMARY KEY IDENTITY(1,1),
    TenNCC NVARCHAR(100) NOT NULL,
    DiaChi NVARCHAR(MAX),
    SoDienThoai NVARCHAR(15),
    Email NVARCHAR(100) UNIQUE
);

-- Bảng Mặt hàng
CREATE TABLE MatHang (
    MaMH INT PRIMARY KEY IDENTITY(1,1),
    TenMH NVARCHAR(100) NOT NULL,
    Gia DECIMAL(12,2) NOT NULL,
    SoLuongTon INT NOT NULL,
    HSD DATE,
    MoTa NVARCHAR(MAX),
    MaLoai INT,
    MaNCC INT,
    img NVARCHAR(255),
    FOREIGN KEY (MaLoai) REFERENCES LoaiMatHang(MaLoai),
    FOREIGN KEY (MaNCC) REFERENCES NhaCungCapMatHang(MaNCC)
);

-- Bảng Khách hàng
CREATE TABLE KhachHang (
    MaKH INT PRIMARY KEY IDENTITY(1,1),
    HoTen NVARCHAR(100) NOT NULL,
    SoDienThoai NVARCHAR(15),
    Email NVARCHAR(100) UNIQUE
);

-- Bảng Đơn hàng
CREATE TABLE DonHang (
    MaDH INT PRIMARY KEY IDENTITY(1,1),
    MaNV INT,
    MaKH INT,
    Ngaymuahang DATE,
    tongtien DECIMAL(12,2),
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH)
);

-- Bảng Chi tiết đơn hàng
CREATE TABLE ChiTietDonHang (
    MaDH INT,
    MaMH INT,
    SoLuong INT NOT NULL,
    GiaBan DECIMAL(12,2) NOT NULL,
    PRIMARY KEY (MaDH, MaMH),
    FOREIGN KEY (MaDH) REFERENCES DonHang(MaDH),
    FOREIGN KEY (MaMH) REFERENCES MatHang(MaMH)
);

-- Bảng Đăng nhập
CREATE TABLE DangNhap (
    MaDangNhap INT PRIMARY KEY IDENTITY(1,1),
    Email NVARCHAR(100) UNIQUE NOT NULL,
    MatKhau NVARCHAR(255) NOT NULL,
    Role INT NOT NULL,
    manguoidung INT NOT NULL
);

go
CREATE TRIGGER trg_UpdateSoLuong 
ON ChiTietDonHang 
AFTER INSERT 
AS
BEGIN
    UPDATE MatHang 
    SET SoLuongTon = MatHang.SoLuongTon - inserted.SoLuong
    FROM MatHang
    INNER JOIN inserted ON MatHang.MaMH = inserted.MaMH
    WHERE MatHang.SoLuongTon >= inserted.SoLuong;
END;





go

-- Bảng Admin
INSERT INTO Admin (HoTen, Email) VALUES 
(N'Nguyễn Văn A', 'admin1@example.com'),
(N'Trần Thị B', 'admin2@example.com'),
(N'Lê Văn C', 'admin3@example.com'),
(N'Phạm Văn D', 'admin4@example.com'),
(N'Huỳnh Thị E', 'admin5@example.com');

-- Bảng Nhân viên
INSERT INTO NhanVien (HoTen, ChucVu, Email, SoDienThoai, NgayVaoLam, MaAdmin) VALUES 
(N'Hoàng Văn D', N'Quản lý', 'nv1@example.com', '0912345678', '2023-01-15', 1),
(N'Phạm Thị E', N'Nhân viên', 'nv2@example.com', '0923456789', '2023-02-20', 1),
(N'Ngô Văn F', N'Thu ngân', 'nv3@example.com', '0934567890', '2023-03-25', 2),
(N'Lý Minh G', N'Bán hàng', 'nv4@example.com', '0945678901', '2023-04-10', 2),
(N'Nguyễn Thị H', N'Kế toán', 'nv5@example.com', '0956789012', '2023-05-05', 3);

-- Bảng Ca làm
INSERT INTO CaLam (TenCa, GioBatDau, GioKetThuc) VALUES 
(N'Ca Sáng', '06:00', '12:00'),
(N'Ca Chiều', '12:00', '18:00'),
(N'Ca Tối', '18:00', '23:00'),
(N'Ca Đêm', '23:00', '06:00');

-- Bảng Phân công
INSERT INTO PhanCong (MaNV, MaCa, NgayLam) VALUES 
(1, 1, '2024-03-10'),
(2, 2, '2024-03-11'),
(3, 3, '2024-03-12'),
(4, 1, '2024-03-13'),
(5, 2, '2024-03-14');

-- Bảng Loại mặt hàng
INSERT INTO LoaiMatHang (TenLoai) VALUES 
('Phone'),
('Quần áo'),
('Thực phẩm'),
('Đồ uống'),
('Gia vị'),
('Mỹ phẩm');


-- Bảng Nhà cung cấp mặt hàng
INSERT INTO NhaCungCapMatHang (TenNCC, DiaChi, SoDienThoai, Email) VALUES 
(N'Công ty ABC', N'123 Đường A, TP.HCM', '0911223344', 'abc@example.com'),
(N'Công ty XYZ', N'456 Đường B, Hà Nội', '0922334455', 'xyz@example.com'),
(N'Công ty DEF', N'789 Đường C, Đà Nẵng', '0933445566', 'def@example.com'),
(N'Công ty áddsa', N'10jq Đường B, Hà Nội', '0922334455', 'yz@example.com'),
(N'Công ty dsasad', N'qka Đường C, Đà Nẵng', '0933445566', 'df@example.com'),
(N'Công ty GHI', N'321 Đường D, Hải Phòng', '0944556677', 'ghi@example.com');

-- Bảng Mặt hàng
INSERT INTO MatHang (TenMH, Gia, SoLuongTon, HSD, MoTa, MaLoai, MaNCC, img) VALUES 
(N'Laptop Dell', 15000000, 10, '2025-12-31', N'Máy tính xách tay', 1, 1, 'laptop.jpg'),
(N'Áo sơ mi nam', 300000, 50, NULL, N'Áo sơ mi cotton', 2, 2, 'aosomi.jpg'),
(N'Nồi cơm điện', 1200000, 20, '2026-01-01', N'Nồi cơm điện 1.8L', 3, 3, 'noicomdien.jpg'),
(N'Kem dưỡng da', 250000, 30, '2026-06-30', N'Kem dưỡng trắng da', 5, 4, 'kemduong.jpg');

-- Bảng Khách hàng
INSERT INTO KhachHang (HoTen, SoDienThoai, Email) VALUES 
(N'Nguyễn Minh Khoa', '0987654321', 'khoa@example.com'),
(N'Võ Thị Thanh', '0976543210', 'thanh@example.com'),
(N'Lê Hoàng Nam', '0965432109', 'nam@example.com'),
(N'Phạm Văn Quang', '0954321098', 'quang@example.com'),
(N'Trịnh Thị Hạnh', '0943210987', 'hanh@example.com');

-- Bảng Đơn hàng
INSERT INTO DonHang (MaNV, MaKH, Ngaymuahang, tongtien) VALUES 
(1, 1, '2024-03-10', 15300000),
(2, 2, '2024-03-11', 300000),
(3, 3, '2024-03-12', 1200000),
(4, 4, '2024-03-13', 250000),
(5, 5, '2024-03-14', 18000000);

-- Bảng Chi tiết đơn hàng
INSERT INTO ChiTietDonHang (MaDH, MaMH, SoLuong, GiaBan) VALUES 
(1, 1, 1, 15000000), (2, 2, 1, 300000), (3, 3, 1, 1200000), 
(4, 4, 1, 250000), (5, 1, 1, 15000000), (5, 2, 1, 300000), (5, 3, 1, 1200000);

-- Bảng Đăng nhập
INSERT INTO DangNhap (Email, MatKhau, Role, manguoidung) VALUES 
('admin1@example.com', 'admin123', 1, 1), ('admin2@example.com', 'admin123', 1, 2),
('nv1@example.com', 'password1', 2, 1), ('nv2@example.com', 'password2', 2, 2),
('nv3@example.com', 'password3', 2, 3), ('nv4@example.com', 'password4', 2, 4),
('nv5@example.com', 'password5', 2, 5), ('khoa@example.com', 'customer1', 3, 1),
('thanh@example.com', 'customer2', 3, 2), ('nam@example.com', 'customer3', 3, 3),
('quang@example.com', 'customer4', 3, 4), ('hanh@example.com', 'customer5', 3, 5);



INSERT INTO MatHang (TenMH, Gia, SoLuongTon, HSD, MoTa, MaLoai, MaNCC) VALUES

('iPhone 15 Pro Max', 32000000, 20, '2026-12-31', 'Điện thoại Apple mới nhất', 1, 1),
('Samsung Galaxy Z Fold 5', 45000000, 10, '2026-12-31', 'Điện thoại gập cao cấp Samsung', 1, 2),
('Xiaomi Redmi Note 12', 7500000, 30, '2026-06-30', 'Điện thoại giá rẻ pin trâu', 1, 3),
('Oppo Reno 8', 9000000, 25, '2026-09-30', 'Điện thoại Oppo chụp ảnh đẹp', 1, 4),

('Áo sơ mi nam', 350000, 50, '2027-06-30', 'Áo sơ mi cao cấp', 2, 3),
('Quần jeans nữ', 600000, 40, '2027-06-30', 'Quần jeans phong cách', 2, 4),
('Áo khoác Adidas', 1500000, 20, '2027-12-30', 'Áo khoác thể thao Adidas', 2, 5),
('Giày Nike Air Force 1', 2500000, 15, '2027-08-15', 'Giày thể thao hot trend', 2, 6),


('Bánh Oreo', 40000, 100, '2025-11-01', 'Bánh quy vị socola', 3, 5),
('Sữa tươi TH True Milk', 28000, 200, '2024-11-20', 'Sữa tươi thanh trùng', 3, 6),
('Snack Poca vị BBQ', 25000, 150, '2025-05-10', 'Snack khoai tây vị BBQ', 3, 1),
('Mì Hảo Hảo tôm chua cay', 5000, 500, '2025-09-10', 'Mì gói tôm chua cay huyền thoại', 3, 2),

('Coca-Cola lon 330ml', 12000, 300, '2025-09-15', 'Nước giải khát có gas', 4, 3),
('Trà xanh 0 độ', 10000, 250, '2025-08-30', 'Nước trà xanh giải nhiệt', 4, 4),
('Cà phê G7 hòa tan', 75000, 180, '2026-07-01', 'Cà phê hòa tan thơm ngon', 4, 5),
('Nước suối Lavie 500ml', 6000, 350, '2026-05-20', 'Nước khoáng thiên nhiên', 4, 6),

('Nước mắm Phú Quốc', 90000, 60, '2026-08-01', 'Nước mắm truyền thống', 5, 1),
('Muối Tây Ninh', 25000, 120, '2027-02-01', 'Muối ớt tây ninh chấm trái cây', 5, 2),
('Tiêu đen nguyên hạt', 80000, 70, '2027-03-15', 'Hạt tiêu đen cay nồng', 5, 3),
('Hạt nêm Knorr 400g', 45000, 100, '2026-06-25', 'Gia vị hạt nêm từ thịt', 5, 4),

('Son Mac Ruby Woo', 500000, 30, '2027-12-31', 'Son lì màu đỏ quyến rũ', 6, 5),
('Sữa rửa mặt Cetaphil', 220000, 50, '2027-09-20', 'Sữa rửa mặt cho da nhạy cảm', 6, 6),
('Kem chống nắng Anessa', 550000, 35, '2027-10-10', 'Kem chống nắng Nhật Bản', 6, 1),
('Dầu gội TRESemmé', 180000, 40, '2027-05-05', 'Dầu gội phục hồi tóc hư tổn', 6, 2);
