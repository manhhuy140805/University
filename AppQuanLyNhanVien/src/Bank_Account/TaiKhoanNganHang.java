package Bank_Account;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import Bank_Interface.History;
import Tools.Check;

public abstract class TaiKhoanNganHang implements History{
	protected String soDienThoai;
	protected String matKhau;
	protected String tenChuTaiKhoan;
	protected String maPIN;
	protected double soDu;
	protected ArrayList<String> listHistory = new ArrayList<String>();
	Scanner sc = new Scanner(System.in);
	Check ck = new Check();
	SimpleDateFormat dayHistory = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
	
	public ArrayList<String> getListHistory() {
		return listHistory;
	}
	public void setListHistory(ArrayList<String> listHistory) {
		this.listHistory = listHistory;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public String getTenChuTaiKhoan() {
		return tenChuTaiKhoan;
	}
	public void setTenChuTaiKhoan(String tenChuTaiKhoan) {
		this.tenChuTaiKhoan = tenChuTaiKhoan;
	}
	public String getMaPIN() {
		return maPIN;
	}
	public void setMaPIN(String maPIN) {
		this.maPIN = maPIN;
	}
	public double getSoDu() {
		return soDu;
	}
	public void setSoDu(double soDu) {
		this.soDu = soDu;
	}
	public TaiKhoanNganHang(String soDienThoai, String matKhau, String tenChuTaiKhoan, double soDu, String maPIN) {
		this.soDienThoai = soDienThoai;
		this.matKhau = matKhau;
		this.tenChuTaiKhoan = tenChuTaiKhoan;
		this.maPIN = maPIN;
		this.soDu = soDu;
	}
	
	public TaiKhoanNganHang() {
	}
	
	public String toString() {
		return this.soDienThoai +", "+this.matKhau+", "+this.tenChuTaiKhoan+", "+this.soDu+", "+this.maPIN;
	}
	
	public boolean Nhap(String soDienThoai) {
        this.soDienThoai = soDienThoai;
        try {
            System.out.print("Nhập tên chủ tài khoản: ");
            this.tenChuTaiKhoan = sc.nextLine();
            // Nhập mật khẩu
            while (true) {
                System.out.print("Nhập mật khẩu: ");
                this.matKhau = sc.nextLine();
                System.out.print("Nhập lại mật khẩu: ");
                String matKhauNo = sc.nextLine();
                if (ck.CheckPassWord(this.matKhau)) 
                {
					System.out.println("---------");
                    System.out.println("Mật khẩu yếu");
                    System.out.println("Lưu ý: mật khẩu phải dài trên 8 kí tự, có chữ hoa, chữ thường, số, ký tự đặc biệt");
                    if (ck.Choice()) 
                        return true;
					System.out.println("---------");
                } 
                else
                	if(ck.CheckSringEqual(matKhauNo, this.matKhau))
                	{
						System.out.println("---------");
                		System.out.println("Mật khẩu không trùng khớp");
	                    if (ck.Choice()) 
	                        return true;
						System.out.println("---------");
                	}
                	else {
                		break;
                }
            }
            // Nhập mã PIN
            while (true) {
                System.out.print("Nhập mã PIN: ");
                this.maPIN = sc.nextLine();
                System.out.print("Nhập lại mã PIN: ");
                String maPINNo = sc.nextLine();
                if (ck.CheckFormatPIN(this.maPIN)) 
                {
					System.out.println("---------");
                    System.out.println("Mã PIN không hợp lệ!!!");
                    System.out.println("Lưu ý: mã PIN phải có 6 kí tự số");
                    if(ck.Choice()) 
                        return true;
					System.out.println("---------");
                } else 
                    if(ck.CheckSringEqual(this.maPIN, maPINNo))
                    {
						System.out.println("---------");
                    	System.out.println("Mã PIN không trùng khớp");
                        if(ck.Choice()) 
                            return true;
						System.out.println("---------");
                    }
                    else
                    	break;
            }
            // Khởi tạo số dư ban đầu
            this.soDu = 0;
        } catch (Exception e) {
			System.out.println("---------");
            System.err.println("Đã xảy ra lỗi trong quá trình nhập dữ liệu: " + e.getMessage());
			if(ck.Choice())
				return true;
			sc.nextLine();
        }
		return false;
    }
	
	public void KiemTraThongTin() {
		
	    System.out.println("Số điện thoại (số tài khoản): " + this.soDienThoai);
	    System.out.println("Tên chủ tài khoản: " + this.tenChuTaiKhoan);
	}
	
	public void KiemTraSoDu() {
		System.out.println("\n--------------------------------------------------");
		System.out.println("E-BANK | <<KIỂM TRA SỐ DƯ>>");
	    System.out.println("Số điện thoại (số tài khoản): " + this.soDienThoai);
	    System.out.println("Tên chủ tài khoản: " + this.tenChuTaiKhoan);
	    System.out.println("Số dư: " + this.soDu + "VND");
	}
	
	public boolean RePassWord(TaiKhoanNganHang tk)
	{
		System.out.println("\n--------------------------------------------------");
		System.out.println("E-BANK | <<Đổi mật khẩu>>");
		while(true)
		{
			if (ck.GioiHanPW(this))
				return true;
			else
			{
				while(true)
				{
					System.out.print("Nhập mật khẩu mới: ");
					String mk = sc.nextLine();
					System.out.print("Nhập lại mật khẩu mới: ");
					String mk2 = sc.nextLine();
					if(ck.CheckSringEqual(mk, mk2))
                	{
						System.out.println("---------");
                		System.out.println("Mật khẩu không trùng khớp");
	                    if (ck.Choice()) 
	                        return false;
                	}
					else
					{
						tk.setMatKhau(mk);
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
	
	public boolean RePIN(TaiKhoanNganHang tk)
	{
		System.out.println("\n--------------------------------------------------");
		System.out.println("E-BANK | <<Đổi mã PIN>>");
		while(true)
		{
			if (ck.GioiHanPIN(this))
				return true;
			else
			{
				while(true)
				{
					System.out.print("Nhập mã PIN mới: ");
					String mp = sc.nextLine();
					System.out.print("Nhập lại mã PIN mới: ");
					String mp2 = sc.nextLine();
					if(ck.CheckSringEqual(mp, mp2))
                	{
						System.out.println("---------");
                		System.out.println("Mã PIN không trùng khớp");
	                    if (ck.Choice()) 
	                        return false;
                	}
					else
					{
						tk.setMaPIN(mp);
						System.out.println("---------");
						System.out.println("Đổi mã PIN thành công");
						System.out.println("Nhấn ENTER để tiếp tục");
					    sc.nextLine();
						return false;
					}
				}
			}
		}
	}
	
	public abstract boolean RutTien();
	
	public boolean GuiTien() {
		while(true)
		{
			try {
				System.out.println("\n--------------------------------------------------");
				System.out.println("E-BANK | <<GỬI TIỀN>>");
				System.out.print("Nhập số tiền bạn muốn gửi: ");
				double money = sc.nextDouble();
				if(money > 0)
				{
					if(ck.GioiHanPIN(this))
						return true;
					else
					{
						if (this instanceof TaiKhoanTietKiem) {
						    ((TaiKhoanTietKiem)this).TinhLaiAn();
						}
						soDu += money;
						this.HGuiTien(money);
						System.out.println("---------");
						System.out.println("<<Thông báo>>");
						System.out.println("Gửi tiền thành công | Tài khoản: "+ soDienThoai + " - "
								+ tenChuTaiKhoan + "\nGD: +" + money +"VND | Số dư hiện tại: "+ soDu +"VND");
						System.out.println("---------");
						System.out.println("Nhấn ENTER để tiếp tục");
					    sc.nextLine();
					    sc.nextLine();
						return false;
					}
				}
				else
				{
					System.out.println("<<Thông báo>>");
					System.out.println("Số tiền gửi không hợp lệ!!!");
					if(ck.Choice())
						return false;
				} 
			}
			catch (Exception e) 
			{
				System.out.println("---------");
				System.err.println("Lỗi nhập liệu");
			    if (ck.Choice())
				return false;
			    sc.nextLine();
			}
		}
	}

	public void SaveHistory(String hst) {
		// TODO Auto-generated method stub
		listHistory.add(hst);
	}
	
	public void InHistory() 
	{
		System.out.println("\n--------------------------------------------------");
		System.out.println("E-BANK | <<BIẾN ĐỘNG SỐ DƯ>>");
		for (String string : listHistory) {
			System.out.println(string);
		}
		System.out.println("---------");
		System.out.println("Nhấn ENTER để tiếp tục");
	    sc.nextLine();
	}
}
