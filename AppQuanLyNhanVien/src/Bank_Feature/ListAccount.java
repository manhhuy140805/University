package Bank_Feature;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;

import Bank_Account.TaiKhoanNganHang;
import Bank_Account.TaiKhoanThanhToan;
import Bank_Account.TaiKhoanTietKiem;
import Tools.Check;

public class ListAccount {
	public HashMap list;
	Scanner sc = new Scanner(System.in);
	Check ck = new Check();

	public ListAccount(int x) {
		if(x==1)
			this.list = new HashMap<String, TaiKhoanThanhToan>();
		if(x==2)
			this.list = new HashMap<String, TaiKhoanTietKiem>();
	}
	
	public void SignIn() {
		TaiKhoanNganHang tk = new TaiKhoanThanhToan();
		String sdt;
		System.out.println("\n--------------------------------------------------");
		System.out.println("E-BANK | <<ĐĂNG KÝ TÀI KHOẢN MỚI>>");
		while(true)
		{
			System.out.print("Nhập số điện thoại(sau này sẽ là số tài khoản của bạn): ");
			sdt = sc.nextLine();
			if(ck.CheckFormatSDT(sdt))
			{
				System.out.println("---------");
				System.out.println("Số điện thoại không hợp lệ!!!");
				if(ck.Choice())
					return;
				System.out.println("---------");
			}
			else
				if(ck.CheckExists(sdt, list))
				{
					System.out.println("---------");
					System.out.println("Số điện thoại đã được đăng ký!!!");
					if(ck.Choice())
						return;
					System.out.println("---------");
				}
				else
					break;
		}
		if(tk.Nhap(sdt))
			return;
		list.put(sdt, tk);
		System.out.println("---------");
		System.out.println("Tạo tài khoản thành công");
		System.out.println("Nhấn ENTER để tiếp tục");
	    sc.nextLine();
	}
	
