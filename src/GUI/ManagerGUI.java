package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import javax.mail.MessagingException;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import BUS.BillBUS;
import BUS.ManagerBUS;
import CONTROLLER.ManagerController;
import DTO.Employee;
import SENDMAIL.SendMail;
import java.awt.Cursor;

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
	private String emailRegex = "(?i)[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}";
	private String birthDateRegex = "^(?:(?:19|20)\\d\\d)-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01]|(?:0[1-9]|1\\d|2[0-8]))|(?:19|20)\\d\\d-(0[1-9]|1[0-2])-(29|30)(?:-0[1-9]|-1[0-9]|-2[0-8])|(?:19|20)(?:0[48]|[2468][048]|[13579][26])-02-29$";
	private String nameRegex = "^[a-zA-Z ]{1,50}$";
	private JTextField textField_FindName;
	private JTextField textField_EmailSender;
	private JPasswordField passwordField;
	private JTextField textField_EmailReceiver;
	private JTextField textField_Email_Title;
	private JTextField textField_ListFile;
	private JTable salaryTable;
	private int maleNumber;
	private int femaleNumber;
	private double maleRate;
	private double femaleRate;
	private JLabel label_Rate_Nu;
	private JLabel lbl_Rate_Nam;
	private JPanel panel_chart_gioitinh;
	private DefaultPieDataset datasetGioiTinh;
	private JFreeChart chartGioiTinh;
	private PiePlot plot;
	private TextTitle title;
	private ChartPanel gioiTinhPanel;
	private DecimalFormat decimalFormat = new DecimalFormat("#.00");
	private DefaultPieDataset datasetPosition;
	private JFreeChart positionChart;
	private PiePlot plot1;
	private TextTitle title2;
	private ChartPanel positionPanel;
	private JLabel labl_Rate_Tiep_Tan;
	private JLabel labl_Rate_Bao_Ve;
	private JLabel labl_Rate_Lao_Cong;
	private int tiepTanNumber;
	private int baoVeNumber;
	private int laoCongNumber;
	private double tiepTanRate;
	private double baoVeRate;
	private double laoCongRate;
	private JPanel panel_chart_position;
	private JComboBox<String> comboBox_Year;
	private JComboBox<String> comboBox_Month;
	private int currentYear;
	private Month currentMonth;
	private int currentMonthValue;
	private JButton btnNewButton_1;
	private double currentDayMoney;
	private int currentDayUsers;
	private int currentDayBillAbated;
	private JLabel label_compare_money;
	private JLabel label_compare_users;
	private JLabel label_compare_bill_abated;
	private double yesterdayMoney;
	private DecimalFormat df = new DecimalFormat("#.##");
	private int yesterdayUsers;
	private int yesterdayBillAbated;
	private JLabel label_TodayMoney;
	private JLabel label_TodayBillAbated;
	private JLabel label_TodayUsers;

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
		df.setRoundingMode(RoundingMode.HALF_UP);
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
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblNewLabel.setForeground(SystemColor.desktop);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(259, 21, 264, 28);
		Tab1.add(lblNewLabel);

		textFieldName = new JTextField();
		textFieldName.setBorder(new LineBorder(new Color(0, 0, 0), 1));
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
		textField_BirthDate.setBorder(new LineBorder(new Color(0, 0, 0), 1));
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
		textField_Email.setBorder(new LineBorder(new Color(0, 0, 0), 1));
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
		comboBox_Position.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		comboBox_Position.setBackground(SystemColor.window);
		comboBox_Position.setForeground(SystemColor.desktop);
		comboBox_Position.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		comboBox_Position.setModel(new DefaultComboBoxModel(new String[] { "Tiếp tân", "Bảo vệ", "Lao công" }));
		comboBox_Position.setSelectedIndex(-1);
		comboBox_Position.setBounds(10, 156, 176, 30);
		Tab1.add(comboBox_Position);

		JButton btn_Them = new JButton("THÊM");
		btn_Them.setIcon(new ImageIcon(ManagerGUI.class.getResource("/image/icons8-add-24.png")));
		btn_Them.setBorder(new LineBorder(new Color(0, 0, 0), 1));
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
		table_Employee.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Họ và tên", "Ngày sinh", "Email", "Giới tính", "Vị trí" }));
		table_Employee.setForeground(SystemColor.desktop);
		table_Employee.setRowHeight(25);
		table_Employee.setGridColor(new Color(0, 0, 0));
		JTableHeader employeeHeader = table_Employee.getTableHeader();
		employeeHeader.setFont(new Font("Segoe UI", Font.BOLD, 16));
		employeeHeader.setBackground(new Color(244, 245, 249));
		employeeHeader.setForeground(SystemColor.desktop);
		table_Employee.setBackground(new Color(244, 245, 249));

		table_Employee.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table_Employee.setBounds(10, 238, 793, 228);
		updateEmployeeTable();
