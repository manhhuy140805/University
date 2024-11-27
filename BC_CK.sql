

if exists (select * from sys.databases where name = 'TranDinhManhHuy_22222')
	begin
		--sử dụng database master để xóa database đã tồn tại 
		use master
		--Đóng tất cả các kết nối đến cơ sở, dữ liệu chuyển sang chế độ single user 
		alter database TranDinhManhHuy_22222 set single_user with rollback immediate
		drop database TranDinhManhHuy_22222;
	end
create database TranDinhManhHuy_22222
go
use TranDinhManhHuy_22222
go

create table CanBoKhoa 
(
	maChucVu	Char(5) Primary key,
	tenChucVu	Nvarchar(64) Not null unique
)

create table KhoaCbk
(
	maKhoa		Char(7),
	maChucVu	Char(5),
	Primary key(maKhoa, maChucVu)
)

create table KhoaCbkGv
(
	maKhoa		Char(7),
	maChucVu	Char(5),
	maGV		Char(15),
	Primary key(maKhoa, maChucVu, maGV)
)

create table Nganh
(
	maNganh		Varchar(7)	Primary key,
	tenNganh	Nvarchar(64) Not null unique,
	maKhoa		Char(7)	Not null
)

create table Khoa
(
	maKhoa		Char(7)	Primary key,
	tenKhoa		Nvarchar(32) Not null unique
)

create table CanBoLop
(
	maChucVu	Char(5)	Primary key,
	tenChucVu	Nvarchar(32) Not null unique
)

create table LopSinhHoat
(
	maLopSH		Varchar(5)	Primary key,
	maNganh		Varchar(7)	Not null
)

create table LopShCbl
(
	maLopSH		Varchar(5),
	maChucVu	Char(5),
	Primary key (maLopSH, maChucVu)
)

create table LopShCblSv
(
	maLopSH		Varchar(5),
	maChucVu	Char(5),
	MaSV		Varchar(15),
	ngayHieuLuc	Date Not null,
	Primary key (maLopSH, maChucVu, MaSV)
)

create table GVCN
(
	maLopSH		Varchar(5),
	maGV		Char(15),
	ngayHieuLuc	Date not null,
	Primary key (maLopSH, MaGV)
)

create table DiemThanhPhan
(
	maDTP		Char(3)	Primary key,
	loaiDTP		Nvarchar(32) Not null,
	tiLe		Float Not null
)

create table DiemTpLopHp
(
	maLHP		Varchar(20),
	maDTP		Char(3),
	Primary key (maDTP, maLHP)
)

create table DiemTpLopHpSv
(
	MaSV		Varchar(15),
	maLHP		Varchar(20),
	maDTP		Char(3),
	Primary key (maLHP, maDTP, MaSV)
)

create table CoSo
(
	maCS		Char(4)	Primary key,
	tenCS		Nvarchar(30) Not null,
	maPX		Char(6)	not null,
	diaChiCuThe Nvarchar(32) not null 
)

create table Khu
(
	maKhu		Char(3)	Primary key,
	tenKhu		Nvarchar(32) Not null,
	maCS		Char(4) 
)

create table PhongHoc
(
	maPhong		Char(5)	Primary key,
	tenPhong	Nvarchar(32) Not null,
	maKhu		Char(3)
)

create table HocPhan
(
	maHP	Char(7)	Primary key,
	tenHP	Nvarchar(64) Not null,
	soTC	Tinyint	Not null
)

create table Tiet
(
	maTiet		Tinyint	Primary key,
	gioBD		Time Not null,
	thoiLuong	Tinyint	Not null,
)

create table HocKy
(
	maHK		Char(5)	Primary key,
	ngayBD 		Date Not null,
	ngayKT		Date Not null,
)

create table LopHocPhan
(
	maLHP		Varchar(20)	Primary key,
	maHP		Char(7)	Not null,
	maHK		Char(5)	Not null,
	maPhong		Char(5)	Not null,
	maGV		Char(15) not null,
	ngayHL		Date Not null,
	Thu			Tinyint Not null,
	maTiet		Tinyint	Not null
)

