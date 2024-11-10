package Account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import Bank.Check;


public class TaiKhoanTietKiem extends TaiKhoanNganHang{
	private Calendar ngayBdGui = new GregorianCalendar();
	private Calendar ngayTinhLai = new GregorianCalendar();
	private double tongTienLai = 0;
	private String kyHan;
	private float laiSuat = 0;
	SimpleDateFormat day = new SimpleDateFormat("dd/MM/yyyy");
	
	public String toString() {
		return ", " +maPIN +", " + kyHan+  ", " + laiSuat + ", " + day.format(ngayBdGui.getTime()) +", " + day.format(ngayTinhLai.getTime()) + ", "+ tongTienLai ;
	}
	
	public Calendar getNgayBdGui() {
		return ngayBdGui;
	}

	public void setNgayBdGui(Calendar ngayBdGui) {
		this.ngayBdGui = ngayBdGui;
	}

	public Calendar getNgayTinhLai() {
		return ngayTinhLai;
	}

	public void setNgayTinhLai(Calendar ngayTinhLai) {
		this.ngayTinhLai = ngayTinhLai;
	}

	public double getTongTienLai() {
		return tongTienLai;
	}

	public void setTongTienLai(double tongTienLai) {
		this.tongTienLai = tongTienLai;
	}

	public String getKyHan() {
		return kyHan;
	}

	public void setKyHan(String kyHan) {
		this.kyHan = kyHan;
	}

	public float getLaiXuat() {
		return laiSuat;
	}

	public void setLaiXuat(float laiXuat) {
		this.laiSuat = laiXuat;
	}

	public TaiKhoanTietKiem(String soTaiKhoan, String matKhau, String tenChuTaiKhoan, double soDu, String maPin, String kyHan, float laiXuat, String ngayBdGui, String ngayTinhLai, double tongTienLai) throws ParseException {
	    super(soTaiKhoan, matKhau, tenChuTaiKhoan, soDu, maPin);
		this.kyHan = kyHan;
		this.laiSuat = laiXuat;
	    this.ngayBdGui.setTime(day.parse(ngayBdGui));
	    this.ngayTinhLai.setTime(day.parse(ngayTinhLai));
	    this.tongTienLai = tongTienLai;
	    // TODO Auto-generated constructor stub
	}
	
	public TaiKhoanTietKiem(String soTaiKhoan, String matKhau, String tenChuTaiKhoan , double soDu, String maPin) throws ParseException {
	    this.soDienThoai = soTaiKhoan;
	    this.tenChuTaiKhoan = tenChuTaiKhoan;
	    this.matKhau = matKhau;
	    this.maPIN = maPin;
	    this.ngayBdGui.setTime(Calendar.getInstance().getTime());
	    this.ngayTinhLai.setTime(Calendar.getInstance().getTime());
	    // TODO Auto-generated constructor stub
	}
	
	@Override
	public void Nhap(String soDienThoai) {
		// TODO Auto-generated method stub
		super.Nhap(soDienThoai);
	}

	
	
	@Override
	public void KiemTraThongTin() {
		// TODO Auto-generated method stub
		System.out.println("\n--------------------------------------------------");
		System.out.println("<<THÔNG TIN SỔ TIẾT KIỆM>>");
		super.KiemTraThongTin();
		System.out.println("Kỳ hạn: "+ kyHan);
		System.out.println("Lãi suất: "+ (laiSuat/100) +"%");
		System.out.println("Ngày bắt đầu gửi: "+ day.format(ngayBdGui.getTime()));
		System.out.println("Ngày tính lãi gần nhất: "+ day.format(ngayTinhLai.getTime()));
		System.out.println("---------");
		System.out.println("Nhấn ENTER để tiếp tục");
	    sc.nextLine();
	}

