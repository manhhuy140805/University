package Account;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import Bank.Check;

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
	public void Nhap(String soDienThoai) {
		// TODO Auto-generated method stub
		super.Nhap(soDienThoai);
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
	public void RutTien() {
		// TODO Auto-generated method stub
		Check ch = new Check();
		System.out.println("\n--------------------------------------------------");
		System.out.println("<<RÚT TIỀN>>");
		while(true)
		{
			try {
				System.out.print("Nhập số tiền bạn muốn rút: ");
				double money = sc.nextDouble();
				if(money <= hanMucThauChi + soDu && money > 0)
				{
					if(ck.GioiHan(this))
						return;
					else
					{
						soDu -= money;
						this.HRutTien(money);
						System.out.println("---------");
						System.out.println("<<Thông báo>>");
						System.out.println("Thành công | Tài khoản: "+ soDienThoai + " - "
								+ tenChuTaiKhoan + "\nGD: -" + money +" | Số dư hiện tại: "+ soDu +"VND");
						System.out.println("Nhấn ENTER để tiếp tục");
					    sc.nextLine();
						return;
					}
				}
				else
				{
					System.out.println("<<Thông báo>>");
					System.out.println("Số tiền rút không hợp lệ!!!");
					if(ch.Choice())
						return;
				}
			}
			catch(Exception e)
			{
				System.out.print("Lỗi nhập liệu: ");
				e.printStackTrace();
				System.out.println();
				if(ch.Choice())
					return;
			}
		}	
	}
	
	public void Pay(HashMap<String , TaiKhoanNganHang> listPay)
	{
		String stk;
		System.out.println("\n--------------------------------------------------");
		System.out.println("<<Chuyển tiền>>");
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
						return;
			}
			else
			{
				System.out.println("---------");
				System.out.println("Số tài khoản không tồn tại!!!");
				if(ck.Choice())
					return;
			}
		}
		while(true)
		{
			System.out.println("---------");
	        System.out.print("Nhập số tiền bạn muốn chuyển: ");
	        double money = sc.nextDouble();
	        if(money <= hanMucThauChi + soDu && money > 0)
	        sc.nextLine();
	        if(ck.GioiHan(this))
					return;
	        else
	        {
				System.out.println("---------");
	        	this.TruTien(money);
		        this.HChuyenTien(listPay.get(stk), money);
		        listPay.get(stk).CongTien(money);
		        ((TaiKhoanThanhToan) listPay.get(stk)).HNhanTien(this, money);
		        System.out.println("Nhấn ENTER để tiếp tục");
		        sc.nextLine();
		        return;
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

	@Override
	public void HGuiTien(double money) {
		// TODO Auto-generated method stub
		Calendar ngayHienTai = new GregorianCalendar();
		ngayHienTai.setTime(Calendar.getInstance().getTime());
		listHistory.add(dayHistory.format(ngayHienTai.getTime())+" | <<GỬI TIỀN>> Tài khoản"+ soDienThoai + " - "
				+ tenChuTaiKhoan + " | GD: +" + money +"VND | Số dư hiện tại: "+ soDu +"VND");
	}

	@Override
	public void HRutTien(double money) {
		// TODO Auto-generated method stub
		Calendar ngayHienTai = new GregorianCalendar();
		ngayHienTai.setTime(Calendar.getInstance().getTime());
		listHistory.add(dayHistory.format(ngayHienTai.getTime())+" | <<RÚT TIỀN>> Tài khoản"+ soDienThoai + " - "
				+ tenChuTaiKhoan + " | GD: -" + money +" | Số dư hiện tại: "+ soDu +"VND");
	}
}
