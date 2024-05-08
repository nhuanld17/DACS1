package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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


public class LoginGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userNameTextField;
	private JPasswordField passWordTextField;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel label;
	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;

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
		setBounds(100, 100, 836, 503);
		contentPane = new JPanel();
		setResizable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
//		panel.setBorder(new LineBorder(new Color(132, 42, 203), 2));
		panel.setBackground(new Color(244, 245, 249));
		panel.setBounds(0, 0, 390, 464);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setForeground(SystemColor.desktop);
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

		userNameTextField.setForeground(SystemColor.desktop);
		userNameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		userNameTextField.setBounds(48, 168, 280, 45);
		userNameTextField.setBorder(new LineBorder(new Color(173, 173, 173), 2));
		userNameTextField.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {

				userNameTextField.setBorder(new LineBorder(new Color(173, 173, 173), 2));
			}
			public void focusGained(FocusEvent e) {
				userNameTextField.setBorder(new LineBorder(new Color(17, 24, 39), 2));
			}
		});
		panel.add(userNameTextField);
		userNameTextField.setColumns(10);
		
		passWordTextField = new JPasswordField();
		passWordTextField.setForeground(SystemColor.desktop);
		passWordTextField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		passWordTextField.setBorder(new LineBorder(new Color(173, 173, 173), 2));
		passWordTextField.setColumns(10);
		passWordTextField.setBounds(48, 249, 280, 45);
		passWordTextField.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				passWordTextField.setBorder(new LineBorder(new Color(173, 173, 173), 2));
			}
			public void focusGained(FocusEvent e) {
				passWordTextField.setBorder(new LineBorder(new Color(17, 24, 39), 2));
			}
		});
		panel.add(passWordTextField);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(LoginGUI.class.getResource("/image/icons8-global-48.png")));
		lblNewLabel_2.setBounds(10, 72, 69, 80);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(LoginGUI.class.getResource("/image/icons8-login-50.png")));
		lblNewLabel_3.setBounds(350, 224, 69, 51);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("User Name");
		lblNewLabel_4.setForeground(SystemColor.desktop);
		lblNewLabel_4.setBackground(SystemColor.window);
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel_4.setBounds(48, 143, 82, 20);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Pass Word");
		lblNewLabel_5.setForeground(SystemColor.desktop);
		lblNewLabel_5.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel_5.setBackground(SystemColor.window);
		lblNewLabel_5.setBounds(48, 224, 82, 20);
		panel.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Manager Login");
		btnNewButton.setFocusable(false);
//		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(new Color(244, 245, 249));
		btnNewButton.setBackground(new Color(17, 24, 39));
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton.setBounds(48, 315, 280, 40);
		btnNewButton.setBorder(new LineBorder(new Color(17, 24, 39),2));
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
		btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnNewButton.setBackground(new Color(244,245,249));
            	btnNewButton.setForeground(new Color(17, 24, 39));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnNewButton.setBackground(new Color(17, 24, 39));  // Màu gốc
            	btnNewButton.setForeground(new Color(244,245,249));
            }
        });
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Employee Login");
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setForeground(new Color(244, 245, 249));
		btnNewButton_1.setBackground(new Color(17, 24, 39));
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton_1.setBounds(48, 365, 280, 40);
//		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBorder(new LineBorder(new Color(17, 24, 39),2));
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnNewButton_1.setBackground(new Color(244,245,249));
            	btnNewButton_1.setForeground(new Color(17, 24, 39));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnNewButton_1.setBackground(new Color(17, 24, 39));
            	btnNewButton_1.setForeground(new Color(244,245,249));
            }
        });
		panel.add(btnNewButton_1);
		
		JButton btnForgotPass = new JButton("Forgot password ??");
		btnForgotPass.setFocusable(false);
		btnForgotPass.setBorderPainted(false);
		btnForgotPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ForgotPassGUI().setVisible(true);
				setVisible(false);
			}
		});
		btnForgotPass.setBackground(new Color(244, 245, 249));
		btnForgotPass.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnForgotPass.setForeground(Color.BLUE);
		btnForgotPass.setBounds(48, 425, 280, 23);
		panel.add(btnForgotPass);
		
		label = new JLabel("New label");
		label.setIcon(new ImageIcon(LoginGUI.class.getResource("/image/bg-img.jpg")));
		label.setBounds(177, 0, 643, 464);
		contentPane.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		try {
//			socket = new Socket("localhost",9999);
//			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			writer = new PrintWriter(socket.getOutputStream(), true);
//		} catch (UnknownHostException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
	
		
		String userName = userNameTextField.getText();
		String passWord = passWordTextField.getText();

		if (new LoginDao().isAccountExist(new Account(userName, passWord))) {
			try {
				socket = new Socket("localhost", 9999);
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream(), true);
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			new Thread((() -> {
				writer.println(userName);
				
				String message;
				
				try {
					message = reader.readLine();
					
					if (message != null && message.equals("DUPLICATE_LOGIN")) {
						System.out.println(message);
						JOptionPane.showMessageDialog(null, "TK đã được đăng nhập");
//						socket.close();
//						reader.close();
//						writer.close();
						return;
					} else if (message != null && message.equals("OKE")) {
						System.out.println(message);
						int id = new LoginDao().getIdByUserName(userName);
						new EmployeeGUI(id, userName, socket, reader, writer).setVisible(true);
						setVisible(false);
//						socket.close();
//						reader.close();
//						writer.close();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			})).start();
		}else {
			JOptionPane.showMessageDialog(null, "FAILED");
			return;
		}
		
//		new Thread(() -> {
//			String message;
//			
//			Account account = new Account(userName, passWord);
//			
//			if (new LoginDao().isAccountExist(account)) {
//				try {
//					message = reader.readLine();
//					if (message != null && message.equals("DUPLICATE_LOGIN")) {
//						System.out.println(message);
//						JOptionPane.showMessageDialog(null, "Tài khoản này đã được đăng nhập");
//					}else if (message != null && message.equals("OKE")) {
//						System.out.println(message);
//						int id = new LoginDao().getIdByUserName(userName);
//						new EmployeeGUI(id, userName).setVisible(true);
//						setVisible(false);
//						
//					}
//				} catch (Exception e2) {
//					// TODO: handle exception
//				}
//			}else {
//				JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
//			}
//		}).start();;
	}
}