create table SinhVien
(
	maSV		Varchar(15)	Primary key,
	hoTen		Nvarchar(32) Not null,
	maLopSH		Varchar(5) Not null,
	ngaySinh	Date,
	SDT			Varchar(20) Not null unique,
	email		Varchar(32), 	
	stkATM		Varchar(15),	
	maPX		Char(6) Not null,
	diaCHiCuThe Nvarchar(32)
)

create table GiangVien
(
	maGV		Char(15)	Primary key,
	hoTen		Nvarchar(32) Not null,
	maKhoa		Char(7),
	ngaySinh	Date,	
	SDT			Varchar(11)	Not null unique,
	email		Varchar(32),
	stkATM		Varchar(15),
	maPX		Char(6),
	diaCHiCuThe Nvarchar(32)
)

create table QuocGia
(
	maQG		Char(6)	Primary key,
	tenQG		Nvarchar(32) Not null
)

create table TinhTP
(
	maTTP		Char(6)	Primary key,
	tenTTP		Nvarchar(32) Not null,
	maQG		Char(6)	Not null,
)

create table QuanHuyen
(
	maQH	Char(6)	Primary key,
	tenQH	Nvarchar(32) Not null,
	maTTP	Char(6)	Not null
)

create table PhuongXa
(
	maPX	Char(6)	Primary key,
	tenPX	Nvarchar(32) Not null,
	maQH	Char(6)	Not null
)
go

--Foreign Key

--Bảng Tỉnh Thành Phố
alter table TinhTP add
	constraint FK_TinhTP_maQG foreign key (maQG) references QuocGia(maQG)
		on delete cascade on update cascade

--Bảng Quận Huyện
alter table QuanHuyen add
	constraint FK_QuanHuyen_maTTP foreign key (maTTP) references TinhTP(maTTP)
		on delete cascade on update cascade

--Bảng Phường Xã
alter table PhuongXa add
	constraint FK_PhuongXa_maQH foreign key (maQH) references QuanHuyen(maQH)
		on delete cascade on update cascade

-- Bảng Khoa - Cán Bộ Khoa
alter table KhoaCbk add
	constraint FK_KhoaCbk_maChucVu 
		foreign key (maChucVu) references CanBoKhoa(maChucVu)
		on delete cascade on update cascade,
	constraint FK_KhoaCbk_maKhoa 
		foreign key (maKhoa) references Khoa(maKhoa)
		on delete cascade on update cascade

-- Bảng Khoa - Cán Bộ Khoa - Giáo Viên
alter table KhoaCbkGv add
	constraint FK_KhoaCbkGv_KhoaCbk
		foreign key (maKhoa, maChucVu) references KhoaCbk(maKhoa, maChucVu)
		on delete cascade on update cascade,
	constraint FK_KhoaCbkGv_maGV 
		foreign key (maGV) references GiangVien(maGV)
		on delete cascade on update cascade

--Bảng Ngành
alter table Nganh add
	constraint FK_Nganh_maKhoa foreign key (maKhoa) references Khoa(maKhoa)
		on delete cascade on update cascade

-- Bảng Lớp Sinh Hoạt
alter table LopSinhHoat add
	constraint FK_LopSinhHoat_maNganh 
		foreign key (maNganh) references Nganh(maNganh)
			on delete cascade on update cascade

-- Bảng Lớp Sinh Hoạt - Cán Bộ Lớp	
alter table LopShCbl add
	constraint FK_LopShCbl_maChucVu 
		foreign key (maChucVu) references CanBoLop(maChucVu)
			on delete cascade on update cascade,
	constraint FK_KhoaCbkGv_maKhoa 
		foreign key (maLopSH) references LopSinhHoat(maLopSH)
			on delete cascade on update cascade

-- Bảng Lớp Sinh Hoạt - Cán Bộ Lớp - Sinh Viên
alter table LopShCblSv add
	constraint FK_LopShCblSv_LopShCbl
		foreign key (maLopSH, maChucVu) references LopShCbl(maLopSH, maChucVu)
			on delete cascade on update cascade,
	constraint FK_LopShCblSv_maSV 
		foreign key (maSV) references SinhVien(maSV)
			on delete cascade on update cascade

