package Bank_Feature;

import java.io.File;
import java.util.Scanner;

import Bank_File.RWFileAccount;
import Bank_File.RWFileHistory;
import Tools.Check;

public class MainBank {

	public static void HomeBank(ListAccount listPay, ListAccount listSave, File FileAccount, File FileHistory) {
		Scanner sc = new Scanner(System.in);
		Check ck = new Check();
		char choice = 0;
		while(true)
		{
			try
			{
				System.out.println("\n--------------------------------------------------");
				System.out.println("E-BANK | <<TRANG CHỦ>>");
				System.out.println("---------");
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
			    System.err.println("Lỗi nhập liệu");
			    if (ck.Choice())
			    	return;
			}
		}
	}
}