	@Override
	public void KiemTraSoDu() {
		// TODO Auto-generated method stub
		super.KiemTraSoDu();
		System.out.println("---------");
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
				sc.nextLine();
				if(money > 0 && money <= soDu)
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
						System.out.println("---------");
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
	
	public void ChonKyHan()
	{
		do 
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("---------");
			System.out.println("Bạn muốn gửi tiết kiệm theo kỳ hạn nào?");
			System.out.println("1. Kỳ hạn 1 tháng với lãi xuất 0.25%");
			System.out.println("2. Kỳ hạn 1 quý (3 tháng) với lãi xuất 1%");
			System.out.println("3. Kỳ hạn 6 tháng với lãi xuất 3%");
			System.out.println("4. Kỳ hạn 1 năm với lãi xuất 7%");
			System.out.println("0. thoát");
			System.out.println("---------");
			System.out.print("Nhập lựa chọn của bạn: ");
			char choice = sc.nextLine().charAt(0);
			switch(choice)
			{
			case '1':
				kyHan = "1 Tháng";
				laiSuat = (float) 0.0025;
				return;
			case '2':
				kyHan = "3 Tháng";
				laiSuat = (float) 0.01;
				return;
			case '3':
				kyHan = "6 Tháng";
				laiSuat = (float) 0.03;
				return;
			case '4':
				kyHan = "1 Năm";
				laiSuat = (float) 0.07;
				return;
			case '0' : return;
			default : 
				System.out.println("---------");
				System.out.println("Lựa chọn của bạn không tồn tại!!!"); 
				if(ck.Choice())
					return;
			}
		}
		while(true);
	}
	
	public void TinhLai() {
		Calendar ngayHienTai = new GregorianCalendar();
		ngayHienTai.setTime(Calendar.getInstance().getTime());
	    double soky = (ngayHienTai.getTimeInMillis() - ngayTinhLai.getTimeInMillis()) / (24 * 60 * 60 * 1000);
	    switch (kyHan) {
	        case "1 Tháng":
	            if ((int) soky / 30 >= 1) {
	            	String hst;
	        		System.out.println("\n--------------------------------------------------");
	                System.out.print("Tiền lãi của bạn tính từ ngày " + day.format(ngayTinhLai.getTime()) + " ");
	                hst = "Tiền lãi của bạn tính từ ngày " + day.format(ngayTinhLai.getTime()) + " ";
	                ngayTinhLai.add(Calendar.DAY_OF_MONTH, 30 * (int) (soky / 30));
	                System.out.println("đến ngày " + day.format(ngayTinhLai.getTime()) + " là: " + (soDu * Math.pow(1 + laiSuat, (int) (soky / 30)) - soDu));
	                hst += "đến ngày " + day.format(ngayTinhLai.getTime()) + " là: " + (soDu * Math.pow(1 + laiSuat, (int) (soky / 30)) - soDu);
	                tongTienLai += soDu * Math.pow(1 + laiSuat, (int) (soky / 30)) - soDu;
	                soDu = soDu * Math.pow(1 + laiSuat, (int) (soky / 30));
	                System.out.println("Hũ tiết kiệm hiện tại có: " + soDu);
	                hst += "Hũ tiết kiệm hiện tại có: " + soDu;
	                listHistory.add(dayHistory.format(ngayHienTai)+" | "+hst);
	            }
	            break;
	        case "3 Tháng":
	            if ((int) soky / 90 >= 1) {
	            	String hst;
	        		System.out.println("\n--------------------------------------------------");
	                System.out.print("Tiền lãi của bạn tính từ ngày " + day.format(ngayTinhLai.getTime()) + " ");
	                hst = "Tiền lãi của bạn tính từ ngày " + day.format(ngayTinhLai.getTime()) + " ";
	                ngayTinhLai.add(Calendar.DAY_OF_MONTH, 90 * (int) (soky / 90));
	                System.out.println("đến ngày " + day.format(ngayTinhLai.getTime()) + " là: " + (soDu * Math.pow(1 + laiSuat, (int) (soky / 90)) - soDu));
	                hst += "đến ngày " + day.format(ngayTinhLai.getTime()) + " là: " + (soDu * Math.pow(1 + laiSuat, (int) (soky / 90)) - soDu);
	                tongTienLai += soDu * Math.pow(1 + laiSuat, (int) (soky / 90)) - soDu;
	                soDu = soDu * Math.pow(1 + laiSuat, (int) (soky / 90));
	                System.out.println("Hũ tiết kiệm hiện tại có: " + soDu);
	                hst += "Hũ tiết kiệm hiện tại có: " + soDu;
	                listHistory.add(dayHistory.format(ngayHienTai)+" | "+hst);
	            }
	            break;
	        case "6 Tháng":
	            if ((int) soky / 180 >= 1) {
	            	String hst;
	        		System.out.println("\n--------------------------------------------------");
	                System.out.print("Tiền lãi của bạn tính từ ngày " + day.format(ngayTinhLai.getTime()) + " ");
	                hst = "Tiền lãi của bạn tính từ ngày " + day.format(ngayTinhLai.getTime()) + " ";
	                ngayTinhLai.add(Calendar.DAY_OF_MONTH, 180 * (int) (soky / 180));
	                System.out.println("đến ngày " + day.format(ngayTinhLai.getTime()) + " là: " + (soDu * Math.pow(1 + laiSuat, (int) (soky / 180)) - soDu));
	                hst += "đến ngày " + day.format(ngayTinhLai.getTime()) + " là: " + (soDu * Math.pow(1 + laiSuat, (int) (soky / 180)) - soDu);
	                tongTienLai += soDu * Math.pow(1 + laiSuat, (int) (soky / 180)) - soDu;
	                soDu = soDu * Math.pow(1 + laiSuat, (int) (soky / 180));
	                System.out.println("Hũ tiết kiệm hiện tại có: " + soDu);
	                hst += "Hũ tiết kiệm hiện tại có: " + soDu;
	                listHistory.add(dayHistory.format(ngayHienTai)+" | "+hst);
	            }
	            break;
	        case "1 Năm":
	            if ((int) soky / 365 >= 1) {
	            	String hst;
	        		System.out.println("\n--------------------------------------------------");
	                System.out.print("Tiền lãi của bạn tính từ ngày " + day.format(ngayTinhLai.getTime()) + " ");
	                hst = "Tiền lãi của bạn tính từ ngày " + day.format(ngayTinhLai.getTime()) + " ";
	                ngayTinhLai.add(Calendar.DAY_OF_MONTH, 365 * (int) (soky / 365));
	                System.out.println("đến ngày " + day.format(ngayTinhLai.getTime()) + " là: " + (soDu * Math.pow(1 + laiSuat, (int) (soky / 365)) - soDu));
	                hst += "đến ngày " + day.format(ngayTinhLai.getTime()) + " là: " + (soDu * Math.pow(1 + laiSuat, (int) (soky / 365)) - soDu);
	                tongTienLai += soDu * Math.pow(1 + laiSuat, (int) (soky / 365)) - soDu;
	                soDu = soDu * Math.pow(1 + laiSuat, (int) (soky / 365));
	                System.out.println("Hũ tiết kiệm hiện tại có: " + soDu);
	                hst += "Hũ tiết kiệm hiện tại có: " + soDu;
	                listHistory.add(dayHistory.format(ngayHienTai)+" | "+hst);
	            }
	            break;
	    }
	}
	