-- Bảng Giảng Viên Chủ Nhiệm
alter table GVCN add
	constraint FK_GVCN_LopSH
		foreign key (maLopSH) references LopSinhHoat(maLopSH)
			on delete cascade on update cascade,
	constraint FK_GVCN_maGV
		foreign key (maGV) references GiangVien(maGV)
			on delete cascade on update cascade

-- Bảng Sinh Viên
alter table SinhVien add
	constraint FK_SinhVien_LopSH
		foreign key (maLopSH) references LopSinhHoat(maLopSH)
			on delete no action on update no action,
	constraint FK_SinhVien_maPX
		foreign key (maPX) references PhuongXa(maPX)
			on delete cascade on update cascade


-- Bảng Giảng Viên
alter table GiangVien add
	constraint FK_GiangVien_maKhoa
		foreign key (maKhoa) references Khoa(maKhoa)
			on delete no action on update no action,
	constraint FK_GiangVien_maPX
		foreign key (maPX) references PhuongXa(maPX)
			on delete set null on update set null

-- Bảng Điểm Thành Phần - Lớp Học Phần
alter table DiemTpLopHp add
	constraint FK_DiemTpLopHp_maLHP
		foreign key (maLHP) references LopHocPhan(maLHP)
			on delete cascade on update cascade,
	constraint FK_DiemTpLopHp_maDTP
		foreign key (maDTP) references DiemThanhPhan(maDTP)
			on delete cascade on update cascade

-- Bảng Điểm Thành Phần - Lớp Học Phần - Sinh Viên
alter table DiemTpLopHpSv add
	constraint FK_DiemTpLopHpSv_maLHP
		foreign key (maDTP, maLHP) references DiemTpLopHp(maDTP, maLHP)
			on delete cascade on update cascade,
	constraint FK_DiemTpLopHpSv_maSV 
		foreign key (maSV) references SinhVien(maSV)
			on delete cascade on update cascade

-- Bảng Lớp Học Phần 
alter table LopHocPhan add
	constraint FK_LopHocPhan_maHP
		foreign key (maHP) references HocPhan(maHP)
			on delete cascade on update cascade,
	constraint FK_LopHocPhan_maPhong
		foreign key (maPhong) references PhongHoc(maPhong)
			on delete cascade on update cascade,
	constraint FK_LopHocPhan_maTiet
		foreign key (maTiet) references Tiet(maTiet)
			on delete cascade on update cascade,
	constraint FK_LopHocPhan_maHK
		foreign key (maHK) references HocKy(maHK)
			on delete cascade on update cascade

-- Bảng Cơ sở 
alter table CoSo add
	constraint FK_CoSo_maPX
		foreign key (maPX) references PhuongXa(maPX)
			on delete no action on update no action

-- Bảng Khu
alter table Khu add
	constraint FK_Khu_maCS
		foreign key (maCS) references CoSo(maCS)
			on delete set null on update set null

-- Bảng Phòng Học
alter table PhongHoc add
	constraint FK_PhongHoc_maKhu
		foreign key (maKhu) references Khu(maKhu)
			on delete set null on update set null


--Bổ sung ràng buộc

--Bảng cán bộ khoa
alter table CanBoKhoa add
	constraint CK_canBoKhoa_maChucVu check(maChucVu like 'CV[0-9][0-9]')

--Bảng ngành
alter table Nganh add
	constraint CK_Nganh_maNganh check 
		(len(maNganh) in(6,7) and maNganh like '[0-9]%' and maNganh not like '%[^0-9]%')

--bảng khoa
alter table Khoa add
	constraint CK_Khoa_maKhoa check (maKhoa LIKE '[0-9]%' AND maKhoa NOT LIKE '%[^0-9]%')

--bảng cán bộ lớp
alter table CanBoLop add
	constraint CK_canBoLop_maChucVu check(maChucVu like 'CV[0-9][0-9]')

