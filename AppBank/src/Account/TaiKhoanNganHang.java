package Account;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import Bank.Check;
import Interface.History;

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
	
	public void Nhap(String soDienThoai) {
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
                        return;
					System.out.println("---------");
                } 
                else
                	if(ck.CheckSringEqual(matKhauNo, this.matKhau))
                	{
						System.out.println("---------");
                		System.out.println("Mật khẩu không trùng khớp");
	                    if (ck.Choice()) 
	                        return;
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
                        return;
					System.out.println("---------");
                } else 
                    if(ck.CheckSringEqual(this.maPIN, maPINNo))
                    {
						System.out.println("---------");
                    	System.out.println("Mã PIN không trùng khớp");
                        if(ck.Choice()) 
                            return;
						System.out.println("---------");
                    }
                    else
                    	break;
            }
            // Khởi tạo số dư ban đầu
            this.soDu = 0;
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi trong quá trình nhập dữ liệu: " + e.getMessage());
			if(ck.Choice())
				return;
        }
    }
	
	public void KiemTraThongTin() {
		
	    System.out.println("Số điện thoại (số tài khoản): " + this.soDienThoai);
	    System.out.println("Tên chủ tài khoản: " + this.tenChuTaiKhoan);
	}
	
	public void KiemTraSoDu() {
		System.out.println("\n--------------------------------------------------");
		System.out.println("<<KIỂM TRA SỐ DƯ>>");
	    System.out.println("Số điện thoại (số tài khoản): " + this.soDienThoai);
	    System.out.println("Tên chủ tài khoản: " + this.tenChuTaiKhoan);
	    System.out.println("Số dư: " + this.soDu + "VND");
	}
	
	public void RePassWord(TaiKhoanNganHang tk)
	{
		System.out.println("\n--------------------------------------------------");
		System.out.println("<<Đổi mật khẩu>>");
		while(true)
		{
			System.out.print("Nhập mật khẩu cũ: ");
			String mk = sc.nextLine();
			if(ck.CheckSringEqual(tk.getMatKhau(), mk))
			{
				System.out.println("Mật khẩu sai!!!");
				if(ck.Choice())
					return;
			}
			else
			{
				while(true)
				{
					System.out.print("Nhập mật khẩu mới: ");
					mk = sc.nextLine();
					System.out.print("Nhập lại mật khẩu mới: ");
					String mk2 = sc.nextLine();
					if(ck.CheckSringEqual(mk, mk2))
                	{
						System.out.println("---------");
                		System.out.println("Mật khẩu không trùng khớp");
	                    if (ck.Choice()) 
	                        return;
                	}
					else
					{
						tk.setMatKhau(mk);
						System.out.println("---------");
						System.out.println("Đổi mật khẩu thành công");
						System.out.println("Nhấn ENTER để tiếp tục");
					    sc.nextLine();
						return;
					}
				}
			}
		}
	}
	
	public void RePIN(TaiKhoanNganHang tk)
	{
		System.out.println("\n--------------------------------------------------");
		System.out.println("<<Đổi mã PIN>>");
		while(true)
		{
			System.out.print("Nhập mã PIN cũ: ");
			String mp = sc.nextLine();
			if(ck.CheckSringEqual(tk.getMaPIN(), mp))
			{
				System.out.println("Mã PIN sai!!!");
				if(ck.Choice())
					return;
			}
			else
			{
				while(true)
				{
					System.out.print("Nhập mã PIN mới: ");
					mp = sc.nextLine();
					System.out.print("Nhập lại mã PIN mới: ");
					String mp2 = sc.nextLine();
					if(ck.CheckSringEqual(mp, mp2))
                	{
						System.out.println("---------");
                		System.out.println("Mã PIN không trùng khớp");
	                    if (ck.Choice()) 
	                        return;
                	}
					else
					{
						tk.setMaPIN(mp);
						System.out.println("---------");
						System.out.println("Đổi mã PIN thành công");
						System.out.println("Nhấn ENTER để tiếp tục");
					    sc.nextLine();
						return;
					}
				}
			}
		}
	}
	
	public abstract void RutTien();
	
	public void GuiTien() {
		while(true)
		{
			try {
				System.out.println("\n--------------------------------------------------");
				System.out.println("<<GỬI TIỀN>>");
				System.out.print("Nhập số tiền bạn muốn gửi: ");
				double money = sc.nextDouble();
				if(money > 0)
				{
					if(ck.GioiHan(this))
						return;
					else
					{
						soDu += money;
						this.HGuiTien(money);
						System.out.println("---------");
						System.out.println("<<Thông báo>>");
						System.out.println("Thành công | Tài khoản: "+ soDienThoai + " - "
								+ tenChuTaiKhoan + "\nGD: +" + money +"VND | Số dư hiện tại: "+ soDu +"VND");
						System.out.println("---------");
						System.out.println("Nhấn ENTER để tiếp tục");
					    sc.nextLine();
					    sc.nextLine();
						return;
					}
				}
				else
				{
					System.out.println("<<Thông báo>>");
					System.out.println("Số tiền gửi không hợp lệ!!!");
					if(ck.Choice())
						return;
				}
			}
			catch(Exception e)
			{
				System.out.print("Lỗi nhập liệu: "+ e.getMessage());
				if(ck.Choice())
					return;
			}
		}
	}

	public void CongTien(double x) 
	{
		if(x <= 0) 
		{
			System.out.println("Số tiền ko hợp lệ");
			return;
		}
		this.soDu += x;
		System.out.println("<<Thông báo>>\n Giao dịch thành công");
		System.out.println("Tài khoản " + soDienThoai + " - " + tenChuTaiKhoan);
		System.out.println("GD: +" + x + "VND");
	}
	
	public void TruTien(double x) 
	{
		if(x < 0) 
		{
			System.out.println("Số tiền ko hợp lệ");
			return;
		}
		this.soDu -= x;
		System.out.println("<<Thông báo>>\n Giao dịch thành công");
		System.out.println("Tài khoản " + soDienThoai + " - " + tenChuTaiKhoan);
		System.out.println("GD: -" + x + "VND");
	}
	public void SaveHistory(String hst) {
		// TODO Auto-generated method stub
		listHistory.add(hst);
	}
	
	public void InHistory() 
	{
		System.out.println("\n--------------------------------------------------");
		System.out.println("<<BIẾN ĐỘNG SỐ DƯ>>");
		for (String string : listHistory) {
			System.out.println(string);
		}
		System.out.println("---------");
		System.out.println("Nhấn ENTER để tiếp tục");
	    sc.nextLine();
	}
}
