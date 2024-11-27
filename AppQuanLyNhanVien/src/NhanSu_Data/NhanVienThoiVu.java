package NhanSu_Data;

import java.time.LocalDate;

import Bank_Feature.ListAccount;

public class NhanVienThoiVu extends NhanSu  implements NhanSu_Interface.TinhLuong{

	public NhanVienThoiVu(String id, String matKhau, String hoTen, String ngaySinh, String soDienThoai, String email,
			String diaChi, String soTaiKhoanNganHang, String tenTaiKhoanNganHang, double luongCoBan, double heSoLuong,String tenGianHang, String phanQuyen, String ngayBatDau, double phuCap) {
		super(id, matKhau, hoTen, ngaySinh, soDienThoai, email, diaChi, soTaiKhoanNganHang, tenTaiKhoanNganHang, heSoLuong, tenGianHang,
				phanQuyen);
		// TODO Auto-generated constructor stub
		this.luongCoBan = luongCoBan;
	}
	
	//constructor dùng để thay đổi chức vụ
	public NhanVienThoiVu(NhanSu ns) {
		super(ns);
		// TODO Auto-generated constructor stub
		this.luongCoBan = 150000;
		this.phanQuyen = "NhanVien";
	}

	public NhanVienThoiVu(String id, String soDienThoai, String matKhau, String hoTen, String ngaySinh, String email, String diaChi,
			String soTaiKhoanNganHang, String tenTaiKhoanNganHang, String ngayBatDau) {
		super(id, soDienThoai, matKhau, hoTen, ngaySinh, email, diaChi, soTaiKhoanNganHang, tenTaiKhoanNganHang);
		// TODO Auto-generated constructor stub
		this.luongCoBan = 150000;
		this.phanQuyen = "NhanVien";
	}
	
	public NhanVienThoiVu() {
		super();
		this.luongCoBan = 150000;
		this.phanQuyen = "NhanVien";
		// TODO Auto-generated constructor stub
	}

	@Override
	public double TinhLuong(int soCaLam) {
		// TODO Auto-generated method stub
		return this.heSoLuong*this.luongCoBan*soCaLam;
	}

	@Override
	public void hienThiThongTin(String tenGH) {
		// TODO Auto-generated method stub
		super.hienThiThongTin(tenGH);
		System.out.println("Cửa hàng \t: " + tenGH);
        System.out.println("Lương cơ bản \t: " + this.luongCoBan + "VND/Ca");
		System.out.println("---------");
		System.out.println("Nhấn ENTER để tiếp tục");
		sc.nextLine();
	}

	@Override
	public boolean Nhap(String sdt, ListAccount listPay) {
		// TODO Auto-generated method stub
		super.Nhap(sdt, listPay);
		return false;
	}


}