--bảng lớp sinh hoạt
alter table LopSinhHoat add
	constraint CK_LopSinhHoat_maLopSH check 
		(maNganh like '[0-9][0-9][A-Z][A-Z][0-9]' and maNganh like '[0-9][0-9][A-Z][0-9]')


-- Bảng DiemThanhPhan
alter table DiemThanhPhan add
	constraint CK_DiemThanhPhan_tiLe 
		check (tiLe >= 0 and tiLe <= 100),
	constraint CK_DiemThanhPhan_maDTP
		check(maDTP like 'CV[0-9]')
-- Bảng CoSo
alter table CoSo add
	constraint CK_CoSo_maCS check (maCS like 'CS[0-9][0-9]')

-- Bảng Khu
alter table Khu add
	constraint CK_Khu_maKhu check (maKhu like 'K[0-9][0-9]');

-- Bảng PhongHoc
alter table PhongHoc add
	constraint CK_PhongHoc_maPhong check (maPhong >= 3 and tenPhong not like '%[^a-zA-Z ]%');

-- Bảng HocPhan
alter table HocPhan add
	constraint CK_HocPhan_soTC check (soTC > 0);

-- Bảng Tiet
alter table Tiet add
	constraint CK_Tiet_thoiLuong check (thoiLuong > 0 and thoiLuong <= 60);

-- Bảng SinhVien
alter table SinhVien add
	constraint CK_SinhVien_SDT check (SDT like '[0-9]%'and SDT not like '%[^0-9]%' and len(SDT) between 10 and 11 ),
	constraint CK_SinhVien_email check (email like '%@%.%'),
	constraint CK_SinhVien_stkATM check (stkATM like '[0-9]%' and stkATM not like '%[^0-9]%');

-- Bảng GiangVien
alter table GiangVien add
	constraint CK_GiangVien_maGV check (maGV like '[0-9]%'and maGV not like '%[^0-9]%' and len(maGV) = 15 ),
	constraint CK_GiangVien_SDT check (SDT like '[0-9]%'and SDT not like '%[^0-9]%' and len(SDT) between 10 and 11 ),
	constraint CK_GiangVien_email check (email like '%@%.%'),
	constraint CK_GiangVien_stkATM check (stkATM like '[0-9]%' and stkATM not like '%[^0-9]%');

go

insert into QuocGia(maQG, tenQG)
values 
	('QG001', N'Việt Nam'),
	('QG002', N'Mỹ'),
	('QG003', N'Canada'),
	('QG004', N'Nhật Bản'),
	('QG005', N'Hàn Quốc'),
	('QG006', N'Pháp'),
	('QG007', N'Đức'),
	('QG008', N'Trung Quốc'),
	('QG009', N'Ấn Độ'),
	('QG010', N'Australia'),
	('QG011', N'Brazil'),
	('QG012', N'Nga');

-- Bảng TinhTP
insert into TinhTP(maTTP, tenTTP, maQG)
values 
	('TTP001', N'Đà Nẵng', 'QG001'),
	('TTP002', N'Đắk Lắk', 'QG001'),
	('TTP003', N'Hà Tĩnh', 'QG001'),
	('TTP004', N'Quảng Nam', 'QG001'),
	('TTP005', N'Quảng Ngãi', 'QG001'),
	('TTP006', N'Quảng Trị', 'QG001'),
	('TTP007', N'Thanh Hóa', 'QG001'),
	('TTP008', N'Thừa Thiên - Huế', 'QG001'),
	('TTP009', N'TP. Hồ Chí Minh', 'QG001'),
	('TTP010', N'Hà Nội', 'QG001');

