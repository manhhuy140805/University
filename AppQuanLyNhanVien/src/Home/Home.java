package Home;

import java.io.File;
import java.text.ParseException;
import java.util.Scanner;
import Bank_Feature.ListAccount;
import Bank_Feature.MainBank;
import Bank_File.RWFileAccount;
import Bank_File.RWFileHistory;
import NhanSu_DanhSach.DanhSachCuaHang;
import NhanSu_DanhSach.DanhSachTaiKhoan;
import NhanSu_DanhSach.CuaHang;
import NhanSu_Data.MainNhanSu;
import NhanSu_Data.NhanSu;
import NhanSu_Data.NhanVienChinhThuc;
import NhanSu_Data.NhanVienThoiVu;
import NhanSu_Data.QuanLi;
import Tools.Check;

public class Home {
	public static void main(String[] args) {
		ListAccount listPay = new ListAccount(1);
		ListAccount listSave = new ListAccount(2);
		File FileAccount = new File("Account.txt");
		File FileHistory = new File("History.txt");
		RWFileAccount.DocFile(FileAccount, FileHistory, listPay.list, listSave.list);
		RWFileHistory.DocFileHistory(FileHistory, listPay.list, listSave.list);
		DanhSachTaiKhoan DSTK = new DanhSachTaiKhoan();
		DanhSachCuaHang DSGH = new DanhSachCuaHang();
		//tài khoản test
		NhanSu ns = new NhanSu(null, "1", "123", "Trần Đình Mạnh Huy", "14/08/2005", "trandinhmanhhuy@gmail.com", "Thừa Thiên Huế", "0326143986", "MB Bank");
		DSTK.danhSachTaiKhoan.put(ns.getSoDienThoai(), ns);
		ns = new NhanSu(null, "2", "123", "Nguyễn Công Minh", "01/01/2005", "trandinhmanhhuy@gmail.com", "Thừa Thiên Huế", "0326143988", "MB Bank");
		DSTK.danhSachTaiKhoan.put(ns.getSoDienThoai(), ns);
		
		//gian hàng
		CuaHang gh = new CuaHang("test", "10 Điểm Không Có Nhưng", "48 Cao Thắng");
		//tài khoản quản lí
		ns = new QuanLi(null, "3", "123", "Nguyễn Trần Hồ Nguyễn", "14/08/2005", "trandinhmanhhuy@gmail.com", "Thừa Thiên Huế", "0123456784", "MB Bank", "21/03/2023");
		ns.setGianHang("test");
		DSTK.danhSachTaiKhoan.put(ns.getSoDienThoai(), ns);
		//tài khoản nhân viên chính thức
		ns = new NhanVienChinhThuc(null, "4", "123", "Nguyễn Phúc An", "01/01/2005", "trandinhmanhhuy@gmail.com", "Thừa Thiên Huế", "0326143987", "MB Bank", "21/03/2023");
		ns.setGianHang("test");
		DSTK.danhSachTaiKhoan.put(ns.getSoDienThoai(), ns);
		//tài khoản nhân viên thời vụ
		ns = new NhanVienThoiVu(null, "5", "123", "Hồ Thị Mỹ Hà", "29/11/2005", "trandinhmanhhuy@gmail.com", "Thừa Thiên Huế", "0326143989", "MB Bank", "21/03/2023");
		ns.setGianHang("test");
		DSTK.danhSachTaiKhoan.put(ns.getSoDienThoai(), ns);
		
		DSGH.setDanhSachGianHang(gh);
		
		Scanner sc = new Scanner(System.in);
		char choice = 0;
		while(true)
		{
			System.out.println("\n--------------------------------------------------");
			System.out.println("<<DANH SÁCH PHẦN MỀM>>");
			System.out.println("1. E-Bank");
			System.out.println("2. Quản lí nhân sự");
			System.out.print("Chọn phần mềm: ");
			choice = sc.nextLine().charAt(0);
			switch(choice)
			{
			case '1': MainBank.HomeBank(listPay, listSave, FileAccount, FileHistory);
					break;
			case '2': MainNhanSu.HomeNhanSu(listPay, FileAccount, FileHistory, DSTK, DSGH);
					break;
			case '0': 
				RWFileAccount.GhiFile(FileAccount, FileHistory, listPay.list, listSave.list);
				RWFileHistory.GhiFileHistory(FileHistory, listPay.list, listSave.list);
				System.out.println("Cảm ơn bạn đã sử dụng tính năng");
				return;
			}
		}
	}
}
