package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BUS.ManagerBUS;
import CONTROLLER.ManagerController;
import DAO.ManagerDAO;
import DTO.Employee;

import javax.swing.JScrollPane;

public class ManagerGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textField_BirthDate;
	private JTextField textField_Email;
	private JTable table_Employee;
	private JRadioButton rdbtn_Nam;
	private JRadioButton rdbtn_Nu;
	private JComboBox comboBox_Position;
	private ButtonGroup buttonGroup;
	private String emailRegex = "^[a-zA-Z0-9._%+-]+@gmail.com$";
	private String birthDateRegex = "^(?:(?:19|20)\\d\\d)-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01]|(?:0[1-9]|1\\d|2[0-8]))|(?:19|20)\\d\\d-(0[1-9]|1[0-2])-(29|30)(?:-0[1-9]|-1[0-9]|-2[0-8])|(?:19|20)(?:0[48]|[2468][048]|[13579][26])-02-29$";
	private String nameRegex = "^[a-zA-Z ]{1,50}$";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerGUI frame = new ManagerGUI();
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
	public ManagerGUI() {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (ClassNotFoundException e) {
//		} catch (InstantiationException e) {
//		} catch (IllegalAccessException e) {
//		} catch (UnsupportedLookAndFeelException e) {
//		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 520);
		contentPane = new JPanel();
		setResizable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ActionListener actionListener = new ManagerController(this);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(17, 24, 39));
		panel.setBounds(0, 0, 147, 481);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(147, -24, 818, 505);
		contentPane.add(tabbedPane);
		
		JPanel Tab1 = new JPanel();
		Tab1.setForeground(SystemColor.desktop);
		Tab1.setBackground(new Color(244, 245, 249));
		tabbedPane.addTab("Tab1", null, Tab1, null);
		Tab1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quản Lí Nhân Viên");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel.setForeground(SystemColor.desktop);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(287, 11, 216, 28);
		Tab1.add(lblNewLabel);
		
		textFieldName = new JTextField();
		textFieldName.setBorder(new LineBorder(new Color(0,0,0),1));
		textFieldName.setForeground(SystemColor.desktop);
		textFieldName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textFieldName.setBounds(10, 80, 176, 28);
		Tab1.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Họ và tên");
		lblNewLabel_1.setForeground(SystemColor.desktop);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 59, 72, 20);
		Tab1.add(lblNewLabel_1);
		
		textField_BirthDate = new JTextField();
		textField_BirthDate.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField_BirthDate.setBorder(new LineBorder(new Color(0,0,0),1));
		textField_BirthDate.setForeground(SystemColor.desktop);
		textField_BirthDate.setColumns(10);
		textField_BirthDate.setBounds(206, 80, 176, 28);
		Tab1.add(textField_BirthDate);
		
		JLabel lblNewLabel_1_1 = new JLabel("Ngày sinh (yyyy-mm-dd)");
		lblNewLabel_1_1.setForeground(SystemColor.desktop);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(206, 61, 176, 17);
		Tab1.add(lblNewLabel_1_1);
		
		textField_Email = new JTextField();
		textField_Email.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField_Email.setBorder(new LineBorder(new Color(0,0,0),1));
		textField_Email.setForeground(SystemColor.desktop);
		textField_Email.setColumns(10);
		textField_Email.setBounds(405, 80, 176, 28);
		Tab1.add(textField_Email);
		
		JLabel lblNewLabel_1_2 = new JLabel("Email");
		lblNewLabel_1_2.setForeground(SystemColor.desktop);
		lblNewLabel_1_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(405, 59, 72, 20);
		Tab1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Giới tính");
		lblNewLabel_1_3.setForeground(SystemColor.desktop);
		lblNewLabel_1_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(210, 119, 72, 20);
		Tab1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Vị trí");
		lblNewLabel_1_1_1.setForeground(SystemColor.desktop);
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(10, 119, 79, 17);
		Tab1.add(lblNewLabel_1_1_1);
		
		rdbtn_Nam = new JRadioButton("Nam");
		rdbtn_Nam.setForeground(SystemColor.desktop);
		rdbtn_Nam.setBackground(new Color(244, 245, 249));
		rdbtn_Nam.setFont(new Font("Segoe UI", Font.BOLD, 16));
		rdbtn_Nam.setBounds(206, 143, 72, 23);
		Tab1.add(rdbtn_Nam);
		
		rdbtn_Nu = new JRadioButton("Nữ");
		rdbtn_Nu.setForeground(SystemColor.desktop);
		rdbtn_Nu.setBackground(new Color(244, 245, 249));
		rdbtn_Nu.setFont(new Font("Segoe UI", Font.BOLD, 16));
		rdbtn_Nu.setBounds(280, 143, 72, 23);
		Tab1.add(rdbtn_Nu);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtn_Nam);
		buttonGroup.add(rdbtn_Nu);
		
		comboBox_Position = new JComboBox();
		comboBox_Position.setBorder(new LineBorder(new Color(255,255,255),1));
		comboBox_Position.setBackground(SystemColor.window);
		comboBox_Position.setForeground(SystemColor.desktop);
		comboBox_Position.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		comboBox_Position.setModel(new DefaultComboBoxModel(new String[] {"Tiếp tân", "Bảo vệ", "Lao công"}));
		comboBox_Position.setSelectedIndex(-1);
		comboBox_Position.setBounds(10, 138, 176, 30);
		Tab1.add(comboBox_Position);
		
		JButton btn_Them = new JButton("THÊM");
		btn_Them.setIcon(new ImageIcon(ManagerGUI.class.getResource("/image/icons8-add-24.png")));
		btn_Them.setBorder(new LineBorder(new Color(0,0,0),1));
		btn_Them.setFocusable(false);
		btn_Them.setBackground(new Color(46, 204, 113));
