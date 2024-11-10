package Bank;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;

import Account.TaiKhoanNganHang;
import Account.TaiKhoanThanhToan;
import Account.TaiKhoanTietKiem;

public class ListAccount {
	protected HashMap list;
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
		System.out.println("<<ĐĂNG KÝ TÀI KHOẢN MỚI>>");
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
			}
			else
				if(ck.CheckExists(sdt, list))
				{
					System.out.println("---------");
					System.out.println("Số điện thoại đã được đăng ký!!!");
					if(ck.Choice())
						return;
				}
				else
					break;
		}
		tk.Nhap(sdt);
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
				System.out.println("<<ĐĂNG NHẬP>>");
				System.out.print("Nhập số tài khoản: ");
				String stk = sc.nextLine();
				if(ck.CheckExists(stk, list))
				{
					System.out.print("Nhập mật khẩu: ");
					String mk = sc.nextLine();
					if(ck.CheckPasswordLogIn(stk, mk, list))
					{
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
					System.out.println("Số điện thoại chưa được đăng ký!!!");
					if(ck.Choice())
						return;
				}
			}
		}
		catch(Exception e)
		{
			System.out.print("Lỗi nhập liệu: "+ e.getMessage());
			if(ck.Choice())
				return;
		}
	}
	
	public void FeatureUser(TaiKhoanThanhToan tk, ListAccount listSave) {
		try
		{
			while(true)
			{
				System.out.println("\n--------------------------------------------------");
				System.out.println("Số tài khoản: "+ tk.getSoDienThoai() +"\nChủ tài khoản: "+ tk.getTenChuTaiKhoan());
				System.out.println("---------");
				System.out.println("1. Chuyển khoản");
				System.out.println("2. Kiểm tra số dư");
				System.out.println("3. Biến động số dư");
				System.out.println("4. Gửi tiền");
				System.out.println("5. Rút tiền");
				System.out.println("6. Đổi mật khẩu");
				System.out.println("7. Đổi mã PIN");
				System.out.println("8. Tài khoản tiết kiệm");
				System.out.println("0. Đăng xuất");
				System.out.println("---------");
				System.out.print("Nhập lựa chọn của bạn: ");
				char choice = sc.nextLine().charAt(0);
				switch(choice)
				{
					case '1' : tk.Pay(this.list);break;
					case '2' : tk.KiemTraSoDu();break;
					case '3' : tk.InHistory();break;
					case '4' : tk.GuiTien();break;
					case '5' : tk.RutTien();break;
					case '6' : tk.RePassWord(tk);break;
					case '7' : tk.RePIN(tk);break;
					case '8' : listSave.TaiKhoanTietKiem(tk);break;
					case '0' : return;
					default : 
						System.out.println("---------");
						System.out.println("Lựa chọn của bạn không tồn tại!!!"); 
						if(ck.Choice())
							break;
						else
							return;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Lỗi nhập liệu: "+ e.getMessage());
			if(ck.Choice())
				return;
		}
	}
	
	public void TaiKhoanTietKiem(TaiKhoanThanhToan tktt) throws ParseException
	{
		char choice;
		System.out.println("\n--------------------------------------------------");
		System.out.println("<<TÀI KHOẢN TIẾT KIỆM>>");
		if(!list.containsKey(tktt.getSoDienThoai()))
		{
			System.out.println("---------");
			System.out.println("Bạn có muốn mở tài khoản tiết kiệm không?");
			System.out.println("1. Mở tài khoản tiết kiệm");
			System.out.println("0. thoát");
			System.out.println("---------");
			System.out.print("Nhập lựa chọn của bạn: ");
			choice = sc.nextLine().charAt(0);
			if(choice == '1')
			{
				System.out.println("\n--------------------------------------------------");
				System.out.println("<<Mở tài khoản tiết kiệm>>");
				System.out.print("Nhập mã pin cho tài khoản tiết kiệm: ");
				String maPIN = sc.nextLine();
				TaiKhoanNganHang tktk = new TaiKhoanTietKiem(tktt.getSoDienThoai(), tktt.getMatKhau(), tktt.getTenChuTaiKhoan(), 0, maPIN);
				((Account.TaiKhoanTietKiem) tktk).ChonKyHan();
				if(((Account.TaiKhoanTietKiem) tktk).getLaiXuat() == 0)
					return;
				list.put(tktt.getSoDienThoai(), tktk);
				System.out.println("---------");
				System.out.println("Mở tài khoản thành công");
				System.out.println("Nhấn ENTER để tiếp tục");
				sc.nextLine();
				this.FeatureSave((TaiKhoanTietKiem) list.get(tktt.getSoDienThoai()));
	        	return;
			}
			else
				return;
		}
		else
		{
			while(true)
			{
				if(ck.GioiHan((TaiKhoanNganHang) list.get(tktt.getSoDienThoai())))
		        {
		        	if(ck.Choice())
						return;
		        }
		        else
		        {
		        	this.FeatureSave((TaiKhoanTietKiem) list.get(tktt.getSoDienThoai()));
		        	return;
		        }
				
			}
		}
	}
	
	public void FeatureSave(TaiKhoanTietKiem tk)
	{
		char choice;
		while(true)
		{
			((Account.TaiKhoanTietKiem) tk).TinhLai();
			System.out.println("\n--------------------------------------------------");
			System.out.println("<<Sổ tiết kiệm số: "+ tk.getSoDienThoai()+">>");
			System.out.println("1. Kiểm tra thông tin sổ tiết kiệm");
			System.out.println("2. Kiểm tra số dư");
			System.out.println("3. Biến động số dư");
			System.out.println("4. Gửi tiền");
			System.out.println("5. Rút tiền");
			System.out.println("6. Tính tổng tiền lãi");
			System.out.println("0. Thoát");
			System.out.println("---------");
			System.out.print("Nhập lựa chọn của bạn: ");
			choice = sc.nextLine().charAt(0);
			switch(choice)
			{
				case '1' : tk.KiemTraThongTin();;break;
				case '2' : tk.KiemTraSoDu();break;
				case '3' : tk.InHistory();break;
				case '4' : tk.GuiTien();break;
				case '5' : tk.RutTien();break;
				case '6' : ((Account.TaiKhoanTietKiem) tk).TinhTienLai();break;		
				case '0' : return;
				default : 
					System.out.println("---------");
					System.out.println("Lựa chọn của bạn không tồn tại!!!"); 
					if(ck.Choice())
						break;
					else
						return;
			}
		}
	}
}