	public void LogIn(ListAccount listSave) {
		try
		{
			while(true)
			{
				System.out.println("\n--------------------------------------------------");
				System.out.println("E-BANK | <<ĐĂNG NHẬP>>");
				System.out.print("Nhập số tài khoản: ");
				String stk = sc.nextLine();
				if(ck.CheckExists(stk, list))
				{
					System.out.print("Nhập mật khẩu: ");
					String mk = sc.nextLine();
					if(ck.CheckPasswordLogIn(stk, mk, list))
					{
						System.out.println("---------");
						System.out.println("Mật khẩu sai!!!");
						if(ck.Choice())
							return;
						System.out.println("---------");
					}
					else
					{
						System.out.println("---------");
						System.out.println("Đăng nhập thành công");
						System.out.println("Nhấn ENTER để tiếp tục");
					    sc.nextLine();
						this.FeatureUser((TaiKhoanThanhToan) this.list.get(stk), listSave);
						break;
					}
				}
				else
				{
					System.out.println("---------");
					System.out.println("Số điện thoại chưa được đăng ký!!!");
					if(ck.Choice())
						return;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("---------");
			System.err.println("Lỗi nhập liệu: "+ e.getMessage());
			if(ck.Choice())
				return;
			sc.nextLine();
		}
	}
	
public boolean DeleteAccountPay(TaiKhoanNganHang tk)
	{
		System.out.println("\n--------------------------------------------------");
		System.out.println("\nE-BANK | <<Xóa tài khoản>>");
		if(this.list.containsKey(tk.getSoDienThoai()))
		{
			System.out.println("Bạn cần xóa tài khoản tiết kiệm trước!!!");
			System.out.println("---------");
			System.out.println("nhấn phím ENTER để thoát");
			sc.nextLine();
			return false;
		}
		if(tk.getSoDu()>0)
		{
			System.out.println("Hiện tại số dư tài khoản của bạn đang lớn hơn 0");
			System.out.println("Bạn cần rút ra hết trước khi xóa tài khoản");
			System.out.println("---------");
			System.out.println("nhấn phím ENTER để thoát");
			sc.nextLine();
			return false;
		}
		if(tk.getSoDu()<0)
		{
			System.out.println("Hiện tại hiện tại bạn đang nợ tín dụng" + tk.getSoDu());
			System.out.println("Bạn cần thanh toán khoản nợ trước khi xóa tài khoản");
			System.out.println("---------");
			System.out.println("nhấn phím ENTER để thoát");
			sc.nextLine();
			return false;
		}
		System.out.println("Bạn muốn xóa tài khoản?");
		System.out.println("1: Xác nhận xóa tài khoản  \n0: Thoát");
		System.out.println("---------");
		System.out.print("Nhập lựa chọn của bạn: ");
		char choice = sc.nextLine().charAt(0);
		if(choice == '0')
			return false;
		while(true)
		{
			if (ck.GioiHanPW((TaiKhoanNganHang) list.get(tk.getSoDienThoai()))) 
			    return true;
			else
				if (ck.GioiHanPIN((TaiKhoanNganHang) list.get(tk.getSoDienThoai())))
				    return true;
				else
				{
					System.out.println("Bạn muốn xóa tài khoản?");
					System.out.println("1: Xác nhận xóa tài khoản  \n0: Thoát");
					System.out.println("---------");
					System.out.print("Nhập lựa chọn của bạn: ");
					choice = sc.nextLine().charAt(0);
					if(choice == '1')
					{
						list.remove(tk.getSoDienThoai());
						System.out.println("---------");
						System.out.println("Xóa tài khoản thành công");
						System.out.println("nhấn phím ENTER để thoát");
						sc.nextLine();
						return true;
					}
					else 
						return false;
				}
		}
	}
	
	public boolean DeleteAccountSave(TaiKhoanNganHang tk)
	{
		System.out.println("\n--------------------------------------------------");
		System.out.println("\nE-BANK | <<Xóa tài khoản tiết kiệm>>");
		if(tk.getSoDu()>0)
		{
			System.out.println("Hiện tại số dư tài khoản của bạn đang lớn hơn 0");
			System.out.println("Bạn cần rút ra hết trước khi xóa tài khoản");
			System.out.println("---------");
			System.out.println("nhấn phím ENTER để thoát");
			sc.nextLine();
			return false;
		}
		System.out.println("Bạn muốn xóa tài khoản?");
		System.out.println("1: Xác nhận xóa tài khoản  \n0: Thoát");
		System.out.println("---------");
		System.out.print("Nhập lựa chọn của bạn: ");
		char choice = sc.nextLine().charAt(0);
		if(choice == '0')
			return false;
		while(true)
		{
			if (ck.GioiHanPIN((TaiKhoanNganHang) list.get(tk.getSoDienThoai())))
				    return true;
			else
			{
				System.out.println("Bạn muốn xóa tài khoản?");
				System.out.println("1: Xác nhận xóa tài khoản  \n0: Thoát");
				System.out.println("---------");
				System.out.print("Nhập lựa chọn của bạn: ");
				choice = sc.nextLine().charAt(0);
				if(choice == '1')
				{
					list.remove(tk.getSoDienThoai());
					System.out.println("---------");
					System.out.println("Xóa tài khoản thành công");
					System.out.println("nhấn phím ENTER để thoát");
					sc.nextLine();
					return true;
				}
				else 
					return false;
			}
		}
	}
	
	public void FeatureUser(TaiKhoanThanhToan tk, ListAccount listSave) {
		try
		{
			while(true)
			{
				System.out.println("\n--------------------------------------------------");
				System.out.println("---<< E-BANK >>---");
				System.out.println("Số tài khoản: "+ tk.getSoDienThoai() +"\nChủ tài khoản: "+ tk.getTenChuTaiKhoan());
				System.out.println("---------");
				System.out.println("1. Chuyển khoản");
				System.out.println("2. Kiểm tra số dư");
				System.out.println("3. Biến động số dư");
				System.out.println("4. Gửi tiền");
				System.out.println("5. Rút tiền");
				System.out.println("6. Gửi tiết kiệm");
				System.out.println("7. Đổi mật khẩu");
				System.out.println("8. Đổi mã PIN");
				System.out.println("9. Tài khoản tiết kiệm");
				System.out.println("10. Xóa tài khoản");
				System.out.println("0. Đăng xuất");
				System.out.println("---------");
				System.out.print("Nhập lựa chọn của bạn: ");
				String choice = sc.nextLine();
				switch(choice)
				{
					case "1" : if(tk.Pay(this.list))return;break;
					case "2" : tk.KiemTraSoDu();break;
					case "3" : tk.InHistory();break;
					case "4" : if(tk.GuiTien())return;break;
					case "5" : if(tk.RutTien())return;break;
					case "6" : if(tk.PayToSave(listSave.list))return;break;
					case "7" : if(tk.RePassWord(tk))return;break;
					case "8" : if(tk.RePIN(tk))return;break;
					case "9" : listSave.TaiKhoanTietKiem(tk, this);break;
					case "10" : if(this.DeleteAccountPay(tk))return;break;
					case "0" : return;
					default : 
						System.out.println("---------");
						System.out.println("Lựa chọn của bạn không tồn tại!!!"); 
						System.out.println("nhấn phím ENTER để thoát");
						sc.nextLine();
						break;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("---------");
			System.err.println("Lỗi nhập liệu: "+ e.getMessage());
			if(ck.Choice())
				return;
			sc.nextLine();
		}
	}
	
	public void TaiKhoanTietKiem(TaiKhoanThanhToan tktt, ListAccount listPay) throws ParseException
	{
		char choice;
		System.out.println("\n--------------------------------------------------");
		System.out.println("E-BANK | <<TÀI KHOẢN TIẾT KIỆM>>");
		if(!list.containsKey(tktt.getSoDienThoai()))
		{
		    System.out.println("---------");
		    System.out.println("Bạn có muốn mở tài khoản tiết kiệm không?");
		    System.out.println("1. Mở tài khoản tiết kiệm");
		    System.out.println("0. thoát");
		    System.out.println("---------");
		    System.out.print("Nhập lựa chọn của bạn: ");
		    choice = sc.nextLine().charAt(0);
		    if (choice == '1') 
		    {
				System.out.println("\n--------------------------------------------------");
				System.out.println("E-BANK | <<Mở tài khoản tiết kiệm>>");
				System.out.print("Nhập mã pin cho tài khoản tiết kiệm: ");
				String maPIN = sc.nextLine();
				TaiKhoanNganHang tktk = new TaiKhoanTietKiem(tktt.getSoDienThoai(), tktt.getMatKhau(), tktt.getTenChuTaiKhoan(), 0, maPIN);
				((Bank_Account.TaiKhoanTietKiem) tktk).ChonKyHan();
				if (((Bank_Account.TaiKhoanTietKiem) tktk).getLaiXuat() == 0)
				    return;
				list.put(tktt.getSoDienThoai(), tktk);
				System.out.println("---------");
				System.out.println("Mở tài khoản thành công");
				System.out.println("Nhấn ENTER để tiếp tục");
				sc.nextLine();
				this.FeatureSave((TaiKhoanTietKiem) list.get(tktt.getSoDienThoai()), listPay);
				return;
		    } 
		    else
		    	return;
		}
		else
		{
		    while (true) 
		    {
				if (ck.GioiHanPIN((TaiKhoanNganHang) list.get(tktt.getSoDienThoai()))) 
				{
				    if (ck.Choice())
				    	return;
				} 
				else 
				{
				    this.FeatureSave((TaiKhoanTietKiem) list.get(tktt.getSoDienThoai()), listPay);
				    return;
				}
		    }
		}
	}
	
	public void FeatureSave(TaiKhoanTietKiem tk, ListAccount listPay)
	{
	    char choice;
	    while (true) 
	    {
			tk.TinhLai();
			System.out.println("\n--------------------------------------------------");
			System.out.println("---<< E-BANK >>---");
			System.out.println("<<Sổ tiết kiệm số: " + tk.getSoDienThoai() + ">>");
			System.out.println("1. Kiểm tra thông tin sổ tiết kiệm");
			System.out.println("2. Kiểm tra số dư");
			System.out.println("3. Biến động số dư");
			System.out.println("4. Gửi tiền");
			System.out.println("5. Rút tiền");
			System.out.println("6. Tính tổng tiền lãi");
			System.out.println("7. Xóa tài khoản");
			System.out.println("0. Thoát");
			System.out.println("---------");
			System.out.print("Nhập lựa chọn của bạn: ");
			choice = sc.nextLine().charAt(0);
			switch (choice) 
			{
	        		case '1': tk.KiemTraThongTin(); break;
	        		case '2': tk.KiemTraSoDu(); break;
	        		case '3': tk.InHistory();  break;
	        		case '4': if(tk.GuiTien())return;break;
	        		case '5': if(tk.RutTien())return;break;
					case '6': if(tk.SaveToPay(listPay.list))return;break;
	        		case '7': tk.TinhTienLai();break;
	        		case '8': if(this.DeleteAccountSave(tk))return;break;
	        		case '0':return;
	        		default:
	        		    System.out.println("---------");
	        		    System.out.println("Lựa chọn của bạn không tồn tại!!!");
	        		    if (ck.Choice())
	        		    	break;
	        		    else
	        		    	return;
			}
	    }
	}
}
