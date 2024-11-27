package NhanSu_Data;

import java.io.File;
import java.util.Scanner;
import Bank_Feature.ListAccount;
import Bank_File.RWFileAccount;
import Bank_File.RWFileHistory;
import NhanSu_DanhSach.DanhSachCuaHang;
import NhanSu_DanhSach.DanhSachTaiKhoan;
import Tools.Check;

public class MainNhanSu {
	public static void HomeNhanSu(ListAccount listPay, File FileAccount, File FileHistory, DanhSachTaiKhoan dstk, DanhSachCuaHang dsgh) {
		Scanner sc = new Scanner(System.in);
		Check ck = new Check();
		char choice = 0;
		while(true)
		{
			try
			{
				System.out.println("\n--------------------------------------------------");
				System.out.println("QUẢN LÍ NHÂN SỰ | <<TRANG CHỦ>>");
				System.out.println("---------");
				System.out.println("1. Đăng Nhập");
				System.out.println("2. Đăng ký");
				System.out.println("0. Thoát");
				System.out.println("---------");
				System.out.print("Nhập lựa chọn của bạn: ");
				choice = sc.nextLine().charAt(0);
				switch(choice)
				{
					case '1' : dstk.dangNhap(listPay ,dsgh); ;break;
					case '2' : dstk.DangKi(listPay);break;
					case '0' : return;
					default:
						System.out.println("---------");
						System.out.println("Lựa chọn của bạn không tồn tại!!!"); 
						if(ck.Choice())
							return;
				}
			}
			catch(Exception e)
			{
				System.out.println("---------");
			    System.err.println("Lỗi nhập liệu: "+ e.getMessage());
			    if (ck.Choice())
			    	return;
			}
		}
	}
}
