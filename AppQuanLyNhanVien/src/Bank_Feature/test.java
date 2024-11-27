package Bank_Feature;

import java.io.File;
import java.util.Scanner;

import Bank_Account.TaiKhoanNganHang;
import Bank_Account.TaiKhoanThanhToan;
import Bank_File.RWFileAccount;
import Bank_File.RWFileHistory;

public class test {
	public static void main(String[] args) {
		ListAccount listPay = new ListAccount(1);
		ListAccount listSave = new ListAccount(2);
		File FileAccount = new File("Account.txt");
		File FileHistory = new File("History.txt");
		RWFileAccount.DocFile(FileAccount, FileHistory, listPay.list, listSave.list);
		RWFileHistory.DocFileHistory(FileHistory, listPay.list, listSave.list);
		
	}
}
			
