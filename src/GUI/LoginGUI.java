package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import DAO.LoginDao;
import DTO.Account;
import DTO.Manager;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userNameTextField;
	private JPasswordField passWordTextField;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 480);
		contentPane = new JPanel();
		setResizable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
//		panel.setBorder(new LineBorder(new Color(132, 42, 203), 2));
		panel.setBackground(new Color(242, 242, 242));
		panel.setBounds(0, 0, 390, 441);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setForeground(new Color(51, 51, 51));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(109, 27, 151, 30);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(LoginGUI.class.getResource("/image/icons8-admin-48_.png")));
		lblNewLabel_1.setBounds(119, 61, 141, 71);
		panel.add(lblNewLabel_1);
		
		userNameTextField = new JTextField();

		userNameTextField.setForeground(new Color(85, 85, 85));
		userNameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		userNameTextField.setBounds(48, 188, 280, 45);
		userNameTextField.setBorder(new LineBorder(new Color(173, 173, 173), 2));
		panel.add(userNameTextField);
		userNameTextField.setColumns(10);
		
		passWordTextField = new JPasswordField();
		passWordTextField.setForeground(new Color(85, 85, 85));
		passWordTextField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		passWordTextField.setBorder(new LineBorder(new Color(173, 173, 173), 2));
		passWordTextField.setColumns(10);
		passWordTextField.setBounds(48, 269, 280, 45);
		panel.add(passWordTextField);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(LoginGUI.class.getResource("/image/icons8-global-48.png")));
		lblNewLabel_2.setBounds(10, 92, 69, 80);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(LoginGUI.class.getResource("/image/icons8-login-50.png")));
		lblNewLabel_3.setBounds(350, 224, 69, 51);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("User Name");
		lblNewLabel_4.setForeground(new Color(132, 42, 203));
		lblNewLabel_4.setBackground(SystemColor.window);
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel_4.setBounds(48, 163, 82, 20);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Pass Word");
		lblNewLabel_5.setForeground(new Color(132, 42, 203));
		lblNewLabel_5.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel_5.setBackground(SystemColor.window);
		lblNewLabel_5.setBounds(48, 244, 82, 20);
		panel.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Manager Login");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setBackground(new Color(132, 42, 203));
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnNewButton.setBounds(48, 335, 280, 40);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String username = userNameTextField.getText();
				String password = passWordTextField.getText();
				
				Manager manager = new Manager(username, password);
				if (new LoginDao().isValidManager(manager)) {
					 System.out.println("Đăng nhập manager thành công");
					 new ManagerGUI().setVisible(true);
					 setVisible(false);
				}else {
					System.out.println("Đăng nhập manager thất bại");
				}
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Employee Login");
		btnNewButton_1.setForeground(SystemColor.text);
		btnNewButton_1.setBackground(new Color(132, 42, 203));
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnNewButton_1.setBounds(48, 385, 280, 40);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = userNameTextField.getText();
				String passWord = passWordTextField.getText();
				
				Account account = new Account(userName, passWord);
				
				if (new LoginDao().isAccountExist(account)) {
					int id = new LoginDao().getIdByUserName(userName);
					new EmployeeGUI(id).setVisible(true);
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
				}
			}
		});
		panel.add(btnNewButton_1);
		
		label = new JLabel("New label");
		label.setIcon(new ImageIcon(LoginGUI.class.getResource("/image/bg-img.jpg")));
		label.setBounds(177, 0, 643, 441);
		contentPane.add(label);
	}
}
