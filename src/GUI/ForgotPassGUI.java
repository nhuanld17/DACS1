package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BUS.ManagerBUS;

import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		
		
		JLabel lblNewLabel = new JLabel("Password Recovery");
		lblNewLabel.setForeground(SystemColor.desktop);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 434, 65);
		panel.add(lblNewLabel);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setForeground(SystemColor.desktop);
		textFieldEmail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textFieldEmail.setBounds(96, 101, 245, 37);
		panel.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		label_Email = new JLabel("Enter your email");
		label_Email.setForeground(SystemColor.desktop);
		label_Email.setFont(new Font("Segoe UI", Font.BOLD, 14));
		label_Email.setBounds(96, 76, 162, 22);
		panel.add(label_Email);
		
		JLabel label_OTPCode = new JLabel("OTP code");
		label_OTPCode.setForeground(SystemColor.desktop);
		label_OTPCode.setFont(new Font("Segoe UI", Font.BOLD, 14));
		label_OTPCode.setBounds(96, 147, 162, 22);
		panel.add(label_OTPCode);
		label_OTPCode.show(false);
		
		textField_OTPCode = new JTextField();
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
		textField_NewPass.setForeground(SystemColor.desktop);
		textField_NewPass.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_NewPass.setColumns(10);
		textField_NewPass.setBounds(96, 241, 245, 37);
		panel.add(textField_NewPass);
		textField_NewPass.show(false);
		
		JButton btn_SendOTP = new JButton(" SEND OTP CODE");
		btn_SendOTP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = textFieldEmail.getText();
				if (!new ManagerBUS().isValidEmail(email)) {
					JOptionPane.showMessageDialog(null, "Email này không thuộc về tài khoản nào");
					return;
				}
				
				label_OTPCode.show(true);
				textField_OTPCode.show(true);
				btn_SendOTP.show(false);
				btn_CheckOTP.show(true);
				
				
			}
		});
		btn_SendOTP.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btn_SendOTP.setForeground(SystemColor.desktop);
		btn_SendOTP.setBounds(133, 160, 173, 30);
		panel.add(btn_SendOTP);
		
		btnNewPassword = new JButton("Change password");
		btnNewPassword.setForeground(SystemColor.desktop);
		btnNewPassword.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnNewPassword.setBounds(133, 289, 173, 30);
		panel.add(btnNewPassword);
		btnNewPassword.show(false);
		
		btn_CheckOTP = new JButton("CHECK OTP");
		btn_CheckOTP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_CheckOTP.setForeground(SystemColor.desktop);
		btn_CheckOTP.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btn_CheckOTP.setBounds(133, 220, 173, 30);
		panel.add(btn_CheckOTP);
		btn_CheckOTP.show(false);
	}
}
