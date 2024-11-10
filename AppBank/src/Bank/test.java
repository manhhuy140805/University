package Bank;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import Account.TaiKhoanNganHang;
import Account.TaiKhoanThanhToan;
import File.RWFileAccount;
import File.RWFileHistory;

public class test {
	public static void main(String[] args) {
		ListAccount listPay = new ListAccount(1);
		ListAccount listSave = new ListAccount(2);
		File FileAccount = new File("Account.txt");
		File FileHistory = new File("History.txt");
		Scanner sc = new Scanner(System.in);
		Check ck = new Check();
		char choice = 0;
		RWFileAccount.DocFile(FileAccount, FileHistory, listPay.list, listSave.list);
		RWFileHistory.DocFileHistory(FileHistory, listPay.list, listSave.list);
		try
		{
			while(true)
			{
				System.out.println("\n--------------------------------------------------");
				System.out.println("<<Trang Chủ>>");
				System.out.println("1. Đăng Nhập");
				System.out.println("2. Đăng ký");
				System.out.println("0. Thoát");
				System.out.println("---------");
				System.out.print("Nhập lựa chọn của bạn: ");
				choice = sc.nextLine().charAt(0);
				switch(choice)
				{
					case '1' : listPay.LogIn(listSave);break;
					case '2' : listPay.SignIn();break;
					case '0' : {
//						listPay.CloseFileData(f);
						RWFileAccount.GhiFile(FileAccount, FileHistory, listPay.list, listSave.list);
						RWFileHistory.GhiFileHistory(FileHistory, listPay.list, listSave.list);
						return;
					}
					default:
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
			System.out.print("Lỗi nhập liệu: "+ e.getMessage());
			if(ck.Choice())
				return;
		}
	}
}
			
