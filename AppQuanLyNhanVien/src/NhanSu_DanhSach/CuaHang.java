package NhanSu_DanhSach;

import java.util.HashMap;
import java.util.Scanner;

import Bank_Feature.ListAccount;
import NhanSu_Data.NhanSu;
import NhanSu_Data.NhanVienChinhThuc;
import NhanSu_Data.NhanVienThoiVu;
import NhanSu_Data.QuanLi;
import Tools.Check;

public class CuaHang {
	protected String maCuaHang;
	protected String tenCuaHang;
	protected String diaChi;
	protected HashMap<String, NhanSu> danhSachChoDuyet = new HashMap<String, NhanSu>();
	
	private Scanner sc = new Scanner(System.in);
	Check ck = new Check();

	
	
	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getMaCuaHang() {
		return maCuaHang;
	}

	public void setMaCuaHang(String maCuaHang) {
		this.maCuaHang = maCuaHang;
	}

	public String getTenCuaHang() {
		return tenCuaHang;
	}

	public void setTenCuaHang(String tenCuaHang) {
		this.tenCuaHang = tenCuaHang;
	}

	public HashMap<String, NhanSu> getDanhSachChoDuyet() {
		return danhSachChoDuyet;
	}

	public void setDanhSachChoDuyet(NhanSu ns) {
		this.danhSachChoDuyet.put(ns.getSoDienThoai(), ns);
	}
	
	public CuaHang() {
	}
	
	public void thongTinCuaHang()
	{
		System.out.println("Tên cửa hàng \t: "+ this.tenCuaHang);
		System.out.println("Địa chỉ \t: "+ this.diaChi);
	}
	
	public void InDSNhanSu(DanhSachTaiKhoan dstk)
	{
		System.out.println("\n--------------------------------------------------");
		System.out.println("Danh sách nhân sự");
		System.out.println("---------");
		for (NhanSu ns : dstk.danhSachTaiKhoan.values()) {
			if(ns.getMaCuaHang() != null)
				if( ns.getMaCuaHang().equals(maCuaHang))
				{
					System.out.print(ns);
					if (ns instanceof NhanVienChinhThuc) {
			            System.out.println("\t\t | Chức vụ : Nhân viên chính thức");
			        }
			        if (ns instanceof NhanVienThoiVu) {
			            System.out.println("\t\t | Chức vụ : Nhân viên thời vụ");
			        }
			        if (ns instanceof QuanLi) {
			            System.out.println("\t\t | Chức vụ : Quản lí");
			        }
				}
		}
		System.out.println("---------");
		System.out.println("Nhấn ENTER để tiếp tục");
		sc.nextLine();
	}
	
	public void InDSChoDuyet()
	{
		System.out.println("\n--------------------------------------------------");
		System.out.println("Danh sách ứng viên");
		System.out.println("---------");
		for (NhanSu ns : danhSachChoDuyet.values()) {
			System.out.println(ns);
		}
	}

	public CuaHang(String maGianHang, String tenGianHang, String diaChi) {
		super();
		this.maCuaHang = maGianHang;
		this.tenCuaHang = tenGianHang;
		this.diaChi = diaChi;
	}

	public boolean Nhap(DanhSachTaiKhoan dstk, DanhSachCuaHang dsgh) {
		while(true)
		{
			System.out.print("Nhập mã cửa hàng: ");
			this.maCuaHang = sc.nextLine();
			if(dsgh.danhSachGianHang.containsKey(this.maCuaHang))
			{
				System.out.println("Mã cửa hàng đã tồn tại!!!");
				if (ck.Choice()) 
	                return true;
				System.out.println("---------");
			}
			else 
				break;
			
		}
		System.out.print("Nhập tên gian hàng: ");
		this.tenCuaHang = sc.nextLine();
		dsgh.danhSachGianHang.put(maCuaHang, this);
		System.out.println("---------");
		System.out.println("Tạo gian hàng thành công");
		System.out.println("Nhấn ENTER để tiếp tục");
		sc.nextLine();
		return false;
		}

	private void timNhanSu(DanhSachTaiKhoan dstk) {
		System.out.println("\n--------------------------------------------------");
		System.out.println("QUẢN LÍ NHÂN SỰ | <<TRA CỨU THÔNG TIN NHÂN SỰ>>");
		System.out.println("---------");
		while(true)
		{
			System.out.println("Nhập số điện thoại nhân sự: ");
			String sdt = sc.nextLine();
			NhanSu ns = dstk.danhSachTaiKhoan.get(sdt);
			if (ns != null && ns.getMaCuaHang().equals(maCuaHang)) {
				System.out.println("\n--------------------------------------------------");
	            System.out.println("THÔNG TIN NHÂN SỰ");
	    		System.out.println("---------");
				ns.hienThiThongTin(this.tenCuaHang);
				return;
			} else {
				System.out.println("---------");
				System.out.println("Nhân sự không tồn tại.");
				if (ck.Choice()) 
	                return;
			}	
		}
	}

	private void ChoThoiViec(DanhSachTaiKhoan dstk) {
	}
	
