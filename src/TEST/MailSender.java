package TEST;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.awt.event.ActionEvent;

public class MailSender extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField email_Field;
	private JPasswordField passwordField;
	private JTextField emailReceiver;
	private JTextField title_tf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MailSender frame = new MailSender();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MailSender() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(70, 182, 524, 193);
		contentPane.add(textArea);
		
		email_Field = new JTextField();
		email_Field.setBounds(70, 28, 253, 37);
		contentPane.add(email_Field);
		email_Field.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(341, 28, 253, 37);
		contentPane.add(passwordField);
		
		emailReceiver = new JTextField();
		emailReceiver.setBounds(341, 80, 253, 37);
		contentPane.add(emailReceiver);
		emailReceiver.setColumns(10);
		
		title_tf = new JTextField();
		title_tf.setBounds(70, 132, 524, 37);
		contentPane.add(title_tf);
		title_tf.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String emailSender = email_Field.getText().trim();
				String password = passwordField.getText().trim();
				
				String receiver = emailReceiver.getText().trim();
				String title = title_tf.getText().trim();
				String content = textArea.getText();
				
				if (emailSender.isEmpty() || password.isEmpty() || receiver.isEmpty() || title.isEmpty() || content.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin");
					return;
				}
				
				try {
					new SendMail(emailSender, password, receiver, title, content);
				} catch (UnsupportedEncodingException | MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Lỗi ! Hãy kiểm tra lại tài khoản và mật khẩu \n"+"Chú ý mật khẩu là mật khẩu ứng dụng");
				}
			}
		});
		btnNewButton.setBounds(505, 386, 89, 23);
		contentPane.add(btnNewButton);
	}
}
