package NhanSu_DanhSach;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;
import Bank_Feature.ListAccount;
import NhanSu_Data.NhanSu;
import Tools.Check;

public class DanhSachTaiKhoan {
	public HashMap<String, NhanSu> danhSachTaiKhoan = new HashMap<String, NhanSu>();
	private Scanner sc = new Scanner(System.in);
	Check ck = new Check();
	
	public void DangKi(ListAccount listPay) throws ParseException 
	{
		System.out.println("\n--------------------------------------------------");
		System.out.println("QUẢN LÍ NHÂN SỰ <<ĐĂNG KÍ TÀI KHOẢN MỚI>>");
		System.out.println("---------");
		String soTaiKhoan;
		while(true)
		{
			System.out.print("Nhập tài khoản (số điện thoại): ");
			soTaiKhoan = sc.nextLine();
			if (danhSachTaiKhoan.containsKey(soTaiKhoan)) 
			{
				System.out.println("---------");
				System.out.println("Số điện thoại đã được đăng kí!!!.");
	            if (ck.Choice()) 
	                return;
				System.out.println("---------");
			}
			else
				if(ck.CheckFormatSDT(soTaiKhoan))
				{
					System.out.println("---------");
					System.out.println("Số điện thoại không hợp lệ!!!");
		            if (ck.Choice()) 
		                return;
					System.out.println("---------");
				}
				break;
		}
		NhanSu ns = new NhanSu();
		if(ns.Nhap(soTaiKhoan, listPay))
			return;
		this.danhSachTaiKhoan.put(soTaiKhoan, ns);
		System.out.println("---------");
		System.out.println("Tạo tài khoản thành công");
		System.out.println("Nhấn ENTER để tiếp tục");
		sc.nextLine();
	}

	public void dangNhap(ListAccount listPay, DanhSachCuaHang dsgh) {
		String soTaiKhoan;
		while(true)
		{
			System.out.println("\n--------------------------------------------------");
			System.out.println("QUẢN LÍ NHÂN SỰ | <<ĐĂNG NHẬP>>");
			System.out.println("---------");
			System.out.print("Nhập số tài khoản: ");
			soTaiKhoan = sc.nextLine();
			System.out.print("Nhập mật khẩu: ");
			String matKhau = sc.nextLine();
			NhanSu NhanSu = danhSachTaiKhoan.get(soTaiKhoan);
			if (NhanSu == null || !NhanSu.getMatKhau().equals(matKhau)) {
				System.out.println("---------");
				System.out.println("Số tài khoản hoặc mật khẩu không đúng!!!");
				if (ck.Choice()) 
	                return;
				System.out.println("---------");
			}
			else
				break;
		}
		NhanSu NhanSu = danhSachTaiKhoan.get(soTaiKhoan);
		System.out.println("---------");
		System.out.println("Đăng nhập thành công");
		System.out.println("Nhấn ENTER để tiếp tục");
	    sc.nextLine();
		if(NhanSu.getMaCuaHang()==null)
			menu(listPay,dsgh, NhanSu);
		else
		{
			if(NhanSu.getPhanQuyen().equals("NhanVien"))
				dsgh.getDanhSachGianHang().get(NhanSu.getMaCuaHang()).menuNhanVien(listPay, this, NhanSu);
			else
				if(NhanSu.getPhanQuyen().equals("QuanLi"))
					dsgh.getDanhSachGianHang().get(NhanSu.getMaCuaHang()).menuQuanLi(listPay, this, NhanSu);
		}
		
	}

	public void menu(ListAccount listPay, DanhSachCuaHang dsgh, NhanSu ns)
	{
		char choice;
		while (true) 
		{
			if(ns.getMaCuaHang() != null)
				return;
			System.out.println("\n--------------------------------------------------");
			System.out.println("QUẢN LÍ NHÂN SỰ | <<TÍNH NĂNG>>");
			System.out.println("---------");
			System.out.println("1. Thông tin tài khoản");
			System.out.println("2. Sửa thông tin");
			System.out.println("3. Xin việc");
			System.out.println("4. Mở gian hàng");
			System.out.println("5. Đổi mật khẩu");
			System.out.println("0. Đăng xuất");
			System.out.println("---------");
			System.out.print("Chọn chức năng: ");
			choice = sc.nextLine().charAt(0);
			switch (choice) 
			{
	            case '1':
	        		System.out.println("\n--------------------------------------------------");
	                System.out.println("QUẢN LÍ NHÂN SỰ | <<THÔNG TIN TÀI KHOẢN>>");
	        		System.out.println("---------");
	                ns.hienThiThongTin(null);
	        		System.out.println("---------");
	        		System.out.println("Tạo gian hàng thành công");
	        		System.out.println("Nhấn ENTER để tiếp tục");
	        		sc.nextLine();
	                break;
	
	            case '2':
	                ns.capNhapThongTin(listPay, this);
	                break;
	
	            case '3':
	                ns.XinViec(dsgh);
	                break;
	
	            case '4':
	            	dsgh.TaoCuaHang(listPay, this, ns);
	                break;
	
	            case '5':
	                if(ns.RePassWord())
	                	return;
	                break;
	
	            case '0': return;
	
	            default:
	            	System.out.println("---------");
					System.out.println("Lựa chọn của bạn không tồn tại!!!"); 
					System.out.println("nhấn phím ENTER để tiếp tục");
					sc.nextLine();
					break;
			}	
		}
	}
}