-- Bảng QuanHuyen
insert into QuanHuyen(maQH, tenQH, maTTP)
values 
	('QH001', N'Quận Sơn Trà', 'TTP001'),
	('QH002', N'Quận Hải Châu', 'TTP001'),
	('QH003', N'Quận Thanh Khê', 'TTP001'),
	('QH004', N'Quận Cẩm Lệ', 'TTP001'),
	('QH005', N'Quận Ngũ Hành Sơn', 'TTP001'),
	('QH006', N'Quận Liên Chiểu', 'TTP001'),
	('QH007', N'Huyện Hòa Vang', 'TTP001'),
	('QH008', N'Thành phố Huế', 'TTP008'),
	('QH009', N'Huyện Phong Điền', 'TTP008'),
	('QH010', N'Huyện Quảng Điền', 'TTP008'),
	('QH011', N'Huyện Phú Vang', 'TTP008'),
	('QH012', N'Huyện Hương Thủy', 'TTP008'),
	('QH013', N'Huyện Hương Trà', 'TTP008'),
	('QH014', N'Huyện A Lưới', 'TTP008'),
	('QH015', N'Huyện Nam Đông', 'TTP008');

-- Bảng PhuongXa
insert into PhuongXa(maPX, tenPX, maQH)
values 
	('PX0001', N'Phường Phước Mỹ', 'QH001'),
	('PX0002', N'Phường An Hải Đông', 'QH001'),
	('PX0003', N'Phường An Hải Bắc', 'QH001'),
	('PX0004', N'Phường An Hải Tây', 'QH001'),
	('PX0005', N'Phường Mân Thái', 'QH001'),
	('PX0006', N'Phường Nại Hiên Đông', 'QH001'),
	('PX0007', N'Phường Thọ Quang', 'QH001'),
	('PX0008', N'Phường Hải Châu I', 'QH002'),
	('PX0009', N'Phường Hải Châu II', 'QH002'),
	('PX0010', N'Phường Nam Dương', 'QH002'),
	('PX0011', N'Phường Phước Ninh', 'QH002'),
	('PX0012', N'Phường Thuận Phước', 'QH002'),
	('PX0013', N'Phường Thanh Bình', 'QH002'),
	('PX0014', N'Phường Thạch Thang', 'QH002'),
	('PX0015', N'Phường Hòa Cường Bắc', 'QH002');

-- Bảng CanBoKhoa
INSERT INTO CanBoKhoa (maChucVu, tenChucVu) VALUES 
    ('CV01', N'Trưởng khoa'),
    ('CV02', N'Phó trưởng khoa'),
	('CV03', N'Giảng viên giảng dạy');


-- Bảng Khoa
INSERT INTO Khoa (maKhoa, tenKhoa) VALUES 
    ('1234001', N'Khoa Công nghệ số'),
    ('1234002', N'Khoa Cơ khí'),
    ('1234003', N'Khoa Điện - Điện tử'),
    ('1234004', N'Khoa Hóa học'),
    ('1234005', N'Khoa Xây dựng');

-- Bảng KhoaCbk
INSERT INTO KhoaCbk (maKhoa, maChucVu) VALUES 
    ('1234001', 'CV01'),
    ('1234002', 'CV02'),
    ('1234002', 'CV01'),
    ('1234004', 'CV02'),
    ('1234002', 'CV03'),
    ('1234003', 'CV03'),
    ('1234005', 'CV02'),
    ('1234001', 'CV03'),
    ('1234003', 'CV02'),
    ('1234004', 'CV03'),
    ('1234005', 'CV03');

set dateformat dmy;
go

