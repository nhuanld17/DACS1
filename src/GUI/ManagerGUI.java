package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.sql.Date;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
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
import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

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
	private JTextField textField_FindName;

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
		panel.setBounds(0, 0, 153, 481);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(SystemColor.desktop);
		tabbedPane.setBounds(152, -24, 813, 505);
		contentPane.add(tabbedPane);
		
		JPanel Tab1 = new JPanel();
		Tab1.setForeground(SystemColor.desktop);
		Tab1.setBackground(new Color(244, 245, 249));
		tabbedPane.addTab("Tab1", null, Tab1, null);
		Tab1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quản Lí Nhân Viên");
		lblNewLabel.setIcon(null);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblNewLabel.setForeground(SystemColor.desktop);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(259, 21, 264, 28);
		Tab1.add(lblNewLabel);
		
		textFieldName = new JTextField();
		textFieldName.setBorder(new LineBorder(new Color(0,0,0),1));
		textFieldName.setForeground(SystemColor.desktop);
		textFieldName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textFieldName.setBounds(10, 98, 176, 28);
		Tab1.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Họ và tên");
		lblNewLabel_1.setForeground(SystemColor.desktop);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 77, 72, 20);
		Tab1.add(lblNewLabel_1);
		
		textField_BirthDate = new JTextField();
		textField_BirthDate.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField_BirthDate.setBorder(new LineBorder(new Color(0,0,0),1));
		textField_BirthDate.setForeground(SystemColor.desktop);
		textField_BirthDate.setColumns(10);
		textField_BirthDate.setBounds(206, 98, 176, 28);
		Tab1.add(textField_BirthDate);
		
		JLabel lblNewLabel_1_1 = new JLabel("Ngày sinh (yyyy-mm-dd)");
		lblNewLabel_1_1.setForeground(SystemColor.desktop);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(206, 79, 176, 17);
		Tab1.add(lblNewLabel_1_1);
		
		textField_Email = new JTextField();
		textField_Email.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField_Email.setBorder(new LineBorder(new Color(0,0,0),1));
		textField_Email.setForeground(SystemColor.desktop);
		textField_Email.setColumns(10);
		textField_Email.setBounds(405, 98, 176, 28);
		Tab1.add(textField_Email);
		
		JLabel lblNewLabel_1_2 = new JLabel("Email");
		lblNewLabel_1_2.setForeground(SystemColor.desktop);
		lblNewLabel_1_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(405, 77, 72, 20);
		Tab1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Giới tính");
		lblNewLabel_1_3.setForeground(SystemColor.desktop);
		lblNewLabel_1_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(210, 137, 72, 20);
		Tab1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Vị trí");
		lblNewLabel_1_1_1.setForeground(SystemColor.desktop);
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(10, 137, 47, 17);
		Tab1.add(lblNewLabel_1_1_1);
		
		rdbtn_Nam = new JRadioButton("Nam");
		rdbtn_Nam.setForeground(SystemColor.desktop);
		rdbtn_Nam.setBackground(new Color(244, 245, 249));
		rdbtn_Nam.setFont(new Font("Segoe UI", Font.BOLD, 18));
		rdbtn_Nam.setBounds(206, 161, 72, 23);
		rdbtn_Nam.setFocusPainted(false);
		Tab1.add(rdbtn_Nam);
		
		rdbtn_Nu = new JRadioButton("Nữ");
		rdbtn_Nu.setForeground(SystemColor.desktop);
		rdbtn_Nu.setBackground(new Color(244, 245, 249));
		rdbtn_Nu.setFont(new Font("Segoe UI", Font.BOLD, 18));
		rdbtn_Nu.setBounds(280, 161, 72, 23);
		rdbtn_Nu.setFocusPainted(false);
		Tab1.add(rdbtn_Nu);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtn_Nam);
		buttonGroup.add(rdbtn_Nu);
		
		comboBox_Position = new JComboBox();
		comboBox_Position.setBorder(new LineBorder(new Color(0,0,0),1));
		comboBox_Position.setBackground(SystemColor.window);
		comboBox_Position.setForeground(SystemColor.desktop);
		comboBox_Position.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		comboBox_Position.setModel(new DefaultComboBoxModel(new String[] {"Tiếp tân", "Bảo vệ", "Lao công"}));
		comboBox_Position.setSelectedIndex(-1);
		comboBox_Position.setBounds(10, 156, 176, 30);
		Tab1.add(comboBox_Position);
		
		JButton btn_Them = new JButton("THÊM");
		btn_Them.setIcon(new ImageIcon(ManagerGUI.class.getResource("/image/icons8-add-24.png")));
		btn_Them.setBorder(new LineBorder(new Color(0,0,0),1));
		btn_Them.setFocusable(false);
		btn_Them.setBackground(new Color(184, 207, 229));