//		Tab1.add(table_Employee);

		JScrollPane scrollPane = new JScrollPane(table_Employee);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		scrollPane.setBounds(10, 238, 793, 228);
		Tab1.add(scrollPane);

		JButton btn_Xoa = new JButton("XÓA");
		btn_Xoa.setBorder(new LineBorder(new Color(0, 0, 0), 1));
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
		btn_Sua.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		btn_Sua.setIcon(new ImageIcon(ManagerGUI.class.getResource("/image/icons8-edit-30.png")));
		btn_Sua.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_Sua.setBounds(591, 154, 100, 30);
		Tab1.add(btn_Sua);

		JButton btn_CapNhat = new JButton("CẬP NHẬT");
		btn_CapNhat.addActionListener(actionListener);
		btn_CapNhat.setBackground(new Color(255, 165, 0));
		btn_CapNhat.setBorder(new LineBorder(new Color(0, 0, 0), 1));
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
		textField_FindName.setBorder(new LineBorder(new Color(0, 0, 0), 1));
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
		comboBox_FindByPosition.setModel(new DefaultComboBoxModel(new String[] { "Tiếp tân", "Bảo vệ", "Lao công" }));
		comboBox_FindByPosition.setSelectedIndex(-1);
		comboBox_FindByPosition.setForeground(SystemColor.desktop);
		comboBox_FindByPosition.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		comboBox_FindByPosition.setBorder(new LineBorder(new Color(0, 0, 0), 1));
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

				if (name.isBlank() && comboBox_FindByPosition.getSelectedItem() == null) {
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

		JLabel label_ToDate = new JLabel(dayOfWeek + "," + day + "/" + month + "/" + year);
		label_ToDate.setForeground(SystemColor.desktop);
		label_ToDate.setFont(new Font("Segoe UI", Font.BOLD, 16));
		label_ToDate.setBounds(594, 0, 209, 28);
		Tab1.add(label_ToDate);

		JPanel Tab2 = new JPanel();
		Tab2.setBackground(new Color(240, 240, 240));
		tabbedPane.addTab("Tab2", null, Tab2, null);
		Tab2.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(17, 205, 239));
		panel_1.setBounds(0, 0, 808, 477);
		
		RoundedPanel roundedPanel = new RoundedPanel(20, 10);
		roundedPanel.setBackground(SystemColor.window);
		roundedPanel.setBounds(25, 11, 240, 150);
		panel_1.add(roundedPanel);
		roundedPanel.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(ManagerGUI.class.getResource("/image/icons8-revenue-60.png")));
		lblNewLabel_6.setBounds(165, 5, 65, 62);
		roundedPanel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("TODAY'S MONEY");
		lblNewLabel_7.setForeground(Color.DARK_GRAY);
		lblNewLabel_7.setBackground(SystemColor.window);
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_7.setBounds(10, 11, 150, 47);
		roundedPanel.add(lblNewLabel_7);
		
		currentDayMoney = new BillBUS().getCurrentRevenue();
		currentDayMoney = currentDayMoney / 1000000;

		
		label_TodayMoney = new JLabel(currentDayMoney+"M");
		label_TodayMoney.setText(currentDayMoney+"M");
		label_TodayMoney.setForeground(new Color(17, 24, 39));
		label_TodayMoney.setBackground(new Color(17, 24, 39));
		label_TodayMoney.setFont(new Font("Segoe UI", Font.BOLD, 25));
		label_TodayMoney.setBounds(10, 55, 145, 40);
		roundedPanel.add(label_TodayMoney);
		
		label_compare_money = new JLabel("");
		label_compare_money.setForeground(new Color(128, 0, 0));
		label_compare_money.setFont(new Font("Segoe UI", Font.BOLD, 18));
		label_compare_money.setBounds(10, 100, 209, 31);
		roundedPanel.add(label_compare_money);
		
		yesterdayMoney = new BillBUS().getYesterdayRevenue();
		yesterdayMoney = yesterdayMoney / 1000000;
//		if (currentDayMoney >= yesterdayMoney) {
//			double percent = ((currentDayMoney - yesterdayMoney) / yesterdayMoney)*100;
//			label_compare_money.setText("+"+percent+"% since yesterday");
//		}else {
//			
//		}
		
		if (yesterdayMoney == 0) {
			label_compare_money.setText("");
		}else if (currentDayMoney >= yesterdayMoney) {
			double percent = ((currentDayMoney - yesterdayMoney) / yesterdayMoney)*100;
			label_compare_money.setText("+"+df.format(percent)+"% since yesterday");
		}else if (currentDayMoney < yesterdayMoney) {
			double percent = ((yesterdayMoney - currentDayMoney) / yesterdayMoney)*100;
			label_compare_money.setText("-"+df.format(percent)+"% since yesterday");
		}
		