-- Bảng GiangVien
INSERT INTO GiangVien (maGV, hoTen, maKhoa, ngaySinh, SDT, email, stkATM, maPX, diaChiCuThe) VALUES
    ('123456789012345', N'Nguyễn Hữu An', '1234001', '15-04-1978', '0912345678', 'nguyenhuuan@university.edu', '123456789012345', 'PX0001', N'123 Đường Hòa Bình'),
    ('234567890123456', N'Lê Minh Tuấn', '1234002', '10-08-1985', '0934567890', 'leminhtuan@university.edu', '234567890123456', 'PX0002', N'456 Đường Thống Nhất'),
    ('345678901234567', N'Phạm Thị Hạnh', '1234003', '23-02-1980', '0961234567', 'phamthihanh@university.edu', '345678901234567', 'PX0003', N'789 Đường Nguyễn Trãi'),
    ('456789012345678', N'Trần Quốc Anh', '1234004', '30-06-1992', '0923456789', 'tranquoanh@university.edu', '456789012345678', 'PX0004', N'101 Đường Trường Sơn'),
    ('567890123456789', N'Vũ Hồng Ngọc', '1234005', '05-11-1988', '0976543210', 'vuhongngoc@university.edu', '567890123456789', 'PX0005', N'202 Đường Hoàng Văn Thụ'),
    ('678901234567890', N'Hoàng Thị Lan', '1234001', '12-03-1975', '0901234567', 'hoangthilan@university.edu', '678901234567890', 'PX0006', N'321 Đường Bình Minh'),
    ('789012345678901', N'Đặng Thanh Hùng', '1234002', '25-07-1987', '0912349876', 'dangthanhhung@university.edu', '789012345678901', 'PX0007', N'654 Đường Lê Lợi'),
    ('890123456789012', N'Ngô Hải Yến', '1234003', '18-12-1989', '0987654321', 'ngohaien@university.edu', '890123456789012', 'PX0008', N'789 Đường Trần Hưng Đạo'),
    ('901234567890123', N'Phan Thị Thu', '1234004', '09-05-1982', '0943214567', 'phanthithu@university.edu', '901234567890123', 'PX0009', N'555 Đường Hoàng Hoa Thám'),
    ('012345678901234', N'Nguyễn Văn Long', '1234005', '22-09-1990', '0932123456', 'nguyenvanlong@university.edu', '012345678901234', 'PX0010', N'888 Đường Xuân Thủy'),
	('112233445566778', N'Phạm Văn Bảo', '1234001', '05-01-1976', '0911223344', 'phamvanbao@university.edu', '112233445566778', 'PX0011', N'123 Đường Tự Do'),
    ('223344556677889', N'Nguyễn Thị Hoa', '1234002', '17-03-1982', '0922334455', 'nguyenthihhoa@university.edu', '223344556677889', 'PX0012', N'456 Đường Hạnh Phúc'),
    ('334455667788990', N'Lê Hoàng Nam', '1234003', '12-07-1985', '0933445566', 'lehoangnam@university.edu', '334455667788990', 'PX0013', N'789 Đường An Bình'),
    ('445566778899001', N'Trần Thị Yến', '1234004', '09-02-1990', '0944556677', 'tranthiyen@university.edu', '445566778899001', 'PX0014', N'321 Đường Thái Hà'),
    ('556677889900112', N'Vũ Văn Thắng', '1234005', '28-11-1987', '0955667788', 'vuvanthuong@university.edu', '556677889900112', 'PX0015', N'654 Đường Nam Đồng'),
    ('667788990011223', N'Ngô Thị Hương', '1234001', '16-06-1991', '0966778899', 'ngothihuong@university.edu', '667788990011223', 'PX0005', N'888 Đường Lạc Long Quân'),
    ('778899001122334', N'Hoàng Minh Quân', '1234002', '21-10-1979', '0977889900', 'hoangminhquan@university.edu', '778899001122334', 'PX0003', N'555 Đường Lê Duẩn'),
    ('889900112233445', N'Nguyễn Văn Bình', '1234003', '08-12-1984', '0988990011', 'nguyenvanbinh@university.edu', '889900112233445', 'PX0008', N'101 Đường Võ Chí Công'),
    ('990011223344556', N'Trần Thị Lý', '1234004', '14-04-1993', '0990011223', 'tranthily@university.edu', '990011223344556', 'PX0010', N'202 Đường Nguyễn Khánh Toàn'),
    ('001122334455667', N'Lê Thanh Huyền', '1234005', '19-09-1988', '0901122334', 'lethanhhuyen@university.edu', '001122334455667', 'PX0010', N'321 Đường Phạm Văn Đồng');

-- Bảng KhoaCbkGv
INSERT INTO KhoaCbkGv (maKhoa, maChucVu, maGV) VALUES 
    ('1234001', 'CV01', '112233445566778'),
    ('1234002', 'CV02', '234567890123456'),
    ('1234002', 'CV01', '345678901234567'),
    ('1234004', 'CV02', '456789012345678'),
    ('1234002', 'CV03', '789012345678901'),
    ('1234003', 'CV03', '890123456789012'),
    ('1234005', 'CV02', '012345678901234'),
    ('1234001', 'CV03', '667788990011223'),
    ('1234004', 'CV03', '990011223344556'),
    ('1234003', 'CV02', '889900112233445');


