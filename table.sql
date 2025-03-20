create database quanlybanhang
usequanlybanhang

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
    Gia DECIMAL(38,2) NOT NULL,
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
    NgayMua DATE,
    tongtien DECIMAL(38,2),
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH)
);

-- Bảng Chi tiết đơn hàng
CREATE TABLE ChiTietDonHang (
    MaDH INT,
    MaMH INT,
    SoLuong INT NOT NULL,
    GiaBan DECIMAL(38,2) NOT NULL,
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
(N'Điện tử'), (N'Thời trang'), (N'Gia dụng'), (N'Thực phẩm'), 
(N'Mỹ phẩm'), (N'Đồ chơi'), (N'Nội thất'), (N'Văn phòng phẩm');

-- Bảng Nhà cung cấp mặt hàng
INSERT INTO NhaCungCapMatHang (TenNCC, DiaChi, SoDienThoai, Email) VALUES 
(N'Công ty ABC', N'3245 Đường A, TP.HCM', '0911223344', 'abc@example.com'),
(N'Công ty a', N'234 Đường B, Hà Nội', '234234', 'xyz@example.com'),
(N'Công ty wert', N'12 Đường C, Đà Nẵng', '52345342', 'def@example.com'),
(N'Công ty sdf', N'675 Đường B, Hà Nội', '1234123423', 'ytry@example.com'),
(N'Công ty asd', N'789 Đường C, Đà Nẵng', '1234234', 'fdsf@example.com'),
(N'Công ty sdfg', N'321 Đường D, Hải Phòng', '0944556677', 'ghi@example.com');


-- Bảng Khách hàng
INSERT INTO KhachHang (HoTen, SoDienThoai, Email) VALUES 
(N'Nguyễn Minh Khoa', '0987654321', 'khoa@example.com'),
(N'Võ Thị Thanh', '0976543210', 'thanh@example.com'),
(N'Lê Hoàng Nam', '0965432109', 'nam@example.com'),
(N'Phạm Văn Quang', '0954321098', 'quang@example.com'),
(N'Trịnh Thị Hạnh', '0943210987', 'hanh@example.com');
-- mh
INSERT INTO MatHang (TenMH, Gia, SoLuongTon, HSD, MoTa, MaLoai, MaNCC, img) VALUES
(N'iPhone 14', 25000000, 9999, '2100-12-31', N'luxury phone', 1, 2, 'img/iphone14.jpg'),
(N'Áo thun nam', 200000, 49997, '2026-06-30', N'Make by cotton', 2, 2, 'img/aothun.jpg'),
(N'milk', 200000, 0, '2025-12-31', N'chai 1L', 3, 3, 'img/sua.jpg'),
(N'Bánh mì', 15000, 4989, '2025-12-31', N'Bánh mì sữa ngon', 3, 3, 'img/banhmi.jpg'),
(N'Mì gói', 50000, 19940, '2026-01-01', N'Mì ăn liền', 3, 3, 'img/migoi.jpg'),
(N'cooking oil', 45000, 30000, '2026-06-10', N'cooking oil 1L', 3, 4, 'img/dauan.jpg'),
(N'cocacola', 12000, 5996, '2025-09-30', N'Chai 1.5L', 4, 5, 'img/nuocngot.webp'),
(N'Nacl', 10000, 99999, '2025-09-30', N'Gói 150g', 4, 3, 'img/muoi.png'),
(N'iPhone 15 Pro Max', 39000000, 50, '2026-12-31', N'Điện thoại Apple mới nhất', 1, 2, 'img/ip15.webp'),
(N'Samsung Galaxy Z Fold 5', 45000000, 10, '2026-12-31', N'Điện thoại gập cao cấp Samsung', 1, 2, 'img/szs5.webp'),
(N'Xiaomi Redmi Note 12', 7000000, 20, '2026-06-30', N'Điện thoại giá rẻ pin trâu', 1, 2, 'img/redmi.jpg'),
(N'Oppo Reno 8', 9000000, 25, '2026-06-30', N'Điện thoại chụp ảnh đẹp', 1, 2, 'img/reno8.jpg'),
(N'Áo sơ mi nam', 350000, 49, '2027-06-30', N'Áo sơ mi cao cấp', 2, 6, 'img/aosomi.jpg'),
(N'Quần jeans nữ', 600000, 30, '2027-06-30', N'Quần jeans phong cách', 2, 6, 'img/jeans.jpg'),
(N'Áo khoác Adidas', 1500000, 10, '2027-12-30', N'Áo khoác thể thao Adidas', 2, 6, 'img/aokhoac.jpg'),
(N'Giày Nike Air Force 1', 2500000, 20, '2027-08-15', N'Giày thể thao hot trend', 2, 6, 'img/giaynike.webp'),
(N'Bánh Oreo', 60000, 100, '2025-10-11', N'Bánh quy vị socola', 3, 3, 'img/oreo.jpg'),
(N'cô gai hạ lan', 2800, 0, '2024-11-10', N'sữa thanh trùng', 3, 3, 'img/cogaihl.jpg'),
(N'Snack Poca vị BBQ', 15000, 50, '2025-10-01', N'Snack khoai tây BBQ', 3, 3, 'img/bim.jpg'),
(N'Hảo Hảo tôm chua cay', 3500, 100, '2025-10-01', N'Mì gói tôm chua cay huyền thoại', 3, 3, 'img/hao.jpg'),
(N'Coca-Cola lon 330ml', 12000, 245, '2025-08-30', N'Nước giải khát có gas', 4, 5, 'img/cocalon.png'),
(N'Trà xanh 0 độ', 16000, 300, '2025-09-10', N'Trà xanh giải nhiệt', 4, 5, 'img/kodo.jpg'),
(N'Cà phê G7 hòa tan', 75000, 100, '2026-10-11', N'Cà phê hòa tan thơm ngon', 4, 6, 'img/g7.jpg'),
(N'Nước suối Lavie 500ml', 10000, 500, '2025-12-31', N'Nước tinh khiết', 4, 5, 'img/lavie.jpg'),
(N'Nước mắm Phú Quốc', 75000, 50, '2026-12-31', N'Nước mắm thượng hạng', 5, 6, 'img/nmpk.webp'),
(N'Muối Tây Ninh', 25000, 100, '2026-12-31', N'Muối ăn Tây Ninh chấm trái cây', 5, 6, 'img/ttn.jpg'),
(N'Tiêu đen nguyên hạt', 250000, 50, '2026-12-31', N'Tiêu đen nguyên hạt', 5, 6, 'img/hn.jpg'),
(N'Hạt nêm Knorr 400g', 45000, 100, '2026-12-31', N'Gia vị hạt nêm thơm ngon', 5, 6, 'img/hn.jpg'),
(N'Son Mac Ruby Woo', 650000, 30, '2027-12-31', N'Son Mac Ruby Woo chính hãng', 6, 2, 'img/sm.jpg'),
(N'Sữa rửa mặt Cetaphil', 250000, 50, '2026-12-31', N'Sữa rửa mặt dịu nhẹ', 6, 2, 'img/srm.jpg'),
(N'Kem chống nắng Anessa', 700000, 20, '2027-06-30', N'Kem chống nắng Nhật Bản', 6, 2, 'img/kcn.webp'),
(N'Dầu gội sunsilk', 180000, 39, '2027-05-05', N'Dầu gội phục hồi tóc hư tổn', 6, 2, 'img/dgs.jpg');

-- Bảng Đơn hàng
INSERT INTO DonHang (MaNV, MaKH, NgayMua, tongtien) VALUES 
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
('admin', '1', 1, 1), ('admin2@example.com', 'admin123', 1, 2),
('nv1', '1', 2, 1), ('nv2', '2', 2, 2),
('nv3e.com', 'password3', 2, 3), ('nv4@example.com', 'password4', 2, 4),
('nv5@example.com', 'password5', 2, 5), ('kh1', '1', 3, 1),
('kh2', '1', 3, 2), ('nam@example.com', 'customer3', 3, 3),
('quang@example.com', 'customer4', 3, 4), ('hanh@example.com', 'customer5', 3, 5);





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

CREATE TRIGGER trg_UpdateSoLuonggiam 
ON ChiTietDonHang 
AFTER delete 
AS
BEGIN
    UPDATE MatHang 
    SET SoLuongTon = MatHang.SoLuongTon + deleted.SoLuong
    FROM MatHang
    INNER JOIN inserted ON MatHang.MaMH = deleted.MaMH
END;