create database QuanLySinhVien
use QuanLySinhVien
go
create table taikhoan(
taikhoan varchar(20),
matkhau varchar(20),
roles varchar(50)
)
create table monhoc(
monhocID varchar(10) primary key,
tenmh nvarchar(100),
gvcn nvarchar(100),
sotinchi int
)
create table sinhvien(
masv varchar(10) primary key,
hoten nvarchar(100),
monhocID varchar(10) references monhoc(monhocID),
gioitinh nvarchar(10),
tuoi int,
diachi nvarchar(100),
sdt varchar(11),
email varchar(100)
)
create table diem(
masv varchar(10) references sinhvien(masv),
monhocid varchar(10) references monhoc(monhocID),
lab1 float,
lab2 float,
lab3 float,
lab4 float,
tiendo float,
asm1 float,
asm2 float,
primary key ( masv,monhocID)
)
create table diemrenluyen(
  diem1 float,
  diem2 float,
  diem3 float,
  diem4 float,
  diem5 float,
  diem6 float,
)
insert into diemrenluyen (diem1,diem2,diem3,diem4,diem5,diem6) values (5,10,5,10,10,20),
                                                                      (5,20,15,20,10,20),
																	  (15,10,5,10,10,25),
																	  (25,15,5,10,20,10),
																	  (35,10,15,10,10,25),
																	  (15,20,5,10,30,10),
																	  (15,30,25,10,10,30),
																	  (25,10,5,10,20,20)

select * from diemrenluyen
insert into monhoc values ('IT18203',N'Nhập Môn Lập Trình',N'Trần Thu Hiền',3)
insert into monhoc values ('IT18202',N'Lập Trình Java 1',N'Dương Tuấn Linh',2)
insert into monhoc values ('SOB203',N'Lập Trình Java 3',N'Trần Ngọc Quang',3)
insert into monhoc values ('COM18203',N'Cơ Sở Dữ Liệu',N'Dương Thanh Phong',3)
select * from monhoc

insert into sinhvien values ('PH30061',N'Nguyễn Thế Anh','SOB203',N'Nam',20,N'Hà Nam','0123456789','theanhph30061@gamil.com')
insert into sinhvien values ('PH30062',N'Nguyễn Bá Anh Toàn','IT18203',N'Nam',23,N'Hà Nội','0123456788','anhtoanph30062@gamil.com')
insert into sinhvien values ('PH30063',N'Lê Thị Thơ','IT18202',N'Nữ',25,N'Thanh Hóa','0123456779','thithoph30063@gamil.com')
insert into sinhvien values ('PH30064',N'Lê Văn Dũng','COM18203',N'Nam',20,N'Sơn La','0123456769','vandungph30064@gamil.com')
insert into sinhvien values ('PH30065',N'Nguyễn Hải Huy','SOB203',N'Nam',23,N'Vĩnh Phúc','0123356789','haihuyph30065@gamil.com')
insert into sinhvien values ('PH30066',N'Đỗ Thành Đạt','COM18203',N'Nam',22,N'Hải Phòng','0113456789','thanhdatph30066@gamil.com')
insert into sinhvien values ('PH30071',N'Trần Ngọc Quang','SOB203',N'Nam',23,N'Thái Bình','0968251020','quangtnph30071@gamil.com')
select * from sinhvien
update sinhvien set masv = 'Ph30072',hoten = N'Trần quang',monhocID = 'IT18202', gioitinh = N'Nam',diachi=N'tb',sdt='01999',email='abc'
where masv like '%PH30069%'

insert into diem values('PH30061','SOB203',8,7.5,9,6.5,7.5,8,9)
insert into diem values('PH30062','IT18203',8.5,7,9.5,7.5,7.5,8.5,9.5)
insert into diem values('PH30063','IT18202',6,6.5,7,6.5,7.5,6,7.5)
insert into diem values('PH30064','COM18203',3,2.5,3,1.5,2.5,2,3)
insert into diem values('PH30065','SOB203',4,6.5,7,5.5,4.5,6,5)
insert into diem values('PH30066','COM18203',6,7.5,8,9.5,8.5,7,7)
insert into diem values('PH30071','SOB203',9,9.5,9,9.5,10,10,9.5)
select diem.masv,hoten,diem.monhocID , (((lab1+lab2+lab3+lab4+tienDo)*5)+asm1*30 + asm2*45 )/100 as dtb from diem join sinhvien on diem.masv= sinhvien.masv
SELECT sinhvien.masv,hoten,sinhvien.monhocID,lab1,lab2,lab3,lab4,tiendo,asm1,asm2 from diem full join sinhvien on diem.masv= sinhvien.masv

insert into taikhoan values('giaovien','giaovien','admin')
insert into taikhoan values('sinhvien','sinhvien','sinhvien')
select*from taikhoan

