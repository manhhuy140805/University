package NhanSu_DanhSach;

import java.util.HashMap;

import Bank_Feature.ListAccount;
import NhanSu_Data.NhanSu;
import NhanSu_Data.QuanLi;

public class DanhSachCuaHang {
	protected HashMap<String, CuaHang> danhSachGianHang = new HashMap<String, CuaHang>();

	public HashMap<String, CuaHang> getDanhSachGianHang() {
		return danhSachGianHang;
	}

	public void setDanhSachGianHang(CuaHang gh) {
		danhSachGianHang.put(gh.getMaCuaHang(), gh);
	}

	public void TaoCuaHang(ListAccount listPay, DanhSachTaiKhoan dstk, NhanSu ns) {
		System.out.println("\n--------------------------------------------------");
		System.out.println("QUẢN LÍ NHÂN SỰ | <<TẠO CỬA HÀNG CỦA BẠN>>");
		System.out.println("---------");
		CuaHang gh = new CuaHang();
		if(gh.Nhap(dstk, this))
			return;
		NhanSu nsMoi = new QuanLi(ns);
		nsMoi.setGianHang(gh.maCuaHang);
		dstk.danhSachTaiKhoan.remove(ns.getSoDienThoai());
		dstk.danhSachTaiKhoan.put(nsMoi.getSoDienThoai(), nsMoi);
		gh.menuQuanLi(listPay, dstk, nsMoi);
		
	}
}
