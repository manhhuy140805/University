package Bank_Account;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import Tools.Check;

public class TaiKhoanThanhToan extends TaiKhoanNganHang{
	private double hanMucThauChi = 1000000;

	public double getHanMucThauChi() {
		return hanMucThauChi;
	}

	public void setHanMucThauChi(double hanMucThauChi) {
		this.hanMucThauChi = hanMucThauChi;
	}

	public TaiKhoanThanhToan(String soDienThoai, String matKhau, String tenChuTaiKhoan, double soDu, String maPIN) {
		super(soDienThoai, matKhau, tenChuTaiKhoan, soDu, maPIN);
	}

	public TaiKhoanThanhToan() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean Nhap(String soDienThoai) {
		// TODO Auto-generated method stub
		super.Nhap(soDienThoai);
		return false;
	}

	@Override
	public void KiemTraSoDu() {
		// TODO Auto-generated method stub
		super.KiemTraSoDu();
		System.out.println("Hạn mức thấu chi: "+ hanMucThauChi);
		System.out.println("Số dư khả dụng: "+ (hanMucThauChi + soDu));
		System.out.println("Nhấn ENTER để tiếp tục");
	    sc.nextLine();
	}

	@Override
	public boolean RutTien() {
		// TODO Auto-generated method stub
		Check ch = new Check();
		System.out.println("\n--------------------------------------------------");
		System.out.println("E-BANK | <<RÚT TIỀN>>");
		System.out.println("Số dư tài khoản: "+ this.soDu + "VND");
		System.out.println("Hạn mức thấu chi: "+ this.hanMucThauChi + "VND");
		System.out.println("Số dư khả dụng: "+ (this.soDu + this.hanMucThauChi) + "VND");
		System.out.println("---------");
		while(true)
		{
			try {
				System.out.print("Nhập số tiền bạn muốn rút: ");
				double money = sc.nextDouble();
				if(money <= hanMucThauChi + soDu && money > 0)
				{
					if(ck.GioiHanPIN(this))
						return true;
					else
					{
						soDu -= money;
						this.HRutTien(money);
						System.out.println("---------");
						System.out.println("<<Thông báo>>");
						System.out.println("Thành công | Tài khoản: "+ soDienThoai + " - "
								+ tenChuTaiKhoan + "\nGD: -" + money +"VND | Số dư hiện tại: "+ soDu +"VND");
						System.out.println("Nhấn ENTER để tiếp tục");
					    sc.nextLine();
						return false;
					}
				}
				else
				{
					System.out.println("<<Thông báo>>");
					System.out.println("Số tiền rút không hợp lệ!!!");
					if(ch.Choice())
						return false;
				}
			}
			catch(Exception e)
			{
				System.out.println("---------");
				System.err.println("Lỗi nhập liệu"+ e.getMessage());
				if(ch.Choice())
					return false;
				sc.nextLine();
			}
		}	
	}
	
	public boolean Pay(HashMap<String , TaiKhoanNganHang> listPay)
	{
		String stk;
		System.out.println("\n--------------------------------------------------");
		System.out.println("E-BANK | <<Chuyển tiền>>");
		System.out.println("Số dư tài khoản: "+ this.soDu + "VND");
		System.out.println("Hạn mức thấu chi: "+ this.hanMucThauChi + "VND");
		System.out.println("Số dư khả dụng: "+ (this.soDu + this.hanMucThauChi) + "VND");
		System.out.println("---------");
		while(true)
		{
			System.out.print("Nhập số tài khoản bạn muốn chuyển đến: ");
			stk = sc.nextLine();
			if(listPay.containsKey(stk))
			{
				System.out.println("---------");
				System.out.println("Mời kiểm tra thông tin người nhận");
				listPay.get(stk).KiemTraThongTin();
				System.out.println("1. Xác nhận");
				System.out.println("2. Quay lại");
				System.out.println("0. Thoát");
				System.out.println("---------");
				System.out.print("Nhập lựa chọn của bạn: ");
				char choice = sc.nextLine().charAt(0);
				if(choice == '1')
					break;
				else
					if(choice != '2')
						return false;
				System.out.println("---------");
			}
			else
			{
				System.out.println("---------");
				System.out.println("Số tài khoản không tồn tại!!!");
				if(ck.Choice())
					return false;
				System.out.println("---------");
			}
		}
		while(true)
		{
			try {
				System.out.println("---------");
				System.out.print("Nhập số tiền bạn muốn chuyển: ");
				double money = sc.nextDouble();
				sc.nextLine();
				if (money > (hanMucThauChi + soDu) || money < 0)
				{
					System.out.println("---------");
					System.out.println("Số dư không đủ!!!");
					if(ck.Choice())
						return false;
					sc.nextLine();
				}
				else
					if (ck.GioiHanPIN(this))
						return true;
					else {
						System.out.println("---------");
						this.soDu -= money;
						this.HChuyenTien(listPay.get(stk), money);
						System.out.println("Chuyển tiền đến tài khoản: "+ listPay.get(stk).soDienThoai +" - "
											+ listPay.get(stk).getTenChuTaiKhoan() + "\nGD: -" + money +"VND | Số dư hiện tại: "+ soDu +"VND");
						listPay.get(stk).soDu += money;
						((TaiKhoanThanhToan) listPay.get(stk)).HNhanTien(this, money);
						System.out.println("Nhấn ENTER để tiếp tục");
						sc.nextLine();
						return false;
					}
			}
			catch(Exception e) {
				System.out.println("---------");
				System.err.println("Lỗi nhập liệu: "+ e.getMessage());
				if(ck.Choice())
					return false;
				sc.nextLine();
			}
		}
	}