	public void menuNhanVien(ListAccount listPay, DanhSachTaiKhoan dstk, NhanSu nv)
	{
		char choice;
		if(nv instanceof NhanVienThoiVu)
			nv = (NhanVienThoiVu) nv;
		if(nv instanceof NhanVienChinhThuc)
			nv = (NhanVienChinhThuc) nv;
		while (true) 
		{
			System.out.println("\n--------------------------------------------------");
			System.out.println("QUẢN LÍ NHÂN SỰ | <<TÍNH NĂNG>>");
			System.out.println("---------");
			System.out.println("1. Xem thông tin cá nhân");
			System.out.println("2. Xem bảng phân công công việc");
			System.out.println("3. Đăng ký ca làm");
			System.out.println("4. Xem bảng công cá nhân");
			System.out.println("5. Gửi yêu cầu sửa bảng công");
			System.out.println("6. Xem bảng lương chi tiết");
			System.out.println("7. Sửa thông tin cá nhân");
			System.out.println("8. Đổi mật khẩu tài khoản");
			System.out.println("9. Xin nghỉ việc");
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
	            	nv.hienThiThongTin(this.tenCuaHang);
	                break;
	
	            case '2':
	                break;
	
	            case '3':
	                break;
	
	            case '4':
	                break;
	
	            case '5':
	                break;
	               
	            case '6':
	                break;
	
	            case '7':
	            	nv.capNhapThongTin(listPay, dstk);
	                break;
	
	            case '8':
	                if(nv.RePassWord())
	                	return;
	                break;
	            case '9':
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
	
	public void menuQuanLi(ListAccount listPay, DanhSachTaiKhoan dstk, NhanSu ql)
	{
		String choice;
		ql = (QuanLi) ql;
		while (true) 
		{
			System.out.println("\n--------------------------------------------------");
			System.out.println("QUẢN LÍ NHÂN SỰ | <<TÍNH NĂNG>>");
			System.out.println("---------");
			System.out.println("1.  Xem danh sách nhân sự trong cửa hàng");
			System.out.println("2.  Tra cứu thông tin nhân sự theo số điện thoại");
			System.out.println("3.  Sắp xếp ca làm cho nhân viên");
			System.out.println("4.  Duyệt ca làm");
			System.out.println("5.  Tính lương cho toàn thể nhân sự");
			System.out.println("6.  Tính tổng quỹ lương");
			System.out.println("7.  Tính lương cho nhân viên cụ thể");
			System.out.println("8.  Duyệt đơn xin việc");
			System.out.println("9.  Cho thôi việc nhân sự");
			System.out.println("10. Xem thông tin cá nhân");
			System.out.println("11. Sửa thông tin cá nhân");
			System.out.println("12. Đổi mật khẩu tài khoản");
			System.out.println("0.  Đăng xuất");
			System.out.println("---------");
			System.out.print("Chọn chức năng: ");
			choice = sc.nextLine();
			switch (choice) 
			{
	            case "1":
	            	InDSNhanSu(dstk);
	                break;
	
	            case "2":
	            	timNhanSu(dstk);
	                break;
	
	            case "3":
	                break;
	
	            case "4":
	                break;
	
	            case "5":
	                break;
	                
	            case "6":
	                break;
	
	            case "7":
	            	ql.capNhapThongTin(listPay, dstk);
	                break;
	
	            case "8":
	            	DuyetXinViec(dstk);
	                break;
	
	            case "9":
	                break;
	                
	            case "10":
	            	System.out.println("\n--------------------------------------------------");
	                System.out.println("QUẢN LÍ NHÂN SỰ | <<THÔNG TIN TÀI KHOẢN>>");
	        		System.out.println("---------");
	            	ql.hienThiThongTin(this.tenCuaHang);
	                break;
	
	            case "11":
	            	ql.capNhapThongTin(listPay, dstk);
	                break;
	                
	            case "12":
	                if(ql.RePassWord())
	                	return;
	                break;    	
	
	            case "0": return;
	
	            default:
	            	System.out.println("---------");
					System.out.println("Lựa chọn của bạn không tồn tại!!!"); 
					System.out.println("nhấn phím ENTER để tiếp tục");
					sc.nextLine();
					break;
			}	
		}
	}

	public void DuyetXinViec(DanhSachTaiKhoan dstk)
	{
		char choice;
		while(true)
		{
			System.out.println("\n--------------------------------------------------");
			System.out.println("QUẢN LÍ NHÂN SỰ | <<DUYỆT ĐƠN XIN VIỆC>>");
			System.out.println("---------");
			System.out.println("1. Xem danh sách ứng viên");
			System.out.println("2. Duyệt theo thứ tự từng ứng viên");
			System.out.println("3. Tìm kiếm ứng viên theo số điện thoại");
			System.out.println("0. Thoát");
			System.out.println("---------");
			System.out.print("Chọn chức năng: ");
			choice = sc.nextLine().charAt(0);
			switch (choice) 
			{
	            case '1':
	            	InDSChoDuyet();
	            	System.out.println("---------");
					System.out.println("nhấn phím ENTER để tiếp tục");
					sc.nextLine();
	                break;

	            case '2':
	            	Duyet2(dstk);
	                break;

	            case '3':
	            	Duyet3(dstk);
	                break;

	            case '4':
	                break;

	            case '5':
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
	
	public void PhanQuyen(DanhSachTaiKhoan dstk, NhanSu ns)
	{
		char choice;
		while(true)
		{
			System.out.println("\n--------------------------------------------------");
			System.out.println("Phân quyền chức vụ cho nhân viên");
			System.out.println("---------");
			System.out.println("1. Nhân viên chính thức");
			System.out.println("2. Nhân viên thời vụ");
			System.out.println("3. Quản lí");
			System.out.println("0. Thoát");
			System.out.println("---------");
			System.out.print("Chọn chức năng: ");
			choice = sc.nextLine().charAt(0);
			switch (choice) 
			{
	            case '1':
	            	NhanSu nsMoi = new NhanVienChinhThuc(ns);
	        		nsMoi.setGianHang(this.maCuaHang);
	            	this.danhSachChoDuyet.remove(ns.getSoDienThoai());
	            	dstk.danhSachTaiKhoan.remove(ns.getSoDienThoai());
	        		dstk.danhSachTaiKhoan.put(nsMoi.getSoDienThoai(), nsMoi);
	            	System.out.println("---------");
					System.out.println("Thêm nhân sự thành công"); 
					System.out.println("nhấn phím ENTER để tiếp tục");
					sc.nextLine();
					return;

	            case '2':
	            	nsMoi = new NhanVienThoiVu(ns);
	        		nsMoi.setGianHang(this.maCuaHang);
	            	this.danhSachChoDuyet.remove(ns.getSoDienThoai());
	            	dstk.danhSachTaiKhoan.remove(ns.getSoDienThoai());
	        		dstk.danhSachTaiKhoan.put(nsMoi.getSoDienThoai(), nsMoi);
	            	System.out.println("---------");
					System.out.println("Thêm nhân sự thành công"); 
					System.out.println("nhấn phím ENTER để tiếp tục");
					sc.nextLine();
					return;

	            case '3':
	            	nsMoi = new QuanLi(ns);
	        		nsMoi.setGianHang(this.maCuaHang);
	            	this.danhSachChoDuyet.remove(ns.getSoDienThoai());
	            	dstk.danhSachTaiKhoan.remove(ns.getSoDienThoai());
	        		dstk.danhSachTaiKhoan.put(nsMoi.getSoDienThoai(), nsMoi);
	            	System.out.println("---------");
					System.out.println("Thêm nhân sự thành công"); 
					System.out.println("nhấn phím ENTER để tiếp tục");
					sc.nextLine();
					return;
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
	
	public void Duyet2(DanhSachTaiKhoan dstk)
	{
		System.out.println("\n--------------------------------------------------");
		System.out.println("QUẢN LÍ NHÂN SỰ | <<DUYỆT ĐƠN XIN VIỆC>>");
		for (NhanSu ns : danhSachChoDuyet.values()) {
    		System.out.println("\n--------------------------------------------------");
            System.out.println("THÔNG TIN ỨNG VIÊN");
    		System.out.println("---------");
			ns.hienThiThongTin(null);
			XacNhanDuyet(dstk, ns);
		}
	}
	
	public void Duyet3(DanhSachTaiKhoan dstk)
	{
		System.out.println("\n--------------------------------------------------");
		System.out.println("QUẢN LÍ NHÂN SỰ | <<DUYỆT ĐƠN XIN VIỆC>>");
		while(true)
		{
			System.out.println("Nhập số điện thoại ứng viên: ");
			String sdt = sc.nextLine();
			if(!danhSachChoDuyet.containsKey(sdt))
			{
	        	System.out.println("---------");
				System.out.println("Ứng viên không tồn tại"); 
				if (ck.Choice())
					return;
			}
			else
			{
				NhanSu ns = danhSachChoDuyet.get(sdt);
				System.out.println("\n--------------------------------------------------");
		        System.out.println("THÔNG TIN ỨNG VIÊN");
		    	System.out.println("---------");
				ns.hienThiThongTin(null);
				XacNhanDuyet(dstk, ns);
				return;
			}
		}
	}
	
	public void XacNhanDuyet(DanhSachTaiKhoan dstk, NhanSu ns)
	{
		char choice;
		while(true)
		{
			System.out.println("---------");
			System.out.println("1. Xác nhận duyệt");
			System.out.println("2. Loại");
			System.out.println("0. Thoát");
			System.out.println("---------");
			System.out.print("Nhập lựa chọn: ");
			choice = sc.nextLine().charAt(0);
			switch (choice) 
			{
	            case '1':
	            	PhanQuyen(dstk, ns);
	                return;

	            case '2':
	            	this.danhSachChoDuyet.remove(ns).getSoDienThoai();
	            	System.out.println("---------");
					System.out.println("Đã loại ứng viên"); 
					System.out.println("nhấn phím ENTER để tiếp tục");
					sc.nextLine();
	                return;

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