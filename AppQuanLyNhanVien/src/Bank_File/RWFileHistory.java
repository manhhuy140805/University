package Bank_File;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import Bank_Account.TaiKhoanNganHang;

public class RWFileHistory {
	public static void DocFileHistory(File f, HashMap<String , TaiKhoanNganHang> listPay, HashMap<String , TaiKhoanNganHang> listSave) {
	      try {  
	      	FileReader fr = new FileReader(f);
	      	BufferedReader br = new BufferedReader(fr);
	      	String oj = null;
	          while (true) {
	        	  		oj = br.readLine();
	                  if (oj == null) 
	                	  break;
	                  String[] arrStr = oj.split(", ");
	                  for (TaiKhoanNganHang tk : listPay.values())
	                	  if(arrStr[0].equals(tk.getSoDienThoai()) && arrStr[1].equals("TKTT"))
	                		  tk.getListHistory().add(arrStr[2]);
	                  for (TaiKhoanNganHang tk : listSave.values())
	                	  if(arrStr[0].equals(tk.getSoDienThoai()) && arrStr[1].equals("TKTK"))
	                		  tk.getListHistory().add(arrStr[2]);
	          }
	          br.close();
	          fr.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }
    
	  public static void GhiFileHistory(File f, HashMap<String , TaiKhoanNganHang> listPay, HashMap<String , TaiKhoanNganHang> listSave) {
		  try {
			  FileWriter fw = new FileWriter(f, StandardCharsets.UTF_8);
			  BufferedWriter bw = new BufferedWriter(fw);
			  for (TaiKhoanNganHang tk : listPay.values()) {
				  for(String hst : tk.getListHistory())
				  {
					  bw.write(tk.getSoDienThoai()+", TKTT, "+hst);
					  bw.write("\n");
				  }
			  }
			  for (TaiKhoanNganHang tk : listSave.values()) {
				  for(String hst : tk.getListHistory())
				  {
					  bw.write(tk.getSoDienThoai()+", TKTK, "+hst);
					  bw.write("\n");
				  }
			  }
			  bw.close();
			  fw.close();  	
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	  }
}