//		btn_Them.setBorderPainted(false);
		btn_Them.addActionListener(actionListener);
		btn_Them.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_Them.setForeground(SystemColor.desktop);
		btn_Them.setBounds(591, 79, 102, 30);
		Tab1.add(btn_Them);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.desktop);
		separator.setBackground(SystemColor.desktop);
		separator.setBounds(10, 179, 793, 2);
		Tab1.add(separator);
		
		table_Employee = new JTable();
		table_Employee.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Họ và tên", "Ngày sinh", "Email", "Giới tính", "Vị trí"
			}
		));
		table_Employee.setForeground(SystemColor.desktop);
		table_Employee.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table_Employee.setBounds(10, 221, 793, 245);
		updateEmployeeTable();
		Tab1.add(table_Employee);
		
		JScrollPane scrollPane = new JScrollPane(table_Employee);
		scrollPane.setBounds(10, 221, 793, 245);
		Tab1.add(scrollPane);
		
		JPanel Tab2 = new JPanel();
		tabbedPane.addTab("Tab2", null, Tab2, null);
		
		JPanel Tab3 = new JPanel();
		tabbedPane.addTab("Tab3", null, Tab3, null);
		
		JPanel Tab4 = new JPanel();
		tabbedPane.addTab("Tab4", null, Tab4, null);
		
		JButton btnTab1 = new JButton("QL nhân viên");
		btnTab1.setFocusable(false);
		btnTab1.setBorderPainted(false);
		btnTab1.setForeground(new Color(156, 163, 175));
		btnTab1.setBackground(new Color(17, 24, 39));
		btnTab1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTab1.setBounds(0, 118, 147, 42);
		panel.add(btnTab1);
		
		JButton btnTab2 = new JButton("Tab2");
		btnTab2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTab2.setFocusable(false);
		btnTab2.setBorderPainted(false);
		btnTab2.setForeground(new Color(156, 163, 175));
		btnTab2.setBackground(new Color(17, 24, 39));
		btnTab2.setBounds(0, 168, 147, 42);
//		btnTab2.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				tabbedPane.setSelectedIndex(1);
//			}
//		});
		panel.add(btnTab2);
		
		JButton btnTab3 = new JButton("Tab3");
		btnTab3.setBorderPainted(false);
		btnTab3.setFocusable(false);
		btnTab3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTab3.setForeground(new Color(156, 163, 175));
		btnTab3.setBackground(new Color(17, 24, 39));
		btnTab3.setBounds(0, 221, 147, 42);
//		btnTab3.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				tabbedPane.setSelectedIndex(2);
//			}
//		});
		panel.add(btnTab3);
		
		JButton btnTab4 = new JButton("Tab4");
		btnTab4.setBorderPainted(false);
		btnTab4.setFocusable(false);
		btnTab4.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTab4.setForeground(new Color(156, 163, 175));
		btnTab4.setBackground(new Color(17, 24, 39));
		btnTab4.setBounds(0, 274, 147, 42);