	public void TinhTienLai()
	{
		System.out.println("---------");
		System.out.println("Tổng tiền lãi tính từ ngày "+ day.format(this.getNgayBdGui().getTime()) + "đến ngày "+ day.format(ngayTinhLai.getTime()));
		System.out.println("Tổng tiền: "+ this.getTongTienLai()+ "VND");
		System.out.println("---------");
		System.out.println("Nhấn ENTER để tiếp tục");
		sc.nextLine();
	}



	@Override
	public void HGuiTien(double money) {
		// TODO Auto-generated method stub
		Calendar ngayHienTai = new GregorianCalendar();
		ngayHienTai.setTime(Calendar.getInstance().getTime());
		listHistory.add(dayHistory.format(ngayHienTai.getTime())+" | <<GỬI TIỀN>> Tài khoản: "+ soDienThoai + " - "
				+ tenChuTaiKhoan + " | GD: +" + money +" | Số dư hiện tại: "+ soDu +"VND");
	}

	@Override
	public void HRutTien(double money) {
		// TODO Auto-generated method stub
		Calendar ngayHienTai = new GregorianCalendar();
		ngayHienTai.setTime(Calendar.getInstance().getTime());
		listHistory.add(dayHistory.format(ngayHienTai.getTime())+" | <<RÚT TIỀN>> Tài khoản: "+ soDienThoai + " - "
				+ tenChuTaiKhoan + " | GD: -" + money +" | Số dư hiện tại: "+ soDu +"VND");
	}
}
