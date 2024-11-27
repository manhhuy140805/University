package Bank_File;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import Bank_Account.TaiKhoanNganHang;
import Bank_Account.TaiKhoanThanhToan;
import Bank_Account.TaiKhoanTietKiem;

public class RWFileAccount {
	public static void DocFile(File fAccount, File fHistory, HashMap<String , TaiKhoanNganHang> listPay, HashMap<String , TaiKhoanNganHang> listSave) {
	      try {  
	      	FileReader fr = new FileReader(fAccount);
	      	BufferedReader br = new BufferedReader(fr);
	      	
	      	String oj = null;
	          while (true) {
	        	  		oj = br.readLine();
	                  if (oj == null) 
	                	  break;
	                  String[] arrStr = oj.split(", ");
	                  TaiKhoanNganHang tk = new TaiKhoanThanhToan(arrStr[0], arrStr[1], arrStr[2],(Double.parseDouble(arrStr[3])), arrStr[4]);
	                  listPay.put(arrStr[0], tk); 
	                  if(arrStr.length == 11)
	                  {
	                	  TaiKhoanNganHang tktk = new TaiKhoanTietKiem(arrStr[0], arrStr[1], arrStr[2],(Double.parseDouble(arrStr[3])), arrStr[5], arrStr[6], (float) (Double.parseDouble(arrStr[7])), arrStr[8], arrStr[9], (Double.parseDouble(arrStr[10])));
	                	  listSave.put(arrStr[0], tktk);
	                  }
	          }
	          br.close();
	          fr.close();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }
	    
	    
	    public static void GhiFile(File fAccount, File fHistory, HashMap<String , TaiKhoanNganHang> listPay, HashMap<String , TaiKhoanNganHang> listSave) {
	      try {
	    	  	FileWriter fw = new FileWriter(fAccount, StandardCharsets.UTF_8);
	    	  	BufferedWriter bw = new BufferedWriter(fw);
	    	  	for(String tk : listPay.keySet())
	    	  	{
	    	  		bw.write(listPay.get(tk).toString());
	    	  		if(listSave.containsKey(tk))
	    	  			bw.write(listSave.get(tk).toString());
	    	  		bw.write("\n");
	    	  	}
	    	  	bw.close();
	    	  	fw.close();
	    	  	
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }
}
