package TEST;

import java.util.Scanner;

public class EmailCheck {
    public static void main(String[] args) {
        String email = "ledinhnhuan1917@gmail.com";
        
        // Sửa biểu thức chính quy bằng cách loại bỏ dấu gạch chéo và thêm double backslashes trước dấu chấm
        if (email.matches("(?i)[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}")) {
            System.out.println("Oke");
        } else {
            System.out.println("No");
        }
    }
}
