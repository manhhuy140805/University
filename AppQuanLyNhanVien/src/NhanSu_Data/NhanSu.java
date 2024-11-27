package NhanSu_Data;

import java.time.LocalDate;
import java.util.Scanner;
import Bank_Feature.ListAccount;
import NhanSu_DanhSach.DanhSachCuaHang;
import NhanSu_DanhSach.DanhSachTaiKhoan;
import Tools.Check;
import java.time.format.DateTimeFormatter;

public class NhanSu {

    protected String id;
    protected String matKhau;
    protected String hoTen;
    protected LocalDate ngaySinh;
    protected String soDienThoai;
    protected String email;
    protected String diaChi;
    protected String soTaiKhoanNganHang;
    protected String tenTaiKhoanNganHang;
    protected double luongCoBan;
    protected double heSoLuong = 1;
    protected String maCuaHang;
    protected String phanQuyen;

    Scanner sc = new Scanner(System.in);
    Check ck = new Check();

    public NhanSu(String id, String matKhau, String hoTen, String ngaySinh, String soDienThoai, String email,
            String diaChi, String soTaiKhoanNganHang, String tenTaiKhoanNganHang, double heSoLuong, String maCuaHang,
            String phanQuyen) {
        super();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.id = id;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.ngaySinh = LocalDate.parse(ngaySinh, format);
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
        this.soTaiKhoanNganHang = soTaiKhoanNganHang;
        this.tenTaiKhoanNganHang = tenTaiKhoanNganHang;
        this.heSoLuong = heSoLuong;
        this.maCuaHang = maCuaHang;
        this.phanQuyen = phanQuyen;
    }
    //constructor dùng để thay đổi chức vụ

    public NhanSu(NhanSu ns) {
        this.id = ns.id;
        this.matKhau = ns.matKhau;
        this.hoTen = ns.hoTen;
        this.ngaySinh = ns.ngaySinh;
        this.soDienThoai = ns.soDienThoai;
        this.email = ns.email;
        this.diaChi = ns.diaChi;
		this.heSoLuong = ns.heSoLuong;
        this.soTaiKhoanNganHang = ns.soTaiKhoanNganHang;
        this.tenTaiKhoanNganHang = ns.tenTaiKhoanNganHang;
    }

