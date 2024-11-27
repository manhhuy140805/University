package Tools;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import Bank_Account.TaiKhoanNganHang;
import NhanSu_Data.NhanSu;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Check {

    private static Scanner sc = new Scanner(System.in);

    public boolean Choice() {
        System.out.println("1. Quay lại bước trước đó");
        System.out.println("0. Thoát");
        System.out.println("---------");
        System.out.print("Nhập lựa chọn của bạn: ");
        char choice = sc.nextLine().charAt(0);
        return (choice != '1');
    }

    public boolean CheckFormatSDT(String sdt) {
        return !(sdt.charAt(0) == '0' && sdt.length() == 10 && sdt.matches("[0-9]+"));
    }

    public boolean CheckExists(String sdt, HashMap<String, TaiKhoanNganHang> list) {
        return list.containsKey(sdt);
    }

    public boolean CheckPassWord(String matKhau) {
        // Kiểm tra độ dài
        if (matKhau.length() < 8) {
            return true;
        }

        // Khởi tạo các biến cờ cho từng loại ký tự cần có
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        // Duyệt từng ký tự của mật khẩu
        for (char c : matKhau.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) { // Ký tự đặc biệt
                hasSpecialChar = true;
            }
        }
        return !(hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar);
    }

    public boolean CheckFormatPIN(String PIN) {
        return !(PIN.length() == 6 && PIN.matches("[0-9]+"));
    }

    public boolean CheckPasswordLogIn(String sdt, String mk, HashMap<String, TaiKhoanNganHang> list) {
        return !(list.get(sdt).getMatKhau().equals(mk));
    }

    public boolean CheckSringEqual(String s1, String s2) {
        return !(s1.equals(s2));
    }

    public boolean GioiHanPIN(TaiKhoanNganHang tk) {
        int gh = 4;
        for (int i = 1; i <= gh; i++) {
            System.out.println("---------");
            System.out.print("Nhập mã PIN: ");
            String mp = sc.nextLine();
            if (this.CheckSringEqual(tk.getMaPIN(), mp)) {
                System.out.println("---------");
                System.out.println("Mã PIN không đúng!!!\nBạn còn " + (gh - i) + " lần nhập");
                if (i < gh) {
                    if (this.Choice()) {
                        return true;
                    }
                }
            } else {
                return false;
            }
        }
        System.out.println("---------");
        System.out.println("Số lần nhập đã hết!!!");
        System.out.println("Nhấn ENTER để tiếp tục");
        sc.nextLine();
        return true;
    }

    public boolean GioiHanPW(TaiKhoanNganHang tk) {
        int gh = 4;
        for (int i = 1; i <= gh; i++) {
            System.out.println("---------");
            System.out.print("Nhập mật khẩu: ");
            String mp = sc.nextLine();
            if (this.CheckSringEqual(tk.getMatKhau(), mp)) {
                System.out.println("---------");
                System.out.println("Mã mật khẩu không đúng!!!\nBạn còn " + (gh - i) + " lần nhập");
                if (i < gh) {
                    if (this.Choice()) {
                        return true;
                    }
                }
            } else {
                return false;
            }
        }
        System.out.println("---------");
        System.out.println("Số lần nhập đã hết!!!");
        System.out.println("Nhấn ENTER để tiếp tục");
        sc.nextLine();
        return true;
    }

    public boolean GioiHanPWNS(NhanSu tk) {
        int gh = 4;
        for (int i = 1; i <= gh; i++) {
            System.out.println("---------");
            System.out.print("Nhập mật khẩu: ");
            String mp = sc.nextLine();
            if (this.CheckSringEqual(tk.getMatKhau(), mp)) {
                System.out.println("---------");
                System.out.println("Mã mật khẩu không đúng!!!\nBạn còn " + (gh - i) + " lần nhập");
                if (i < gh) {
                    if (this.Choice()) {
                        return true;
                    }
                }
            } else {
                return false;
            }
        }
        System.out.println("---------");
        System.out.println("Số lần nhập đã hết!!!");
        System.out.println("Nhấn ENTER để tiếp tục");
        sc.nextLine();
        return true;
    }

    public static boolean FormatEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return email == null && pattern.matcher(email).matches();
    }

    public static LocalDate getDate(String input, String error) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dob;
        while (true) {
            try {
                System.out.print(input);
                dob = LocalDate.parse(sc.nextLine().trim(), format);
                return dob;
            } catch (Exception e) {
                System.out.println(error);
            }
        }
    }
    
    public static String getString(String input, String error) {
        String s;
        while(true) {
            System.out.print(input);
            s = sc.nextLine().trim();
            if(s.isEmpty())
                System.out.println(error);
            else return s;
        }
    }
}