//		System.out.println("Current: "+currentDayMoney);
//		System.out.println("Yester: "+yesterdayMoney);
		
		JScrollPane scrollPane_3 = new JScrollPane(panel_1);
		panel_1.setLayout(null);
		
		RoundedPanel roundedPanel_1 = new RoundedPanel(20, 10);
		roundedPanel_1.setBackground(SystemColor.window);
		roundedPanel_1.setBounds(286, 11, 240, 150);
		panel_1.add(roundedPanel_1);
		roundedPanel_1.setLayout(null);
		
		JLabel lblNewLabel_6_1 = new JLabel("");
		lblNewLabel_6_1.setIcon(new ImageIcon(ManagerGUI.class.getResource("/image/icons8-user-60.png")));
		lblNewLabel_6_1.setBounds(167, 5, 60, 62);
		roundedPanel_1.add(lblNewLabel_6_1);
		
		currentDayUsers = new BillBUS().getNumberOfCurrentUsers();

		
		JLabel lblNewLabel_7_1 = new JLabel("TODAY'S USERS");
		lblNewLabel_7_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_7_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_7_1.setBackground(SystemColor.window);
		lblNewLabel_7_1.setBounds(10, 11, 150, 47);
		roundedPanel_1.add(lblNewLabel_7_1);
		
		label_TodayUsers = new JLabel(currentDayUsers+" USERS");
		label_TodayUsers.setText(currentDayUsers+" USERS");
		label_TodayUsers.setForeground(new Color(17, 24, 39));
		label_TodayUsers.setFont(new Font("Segoe UI", Font.BOLD, 25));
		label_TodayUsers.setBackground(SystemColor.window);
		label_TodayUsers.setBounds(10, 55, 145, 40);
		roundedPanel_1.add(label_TodayUsers);
		
		label_compare_users = new JLabel("");
		label_compare_users.setForeground(new Color(128, 0, 0));
		label_compare_users.setFont(new Font("Segoe UI", Font.BOLD, 18));
		label_compare_users.setBounds(10, 100, 209, 31);
		roundedPanel_1.add(label_compare_users);
		
		yesterdayUsers = new BillBUS().getTotalUserYesterday();
		if (yesterdayUsers == 0) {
			label_compare_users.setText("");
		}else if(currentDayUsers >= yesterdayUsers) {
			double percent = (currentDayUsers - yesterdayUsers)*100/yesterdayUsers;
			label_compare_users.setText("+"+df.format(percent)+"% since yesterday");
		}else if (currentDayUsers < yesterdayUsers) {
			double percent = (yesterdayUsers - currentDayUsers)*100/yesterdayUsers;
			label_compare_users.setText("-"+df.format(percent)+"% since yesterday");
		}
		
		RoundedPanel roundedPanel_2 = new RoundedPanel(20, 10);
		roundedPanel_2.setBackground(SystemColor.window);
		roundedPanel_2.setBounds(550, 11, 240, 150);
		panel_1.add(roundedPanel_2);
		roundedPanel_2.setLayout(null);
		
		JLabel lblNewLabel_6_1_1 = new JLabel("");
		lblNewLabel_6_1_1.setIcon(new ImageIcon(ManagerGUI.class.getResource("/image/icons8-bill-70.png")));
		lblNewLabel_6_1_1.setBounds(147, 11, 67, 62);
		roundedPanel_2.add(lblNewLabel_6_1_1);
		
		JLabel lblNewLabel_7_1_1 = new JLabel("BILL ABATED");
		lblNewLabel_7_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_7_1_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_7_1_1.setBackground(SystemColor.window);
		lblNewLabel_7_1_1.setBounds(10, 11, 127, 47);
		roundedPanel_2.add(lblNewLabel_7_1_1);
		
		currentDayBillAbated = new BillBUS().getCurrentNumberOfBillAbated();
		
		label_TodayBillAbated = new JLabel(currentDayBillAbated+" BILLS");
		label_TodayBillAbated.setText(currentDayBillAbated+" BILLS");
		label_TodayBillAbated.setForeground(new Color(17, 24, 39));
		label_TodayBillAbated.setFont(new Font("Segoe UI", Font.BOLD, 25));
		label_TodayBillAbated.setBackground(new Color(17, 24, 39));
		label_TodayBillAbated.setBounds(10, 55, 145, 40);
		roundedPanel_2.add(label_TodayBillAbated);
		
		label_compare_bill_abated = new JLabel("");
		label_compare_bill_abated.setForeground(new Color(128, 0, 0));
		label_compare_bill_abated.setFont(new Font("Segoe UI", Font.BOLD, 18));
		label_compare_bill_abated.setBounds(10, 100, 209, 31);
		roundedPanel_2.add(label_compare_bill_abated);
		
		RoundedPanel roundedPanel_1_1 = new RoundedPanel(20, 5);
		roundedPanel_1_1.setLayout(null);
		roundedPanel_1_1.setBackground(SystemColor.window);
		roundedPanel_1_1.setBounds(681, 172, 109, 55);
		panel_1.add(roundedPanel_1_1);
		
		JLabel lblNewLabel_6_1_2 = new JLabel("");
		lblNewLabel_6_1_2.setBounds(167, 5, 60, 62);
		roundedPanel_1_1.add(lblNewLabel_6_1_2);
		
		JButton btnNewButton_2 = new JButton("RELOAD");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadDashBoard();
			}
		});
		btnNewButton_2.setForeground(new Color(17, 24, 39));
		btnNewButton_2.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
		btnNewButton_2.setFocusable(false);
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setBounds(5, 3, 89, 39);
		roundedPanel_1_1.add(btnNewButton_2);
		scrollPane_3.setBounds(0, 0, 808, 477);
		Tab2.add(scrollPane_3);
		
		yesterdayBillAbated = new BillBUS().getYesterdayBillAbated();
		if (yesterdayBillAbated == 0) {
			label_compare_bill_abated.setText("");
		}else if(currentDayBillAbated >= yesterdayBillAbated) {
			double percent = (currentDayBillAbated - yesterdayBillAbated)*100/ yesterdayBillAbated;
			label_compare_bill_abated.setText("+"+df.format(percent)+"% since yesterday");
		}else if(currentDayBillAbated < yesterdayBillAbated) {
			double percent = (yesterdayBillAbated - currentDayBillAbated)*100/ yesterdayBillAbated;
			label_compare_bill_abated.setText("-"+df.format(percent)+"% since yesterday");
		}
		

		datasetGioiTinh = createDatasetGioiTinh();

		chartGioiTinh = ChartFactory.createPieChart("Tỉ lệ nam nữ", // Tiêu đề biểu đồ
				datasetGioiTinh, // Tập dữ liệu
				false, // Hiển thị legend
				false, false);

		plot = (PiePlot) chartGioiTinh.getPlot();
		plot.setSectionPaint("Nam", new Color(255, 127, 14));
		plot.setSectionPaint("Nữ", new Color(31, 119, 180));
		plot.setBackgroundPaint(new Color(254, 245, 249));
		plot.setShadowPaint(null);
		chartGioiTinh.setBackgroundPaint(new Color(254, 245, 249));

		// Đổi font cho title
		title = chartGioiTinh.getTitle();
		title.setFont(new Font("Segoe UI", Font.BOLD, 20));

		maleNumber = new ManagerBUS().getMaleNumber();
		femaleNumber = new ManagerBUS().getFemaleNumber();

		maleRate = (double) maleNumber / (maleNumber + femaleNumber);
		femaleRate = (double) femaleNumber / (maleNumber + femaleNumber);

		datasetPosition = createDatasetPosition();

		positionChart = ChartFactory.createPieChart("Vị trí", datasetPosition, false, false, false);

		plot1 = (PiePlot) positionChart.getPlot();
		plot1.setSectionPaint("Tiếp tân", new Color(255, 127, 14));
		plot1.setSectionPaint("Bảo vệ", new Color(31, 119, 180));
		plot1.setSectionPaint("Lao công", new Color(43, 160, 45));
		plot1.setBackgroundPaint(new Color(254, 245, 249));
		plot1.setShadowPaint(null);
		positionChart.setBackgroundPaint(new Color(254, 245, 249));
		title2 = positionChart.getTitle();
		title2.setFont(new Font("Segoe UI", Font.BOLD, 20));

		tiepTanNumber = new ManagerBUS().getTiepTanNumber();
		baoVeNumber = new ManagerBUS().getBaoVeNumber();
		laoCongNumber = new ManagerBUS().getLaoCongNumber();

		tiepTanRate = (double) tiepTanNumber / (tiepTanNumber + baoVeNumber + laoCongNumber);
		baoVeRate = (double) baoVeNumber / (tiepTanNumber + baoVeNumber + laoCongNumber);
		laoCongRate = (double) laoCongNumber / (tiepTanNumber + baoVeNumber + laoCongNumber);

		// Lấy ngày hiện tại
		LocalDate currentDate = LocalDate.now();

		// Lấy tháng hiện tại (Theo kiểu số nguyên)
		currentMonthValue = currentDate.getMonthValue();

		JPanel tab3 = new JPanel();
		tab3.setBackground(new Color(254, 245, 249));
		tabbedPane.addTab("Tab3", null, tab3, null);
		tab3.setLayout(null);

		salaryTable = new JTable();
		salaryTable.setFillsViewportHeight(true);
		salaryTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Họ và tên", "Vị trí", "Số lượng khách hàng" }));
		salaryTable.setForeground(SystemColor.desktop);
		salaryTable.setRowHeight(25);
		salaryTable.setGridColor(new Color(0, 0, 0));
		JTableHeader salaryHeader = salaryTable.getTableHeader();
		salaryHeader = salaryTable.getTableHeader();
		salaryHeader.setFont(new Font("Segoe UI", Font.BOLD, 16));
		salaryHeader.setBackground(new Color(244, 245, 249));
		salaryHeader.setForeground(SystemColor.desktop);
		updateTableSalary();

		salaryTable.setBackground(new Color(244, 245, 249));
		salaryTable.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		salaryTable.setBackground(new Color(244, 245, 249));
		salaryTable.setBounds(37, 167, 493, 267);

		JScrollPane scrollPane_2 = new JScrollPane(salaryTable);
		scrollPane_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		scrollPane_2.setBounds(5, 261, 685, 210);
		tab3.add(scrollPane_2);

		panel_chart_gioitinh = new JPanel();
		panel_chart_gioitinh.setBackground(new Color(254, 245, 249));
		panel_chart_gioitinh.setBounds(0, 0, 313, 220);
		tab3.add(panel_chart_gioitinh);
		panel_chart_gioitinh.setLayout(new BorderLayout(0, 0));

		gioiTinhPanel = new ChartPanel(chartGioiTinh);
		gioiTinhPanel.setPreferredSize(new Dimension(367, 210));
		panel_chart_gioitinh.add(gioiTinhPanel, BorderLayout.CENTER);
		gioiTinhPanel.setLayout(null);

		panel_chart_position = new JPanel();
		panel_chart_position.setBackground(new Color(254, 245, 249));
		panel_chart_position.setBounds(461, 0, 347, 220);
		tab3.add(panel_chart_position);
		panel_chart_position.setLayout(new BorderLayout());

		positionPanel = new ChartPanel(positionChart);
		positionPanel.setPreferredSize(new Dimension(367, 210));
		panel_chart_position.add(positionPanel);
		positionPanel.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Tháng");
		lblNewLabel_4.setBackground(new Color(254, 245, 249));
		lblNewLabel_4.setForeground(SystemColor.desktop);
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_4.setBounds(413, 232, 54, 26);
		tab3.add(lblNewLabel_4);

		// Khởi tạo comboBox_Month
		comboBox_Month = new JComboBox<>();
		comboBox_Month.setModel(new DefaultComboBoxModel<>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		comboBox_Month.setForeground(SystemColor.desktop);
		comboBox_Month.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		comboBox_Month.setBackground(new Color(254, 245, 249));
		comboBox_Month.setBounds(474, 231, 76, 29);
		comboBox_Month.setSelectedItem(currentMonthValue + "");
		tab3.add(comboBox_Month);
		comboBox_Month.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTableByTime();
			}
		});

		JLabel lblNewLabel_5 = new JLabel("Năm");
		lblNewLabel_5.setForeground(SystemColor.desktop);
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_5.setBounds(563, 232, 44, 26);
		tab3.add(lblNewLabel_5);

		currentYear = currentDate.getYear();

		comboBox_Year = new JComboBox();

		comboBox_Year.setModel(new DefaultComboBoxModel(
		new String[] { "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
		comboBox_Year.setForeground(SystemColor.desktop);
		comboBox_Year.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		comboBox_Year.setBackground(new Color(254, 245, 249));
		comboBox_Year.setBounds(608, 231, 82, 29);
		comboBox_Year.setSelectedItem(currentYear + "");
		comboBox_Year.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTableByTime();
			}
		});

		tab3.add(comboBox_Year);

		lbl_Rate_Nam = new JLabel("Nam: " + decimalFormat.format(maleRate * 100) + "%");
		lbl_Rate_Nam.setBounds(336, 46, 90, 28);
		tab3.add(lbl_Rate_Nam);
		lbl_Rate_Nam.setForeground(Color.BLACK);
		lbl_Rate_Nam.setFont(new Font("Segoe UI", Font.BOLD, 14));

		label_Rate_Nu = new JLabel("Nữ: " + decimalFormat.format(femaleRate * 100) + "%");
		label_Rate_Nu.setBounds(336, 75, 90, 28);
		tab3.add(label_Rate_Nu);
		label_Rate_Nu.setForeground(Color.BLACK);
		label_Rate_Nu.setFont(new Font("Segoe UI", Font.BOLD, 14));

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(SystemColor.desktop);
		separator_3.setBackground(SystemColor.desktop);
		separator_3.setBounds(323, 111, 128, 2);
		tab3.add(separator_3);

		labl_Rate_Tiep_Tan = new JLabel("Tiếp tân:");
		labl_Rate_Tiep_Tan.setBounds(336, 113, 115, 28);
		tab3.add(labl_Rate_Tiep_Tan);
		labl_Rate_Tiep_Tan.setForeground(Color.BLACK);
		labl_Rate_Tiep_Tan.setFont(new Font("Segoe UI", Font.BOLD, 14));

		labl_Rate_Tiep_Tan.setText("Tiếp tân: " + decimalFormat.format(tiepTanRate * 100) + "%");

		labl_Rate_Bao_Ve = new JLabel("Bảo vệ:");
		labl_Rate_Bao_Ve.setBounds(336, 140, 115, 28);
		tab3.add(labl_Rate_Bao_Ve);
		labl_Rate_Bao_Ve.setForeground(Color.BLACK);
		labl_Rate_Bao_Ve.setFont(new Font("Segoe UI", Font.BOLD, 14));
		labl_Rate_Bao_Ve.setText("Bảo vệ: " + decimalFormat.format(baoVeRate * 100) + "%");

		labl_Rate_Lao_Cong = new JLabel("Lao công:");
		labl_Rate_Lao_Cong.setBounds(336, 170, 115, 28);
		tab3.add(labl_Rate_Lao_Cong);
		labl_Rate_Lao_Cong.setForeground(Color.BLACK);
		labl_Rate_Lao_Cong.setFont(new Font("Segoe UI", Font.BOLD, 14));
		labl_Rate_Lao_Cong.setText("Lao công:" + decimalFormat.format(laoCongRate * 100) + "%");

		btnNewButton_1 = new JButton("Chi tiết");
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBorder(new LineBorder(new Color(17, 24, 39), 2));
		btnNewButton_1.setForeground(new Color(17, 24, 39));
		btnNewButton_1.setBackground(new Color(254, 245, 249));
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnNewButton_1.setBounds(700, 261, 98, 35);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) salaryTable.getModel();
				int rowIndex = salaryTable.getSelectedRow();
				
				if (!salaryTable.isRowSelected(rowIndex)) {
					JOptionPane.showMessageDialog(null, "Hãy chọn 1 nhân viên để xem chi tiết");
					return;
				}
				
				int id = (Integer) model.getValueAt(rowIndex, 0);
				new NumberOfCustomerServedChart(id).setVisible(true);
			}
		});
		tab3.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Tính lương");
		btnNewButton_1_1.setForeground(new Color(17, 24, 39));
		btnNewButton_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnNewButton_1_1.setFocusable(false);
		btnNewButton_1_1.setBorder(new LineBorder(new Color(17, 24, 39), 2));
		btnNewButton_1_1.setBackground(new Color(254, 245, 249));
		btnNewButton_1_1.setBounds(700, 307, 98, 35);
		tab3.add(btnNewButton_1_1);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) salaryTable.getModel();
				int rowIndex = salaryTable.getSelectedRow();
				
				if (!salaryTable.isRowSelected(rowIndex)) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 nhân viên để tính lương");
					return;
				}
				
				int month = Integer.valueOf((String) comboBox_Month.getSelectedItem());
				int id = (int) model.getValueAt(rowIndex, 0);
				String name = String.valueOf(model.getValueAt(rowIndex, 1));
				String position = String.valueOf(model.getValueAt(rowIndex, 2));
				int numberCustomer = (int) model.getValueAt(rowIndex, 3);
				
				new SalaryGUI(month, name, id, position, numberCustomer).setVisible(true);;
			}
		});

		JPanel Tab4 = new JPanel();
		tabbedPane.addTab("Tab4", null, Tab4, null);
		Tab4.setLayout(null);

		JLabel lblNewLabel_8 = new JLabel("EMAIL AREA");
		lblNewLabel_8.setIcon(new ImageIcon(ManagerGUI.class.getResource("/image/icons8-gmail-30.png")));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setForeground(SystemColor.desktop);
		lblNewLabel_8.setFont(new Font("Segoe Script", Font.BOLD, 18));
		lblNewLabel_8.setBounds(21, 0, 210, 44);
		Tab4.add(lblNewLabel_8);

		textField_EmailSender = new JTextField();
		textField_EmailSender.setForeground(SystemColor.desktop);
		textField_EmailSender.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_EmailSender.setColumns(10);
		textField_EmailSender.setBorder(new LineBorder(SystemColor.desktop));
		textField_EmailSender.setBounds(46, 68, 246, 35);
		Tab4.add(textField_EmailSender);

		JLabel lblNewLabel_9 = new JLabel("From email:");
		lblNewLabel_9.setForeground(SystemColor.desktop);
		lblNewLabel_9.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_9.setBounds(46, 38, 246, 26);
		Tab4.add(lblNewLabel_9);

		JLabel lblNewLabel_9_1 = new JLabel("Password:");
		lblNewLabel_9_1.setForeground(SystemColor.desktop);
		lblNewLabel_9_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_9_1.setBounds(352, 38, 246, 26);
		Tab4.add(lblNewLabel_9_1);

		passwordField = new JPasswordField();
		passwordField.setForeground(SystemColor.desktop);
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		passwordField.setBorder(new LineBorder(SystemColor.desktop));
		passwordField.setBounds(352, 68, 246, 35);
		Tab4.add(passwordField);

		textField_EmailReceiver = new JTextField();
		textField_EmailReceiver.setForeground(SystemColor.desktop);
		textField_EmailReceiver.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_EmailReceiver.setColumns(10);
		textField_EmailReceiver.setBorder(new LineBorder(SystemColor.desktop));
		textField_EmailReceiver.setBounds(46, 131, 246, 35);
		Tab4.add(textField_EmailReceiver);

		JLabel lblNewLabel_9_2 = new JLabel("To:");
		lblNewLabel_9_2.setForeground(SystemColor.desktop);
		lblNewLabel_9_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_9_2.setBounds(46, 104, 246, 26);
		Tab4.add(lblNewLabel_9_2);

		JLabel lblNewLabel_9_2_1 = new JLabel("Title:");
		lblNewLabel_9_2_1.setForeground(SystemColor.desktop);
		lblNewLabel_9_2_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_9_2_1.setBounds(46, 166, 246, 26);
		Tab4.add(lblNewLabel_9_2_1);

		textField_Email_Title = new JTextField();
		textField_Email_Title.setForeground(SystemColor.desktop);
		textField_Email_Title.setFont(new Font("Segoe UI", Font.BOLD, 18));
		textField_Email_Title.setColumns(10);
		textField_Email_Title.setBorder(new LineBorder(SystemColor.desktop));
		textField_Email_Title.setBounds(46, 193, 553, 35);
		Tab4.add(textField_Email_Title);

		JLabel lblNewLabel_9_2_1_1 = new JLabel("Content:");
		lblNewLabel_9_2_1_1.setForeground(SystemColor.desktop);
		lblNewLabel_9_2_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_9_2_1_1.setBounds(46, 227, 246, 26);
		Tab4.add(lblNewLabel_9_2_1_1);

		JTextArea textArea_Email_Content = new JTextArea();
		textArea_Email_Content.setForeground(SystemColor.desktop);
		textArea_Email_Content.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textArea_Email_Content.setBounds(46, 254, 551, 129);

		textField_ListFile = new JTextField();
		textField_ListFile.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_ListFile.setColumns(10);
		textField_ListFile.setBorder(new LineBorder(SystemColor.desktop));
		textField_ListFile.setBounds(45, 394, 523, 35);
		Tab4.add(textField_ListFile);

		JButton btn_fileAttachment = new JButton("");
		btn_fileAttachment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int respone = fileChooser.showOpenDialog(null);

				if (respone == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String currentListFile = textField_ListFile.getText();

					if (currentListFile.isEmpty()) {
						textField_ListFile.setText(selectedFile.getAbsolutePath());
					} else {
						currentListFile += "-" + selectedFile.getAbsolutePath();
						textField_ListFile.setText(currentListFile);
					}
				}
			}
		});
		btn_fileAttachment.setIcon(new ImageIcon(ManagerGUI.class.getResource("/image/icons8-attachment-24.png")));
		btn_fileAttachment.setFocusable(false);
		btn_fileAttachment.setBorderPainted(false);
		btn_fileAttachment.setBackground(new Color(240, 240, 240));
		btn_fileAttachment.setBounds(569, 394, 31, 35);
		Tab4.add(btn_fileAttachment);

		JButton btnSendMail = new JButton("SEND");
		btnSendMail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSendMail.setBackground(new Color(244, 245, 249));
				btnSendMail.setForeground(new Color(17, 24, 39));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSendMail.setBackground(new Color(17, 24, 39));
				btnSendMail.setForeground(new Color(244, 245, 249));
			}
		});
		btnSendMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String emailSender = textField_EmailSender.getText();
				String password = passwordField.getText();

				String receiver = textField_EmailReceiver.getText();
				String title = textField_Email_Title.getText();
				String content = textArea_Email_Content.getText();
				String paths = textField_ListFile.getText().trim();

				ArrayList<File> files = new ArrayList<>();
				if (!paths.isEmpty()) {
					String[] listPath = paths.split("\\-");
					for (String path : listPath) {
						File file = new File(path.trim());
						if (file.exists()) {
							files.add(file);
						} else {
							JOptionPane.showMessageDialog(null, "Tệp không tồn tại: " + path);
							return;
						}
					}
				}

				if (emailSender.isEmpty() || password.isEmpty() || receiver.isEmpty() || content.isEmpty()
						|| title.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
					return;
				}

				if (emailSender.equals(receiver)) {
					JOptionPane.showMessageDialog(null, "Email người gửi và Email người nhận không được trùng nhau");
					return;
				}

				try {
					new SendMail(emailSender, password, receiver, title, content, files);
					JOptionPane.showMessageDialog(null, "Gửi thành công");
				} catch (UnsupportedEncodingException | MessagingException e1) {
					JOptionPane.showMessageDialog(null,
							"Lỗi ! \n Hãy kiểm tra lại tài khoản Gmail và mật khẩu \n Chú ý mật khẩu Gmail là mật khẩu ứng dụng");
					System.out.println(e1.getMessage());
					return;
				}
			}
		});
		btnSendMail.setBackground(new Color(17, 24, 39));
		btnSendMail.setForeground(new Color(254, 245, 249));
		btnSendMail.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnSendMail.setBorder(new LineBorder(new Color(17, 24, 39), 2));
		btnSendMail.setBounds(46, 436, 102, 35);
		Tab4.add(btnSendMail);

		JScrollPane scrollPane_1 = new JScrollPane(textArea_Email_Content);
		scrollPane_1.setBorder(new LineBorder(SystemColor.desktop));
		scrollPane_1.setBounds(46, 254, 552, 129);
		Tab4.add(scrollPane_1);

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

		JButton btnTab2 = new JButton("Tổng quan");
		btnTab2.setIcon(new ImageIcon(ManagerGUI.class.getResource("/image/icons8-overview-30.png")));
		btnTab2.setHorizontalAlignment(SwingConstants.LEFT);
		btnTab2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTab2.setFocusable(false);
		btnTab2.setBorderPainted(false);
		btnTab2.setForeground(new Color(244, 245, 249));
		btnTab2.setBackground(new Color(17, 24, 39));
		btnTab2.setBounds(0, 204, 153, 42);
		panel.add(btnTab2);

		JButton btnTab3 = new JButton("Lương");
		btnTab3.setIcon(new ImageIcon(ManagerGUI.class.getResource("/image/icons8-salary-30.png")));
		btnTab3.setHorizontalAlignment(SwingConstants.LEFT);
		btnTab3.setBorderPainted(false);
		btnTab3.setFocusable(false);
		btnTab3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTab3.setForeground(new Color(244, 245, 249));
		btnTab3.setBackground(new Color(17, 24, 39));
		btnTab3.setBounds(0, 257, 153, 42);
		panel.add(btnTab3);

		JButton btnTab4 = new JButton("GMAIL");
		btnTab4.setIcon(new ImageIcon(ManagerGUI.class.getResource("/image/icons8-gmail-30.png")));
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
				btnNewButton.setBackground(new Color(17, 24, 39));
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

	protected void reloadDashBoard() {
		
		currentDayMoney = new BillBUS().getCurrentRevenue();
		currentDayMoney = currentDayMoney / 1000000;
		label_TodayMoney.setText(currentDayMoney+"M");
		yesterdayMoney = new BillBUS().getYesterdayRevenue();
		yesterdayMoney = yesterdayMoney / 1000000;
		if (yesterdayMoney == 0) {
			label_compare_money.setText("");
		}else if (currentDayMoney >= yesterdayMoney) {
			double percent = ((currentDayMoney - yesterdayMoney) / yesterdayMoney)*100;
			label_compare_money.setText("+"+df.format(percent)+"% since yesterday");
		}else if (currentDayMoney < yesterdayMoney) {
			double percent = ((yesterdayMoney - currentDayMoney) / yesterdayMoney)*100;
			label_compare_money.setText("-"+df.format(percent)+"% since yesterday");
		}
		
		currentDayUsers = new BillBUS().getNumberOfCurrentUsers();
		label_TodayUsers.setText(currentDayUsers+" USERS");
		yesterdayUsers = new BillBUS().getTotalUserYesterday();
		if (yesterdayUsers == 0) {
			label_compare_users.setText("");
		}else if(currentDayUsers >= yesterdayUsers) {
			double percent = (currentDayUsers - yesterdayUsers)*100/yesterdayUsers;
			label_compare_users.setText("+"+df.format(percent)+"% since yesterday");
		}else if (currentDayUsers < yesterdayUsers) {
			double percent = (yesterdayUsers - currentDayUsers)*100/yesterdayUsers;
			label_compare_users.setText("-"+df.format(percent)+"% since yesterday");
		}
		
		currentDayBillAbated = new BillBUS().getCurrentNumberOfBillAbated();
		label_TodayBillAbated.setText(currentDayBillAbated+" BILLS");
		yesterdayBillAbated = new BillBUS().getYesterdayBillAbated();
		if (yesterdayBillAbated == 0) {
			label_compare_bill_abated.setText("");
		}else if(currentDayBillAbated >= yesterdayBillAbated) {
			double percent = (currentDayBillAbated - yesterdayBillAbated)*100/ yesterdayBillAbated;
			label_compare_bill_abated.setText("+"+df.format(percent)+"% since yesterday");
		}else if(currentDayBillAbated < yesterdayBillAbated) {
			double percent = (yesterdayBillAbated - currentDayBillAbated)*100/ yesterdayBillAbated;
			label_compare_bill_abated.setText("-"+df.format(percent)+"% since yesterday");
		}
		
	}

	protected void showTableByTime() {

		int month = Integer.valueOf((String) comboBox_Month.getSelectedItem());
		int year = Integer.valueOf((String) comboBox_Year.getSelectedItem());

		ArrayList<Object[]> rows = new ManagerBUS().getRowByTime(month, year);
		clearTableSalary();
		DefaultTableModel model = (DefaultTableModel) salaryTable.getModel();
		for (Object[] objects : rows) {
			model.addRow(objects);
		}
	}

	private DefaultPieDataset createDatasetPosition() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DefaultPieDataset dataset = new DefaultPieDataset();
		int tiepTanNumber = new ManagerBUS().getTiepTanNumber();
		int baoVeNumber = new ManagerBUS().getBaoVeNumber();
		int laoCongNumber = new ManagerBUS().getLaoCongNumber();

		dataset.setValue("Tiếp tân", tiepTanNumber);
		dataset.setValue("Bảo vệ", baoVeNumber);
		dataset.setValue("Lao công", laoCongNumber);

		return dataset;
	}

	private DefaultPieDataset createDatasetGioiTinh() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		int maleNumber = new ManagerBUS().getMaleNumber();
		int femaleNumber = new ManagerBUS().getFemaleNumber();

		dataset.setValue("Nam", maleNumber);
		dataset.setValue("Nữ", femaleNumber);

		return dataset;
	}

	private void updateTableSalary() {
		clearTableSalary();
		LocalDate currentDate = LocalDate.now();
		currentYear = currentDate.getYear();
		currentMonthValue = currentDate.getMonthValue();
		DefaultTableModel model = (DefaultTableModel) salaryTable.getModel();
		ArrayList<Object[]> employee = new ManagerBUS().getEmpSalary(currentMonthValue, currentYear);

		for (Object[] objects : employee) {
			model.addRow(objects);
		}
	}

	private void clearTableSalary() {
		DefaultTableModel model = (DefaultTableModel) salaryTable.getModel();
		int rowCount = model.getRowCount();

		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
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
		DefaultTableModel model = (DefaultTableModel) table_Employee.getModel();
		int rowCount = model.getRowCount();

		for (int i = rowCount - 1; i >= 0; i--) {
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
		return Normalizer.normalize(input, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
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
		if (textFieldName.getText().isBlank() || textField_BirthDate.getText().isBlank()
				|| textField_Email.getText().isBlank() || (!rdbtn_Nam.isSelected() && !rdbtn_Nu.isSelected())
				|| comboBox_Position.getSelectedItem() == null) {
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
			JOptionPane.showMessageDialog(null,
					"Định dạng ngày sinh không đúng, nhập ngày sinh theo định dạng (yyyy-mm-dd)");
			return;
		}
		if (!textField_Email.getText().matches(emailRegex)) {
			JOptionPane.showMessageDialog(null, "Email không hợp lệ");
			return;
		}
		String name = textFieldName.getText();
		Date birthDate = Date.valueOf(textField_BirthDate.getText());
		String email = textField_Email.getText();
		String position = String.valueOf(comboBox_Position.getSelectedItem());
		boolean sex = true;
		if (rdbtn_Nam.isSelected()) {
			sex = true;
		} else if (rdbtn_Nu.isSelected()) {
			sex = false;
		}

		if (position.equals("Tiếp tân")) {
			String userName = generateUserName(name);
			String passWord = generatePassWord();
			Employee employee = new Employee(name, birthDate, email, sex, position, userName, passWord);
			new ManagerBUS().addEmployee(employee);
		} else {
			Employee employee = new Employee(name, birthDate, email, sex, position, null, null);
			new ManagerBUS().addEmployee(employee);
		}
		updateEmployeeTable();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateAllChart();
		updateTableSalary();
	}

	public void updateAllChart() {
		// Update biểu đồ giới tính
		femaleNumber = new ManagerBUS().getFemaleNumber();
		maleNumber = new ManagerBUS().getMaleNumber();

		System.out.println("Nữ:" + femaleNumber);
		System.out.println("Nam:" + maleNumber);

		maleRate = (double) maleNumber / (maleNumber + femaleNumber);
		femaleRate = (double) femaleNumber / (maleNumber + femaleNumber);
		lbl_Rate_Nam.setText("Nam: " + decimalFormat.format(maleRate * 100) + "%");
		label_Rate_Nu.setText("Nữ: " + decimalFormat.format(femaleRate * 100) + "%");

		datasetGioiTinh = createDatasetGioiTinh();

		chartGioiTinh = ChartFactory.createPieChart("Tỉ lệ nam nữ", // Tiêu đề biểu đồ
				datasetGioiTinh, // Tập dữ liệu
				false, // Hiển thị legend
				true, false);

		plot = (PiePlot) chartGioiTinh.getPlot();
		plot.setSectionPaint("Nam", new Color(255, 127, 14));
		plot.setSectionPaint("Nữ", new Color(31, 119, 180));
		plot.setBackgroundPaint(new Color(254, 245, 249));
		plot.setShadowPaint(null);
		chartGioiTinh.setBackgroundPaint(new Color(254, 245, 249));

		// Đổi font cho title
		title = chartGioiTinh.getTitle();
		title.setFont(new Font("Segoe UI", Font.BOLD, 20));

		gioiTinhPanel = new ChartPanel(chartGioiTinh);
		gioiTinhPanel.setPreferredSize(new Dimension(367, 210));

		panel_chart_gioitinh.removeAll(); // Xóa các thành phần cũ
		panel_chart_gioitinh.add(gioiTinhPanel);
		panel_chart_gioitinh.revalidate(); // Cập nhật lại giao diện
		panel_chart_gioitinh.repaint(); // Vẽ lại giao diện

		// UPDATE biểu đồ vị trí

		datasetPosition = createDatasetPosition();

		positionChart = ChartFactory.createPieChart("Vị trí", datasetPosition, false, false, false);

		plot1 = (PiePlot) positionChart.getPlot();
		plot1.setSectionPaint("Tiếp tân", new Color(255, 127, 14));
		plot1.setSectionPaint("Bảo vệ", new Color(31, 119, 180));
		plot1.setSectionPaint("Lao công", new Color(43, 160, 45));
		plot1.setBackgroundPaint(new Color(254, 245, 249));
		plot1.setShadowPaint(null);
		positionChart.setBackgroundPaint(new Color(254, 245, 249));
		title2 = positionChart.getTitle();
		title2.setFont(new Font("Segoe UI", Font.BOLD, 20));

		positionPanel = new ChartPanel(positionChart);
		positionPanel.setPreferredSize(new Dimension(367, 210));

		panel_chart_position.removeAll(); // Xóa các thành phần cũ
		panel_chart_position.add(positionPanel);

		tiepTanNumber = new ManagerBUS().getTiepTanNumber();
		baoVeNumber = new ManagerBUS().getBaoVeNumber();
		laoCongNumber = new ManagerBUS().getLaoCongNumber();

		tiepTanRate = (double) tiepTanNumber / (tiepTanNumber + baoVeNumber + laoCongNumber);
		baoVeRate = (double) baoVeNumber / (tiepTanNumber + baoVeNumber + laoCongNumber);
		laoCongRate = (double) laoCongNumber / (tiepTanNumber + baoVeNumber + laoCongNumber);

		labl_Rate_Tiep_Tan.setText("Tiếp tân: " + decimalFormat.format(tiepTanRate * 100) + "%");
		labl_Rate_Bao_Ve.setText("Bảo vệ: " + decimalFormat.format(baoVeRate * 100) + "%");
		labl_Rate_Lao_Cong.setText("Lao công:" + decimalFormat.format(laoCongRate * 100) + "%");
		panel_chart_position.revalidate(); // Cập nhật lại giao diện
		panel_chart_position.repaint(); // Vẽ lại giao diện
	}

	public void deleteEmployee() {
		DefaultTableModel model = (DefaultTableModel) table_Employee.getModel();
		int rowIndex = table_Employee.getSelectedRow();

		if (!table_Employee.isRowSelected(rowIndex)) {
			JOptionPane.showMessageDialog(null, "Chọn 1 nhân viên để xóa");
			return;
		}
		int id = Integer.valueOf((String) model.getValueAt(rowIndex, 0));
		new ManagerBUS().deleteEmployee(id);
		updateEmployeeTable();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateAllChart();
		updateTableSalary();
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
		Date oldBirthDate = (Date) model.getValueAt(rowIndex, 2);
		String oldEmail = String.valueOf(model.getValueAt(rowIndex, 3));
		String oldSex = String.valueOf(model.getValueAt(rowIndex, 4));
		String position = String.valueOf(model.getValueAt(rowIndex, 5));

		textFieldName.setText(oldName);
		textField_BirthDate.setText(oldBirthDate.toString());
		textField_Email.setText(oldEmail);
		comboBox_Position.setSelectedItem(position);
		if (oldSex.equals("Nam")) {
			rdbtn_Nam.setSelected(true);
		} else if (oldSex.equals("Nữ")) {
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
		if (textFieldName.getText().isBlank() || textField_BirthDate.getText().isBlank()
				|| textField_Email.getText().isBlank() || (!rdbtn_Nam.isSelected() && !rdbtn_Nu.isSelected())
				|| comboBox_Position.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Hãy điền đầy đủ thông tin");
			return;
		}

		if (!textField_BirthDate.getText().toString().matches(birthDateRegex)) {
			JOptionPane.showMessageDialog(null,
					"Định dạng ngày sinh không đúng, nhập ngày sinh theo định dạng (yyyy-mm-dd)");
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
		} else if (rdbtn_Nu.isSelected()) {
			newSex = false;
		}
		if (newPosition.equals("Tiếp tân")) {
			String userName = generateUserName(newName);
			String passWord = generatePassWord();
			Employee newEmployee = new Employee(id, newName, newBirthDate, newEmail, newSex, newPosition, userName,
					passWord);
			new ManagerBUS().updateEmployee(newEmployee);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			updateAllChart();
			updateTableSalary();
		} else {
			Employee newEmployee = new Employee(id, newName, newBirthDate, newEmail, newSex, newPosition, null, null);
			new ManagerBUS().updateEmployee(newEmployee);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			updateAllChart();
			updateTableSalary();
		}
		updateEmployeeTable();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		updateAllChart();
		updateTableSalary();
	}
}
