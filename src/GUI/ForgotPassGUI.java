package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import BUS.ManagerBUS;
import SENDMAIL.SendMailOTP;

import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class ForgotPassGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JLabel label_Email;
	private JTextField textField_OTPCode;
	private JLabel label_newPass;
	private JTextField textField_NewPass;
	private JButton btn_CheckOTP;
	private JButton btnNewPassword;
	private String OTP;
	private String passwordregex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$";
	private JTextField textField_ID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassGUI frame = new ForgotPassGUI();
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
	public ForgotPassGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(244, 245, 249));
		panel.setForeground(SystemColor.desktop);
		panel.setBounds(0, 0, 434, 342);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel lblEnterYourId = new JLabel("Enter your ID");
		lblEnterYourId.setForeground(SystemColor.desktop);
		lblEnterYourId.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblEnterYourId.setBounds(247, 76, 162, 22);
		panel.add(lblEnterYourId);
		
		textField_ID = new JTextField();
		textField_ID.setBorder(new LineBorder(new Color(173, 173, 173), 2));
		textField_ID.setForeground(SystemColor.desktop);
		textField_ID.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_ID.setColumns(10);
		textField_ID.setBounds(247, 101, 177, 37);
		panel.add(textField_ID);
		
		JLabel lblNewLabel = new JLabel("Password Recovery");
		lblNewLabel.setForeground(SystemColor.desktop);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(100, 0, 251, 65);
		panel.add(lblNewLabel);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBorder(new LineBorder(new Color(173, 173, 173), 2));
		textFieldEmail.setForeground(SystemColor.desktop);
		textFieldEmail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textFieldEmail.setBounds(23, 101, 214, 37);
		panel.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		label_Email = new JLabel("Enter your email");
		label_Email.setForeground(SystemColor.desktop);
		label_Email.setFont(new Font("Segoe UI", Font.BOLD, 14));
		label_Email.setBounds(23, 76, 162, 22);
		panel.add(label_Email);
		
		JLabel label_OTPCode = new JLabel("OTP code");
		label_OTPCode.setForeground(SystemColor.desktop);
		label_OTPCode.setFont(new Font("Segoe UI", Font.BOLD, 14));
		label_OTPCode.setBounds(96, 147, 162, 22);
		panel.add(label_OTPCode);
		label_OTPCode.show(false);
		
		textField_OTPCode = new JTextField();
		textField_OTPCode.setBorder(new LineBorder(new Color(173, 173, 173), 2));
		textField_OTPCode.setForeground(SystemColor.desktop);
		textField_OTPCode.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_OTPCode.setColumns(10);
		textField_OTPCode.setBounds(96, 171, 245, 37);
		panel.add(textField_OTPCode);
		textField_OTPCode.show(false);
		
		label_newPass = new JLabel("Enter new password");
		label_newPass.setForeground(SystemColor.desktop);
		label_newPass.setFont(new Font("Segoe UI", Font.BOLD, 14));
		label_newPass.setBounds(96, 218, 162, 22);
		panel.add(label_newPass);
		label_newPass.show(false);
		
		textField_NewPass = new JTextField();
		textField_NewPass.setBorder(new LineBorder(new Color(173, 173, 173), 2));
		textField_NewPass.setForeground(SystemColor.desktop);
		textField_NewPass.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_NewPass.setColumns(10);
		textField_NewPass.setBounds(96, 241, 245, 37);
		panel.add(textField_NewPass);
		textField_NewPass.show(false);
		
		JButton btn_SendOTP = new JButton("SEND OTP CODE");
		btn_SendOTP.setFocusable(false);
		btn_SendOTP.setBackground(new Color(17, 24, 39));
		btn_SendOTP.setBorderPainted(false);
		btn_SendOTP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_ID.getText().isBlank() || textFieldEmail.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
					return;
				}
				
				String email = textFieldEmail.getText();
				int id = Integer.valueOf(textField_ID.getText());
				
				if (!new ManagerBUS().isValidEmail(email, id)) {
					JOptionPane.showMessageDialog(null, "Email không hợp lệ");
					return;
				}
				

				
				label_OTPCode.show(true);
				textField_OTPCode.show(true);
				btn_SendOTP.show(false);
				btn_CheckOTP.show(true);
				OTP = generateOTP();
				
				new SendMailOTP(email, OTP);
			}
		});
		btn_SendOTP.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btn_SendOTP.setForeground(new Color(244, 245, 249));
		btn_SendOTP.setBounds(23, 161, 173, 30);
		panel.add(btn_SendOTP);
		

		
		btn_CheckOTP = new JButton("CHECK OTP");
		btn_CheckOTP.setFocusable(false);
		btn_CheckOTP.setBackground(new Color(17, 24, 39));
		btn_CheckOTP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (textField_OTPCode.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập mã OTP");
					return;
				}
				
				if (!textField_OTPCode.getText().equals(OTP)) {
					JOptionPane.showMessageDialog(null, "Mã OTP không đúng");
					return;
				}
	
				
				textField_NewPass.show(true);
				label_newPass.show(true);
				btnNewPassword.show(true);
				btn_CheckOTP.show(false);
			}
		});
		btn_CheckOTP.setForeground(new Color(244, 245, 249));
		btn_CheckOTP.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btn_CheckOTP.setBounds(23, 219, 173, 30);
		panel.add(btn_CheckOTP);
		btn_CheckOTP.show(false);
		
		btnNewPassword = new JButton("UPDATE");
		btnNewPassword.setFocusable(false);
		btnNewPassword.setBackground(new Color(17, 24, 39));
		btnNewPassword.setForeground(new Color(244, 245, 249));
		btnNewPassword.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnNewPassword.setBounds(23, 289, 173, 30);
		btnNewPassword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField_NewPass.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu mới");
					return;
				}
				if (!textField_NewPass.getText().matches(passwordregex)) {
					JOptionPane.showMessageDialog(null, "Mật khẩu gồm 6 kí tự bao gồm chữ thường, chữ hoa, và chữ số");
					return;
				}
				String password = textField_NewPass.getText();
				String email = textFieldEmail.getText();
				int id = Integer.valueOf(textField_ID.getText());
				new ManagerBUS().changePassWord(password, email, id);
				JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
			}
		});
		panel.add(btnNewPassword);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginGUI().setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBackground(new Color(244, 245, 249));
		btnNewButton.setIcon(new ImageIcon(ForgotPassGUI.class.getResource("/image/icons8-log-out-48.png")));
		btnNewButton.setBounds(0, 0, 55, 44);
		panel.add(btnNewButton);
		btnNewPassword.show(false);
	}
	
	public String generateOTP() {
		Random random = new Random();
		StringBuilder otp = new StringBuilder();
		
		for (int i = 0; i < 6; i++) {
			int digit = random.nextInt(10);
			otp.append(digit);
		}
		
		return otp.toString();
	}
}