//		btnTab4.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				tabbedPane.setSelectedIndex(3);
//			}
//		});
		panel.add(btnTab4);
		
		/*----------------- ACTIONLISTENER FOR SIDE BAR BUTTON ---------------*/
		btnTab1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				btnTab1.setBackground(new Color(55, 65, 81));
				btnTab1.setForeground(new Color(229, 231, 210));
				btnTab2.setForeground(new Color(156, 163, 175));
				btnTab2.setBackground(new Color(17, 24, 39));
				btnTab3.setForeground(new Color(156, 163, 175));
				btnTab3.setBackground(new Color(17, 24, 39));
				btnTab4.setForeground(new Color(156, 163, 175));
				btnTab4.setBackground(new Color(17, 24, 39));
			}
		});
		
		btnTab2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
				btnTab2.setBackground(new Color(55, 65, 81));
				btnTab2.setForeground(new Color(229, 231, 210));
				btnTab1.setForeground(new Color(156, 163, 175));
				btnTab1.setBackground(new Color(17, 24, 39));
				btnTab3.setForeground(new Color(156, 163, 175));
				btnTab3.setBackground(new Color(17, 24, 39));
				btnTab4.setForeground(new Color(156, 163, 175));
				btnTab4.setBackground(new Color(17, 24, 39));
			}
		});
		
		btnTab3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				btnTab3.setBackground(new Color(55, 65, 81));
				btnTab3.setForeground(new Color(229, 231, 210));
				btnTab2.setForeground(new Color(156, 163, 175));
				btnTab2.setBackground(new Color(17, 24, 39));
				btnTab1.setForeground(new Color(156, 163, 175));
				btnTab1.setBackground(new Color(17, 24, 39));
				btnTab4.setForeground(new Color(156, 163, 175));
				btnTab4.setBackground(new Color(17, 24, 39));
			}
		});
		
		btnTab4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
				btnTab4.setBackground(new Color(55, 65, 81));
				btnTab4.setForeground(new Color(229, 231, 210));
				btnTab3.setForeground(new Color(156, 163, 175));
				btnTab3.setBackground(new Color(17, 24, 39));
				btnTab2.setForeground(new Color(156, 163, 175));
				btnTab2.setBackground(new Color(17, 24, 39));
				btnTab1.setForeground(new Color(156, 163, 175));
				btnTab1.setBackground(new Color(17, 24, 39));
			}
		});
		/*----------------- END ACTION LISTENER FOR SIDE BAR BUTTON ---------------*/
		
	}
	
	public void updateEmployeeTable() {
		clearEmployeeTable();
		DefaultTableModel model = (DefaultTableModel) table_Employee.getModel();
		
		ArrayList<Employee> employees = new ManagerBUS().listEmployees();
		for (Employee employee : employees) {
			model.addRow(employee.toObjects());
		}
	}
	
	public void setEmptyForm() {
		textFieldName.setText(null);
		textField_Email.setText(null);
		textField_BirthDate.setText(null);
		comboBox_Position.setSelectedIndex(-1);
		buttonGroup.clearSelection();
	}
	
	public void clearEmployeeTable() {
		DefaultTableModel model = (DefaultTableModel)table_Employee.getModel();
		int rowCount = model.getRowCount();
		
		for (int i = rowCount-1; i >= 0; i--) {
			model.removeRow(i);
		}
	}
	
	public boolean isDuplicateUserName(String userName) {
		ArrayList<String> userNames = new ManagerBUS().getListUserName();
		for (String string : userNames) {
			if (userName.equals(string)) {
				return true;
			}
		}
		return false;
	}
	
	public String generateUserName(String string) {
		string = string.toLowerCase().replaceAll("\\s", "");
		
		// Tạo số ngẫu nhiên có 3 chữ số
		int randomNumber = new Random().nextInt(900) + 100; // Số từ 100 đến 999
		 // Tạo user name;
		String res = string + randomNumber;
		while (isDuplicateUserName(res)) {
			// Tạo số ngẫu nhiên có 3 chữ số
			randomNumber = new Random().nextInt(900) + 100; // Số từ 100 đến 999
			res = string + randomNumber;
		}
		return res;
	}
	
	public String generatePassWord() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();

        // Tạo 8 kí tự ngẫu nhiên từ chuỗi characters
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
	}
	
	public void addEmployee() {
		if (textFieldName.getText().isBlank() || textField_BirthDate.getText().isBlank() || textField_Email.getText().isBlank() || (!rdbtn_Nam.isSelected() && !rdbtn_Nu.isSelected()) || comboBox_Position.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Hãy điền đầy đủ thông tin");
			return;
		}
		

		if (!textField_BirthDate.getText().toString().matches(birthDateRegex)) {
			JOptionPane.showMessageDialog(null, "Định dạng ngày sinh không đúng, nhập ngày sinh theo định dạng (yyyy-mm-dd)");
			return;
		}
		if (!textField_Email.getText().matches(emailRegex)) {
			JOptionPane.showMessageDialog(null, "Email không hợp lệ");
			return;
		}
		String name = textFieldName.getText();
		Date birthDate = Date.valueOf(textField_BirthDate.getText());
		String email = textField_Email.getText();
		String position  = String.valueOf(comboBox_Position.getSelectedItem());
		boolean sex = true;
		if(rdbtn_Nam.isSelected()) {
			sex = true;
		}else if (rdbtn_Nu.isSelected()) {
			sex = false;
		}
		

		
		if (position.equals("Tiếp tân")) {
			String userName = generateUserName(name);
			String passWord = generatePassWord();
			
			Employee employee = new Employee(name, birthDate, email, sex, position, userName, passWord);
			new ManagerDAO().addEmployeeWithAccount(employee);
		}else {
			Employee employee = new Employee(name, birthDate, email, sex, position);
			
			new ManagerDAO().addEmployeeWithOutAccount(employee);
		}
		updateEmployeeTable();
	}
}