-- Bảng Nganh
INSERT INTO Nganh (maNganh, tenNganh, maKhoa) VALUES 
    ('123001', N'Công nghệ phần mềm', '1234001'),
    ('123002', N'Hệ thống thông tin', '1234001'),
    ('123003', N'Trí tuệ nhân tạo', '1234001'),
    ('234001', N'Cơ điện tử', '1234002'),
    ('234002', N'Kỹ thuật ô tô', '1234002'),
    ('345001', N'Điện tử viễn thông', '1234003'),
    ('345002', N'Tự động hóa', '1234003'),
    ('456001', N'Hóa phân tích', '1234004'),
    ('456002', N'Hóa hữu cơ', '1234004'),
    ('567001', N'Kỹ thuật xây dựng', '1234005'),
    ('567002', N'Quản lý dự án', '1234005');

-----------------------------------------------------------------------------
-- Bảng LopSinhHoat
INSERT INTO LopSinhHoat (maLopSH, maNganh) VALUES 
    ('IT001', 'CNTT01'),
    ('IT002', 'CNTT02'),
    ('MATH01', 'MAT01'),
    ('PHY01', 'PHY01'),
    ('CHE01', 'CHE01');

-- Bảng LopShCbl
INSERT INTO LopShCbl (maLopSH, maChucVu) VALUES 
    ('IT001', 'CV001'),
    ('IT002', 'CV002'),
    ('MATH01', 'CV001'),
    ('PHY01', 'CV002'),
    ('CHE01', 'CV003');

-- Bảng SinhVien
INSERT INTO SinhVien (maSV, hoTen, maLopSH, ngaySinh, SDT, email, stkATM, maPX, diaCHiCuThe) VALUES 
    ('SV001', N'Nguyễn Thị C', 'IT001', CONVERT(DATE, '25062000', 103), '0922334455', 'nguyenthic@university.edu', '123456789012345', 'PX001', N'01 Đường X, Phường Y'),
    ('SV002', N'Lê Hoàng D', 'IT002', CONVERT(DATE, '13082001', 103), '0911223344', 'lehoangd@university.edu', '234567890123456', 'PX002', N'02 Đường Z, Phường Y'),
    ('SV003', N'Phan Văn E', 'MATH01', CONVERT(DATE, '15032002', 103), '0933445566', 'phanvane@university.edu', '345678901234567', 'PX003', N'03 Đường W, Phường T'),
    ('SV004', N'Trần Thị F', 'PHY01', CONVERT(DATE, '06092001', 103), '0944556677', 'tranthif@university.edu', '456789012345678', 'PX004', N'04 Đường V, Phường U'),
    ('SV005', N'Vũ Minh G', 'CHE01', CONVERT(DATE, '24122000', 103), '0955667788', 'vuminhg@university.edu', '567890123456789', 'PX005', N'05 Đường S, Phường R');

-- Bảng LopShCblSv
INSERT INTO LopShCblSv (maLopSH, maChucVu, MaSV, ngayHieuLuc) VALUES 
    ('IT001', 'CV001', 'SV001', CONVERT(DATE, '01092022', 103)),
    ('IT002', 'CV002', 'SV002', CONVERT(DATE, '02092022', 103)),
    ('MATH01', 'CV001', 'SV003', CONVERT(DATE, '03092022', 103)),
    ('PHY01', 'CV002', 'SV004', CONVERT(DATE, '04092022', 103)),
    ('CHE01', 'CV003', 'SV005', CONVERT(DATE, '05092022', 103));

