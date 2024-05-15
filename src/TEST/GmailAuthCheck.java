package TEST;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

public class GmailAuthCheck {
    public static void main(String[] args) {

        String username = "ledinhnhuan1917@gmail.com";
        String password = "rbxx lwir hhvu mgrz";  // Thay bằng mật khẩu ứng dụng

        // Thiết lập các thuộc tính cho JavaMail
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imap.host", "imap.gmail.com");
        props.put("mail.imap.port", "993");
        props.put("mail.imap.ssl.enable", "true");

        try {
            // Tạo phiên làm việc với thông tin xác thực
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            // Kết nối tới store (lưu trữ)
            Store store = session.getStore("imap");
            store.connect("imap.gmail.com", username, password);

            // Nếu kết nối thành công, in ra "Đăng nhập thành công"
            System.out.println("Đăng nhập thành công");

            // Đóng kết nối store
            store.close();
        } catch (Exception e) {
            // Nếu có lỗi, in ra "Đăng nhập thất bại"
            System.out.println("Đăng nhập thất bại");
            e.printStackTrace();
        }

    }
}