//		btn_Them.setBorderPainted(false);
		btn_Them.addActionListener(actionListener);
		btn_Them.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_Them.setForeground(SystemColor.desktop);
		btn_Them.setBounds(591, 97, 100, 30);
		Tab1.add(btn_Them);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.desktop);
		separator.setBackground(SystemColor.desktop);
		separator.setBounds(10, 197, 793, 2);
		Tab1.add(separator);
		
		table_Employee = new JTable();
		table_Employee.setFillsViewportHeight(true);
		table_Employee.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Họ và tên", "Ngày sinh", "Email", "Giới tính", "Vị trí"
			}
		));
		table_Employee.setForeground(SystemColor.desktop);
		table_Employee.setRowHeight(25);
		table_Employee.setGridColor(new Color(0,0,0));
		JTableHeader employeeHeader = table_Employee.getTableHeader();
		employeeHeader.setFont(new Font("Segoe UI", Font.BOLD, 16));
		employeeHeader.setBackground(new Color(244, 245, 249));
		employeeHeader.setForeground(SystemColor.desktop);
		table_Employee.setBackground(new Color(244,245,249));
		
		table_Employee.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table_Employee.setBounds(10, 238, 793, 228);
		updateEmployeeTable();
//		Tab1.add(table_Employee);
		
		JScrollPane scrollPane = new JScrollPane(table_Employee);
		scrollPane.setBorder(new LineBorder(new Color(0,0,0),2));
		scrollPane.setBounds(10, 238, 793, 228);
		Tab1.add(scrollPane);
		
		JButton btn_Xoa = new JButton("XÓA");
		btn_Xoa.setBorder(new LineBorder(new Color(0,0,0),1));
		btn_Xoa.setFocusable(false);
		btn_Xoa.setBackground(new Color(130, 194, 114));
		btn_Xoa.setIcon(new ImageIcon(ManagerGUI.class.getResource("/image/icons8-delete-24.png")));
		btn_Xoa.setForeground(SystemColor.desktop);
		btn_Xoa.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_Xoa.setBounds(703, 97, 100, 30);
		btn_Xoa.addActionListener(actionListener);
		Tab1.add(btn_Xoa);
		
		JButton btn_Sua = new JButton("SỬA");
		btn_Sua.setFocusable(false);
		btn_Sua.addActionListener(actionListener);
		btn_Sua.setForeground(SystemColor.desktop);
		btn_Sua.setBackground(new Color(255, 165, 0));
		btn_Sua.setBorder(new LineBorder(new Color(0,0,0), 1));
		btn_Sua.setIcon(new ImageIcon(ManagerGUI.class.getResource("/image/icons8-edit-30.png")));
		btn_Sua.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_Sua.setBounds(591, 154, 100, 30);
		Tab1.add(btn_Sua);
		
		JButton btn_CapNhat = new JButton("CẬP NHẬT");
		btn_CapNhat.addActionListener(actionListener);
		btn_CapNhat.setBackground(new Color(255, 165, 0));
		btn_CapNhat.setBorder(new LineBorder(new Color(0,0,0), 1));
		btn_CapNhat.setFocusable(false);
		btn_CapNhat.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_CapNhat.setForeground(SystemColor.desktop);
		btn_CapNhat.setBounds(703, 154, 100, 30);
		Tab1.add(btn_CapNhat);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.desktop);
		separator_1.setBackground(SystemColor.desktop);
		separator_1.setBounds(10, 70, 793, 2);
		Tab1.add(separator_1);
		
		textField_FindName = new JTextField();
		textField_FindName.setForeground(SystemColor.desktop);
		textField_FindName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField_FindName.setColumns(10);
		textField_FindName.setBorder(new LineBorder(new Color(0,0,0),1));
		textField_FindName.setBounds(90, 204, 176, 28);
		Tab1.add(textField_FindName);
		
		JLabel lblNewLabel_1_4 = new JLabel("Họ và tên:");
		lblNewLabel_1_4.setForeground(SystemColor.desktop);
		lblNewLabel_1_4.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(10, 207, 72, 20);
		Tab1.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Vị trí:");
		lblNewLabel_1_1_1_1.setForeground(SystemColor.desktop);
		lblNewLabel_1_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBounds(271, 210, 47, 17);
		Tab1.add(lblNewLabel_1_1_1_1);
		
		JComboBox comboBox_FindByPosition = new JComboBox();
		comboBox_FindByPosition.setModel(new DefaultComboBoxModel(new String[] {"Tiếp tân", "Bảo vệ", "Lao công"}));
		comboBox_FindByPosition.setSelectedIndex(-1);
		comboBox_FindByPosition.setForeground(SystemColor.desktop);
		comboBox_FindByPosition.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		comboBox_FindByPosition.setBorder(new LineBorder(new Color(0,0,0),1));
		comboBox_FindByPosition.setBackground(SystemColor.window);
		comboBox_FindByPosition.setBounds(314, 203, 176, 30);
		Tab1.add(comboBox_FindByPosition);
		
		JButton btnRefresh = new JButton("");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateEmployeeTable();
				textField_FindName.setText(null);
				comboBox_FindByPosition.setSelectedIndex(-1);
			}
		});
		btnRefresh.setIcon(new ImageIcon(ManagerGUI.class.getResource("/image/icons8-refresh-30.png")));
		btnRefresh.setFocusable(false);
		btnRefresh.setBorderPainted(false);
		btnRefresh.setBackground(new Color(244, 245, 249));
		btnRefresh.setBounds(531, 199, 40, 39);
		Tab1.add(btnRefresh);
		
		JButton btnFindName = new JButton("");
		btnFindName.setFocusable(false);
		btnFindName.setBorderPainted(false);
		btnFindName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField_FindName.getText();
				String position = (String) comboBox_FindByPosition.getSelectedItem();
				DefaultTableModel model = (DefaultTableModel) table_Employee.getModel();
				
				if (name.isBlank() && comboBox_FindByPosition.getSelectedItem() == null){
					JOptionPane.showMessageDialog(null, "Hãy nhập thông tin để tìm kiếm");
					return;
				}
				
				if (!name.isBlank() && comboBox_FindByPosition.getSelectedItem() != null) {
					ArrayList<Employee> employees = new ManagerBUS().getEmpByNameAndPosition(name, position);
					clearEmployeeTable();
					for (Employee employee : employees) {
						model.addRow(employee.toObjects());
					}
				}
				
				if (!name.isBlank() && comboBox_FindByPosition.getSelectedItem() == null) {
					ArrayList<Employee> employees = new ManagerBUS().getEmpByName(name);
					clearEmployeeTable();
					for (Employee employee : employees) {
						model.addRow(employee.toObjects());
					}
				}
				
				if (name.isBlank() && comboBox_FindByPosition.getSelectedItem() != null) {
					ArrayList<Employee> employees = new ManagerBUS().getEmpByPosition(position);
					clearEmployeeTable();
					for (Employee employee : employees) {
						model.addRow(employee.toObjects());
					}
				}
			}
		});
		btnFindName.setIcon(new ImageIcon(ManagerGUI.class.getResource("/image/icons8-find-30.png")));
		btnFindName.setBackground(new Color(244, 245, 249));
		btnFindName.setBounds(491, 199, 40, 39);
		Tab1.add(btnFindName);
		
		// Lấy ngày hiện tại
		LocalDate today = LocalDate.now();
		
		// Lấy ngày trong tuần
		String dayOfWeek = today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		
		// Định dạng ngày tháng theo yêu cầu
		String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		
		// Tách chuỗi để lấy ngày riêng biệt
		String[] dateParts = formattedDate.split("-");
		String day = dateParts[0];
		String month = dateParts[1];
		String year = dateParts[2];
		
		JLabel label_ToDate = new JLabel(dayOfWeek+","+day+"/"+month+"/"+year);
		label_ToDate.setForeground(SystemColor.desktop);
		label_ToDate.setFont(new Font("Segoe UI", Font.BOLD, 16));
		label_ToDate.setBounds(594, 0, 209, 28);
		Tab1.add(label_ToDate);
		
		JPanel Tab2 = new JPanel();
		tabbedPane.addTab("Tab2", null, Tab2, null);
		
		JPanel Tab3 = new JPanel();
		tabbedPane.addTab("Tab3", null, Tab3, null);
		
		JPanel Tab4 = new JPanel();
		tabbedPane.addTab("Tab4", null, Tab4, null);
		
		JButton btnTab1 = new JButton("Nhân Viên");
		btnTab1.setHorizontalAlignment(SwingConstants.LEFT);
		btnTab1.setIcon(new ImageIcon(ManagerGUI.class.getResource("/image/icons8-worker-30.png")));
		btnTab1.setFocusable(false);
		btnTab1.setBorderPainted(false);
		btnTab1.setForeground(new Color(244, 245, 249));
		btnTab1.setBackground(new Color(55, 65, 81));
		btnTab1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTab1.setBounds(0, 154, 153, 42);
		panel.add(btnTab1);
		
		JButton btnTab2 = new JButton("Tab2");
		btnTab2.setHorizontalAlignment(SwingConstants.LEFT);
		btnTab2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTab2.setFocusable(false);
		btnTab2.setBorderPainted(false);
		btnTab2.setForeground(new Color(244, 245, 249));
		btnTab2.setBackground(new Color(17, 24, 39));
		btnTab2.setBounds(0, 204, 153, 42);
		panel.add(btnTab2);
		
		JButton btnTab3 = new JButton("Tab3");
		btnTab3.setHorizontalAlignment(SwingConstants.LEFT);
		btnTab3.setBorderPainted(false);
		btnTab3.setFocusable(false);
		btnTab3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTab3.setForeground(new Color(244, 245, 249));
		btnTab3.setBackground(new Color(17, 24, 39));
		btnTab3.setBounds(0, 257, 153, 42);
		panel.add(btnTab3);
		
		JButton btnTab4 = new JButton("Tab4");
		btnTab4.setHorizontalAlignment(SwingConstants.LEFT);
		btnTab4.setBorderPainted(false);
		btnTab4.setFocusable(false);
		btnTab4.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTab4.setForeground(new Color(244, 245, 249));
		btnTab4.setBackground(new Color(17, 24, 39));
		btnTab4.setBounds(0, 310, 153, 42);
		panel.add(btnTab4);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setFocusable(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setBackground(new Color(17,24,39));
				int response = JOptionPane.showConfirmDialog(null, "Bạn có chắn chắn thoát ??");
				if (response == JOptionPane.OK_OPTION) {
					setVisible(false);
					new LoginGUI().setVisible(true);
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(ManagerGUI.class.getResource("/image/icons8-log-out-48.png")));
		btnNewButton.setBackground(new Color(17, 24, 39));
		btnNewButton.setBounds(10, 428, 40, 42);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(ManagerGUI.class.getResource("/image/icons8-admin-80.png")));
		lblNewLabel_2.setBounds(0, 0, 153, 87);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Hello admin !!!");
		lblNewLabel_3.setForeground(new Color(244, 245, 249));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_3.setBounds(0, 79, 153, 27);
		panel.add(lblNewLabel_3);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(244, 245, 249));
		separator_2.setBackground(new Color(244, 245, 249));
		separator_2.setBounds(11, 135, 132, 1);
		panel.add(separator_2);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setForeground(new Color(244, 245, 249));
		separator_2_1.setBackground(new Color(244, 245, 249));
		separator_2_1.setBounds(10, 415, 133, 1);
		panel.add(separator_2_1);
		
		/*----------------- ACTIONLISTENER FOR SIDE BAR BUTTON ---------------*/
		btnTab1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				btnTab1.setBackground(new Color(55, 65, 81));
				btnTab1.setForeground(new Color(244, 245, 249));
				btnTab2.setForeground(new Color(244, 245, 249));
				btnTab2.setBackground(new Color(17, 24, 39));
				btnTab3.setForeground(new Color(244, 245, 249));
				btnTab3.setBackground(new Color(17, 24, 39));
				btnTab4.setForeground(new Color(244, 245, 249));
				btnTab4.setBackground(new Color(17, 24, 39));
			}
		});
		
		btnTab2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
				btnTab2.setBackground(new Color(55, 65, 81));
				btnTab2.setForeground(new Color(244, 245, 249));
				btnTab1.setForeground(new Color(244, 245, 249));
				btnTab1.setBackground(new Color(17, 24, 39));
				btnTab3.setForeground(new Color(244, 245, 249));
				btnTab3.setBackground(new Color(17, 24, 39));
				btnTab4.setForeground(new Color(244, 245, 249));
				btnTab4.setBackground(new Color(17, 24, 39));
			}
		});
		
		btnTab3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				btnTab3.setBackground(new Color(55, 65, 81));
				btnTab3.setForeground(new Color(244, 245, 249));
				btnTab2.setForeground(new Color(244, 245, 249));
				btnTab2.setBackground(new Color(17, 24, 39));
				btnTab1.setForeground(new Color(244, 245, 249));
				btnTab1.setBackground(new Color(17, 24, 39));
				btnTab4.setForeground(new Color(244, 245, 249));
				btnTab4.setBackground(new Color(17, 24, 39));
			}
		});
		
		btnTab4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
				btnTab4.setBackground(new Color(55, 65, 81));
				btnTab4.setForeground(new Color(244, 245, 249));
				btnTab3.setForeground(new Color(244, 245, 249));
				btnTab3.setBackground(new Color(17, 24, 39));
				btnTab2.setForeground(new Color(244, 245, 249));
				btnTab2.setBackground(new Color(17, 24, 39));
				btnTab1.setForeground(new Color(244, 245, 249));
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
	
	// Phương thức loại bỏ các dấu tiếng việt khi tạo UserName:
    public String removeAccents(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }
	
	public String generateUserName(String string) {
		string = removeAccents(string).toLowerCase().replaceAll("\\s", "");
		
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
		
		String nameTest = textFieldName.getText();
		for (int i = 0; i < nameTest.length(); i++) {
			if (nameTest.charAt(i) >= '0' && nameTest.charAt(i) <= '9') {
				JOptionPane.showMessageDialog(null, "Tên không hợp lệ");
				return;
			}
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
			new ManagerBUS().addEmployee(employee);
		}else {
			Employee employee = new Employee(name, birthDate, email, sex, position, null, null);
			new ManagerBUS().addEmployee(employee);
		}
		updateEmployeeTable();
	}

	public void deleteEmployee() {
		DefaultTableModel model = (DefaultTableModel) table_Employee.getModel();
		int rowIndex = table_Employee.getSelectedRow();
		
		if (!table_Employee.isRowSelected(rowIndex)) {
			JOptionPane.showMessageDialog(null, "Chọn 1 nhân viên để xóa");
			return;
		}
		int id = Integer.valueOf((String)model.getValueAt(rowIndex, 0));
		new ManagerBUS().deleteEmployee(id);
		updateEmployeeTable();
	}

	public void setForm() {
		DefaultTableModel model = (DefaultTableModel) table_Employee.getModel();
		int rowIndex = table_Employee.getSelectedRow();
		
		if (!table_Employee.isRowSelected(rowIndex)) {
			JOptionPane.showMessageDialog(null, "Chọn 1 nhân viên để chỉnh sửa");	
			return;
		}
		
		int id = Integer.valueOf((String) model.getValueAt(rowIndex, 0));
		String oldName = String.valueOf(model.getValueAt(rowIndex, 1));
		Date oldBirthDate = (Date)model.getValueAt(rowIndex, 2);
		String oldEmail = String.valueOf(model.getValueAt(rowIndex, 3));
		String oldSex = String.valueOf(model.getValueAt(rowIndex, 4));
		String position = String.valueOf(model.getValueAt(rowIndex, 5));
		
		textFieldName.setText(oldName);
		textField_BirthDate.setText(oldBirthDate.toString());
		textField_Email.setText(oldEmail);
		comboBox_Position.setSelectedItem(position);
		if (oldSex.equals("Nam")) {
			rdbtn_Nam.setSelected(true);
		}else if (oldSex.equals("Nữ")) {
			rdbtn_Nu.setSelected(true);
		}
	}

	public void updateEmployee() {
		DefaultTableModel model = (DefaultTableModel) table_Employee.getModel();
		int rowIndex = table_Employee.getSelectedRow();
		
		if (!table_Employee.isRowSelected(rowIndex)) {
			JOptionPane.showMessageDialog(null, "Chọn 1 nhân viên để cập nhật");
			return;
		}
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
		
		int id = Integer.valueOf((String) model.getValueAt(rowIndex, 0));
		String newName = textFieldName.getText();
		Date newBirthDate = Date.valueOf(textField_BirthDate.getText());
		String newEmail = textField_Email.getText();
		String newPosition = String.valueOf(comboBox_Position.getSelectedItem());
		boolean newSex = true;
		
		if (rdbtn_Nam.isSelected()) {
			newSex = true;
		}else if (rdbtn_Nu.isSelected()) {
			newSex = false;
		}
		if (newPosition.equals("Tiếp tân")) {
			String userName = generateUserName(newName);
			String passWord = generatePassWord();
			Employee newEmployee = new Employee(id, newName, newBirthDate, newEmail, newSex, newPosition, userName, passWord);
			new ManagerBUS().updateEmployee(newEmployee);
		}else {
			Employee newEmployee = new Employee(id, newName, newBirthDate, newEmail, newSex, newPosition, null, null);
			new ManagerBUS().updateEmployee(newEmployee);
		}
		updateEmployeeTable();
	}
}