    public NhanSu(String id, String soDienThoai, String matKhau, String hoTen, String ngaySinh, String email, String diaChi, String soTaiKhoanNganHang, String tenTaiKhoanNganHang) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.id = id;
        this.soDienThoai = soDienThoai;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.ngaySinh = LocalDate.parse(ngaySinh, format);
        this.email = email;
        this.diaChi = diaChi;
        this.soTaiKhoanNganHang = soTaiKhoanNganHang;
        this.tenTaiKhoanNganHang = tenTaiKhoanNganHang;
    }

    @Override
	public String toString() {
		return "SDT: "+ soDienThoai +" | Họ và tên: "+ hoTen;
	}

	public NhanSu() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoTaiKhoanNganHang() {
        return soTaiKhoanNganHang;
    }

    public void setSoTaiKhoanNganHang(String soTaiKhoanNganHang) {
        this.soTaiKhoanNganHang = soTaiKhoanNganHang;
    }

    public String getTenTaiKhoanNganHang() {
        return tenTaiKhoanNganHang;
    }

    public void setTenTaiKhoanNganHang(String tenTaiKhoanNganHang) {
        this.tenTaiKhoanNganHang = tenTaiKhoanNganHang;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public String getPhanQuyen() {
        return phanQuyen;
    }

    public void setPhanQuyen(String phanQuyen) {
        this.phanQuyen = phanQuyen;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public String getMaCuaHang() {
        return maCuaHang;
    }

    public void setGianHang(String maCuaHang) {
        this.maCuaHang = maCuaHang;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public void hienThiThongTin(String tenGH) {
        System.out.println("Họ và tên \t: " + this.hoTen);
        System.out.println("Ngày sinh \t: " + this.ngaySinh);
        System.out.println("Số điện thoại \t: " + this.soDienThoai);
        System.out.println("Email \t\t: " + this.email);
        System.out.println("Địa chỉ \t: " + this.diaChi);
        System.out.println("STK ngân hàng \t: " + this.soTaiKhoanNganHang);
        System.out.println("Tên ngân hàng \t: " + this.tenTaiKhoanNganHang);
        if (this instanceof NhanVienChinhThuc) {
            System.out.println("Chức vụ \t: Nhân viên chính thức");
        }
        if (this instanceof NhanVienThoiVu) {
            System.out.println("Chức vụ \t: Nhân viên thời vụ");
        }
        if (this instanceof QuanLi) {
            System.out.println("Chức vụ \t: Quản lí");
        }
    }

    public boolean Nhap(String sdt, ListAccount listPay) {
        //số điện thoại được nhập ở hàm đăng kí để check 
        this.soDienThoai = sdt;
        try {
            //nhập mật khẩu
            while (true) {
                System.out.print("Nhập mật khẩu: ");
                this.matKhau = sc.nextLine();
                System.out.print("Nhập lại mật khẩu: ");
                String matKhauNo = sc.nextLine();
                if (ck.CheckPassWord(this.matKhau)) {
                    System.out.println("---------");
                    System.out.println("Mật khẩu yếu");
                    System.out.println("Lưu ý: mật khẩu phải dài trên 8 kí tự, có chữ hoa, chữ thường, số, ký tự đặc biệt");
                    if (ck.Choice()) {
                        return true;
                    }
                    System.out.println("---------");
                } else if (ck.CheckSringEqual(matKhauNo, this.matKhau)) {
                    System.out.println("---------");
                    System.out.println("Mật khẩu không trùng khớp");
                    if (ck.Choice()) {
                        return true;
                    }
                    System.out.println("---------");
                } else {
                    break;
                }
            }
            //nhập ngày sinh
            this.ngaySinh = Check.getDate("Nhập ngày sinh: ", "Ngày sinh không đúng định dạng(dd/MM/yyyy)");
            //nhập tên chủ tài khoản
            System.out.print("Nhập tên chủ tài khoản: ");
            this.hoTen = sc.nextLine();
            //nhập địa chỉ 
            System.out.print("Nhập địa chỉ: ");
            this.diaChi = sc.nextLine();
            //nhập email
            while (true) {
                System.out.print("Nhập Email: ");
                this.email = sc.nextLine();
                if (ck.FormatEmail(this.email)) {
                    System.out.println("---------");
                    System.out.println("Email không hợp lệ!!!");
                    if (ck.Choice()) {
                        return true;
                    }
                    System.out.println("---------");
                } else {
                    break;
                }
            }
            //nhập số tài khoản ngân hàng
            while (true) {
                System.out.print("Nhập số tài khoản ngân hàng: ");
                this.soTaiKhoanNganHang = sc.nextLine();
                if (!listPay.list.containsKey(this.soTaiKhoanNganHang)) {
                    System.out.println("Tài khoản ngân hàng không tồn tại!!");
                    if (ck.Choice()) {
                        return true;
                    }
                    System.out.println("---------");
                } else {
                    break;
                }
            }
            
            // nhập tên tài khoản
            this.tenTaiKhoanNganHang = Check.getString("Nhập tên ngân hàng: ",
                                       "Thông tin này không được để trống!");
        } catch (Exception e) {
            System.out.println("---------");
            System.err.println("Đã xảy ra lỗi trong quá trình nhập dữ liệu: " + e.getMessage());
            if (ck.Choice()) {
                return true;
            }
            sc.nextLine();
        }
        return false;
    }

    public boolean RePassWord() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("<<Đổi mật khẩu>>");
        while (true) {
            if (ck.GioiHanPWNS(this)) {
                return true;
            } else {
                while (true) {
                    System.out.print("Nhập mật khẩu mới: ");
                    String mk = sc.nextLine();
                    System.out.print("Nhập lại mật khẩu mới: ");
                    String mk2 = sc.nextLine();
                    if (ck.CheckSringEqual(mk, mk2)) {
                        System.out.println("---------");
                        System.out.println("Mật khẩu không trùng khớp");
                        if (ck.Choice()) {
                            return false;
                        }
                    } else {
                        this.matKhau = mk;
                        System.out.println("---------");
                        System.out.println("Đổi mật khẩu thành công");
                        System.out.println("Nhấn ENTER để tiếp tục");
                        sc.nextLine();
                        return false;
                    }
                }
            }
        }
    }

    public void capNhapThongTin(ListAccount listPay, DanhSachTaiKhoan dstk) {
        while(true)
        {
        	System.out.println("\n--------------------------------------------------");
        	System.out.println("QUẢN LÍ NHÂN SỰ | <<SỬA THÔNG TIN TÀI KHOẢN>>");
            System.out.println("---------");
            System.out.println("1. Họ và tên \t\t: " + this.hoTen);
            System.out.println("2. Ngày sinh \t\t: " + this.ngaySinh);
            System.out.println("3. Số điện thoại \t: " + this.soDienThoai);
            System.out.println("4. Email \t\t: " + this.email);
            System.out.println("5. Địa chỉ \t\t: " + this.diaChi);
            System.out.println("6. STK ngân hàng \t: " + this.soTaiKhoanNganHang);
            System.out.println("7. Tên ngân hàng \t: " + this.tenTaiKhoanNganHang);
            System.out.println("---------");
            System.out.println("0. Thoát");
            System.out.println("---------");
            System.out.print("Chọn thông tin cần sửa: ");
            char choice;
            choice = sc.nextLine().charAt(0);
            System.out.println("---------");
            switch (choice) {
                case '1': {
                    System.out.print("Nhập tên cần sửa: ");
                    this.hoTen = sc.nextLine();
            		System.out.println("---------");
            		System.out.println("Cập nhật tên thành công");
            		System.out.println("Nhấn ENTER để tiếp tục");
            		sc.nextLine();
                    break;
                }
                case '2': {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    System.out.print("Nhập ngày sinh cần sửa: ");
                    this.ngaySinh = LocalDate.parse(sc.nextLine(), format);
            		System.out.println("---------");
            		System.out.println("Cập nhật ngày sinh thành công");
            		System.out.println("Nhấn ENTER để tiếp tục");
            		sc.nextLine();
                    break;
                }
                case '3': {
                    String sdt;
                    String sdtc = this.soDienThoai;
                    while(true)
            		{
                        System.out.print("Nhập số điện thoại cần sửa: ");
            			sdt = sc.nextLine();
            			if (dstk.danhSachTaiKhoan.containsKey(sdt)) 
            			{
            				System.out.println("---------");
            				System.out.println("Số điện thoại đã được đăng kí!!!.");
            	            if (ck.Choice()) 
            	                return;
            				System.out.println("---------");
            			}
            			else
            				if(ck.CheckFormatSDT(sdt))
            				{
            					System.out.println("---------");
            					System.out.println("Số điện thoại không hợp lệ!!!");
            		            if (ck.Choice()) 
            		                return;
            					System.out.println("---------");
            				}
            				break;
            		}
                    this.soDienThoai = sdt;
                    dstk.danhSachTaiKhoan.remove(sdtc);
                    dstk.danhSachTaiKhoan.put(sdt, this);
            		System.out.println("---------");
            		System.out.println("Cập nhật số điện thoại thành công");
            		System.out.println("Nhấn ENTER để tiếp tục");
            		sc.nextLine();
                    break;
                }
                case '4': {
                    String email;
                    while (true) {
                        System.out.print("Nhập email cần sửa: ");
                        email = sc.nextLine();
                        if (ck.FormatEmail(email)) {
                            System.out.println("---------");
                            System.out.println("Email không hợp lệ!!!");
                            if (ck.Choice()) {
                                return;
                            }
                            System.out.println("---------");
                        } else {
                            break;
                        }
                    }
                    this.email = email;
            		System.out.println("---------");
            		System.out.println("Cập nhật email thành công");
            		System.out.println("Nhấn ENTER để tiếp tục");
            		sc.nextLine();
                    break;
                }
                case '5': {
                    System.out.print("Nhập địa chỉ cần sửa: ");
                    this.diaChi = sc.nextLine();
            		System.out.println("---------");
            		System.out.println("Cập nhật địa chỉ thành công");
            		System.out.println("Nhấn ENTER để tiếp tục");
            		sc.nextLine();
                    break;
                }
                case '6': {
                    while (true) {
                    	System.out.print("Nhập số tài khoản ngân hàng cần sửa: ");
                        this.soTaiKhoanNganHang = sc.nextLine();
                        if (!listPay.list.containsKey(this.soTaiKhoanNganHang)) {
                            System.out.println("Tài khoản ngân hàng không tồn tại!!");
                            if (ck.Choice()) {
                                return;
                            }
                            System.out.println("---------");
                        } else {
                            break;
                        }
                    }
            		System.out.println("---------");
            		System.out.println("Cập nhật số tài khoản ngân hàng thành công");
            		System.out.println("Nhấn ENTER để tiếp tục");
            		sc.nextLine();
                    break;
                }
                case '7': {
                    System.out.print("Nhập tên ngân hàng cần sửa: ");
                    this.tenTaiKhoanNganHang = sc.nextLine();
            		System.out.println("---------");
            		System.out.println("Cập nhật tên ngân hàng thành công");
            		System.out.println("Nhấn ENTER để tiếp tục");
            		sc.nextLine();
                    break;
                }
                case '0':
                    return;
                default:
                    throw new IllegalArgumentException("Unexpected value: " + choice);
            }
        }
    }
    
    public void XinViec(DanhSachCuaHang dsgh)
    {
    	System.out.println("\n--------------------------------------------------");
    	System.out.println("QUẢN LÍ NHÂN SỰ | <<GỬI YÊU CẦU XIN VIỆC>>");
       while(true)
       {
    	   System.out.println("---------");
           System.out.print("Nhập mã cửa hàng bạn muốn xin việc: ");
           String magh = sc.nextLine();
			if (dsgh.getDanhSachGianHang().containsKey(magh)) 
			{
				System.out.println("--------------------------------------");
				System.out.println("Kiểm tra thông tin Cửa hàng ");
				dsgh.getDanhSachGianHang().get(magh).thongTinCuaHang();
				System.out.println("---------");
				System.out.println("1. Xác nhận");
				System.out.println("2. Nhập lại");
				System.out.println("0. Thoát");
				System.out.println("---------");
				System.out.print("Nhập lựa chọn của bạn: ");
				char choice = sc.nextLine().charAt(0);
				if(choice == '1')
				{
					dsgh.getDanhSachGianHang().get(magh).setDanhSachChoDuyet(this);
            		System.out.println("---------");
					System.out.println("Gửi yêu cầu thành công");
            		System.out.println("Nhấn ENTER để tiếp tục");
            		sc.nextLine();
                    break;
				}
				else
					if(choice != '2')
						return;
			}
       }
    }
}