-- Bảng GVCN
INSERT INTO GVCN (maLopSH, maGV, ngayHieuLuc) VALUES 
    ('IT001', 'GV001', CONVERT(DATE, '01092023', 103)),
    ('IT002', 'GV002', CONVERT(DATE, '02092023', 103)),
    ('MATH01', 'GV003', CONVERT(DATE, '03092023', 103)),
    ('PHY01', 'GV004', CONVERT(DATE, '04092023', 103)),
    ('CHE01', 'GV005', CONVERT(DATE, '05092023', 103));

-- Bảng DiemThanhPhan
INSERT INTO DiemThanhPhan (maDTP, loaiDTP, tiLe) VALUES 
    ('DTP01', N'Điểm chuyên cần', 10),
    ('DTP02', N'Điểm giữa kỳ', 30),
    ('DTP03', N'Điểm cuối kỳ', 60);

-- Bảng DiemTpLopHp
INSERT INTO DiemTpLopHp (maLHP, maDTP) VALUES 
    ('LHP001', 'DTP01'),
    ('LHP001', 'DTP02'),
    ('LHP001', 'DTP03'),
    ('LHP002', 'DTP01'),
    ('LHP002', 'DTP02');

-- Bảng CoSo
INSERT INTO CoSo (maCS, tenCS, maPX, diaChiCuThe) VALUES 
    ('CS01', N'Cơ sở A', 'PX001', N'123 Đường X'),
    ('CS02', N'Cơ sở B', 'PX002', N'456 Đường Y'),
    ('CS03', N'Cơ sở C', 'PX003', N'789 Đường Z'),
    ('CS04', N'Cơ sở D', 'PX004', N'101 Đường W'),
    ('CS05', N'Cơ sở E', 'PX005', N'202 Đường V');

-- Bảng Khu
INSERT INTO Khu (maKhu, tenKhu, maCS) VALUES 
    ('KHU01', N'Khu A1', 'CS01'),
    ('KHU02', N'Khu B1', 'CS02'),
    ('KHU03', N'Khu C1', 'CS03'),
    ('KHU04', N'Khu D1', 'CS04'),
    ('KHU05', N'Khu E1', 'CS05');

-- Bảng PhongHoc
INSERT INTO PhongHoc (maPhong, maKhu, tenPhong) VALUES 
    ('PH01', 'KHU01', N'Phòng học A1-101'),
    ('PH02', 'KHU02', N'Phòng học B1-201'),
    ('PH03', 'KHU03', N'Phòng học C1-301'),
    ('PH04', 'KHU04', N'Phòng học D1-401'),
    ('PH05', 'KHU05', N'Phòng học E1-501');

-- Bảng HocPhan
INSERT INTO HocPhan (maHP, tenHP, soTC) VALUES 
    ('HP01', N'Lập trình hướng đối tượng', 3),
    ('HP02', N'Giải tích', 4),
    ('HP03', N'Cơ học lượng tử', 3),
    ('HP04', N'Hóa hữu cơ', 4),
    ('HP05', N'Sinh học phân tử', 3);

-- Bảng LopHocPhan
INSERT INTO LopHocPhan (maLHP, maHP, maGV, maPhong) VALUES 
    ('LHP001', 'HP01', 'GV001', 'PH01'),
    ('LHP002', 'HP02', 'GV002', 'PH02'),
    ('LHP003', 'HP03', 'GV003', 'PH03'),
    ('LHP004', 'HP04', 'GV004', 'PH04'),
    ('LHP005', 'HP05', 'GV005', 'PH05');

-- Bảng DkyLopHp
INSERT INTO DkyLopHp (maLHP, maSV) VALUES 
    ('LHP001', 'SV001'),
    ('LHP002', 'SV002'),
    ('LHP003', 'SV003'),
    ('LHP004', 'SV004'),
    ('LHP005', 'SV005');

-- Bảng DiemHp
INSERT INTO DiemHp (maLHP, maSV, maDTP, diem) VALUES 
    ('LHP001', 'SV001', 'DTP01', 8.5),
    ('LHP001', 'SV001', 'DTP02', 7.0),
    ('LHP001', 'SV001', 'DTP03', 6.5),
    ('LHP002', 'SV002', 'DTP01', 9.0),
    ('LHP002', 'SV002', 'DTP02', 8.0);