	public boolean PayToSave(HashMap<String , TaiKhoanNganHang> listSave)
	{
		System.out.println("\n--------------------------------------------------");
		System.out.println("E-BANK | <<Chuyển tiền đến tài khoản tiết kiệm>>");
		System.out.println("Số dư tài khoản: "+ this.soDu + "VND");
		System.out.println("Hạn mức thấu chi: "+ this.hanMucThauChi + "VND");
		System.out.println("Số dư khả dụng: "+ (this.soDu + this.hanMucThauChi) + "VND");
		System.out.println("---------");
		if(!listSave.containsKey(this.soDienThoai))
		{
			System.out.println("Bạn chưa có tài khoản tiết kiệm!!!");
			System.out.println("---------");
			System.out.println("nhấn phím ENTER để thoát");
			sc.nextLine();
			return false;
			
		}
		while(true)
		{
			try {
				System.out.println("---------");
				System.out.print("Nhập số tiền bạn muốn chuyển: ");
				double money = sc.nextDouble();
				sc.nextLine();
				if (money > (hanMucThauChi + soDu) || money < 0)
				{
					System.out.println("---------");
					System.out.println("Số dư không đủ!!!");
					if(ck.Choice())
						return false;
					sc.nextLine();
				}
				else
					if (ck.GioiHanPIN(this))
						return true;
					else {
						System.out.println("---------");
						this.soDu -= money;
						this.HChuyenTienSave(listSave.get(this.soDienThoai), money);
						System.out.println("Chuyển tiền đến tài khoản tiết kiệm" +" \nGD: -" + money + "VND | Số dư hiện tại: "+ soDu + "VND");
						((TaiKhoanTietKiem) listSave.get(this.soDienThoai)).TinhLaiAn();
						listSave.get(this.soDienThoai).soDu += money;
						((TaiKhoanTietKiem) listSave.get(this.soDienThoai)).HNhanTienPay(this, money);
						System.out.println("Nhấn ENTER để tiếp tục");
						sc.nextLine();
						return false;
					}
			}
			catch(Exception e) {
				System.out.println("---------");
				System.err.println("Lỗi nhập liệu: "+ e.getMessage());
				if(ck.Choice())
					return false;
				sc.nextLine();
			}
		}
	}

	public void HNhanTien(TaiKhoanNganHang tkc, double money) {
		// TODO Auto-generated method stub
		Calendar ngayHienTai = new GregorianCalendar();
		ngayHienTai.setTime(Calendar.getInstance().getTime());
		listHistory.add(dayHistory.format(ngayHienTai.getTime())+" | Nhận tiền từ tài khoản: "+ tkc.soDienThoai + " - "
				+ tkc.tenChuTaiKhoan + " | GD: +" + money +" | Số dư hiện tại: "+ soDu +"VND");
	}


	public void HChuyenTien(TaiKhoanNganHang tkn, double money) {
		// TODO Auto-generated method stub
		Calendar ngayHienTai = new GregorianCalendar();
		ngayHienTai.setTime(Calendar.getInstance().getTime());
		listHistory.add(dayHistory.format(ngayHienTai.getTime())+" | Chuyển tiền đến tài khoản: "+ tkn.soDienThoai + " - "
				+ tkn.tenChuTaiKhoan + " | GD: -" + money +" | Số dư hiện tại: "+ soDu +"VND");
	}
	
	public void HNhanTienSave(TaiKhoanNganHang tkc, double money) {
		// TODO Auto-generated method stub
		Calendar ngayHienTai = new GregorianCalendar();
		ngayHienTai.setTime(Calendar.getInstance().getTime());
		listHistory.add(dayHistory.format(ngayHienTai.getTime())+" | Nhận tiền từ tài khoản tiết kiệm | GD: +" + money +" | Số dư hiện tại: "+ soDu +"VND");
	}


	public void HChuyenTienSave(TaiKhoanNganHang tkn, double money) {
		// TODO Auto-generated method stub
		Calendar ngayHienTai = new GregorianCalendar();
		ngayHienTai.setTime(Calendar.getInstance().getTime());
		listHistory.add(dayHistory.format(ngayHienTai.getTime())+" | Chuyển tiền đến tài khoản tiết kiệm | GD: -" + money +" | Số dư hiện tại: "+ soDu +"VND");
	}

	@Override
	public void HGuiTien(double money) {
		// TODO Auto-generated method stub
		Calendar ngayHienTai = new GregorianCalendar();
		ngayHienTai.setTime(Calendar.getInstance().getTime());
		listHistory.add(dayHistory.format(ngayHienTai.getTime())+" | Gửi tiền | Tài khoản"+ soDienThoai + " - "
				+ tenChuTaiKhoan + " | GD: +" + money +"VND | Số dư hiện tại: "+ soDu +"VND");
	}

	@Override
	public void HRutTien(double money) {
		// TODO Auto-generated method stub
		Calendar ngayHienTai = new GregorianCalendar();
		ngayHienTai.setTime(Calendar.getInstance().getTime());
		listHistory.add(dayHistory.format(ngayHienTai.getTime())+" | Rút tiền | Tài khoản"+ soDienThoai + " - "
				+ tenChuTaiKhoan + " | GD: -" + money +" | Số dư hiện tại: "+ soDu +"VND");
	}
}
