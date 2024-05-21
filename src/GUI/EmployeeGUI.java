package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.mail.MessagingException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BUS.BillBUS;
import BUS.EmployeeBUS;
import BUS.HistoryBUS;
import BUS.ManagerBUS;
import BUS.RoomBUS;
import CONTROLLER.EmployeeController;
import DAO.EmployeeDAO;
import DAO.HistoryDAO;
import DTO.Bill;
import DTO.Customer;
import DTO.Employee;
import DTO.Room;
import TEST.SendMail;

import javax.swing.JPasswordField;

public class EmployeeGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_Name;
	private JTextField textField_CCCD;
	private JTextField textField_birthDate;
	private JTextField textField_roomNumber;
	private JTable table_Customer;
	private String birthDateRegex = "^(?:(?:19|20)\\d\\d)-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01]|(?:0[1-9]|1\\d|2[0-8]))|(?:19|20)\\d\\d-(0[1-9]|1[0-2])-(29|30)(?:-0[1-9]|-1[0-9]|-2[0-8])|(?:19|20)(?:0[48]|[2468][048]|[13579][26])-02-29$";
	public String regex_cccd = "^(01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64)[0-9]{10}$";
	private String emailRegex = "(?i)[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}";
	public int idEmp;
	private JTable table_Room;
	private JTable table_Bill;
	private JTextField textField_FindByName;
	private JTextField textField_FindByCCCD;
	private String EmployeeName;
	private JLabel lblBnPhc;
	private String username;
	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;
	private JPanel panel_OnLineList;
	private JTextArea displayMessArea;
	private JTextArea typeArea;
	private JButton btnSendMessage;
	private JPanel tab5;
	private JButton btnTab5;
	private JTextField textFieldEmpName;
	private JTextField textFieldBirthDateEmp;
	private JTextField textField_EmailEmp;
	private JTextField textField_usernameEmp;
	private JTextField textField_passwordEmp;
	private ButtonGroup btnGroupSex;
	private JLabel label_EmployeeName;
	private JTextField textField_EmailSender;
	private JPasswordField passwordEmailField;
	private JTextField textField_EmailReceiver;
	private JTextField textField_EmailTitle;
	private JTextField textField_ListFile;
	private JButton btn_CustomerChart;
	private JLabel lbl_datenow;
	private String dayOfWeek;
	private String day;
	private String month;
	private String year;
	private JLabel label_TotalBookingThisDate;
	private String formattedDate;
	private LocalDate today;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EmployeeGUI frame = new EmployeeGUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @param userName 
	 * @param writer 
	 * @param reader 
	 * @param socket 
	 */
	public EmployeeGUI(int idEmp, String userName, Socket socket, BufferedReader reader, PrintWriter writer) {
		this.socket = socket;
		this.reader = reader;
		this.writer = writer;
		this.username = userName;
		this.idEmp = idEmp;
		EmployeeName = new ManagerBUS().getEmpNameById(idEmp);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 520);
		contentPane = new JPanel();
		setResizable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(17, 24, 39));
		panel.setBounds(0, 0, 162, 481);
		contentPane.add(panel);
		panel.setLayout(null);

		ActionListener actionListener = new EmployeeController(this);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(162, -24, 802, 505);
		contentPane.add(tabbedPane);

		JPanel tab1 = new JPanel();
		tab1.setBackground(new Color(244, 245, 249));
		tabbedPane.addTab("Tab1", null, tab1, null);
		tab1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Quản Lí Khách Hàng");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblNewLabel.setForeground(SystemColor.desktop);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(271, 38, 255, 36);
		tab1.add(lblNewLabel);

		JLabel lblNewLabel_4 = new JLabel("Họ và tên");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_4.setForeground(SystemColor.desktop);
		lblNewLabel_4.setBounds(10, 126, 73, 20);
		tab1.add(lblNewLabel_4);

		textField_Name = new JTextField();
		textField_Name.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField_Name.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		textField_Name.setForeground(SystemColor.desktop);
		textField_Name.setBounds(93, 123, 176, 28);
		tab1.add(textField_Name);
		textField_Name.setColumns(10);

		JLabel lblNewLabel_4_1 = new JLabel("CCCD");
		lblNewLabel_4_1.setForeground(SystemColor.desktop);
		lblNewLabel_4_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_4_1.setBounds(10, 175, 73, 20);
		tab1.add(lblNewLabel_4_1);

		textField_CCCD = new JTextField();
		textField_CCCD.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		textField_CCCD.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField_CCCD.setForeground(SystemColor.desktop);
		textField_CCCD.setColumns(10);
		textField_CCCD.setBounds(93, 172, 176, 28);
		tab1.add(textField_CCCD);

		JLabel lblNewLabel_4_2 = new JLabel("Ngày sinh");
		lblNewLabel_4_2.setForeground(SystemColor.desktop);
		lblNewLabel_4_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_4_2.setBounds(292, 125, 83, 25);
		tab1.add(lblNewLabel_4_2);

		textField_birthDate = new JTextField();
		textField_birthDate.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		textField_birthDate.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField_birthDate.setForeground(SystemColor.desktop);
		textField_birthDate.setColumns(10);
		textField_birthDate.setBounds(375, 123, 176, 28);
		tab1.add(textField_birthDate);

		JLabel lblNewLabel_4_3 = new JLabel("Số phòng");
		lblNewLabel_4_3.setForeground(SystemColor.desktop);
		lblNewLabel_4_3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_4_3.setBounds(292, 172, 83, 26);
		tab1.add(lblNewLabel_4_3);

		textField_roomNumber = new JTextField();
		textField_roomNumber.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		textField_roomNumber.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField_roomNumber.setForeground(SystemColor.desktop);
		textField_roomNumber.setColumns(10);
		textField_roomNumber.setBounds(375, 172, 176, 28);
		tab1.add(textField_roomNumber);

		JButton btn_Them = new JButton("THÊM");
		btn_Them.addActionListener(actionListener);
		btn_Them.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-add-24.png")));
		btn_Them.setForeground(SystemColor.desktop);
		btn_Them.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_Them.setFocusable(false);
		btn_Them.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		btn_Them.setBackground(new Color(52, 152, 219));
		btn_Them.setBounds(568, 122, 100, 30);
		tab1.add(btn_Them);

		JButton btn_Xoa = new JButton("XÓA");
		btn_Xoa.addActionListener(actionListener);
		btn_Xoa.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-delete-24.png")));
		btn_Xoa.setForeground(SystemColor.desktop);
		btn_Xoa.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_Xoa.setFocusable(false);
		btn_Xoa.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		btn_Xoa.setBackground(new Color(70, 181, 98));
		btn_Xoa.setBounds(687, 122, 100, 30);
		tab1.add(btn_Xoa);

		JButton btn_Sua = new JButton("SỬA");
		btn_Sua.addActionListener(actionListener);
		btn_Sua.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-edit-30.png")));
		btn_Sua.setForeground(SystemColor.desktop);
		btn_Sua.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_Sua.setFocusable(false);
		btn_Sua.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		btn_Sua.setBackground(new Color(255, 165, 0));
		btn_Sua.setBounds(568, 171, 100, 30);
		tab1.add(btn_Sua);

		JButton btn_CapNhat = new JButton("CẬP NHẬT");
		btn_CapNhat.addActionListener(actionListener);
		btn_CapNhat.setForeground(SystemColor.desktop);
		btn_CapNhat.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_CapNhat.setFocusable(false);
		btn_CapNhat.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		btn_CapNhat.setBackground(new Color(255, 165, 0));
		btn_CapNhat.setBounds(687, 171, 100, 30);
		tab1.add(btn_CapNhat);

		JSeparator separator = new JSeparator();
		separator.setBackground(SystemColor.desktop);
		separator.setForeground(SystemColor.desktop);
		separator.setBounds(10, 220, 777, 2);
		tab1.add(separator);

		table_Customer = new JTable();
		table_Customer.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "STT", "Họ và tên", "Số CCCD", "Ngày sinh" }));
		table_Customer.setForeground(SystemColor.desktop);
		table_Customer.setRowHeight(25);
		table_Customer.setGridColor(new Color(0, 0, 0));
		JTableHeader headerCustomer = table_Customer.getTableHeader();
		headerCustomer.setFont(new Font("Segoe UI", Font.BOLD, 16));
		headerCustomer.setBackground(new Color(244, 245, 249));
		headerCustomer.setForeground(SystemColor.desktop);
		table_Customer.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table_Customer.setBounds(10, 235, 777, 231);
		updateCustomerTable();

		JScrollPane scrollPane_Customer = new JScrollPane(table_Customer);
		scrollPane_Customer.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		scrollPane_Customer.setBounds(10, 264, 777, 202);
		tab1.add(scrollPane_Customer);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.desktop);
		separator_1.setBackground(SystemColor.desktop);
		separator_1.setBounds(10, 105, 777, 2);
		tab1.add(separator_1);
		
		textField_FindByName = new JTextField();
		textField_FindByName.setForeground(SystemColor.desktop);
		textField_FindByName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField_FindByName.setColumns(10);
		textField_FindByName.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		textField_FindByName.setBounds(93, 230, 176, 28);
		tab1.add(textField_FindByName);
		
		JLabel lblNewLabel_4_4 = new JLabel("Họ và tên");
		lblNewLabel_4_4.setForeground(SystemColor.desktop);
		lblNewLabel_4_4.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_4_4.setBounds(10, 233, 73, 20);
		tab1.add(lblNewLabel_4_4);
		
		JButton btn_FindName = new JButton("");
		btn_FindName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_FindByName.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Nhập tên để tìm kiếm");
					return;
				}
				clearCustomerTable();

				String name = textField_FindByName.getText();
				ArrayList<Customer> customers = new EmployeeBUS().findCustomerByName(name);
				DefaultTableModel model = (DefaultTableModel) table_Customer.getModel();
				for (Customer customer : customers) {
					model.addRow(customer.toObjects());
				}
			}
		});
		btn_FindName.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-find-30.png")));
		btn_FindName.setForeground(SystemColor.desktop);
		btn_FindName.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_FindName.setFocusable(false);
//		btn_FindName.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		btn_FindName.setBorderPainted(false);
		btn_FindName.setBackground(new Color(244, 245, 249));
		btn_FindName.setBounds(270, 229, 37, 30);
		tab1.add(btn_FindName);
		
		JRadioButton rdbtn_SortByName = new JRadioButton("Sắp xếp theo tên");
		rdbtn_SortByName.setFocusable(false);
		rdbtn_SortByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearCustomerTable();
				DefaultTableModel model = (DefaultTableModel) table_Customer.getModel();
				ArrayList<Customer> customers = new EmployeeBUS().sortByName();
				
				for (Customer customer : customers) {
					model.addRow(customer.toObjects());
				}
			}
		});
		rdbtn_SortByName.setBackground(new Color(244, 245, 249));
		rdbtn_SortByName.setFont(new Font("Segoe UI", Font.BOLD, 16));
		rdbtn_SortByName.setForeground(SystemColor.desktop);
		rdbtn_SortByName.setBounds(410, 229, 176, 28);
		tab1.add(rdbtn_SortByName);
		
		JRadioButton rdbtn_SortByDoB = new JRadioButton("Sắp xếp theo năm sinh");
		rdbtn_SortByDoB.setFocusable(false);
		rdbtn_SortByDoB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearCustomerTable();
				DefaultTableModel model = (DefaultTableModel)table_Customer.getModel();
				
				ArrayList<Customer> customers = new EmployeeBUS().sortByDoB();
				for (Customer customer : customers) {
					model.addRow(customer.toObjects());
				}
			}
		});
		rdbtn_SortByDoB.setForeground(SystemColor.desktop);
		rdbtn_SortByDoB.setFont(new Font("Segoe UI", Font.BOLD, 16));
		rdbtn_SortByDoB.setBackground(new Color(244, 245, 249));
		rdbtn_SortByDoB.setBounds(588, 229, 199, 28);
		tab1.add(rdbtn_SortByDoB);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtn_SortByDoB);
		buttonGroup.add(rdbtn_SortByName);
		
		JButton btn_Refresh = new JButton("");
		btn_Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearCustomerTable();
				updateCustomerTable();
				buttonGroup.clearSelection();
			}
		});
		btn_Refresh.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-refresh-30.png")));
		btn_Refresh.setForeground(SystemColor.desktop);
		btn_Refresh.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_Refresh.setFocusable(false);
		btn_Refresh.setBorderPainted(false);
		btn_Refresh.setBackground(new Color(244, 245, 249));
		btn_Refresh.setBounds(305, 228, 44, 30);
		tab1.add(btn_Refresh);
		
		
		
		// Lấy ngày hiện tại
		today = LocalDate.now();
		
		// Lấy ngày trong tuần
		dayOfWeek = today.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("vi", "VN"));
		
		// Định dạng ngày tháng theo yêu cầu
		formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		
		// Tách chuỗi để lấy ngày riêng biệt
		String[] dateParts = formattedDate.split("-");
		day = dateParts[0];
		month = dateParts[1];
		year = dateParts[2];
		
		JLabel label_ToDate = new JLabel(dayOfWeek+","+day+"/"+month+"/"+year);
		label_ToDate.setForeground(SystemColor.desktop);
		label_ToDate.setFont(new Font("Segoe UI", Font.BOLD, 16));
		label_ToDate.setBounds(605, 0, 192, 28);
		tab1.add(label_ToDate);

		JPanel tab2 = new JPanel();
		tab2.setForeground(SystemColor.desktop);
		tab2.setBackground(new Color(244, 245, 249));
		tabbedPane.addTab("Tab2", null, tab2, null);
		tab2.setLayout(null);

		table_Room = new JTable();
		table_Room.setFillsViewportHeight(true);
		table_Room.setRowHeight(25);
		table_Room.setGridColor(new Color(0, 0, 0));
		table_Room.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Số Phòng" }));
		JTableHeader roomHeader = table_Room.getTableHeader();
		roomHeader.setFont(new Font("Segoe UI", Font.BOLD, 16));
		roomHeader.setForeground(SystemColor.desktop);
		table_Room.setFont(new Font("Segoe UI", Font.BOLD, 16));
		table_Room.setForeground(SystemColor.desktop);
		table_Room.setBounds(244, 88, 288, 363);
		updateRoomTable();
		// tab2.add(table_Room);

		JScrollPane scrollPane = new JScrollPane(table_Room);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		scrollPane.setBounds(244, 88, 288, 363);
		tab2.add(scrollPane);

		JLabel lblDanhSchPhng = new JLabel("Danh sách phòng");
		lblDanhSchPhng.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-room-60.png")));
		lblDanhSchPhng.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhSchPhng.setForeground(SystemColor.desktop);
		lblDanhSchPhng.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblDanhSchPhng.setBounds(244, 11, 288, 66);
		tab2.add(lblDanhSchPhng);
		
		JRadioButton rdbtn_isEmptyRoom = new JRadioButton("Danh sách phòng trống");
		rdbtn_isEmptyRoom.setFocusable(false);
		rdbtn_isEmptyRoom.setFont(new Font("Segoe UI", Font.BOLD, 16));
		rdbtn_isEmptyRoom.setBackground(new Color(244, 245, 249));
		rdbtn_isEmptyRoom.setForeground(SystemColor.desktop);
		rdbtn_isEmptyRoom.setBounds(538, 94, 253, 23);
		rdbtn_isEmptyRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearRoomTable();
				DefaultTableModel model = (DefaultTableModel) table_Room.getModel();
				ArrayList<Room> rooms = new RoomBUS().getListEmptyRoom();
				
				for (Room room : rooms) {
					model.addRow(room.toObjects());
				}
			}
		});
		tab2.add(rdbtn_isEmptyRoom);
		
		JRadioButton rdbtn_isOrderRoom = new JRadioButton("Danh sách phòng đã được đặt");
		rdbtn_isOrderRoom.setFocusable(false);
		rdbtn_isOrderRoom.setFont(new Font("Segoe UI", Font.BOLD, 16));
		rdbtn_isOrderRoom.setBackground(new Color(244, 245, 249));
		rdbtn_isOrderRoom.setForeground(SystemColor.desktop);
		rdbtn_isOrderRoom.setBounds(538, 120, 253, 43);
		rdbtn_isOrderRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearRoomTable();
				DefaultTableModel model = (DefaultTableModel) table_Room.getModel();
				ArrayList<Room> rooms = new RoomBUS().getListOrderedRoom();
				
				for (Room room : rooms) {
					model.addRow(room.toObjects());
				}
			}
		});
		tab2.add(rdbtn_isOrderRoom);
		
		ButtonGroup buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(rdbtn_isOrderRoom);
		buttonGroup2.add(rdbtn_isEmptyRoom);
		
		JButton btnReloadRoomTable = new JButton("");
		
		btnReloadRoomTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateRoomTable();
				buttonGroup2.clearSelection();
			}
		});
		btnReloadRoomTable.setFocusable(false);
		btnReloadRoomTable.setBorderPainted(false);
		btnReloadRoomTable.setForeground(new Color(244, 245, 249));
		btnReloadRoomTable.setBackground(new Color(244, 245, 249));
		btnReloadRoomTable.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-refresh-30.png")));
		btnReloadRoomTable.setBounds(535, 158, 35, 37);
		tab2.add(btnReloadRoomTable);

		JPanel tab3 = new JPanel();
		tab3.setForeground(SystemColor.desktop);
		tab3.setBackground(new Color(244, 245, 249));
		tabbedPane.addTab("Tab3", null, tab3, null);
		tab3.setLayout(null);

		JLabel lblDanhSchHa = new JLabel("Danh sách hóa đơn");
		lblDanhSchHa.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-bill-60.png")));
		lblDanhSchHa.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhSchHa.setForeground(SystemColor.desktop);
		lblDanhSchHa.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblDanhSchHa.setBounds(227, 0, 321, 66);
		tab3.add(lblDanhSchHa);

		table_Bill = new JTable();
		table_Bill.setBounds(10, 183, 777, 182);
		table_Bill.setFillsViewportHeight(true);
		table_Bill.setRowHeight(25);
		table_Bill.setGridColor(new Color(0, 0, 0));
		table_Bill.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Số CCCD", "Số phòng", "Ngày đặt", "Ngày trả", "Giá" }));
		JTableHeader headerBill = table_Bill.getTableHeader();
		headerBill.setFont(new Font("Segoe UI", Font.BOLD, 16));
		headerBill.setBackground(new Color(244, 245, 249));
		headerBill.setForeground(SystemColor.desktop);
		table_Bill.setForeground(SystemColor.desktop);
		table_Bill.setFillsViewportHeight(true);
		table_Bill.setRowHeight(25);
		table_Bill.setGridColor(new Color(0, 0, 0));
		table_Bill.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		updateBillTable();

		JScrollPane scrollPane_Bill = new JScrollPane(table_Bill);
		scrollPane_Bill.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		scrollPane_Bill.setBounds(10, 150, 777, 193);
		tab3.add(scrollPane_Bill);
		
		textField_FindByCCCD = new JTextField();
		textField_FindByCCCD.setForeground(SystemColor.desktop);
		textField_FindByCCCD.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField_FindByCCCD.setColumns(10);
		textField_FindByCCCD.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		textField_FindByCCCD.setBounds(11, 111, 176, 28);
		tab3.add(textField_FindByCCCD);
		
		JLabel lblNewLabel_5 = new JLabel("Nhập CCCD");
		lblNewLabel_5.setForeground(SystemColor.desktop);
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_5.setBounds(10, 83, 89, 22);
		tab3.add(lblNewLabel_5);
		
		JButton btn_FindCCCD = new JButton("");
		btn_FindCCCD.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-find-30.png")));
		btn_FindCCCD.setForeground(SystemColor.desktop);
		btn_FindCCCD.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_FindCCCD.setFocusable(false);
		btn_FindCCCD.setBorderPainted(false);
		btn_FindCCCD.setBackground(new Color(244, 245, 249));
		btn_FindCCCD.setBounds(197, 109, 37, 30);
		btn_FindCCCD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_FindByCCCD.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
					return;
				}
				
				clearBillTable();
				String CCCD = textField_FindByCCCD.getText();
				ArrayList<Bill> bills = new BillBUS().findBillByCCCD(CCCD);
				DefaultTableModel model = (DefaultTableModel) table_Bill.getModel();
				
				for (Bill bill : bills) {
					model.addRow(bill.toObject());
				}
			}
		});
		tab3.add(btn_FindCCCD);
		
		JRadioButton rdbtn_showNotAbatedBill = new JRadioButton("Hóa đơn chưa thanh toán");
		rdbtn_showNotAbatedBill.setBackground(new Color(244, 245, 249));
		rdbtn_showNotAbatedBill.setFocusable(false);
		rdbtn_showNotAbatedBill.setFont(new Font("Segoe UI", Font.BOLD, 16));
		rdbtn_showNotAbatedBill.setForeground(SystemColor.desktop);
		rdbtn_showNotAbatedBill.setBounds(566, 111, 221, 23);
		rdbtn_showNotAbatedBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Bill> bills = new BillBUS().getListNotAbatedBill();
				DefaultTableModel model = (DefaultTableModel) table_Bill.getModel();
				clearBillTable();

				for (Bill bill : bills) {
					model.addRow(bill.NotAbateToObject());
				}
			}
		});
		tab3.add(rdbtn_showNotAbatedBill);
		
		JButton btn_RefreshBillTable = new JButton("");
		btn_RefreshBillTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBillTable();
				textField_FindByCCCD.setText(null);
				rdbtn_showNotAbatedBill.setSelected(false);
			}
		});
		btn_RefreshBillTable.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-refresh-30.png")));
		btn_RefreshBillTable.setForeground(SystemColor.desktop);
		btn_RefreshBillTable.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_RefreshBillTable.setFocusable(false);
		btn_RefreshBillTable.setBorderPainted(false);
		btn_RefreshBillTable.setBackground(new Color(244, 245, 249));
		btn_RefreshBillTable.setBounds(232, 108, 44, 30);
		tab3.add(btn_RefreshBillTable);
		
		JButton btn_Abate = new JButton("THANH TOÁN");

		btn_Abate.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_Abate.setFocusable(false);
		btn_Abate.setForeground(new Color(244, 245, 249));
		btn_Abate.setBorder(new LineBorder(new Color(17, 24, 39), 2));
		btn_Abate.setBackground(new Color(17, 24, 39));
		btn_Abate.setBounds(278, 109, 114, 30);
		btn_Abate.addActionListener(actionListener);
		btn_Abate.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		        btn_Abate.setBackground(new Color(244, 245, 249)); // Màu khi hover
		        btn_Abate.setBorder(new LineBorder(new Color(17, 24, 39), 2));
		        btn_Abate.setForeground(new Color(17, 24, 39));
		    }

		    public void mouseExited(MouseEvent e) {
		        btn_Abate.setBackground(new Color(17, 24, 39)); // Màu ban đầu
		        btn_Abate.setBorder(new LineBorder(new Color(17, 24, 39), 2));
		        btn_Abate.setForeground(new Color(244, 245, 249));
		    }
		});
		tab3.add(btn_Abate);
		
		JButton btn_PrintBill = new JButton("PRINT BILL");
		btn_PrintBill.setForeground(new Color(244, 245, 249));
		btn_PrintBill.setBorder(new LineBorder(new Color(17, 24, 39), 2));
		btn_PrintBill.setBackground(new Color(17, 24, 39));
		btn_PrintBill.setFocusable(false);
//		btn_PrintBill.setBorder(new LineBorder(new Color(17, 24, 39), 2));
		btn_PrintBill.setIcon(null);
//		btn_PrintBill.setForeground(SystemColor.desktop);
		btn_PrintBill.addActionListener(actionListener);
//		btn_PrintBill.setBackground(new Color(244, 245, 249));
		btn_PrintBill.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_PrintBill.setBounds(399, 109, 107, 30);
		btn_PrintBill.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	btn_PrintBill.setBackground(new Color(244, 245, 249)); // Màu khi hover
		    	btn_PrintBill.setBorder(new LineBorder(new Color(17, 24, 39), 2));
		    	btn_PrintBill.setForeground(new Color(17, 24, 39));
		    }

		    public void mouseExited(MouseEvent e) {
		    	btn_PrintBill.setBackground(new Color(17, 24, 39)); // Màu ban đầu
		    	btn_PrintBill.setBorder(new LineBorder(new Color(17, 24, 39), 2));
		    	btn_PrintBill.setForeground(new Color(244, 245, 249));
		    }
		});
		tab3.add(btn_PrintBill);
		
		JButton btn_PrintBill_1 = new JButton("EXPORT .XLS");
		btn_PrintBill_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					toExcel();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_PrintBill_1.setForeground(SystemColor.desktop);
		btn_PrintBill_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_PrintBill_1.setFocusable(false);
		btn_PrintBill_1.setBorder(new LineBorder(new Color(17, 24, 39), 2));
		btn_PrintBill_1.setBackground(new Color(244, 245, 249));
		btn_PrintBill_1.setBounds(673, 353, 114, 30);
		tab3.add(btn_PrintBill_1);
		
		int numberOfCustomerThisDay = new EmployeeBUS().getTotalCustomerThisDay(idEmp);
		lblBnPhc = new JLabel("Bạn đã phục vụ: "+numberOfCustomerThisDay+" khách hàng");

		lblBnPhc.setForeground(SystemColor.desktop);
		lblBnPhc.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblBnPhc.setBounds(10, 375, 291, 22);
		tab3.add(lblBnPhc);
		
		btn_CustomerChart = new JButton("XEM BIỂU ĐỒ");
		btn_CustomerChart.setToolTipText("BIỂU ĐỒ SỐ KHÁCH HÀNG MÀ NHÂN VIÊN ĐÃ PHỤC VỤ TRONG 1 NGÀY");
		btn_CustomerChart.setIcon(null);
		btn_CustomerChart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NumberOfCustomerServedChart(idEmp);
			}
		});
		btn_CustomerChart.setContentAreaFilled(false);
		btn_CustomerChart.setForeground(SystemColor.desktop);
		btn_CustomerChart.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_CustomerChart.setFocusable(false);
		btn_CustomerChart.setBorder(new LineBorder(new Color(17, 24, 39), 2));
		btn_CustomerChart.setBackground(new Color(244, 245, 249));
		btn_CustomerChart.setBounds(270, 372, 114, 30);
		tab3.add(btn_CustomerChart);
		
		lbl_datenow = new JLabel("Hôm nay, "+dayOfWeek+", ngày "+day+", tháng "+month+", năm "+year+":");
		lbl_datenow.setForeground(SystemColor.desktop);
		lbl_datenow.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbl_datenow.setBounds(10, 350, 347, 22);
		tab3.add(lbl_datenow);
		
		int totalBookingNow = new BillBUS().getTotalBookingByDate(today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		
		label_TotalBookingThisDate = new JLabel("Số lượt đặt phòng: "+totalBookingNow);
		label_TotalBookingThisDate.setForeground(SystemColor.desktop);
		label_TotalBookingThisDate.setFont(new Font("Segoe UI", Font.BOLD, 14));
		label_TotalBookingThisDate.setBounds(10, 408, 163, 22);
		tab3.add(label_TotalBookingThisDate);
		
		JButton btn_TotalBookingChart = new JButton("XEM BIỂU ĐỒ");
		btn_TotalBookingChart.setToolTipText("BIỂU ĐỒ SỐ LƯỢT ĐẶT PHÒNG TRONG CÁC NGÀY");
		btn_TotalBookingChart.setForeground(SystemColor.desktop);
		btn_TotalBookingChart.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_TotalBookingChart.setFocusable(false);
		btn_TotalBookingChart.setContentAreaFilled(false);
		btn_TotalBookingChart.setBorder(new LineBorder(new Color(17, 24, 39), 2));
		btn_TotalBookingChart.setBackground(new Color(244, 245, 249));
		btn_TotalBookingChart.setBounds(175, 406, 114, 30);
		btn_TotalBookingChart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TotalBookingEveryDateByChart().setVisible(true);;
			}
		});
		tab3.add(btn_TotalBookingChart);
		
		JPanel tab4 = new JPanel();
		tab4.setBackground(new Color(244, 245, 249));
		tabbedPane.addTab("Tab4", null, tab4, null);
		tab4.setLayout(null);
		
		panel_OnLineList = new JPanel();
		panel_OnLineList.setBorder(new LineBorder(new Color(17, 24, 39), 2));
		panel_OnLineList.setForeground(SystemColor.desktop);
		panel_OnLineList.setBackground(new Color(244, 245, 249));
		panel_OnLineList.setBounds(0, 49, 193, 428);
		panel_OnLineList.setLayout(null);
		
		JScrollPane scrollPane_OnlineList = new JScrollPane(panel_OnLineList);
		scrollPane_OnlineList.setBorder(null);
		scrollPane_OnlineList.setBackground(new Color(244, 245, 249));
	
		scrollPane_OnlineList.setBounds(0, 39, 193, 435);
		tab4.add(scrollPane_OnlineList);
		
		JPanel panel_OnlineLabel = new JPanel();
		panel_OnlineLabel.setForeground(SystemColor.desktop);
		panel_OnlineLabel.setBackground(new Color(244, 245, 249));
		panel_OnlineLabel.setBounds(0, 3, 193, 33);
		tab4.add(panel_OnlineLabel);
		panel_OnlineLabel.setBorder(new LineBorder(new Color(17, 24, 39), 2));
		panel_OnlineLabel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ONLINE");
		lblNewLabel_1.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-online-30.png")));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_1.setForeground(new Color(17, 29, 34));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 193, 33);
		panel_OnlineLabel.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(244, 245, 249));
		panel_2.setBounds(197, 3, 600, 474);
		tab4.add(panel_2);
		panel_2.setLayout(null);
		
		displayMessArea = new JTextArea();
		displayMessArea.setEditable(false);
		displayMessArea.setForeground(SystemColor.desktop);
		displayMessArea.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		displayMessArea.setBorder(null);
		displayMessArea.setBounds(0, 0, 602, 364);

//		panel_2.add(displayMessArea);
		
		typeArea = new JTextArea();
		typeArea.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		typeArea.setForeground(SystemColor.desktop);
		typeArea.setBorder(new LineBorder(new Color(17, 24, 39), 2));
		typeArea.setBounds(0, 372, 521, 91);
		
		JScrollPane scrollPane_typeArea = new JScrollPane(typeArea);
		scrollPane_typeArea.setBorder(null);
		scrollPane_typeArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_typeArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_typeArea.setBackground(new Color(244, 245, 249));
		scrollPane_typeArea.setBounds(0, 372, 520, 97);
		panel_2.add(scrollPane_typeArea);
		
		btnSendMessage = new JButton("");
		btnSendMessage.setBorderPainted(false);
		btnSendMessage.setFocusable(false);
		btnSendMessage.setHorizontalAlignment(SwingConstants.LEFT);
		btnSendMessage.setVerticalAlignment(SwingConstants.TOP);
		btnSendMessage.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-send-40.png")));
		btnSendMessage.setBackground(new Color(244, 245, 249));
		btnSendMessage.setForeground(new Color(17, 29, 34));
		btnSendMessage.addActionListener(this);
		btnSendMessage.setBounds(520, 365, 76, 56);
		panel_2.add(btnSendMessage);
		
		JScrollPane scrollPane_displayMess = new JScrollPane(displayMessArea);
		scrollPane_displayMess.setBorder(new LineBorder(SystemColor.desktop, 2));
		scrollPane_displayMess.setBackground(new Color(244, 245, 249));
		scrollPane_displayMess.setBounds(0, 0, 600, 364);
		panel_2.add(scrollPane_displayMess);
		
		tab5 = new JPanel();
		tab5.setBackground(new Color(240, 240, 240));
		tabbedPane.addTab("Tab5", null, tab5, null);
		tabbedPane.setBackgroundAt(4, new Color(244, 245, 249));
		tab5.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("EMAIL AREA");
		lblNewLabel_8.setForeground(SystemColor.desktop);
		lblNewLabel_8.setFont(new Font("Segoe Script", Font.BOLD, 18));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-gmail-30.png")));
		lblNewLabel_8.setBounds(20, 0, 210, 44);
		tab5.add(lblNewLabel_8);
		
		textField_EmailSender = new JTextField();
		textField_EmailSender.setForeground(SystemColor.desktop);
		textField_EmailSender.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_EmailSender.setBorder(new LineBorder(SystemColor.desktop));
		textField_EmailSender.setBounds(41, 69, 246, 35);
		tab5.add(textField_EmailSender);
		textField_EmailSender.setColumns(10);
		
		passwordEmailField = new JPasswordField();
		passwordEmailField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		passwordEmailField.setForeground(SystemColor.desktop);
		passwordEmailField.setBorder(new LineBorder(SystemColor.desktop));
		passwordEmailField.setBounds(347, 69, 246, 35);
		tab5.add(passwordEmailField);
		
		JLabel lblNewLabel_9 = new JLabel("From email:");
		lblNewLabel_9.setForeground(SystemColor.desktop);
		lblNewLabel_9.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_9.setBounds(41, 39, 246, 26);
		tab5.add(lblNewLabel_9);
		
		JLabel lblNewLabel_9_1 = new JLabel("Password:");
		lblNewLabel_9_1.setForeground(SystemColor.desktop);
		lblNewLabel_9_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_9_1.setBounds(347, 39, 246, 26);
		tab5.add(lblNewLabel_9_1);
		
		textField_EmailReceiver = new JTextField();
		textField_EmailReceiver.setBorder(new LineBorder(SystemColor.desktop));
		textField_EmailReceiver.setForeground(SystemColor.desktop);
		textField_EmailReceiver.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_EmailReceiver.setBounds(41, 132, 246, 35);
		tab5.add(textField_EmailReceiver);
		textField_EmailReceiver.setColumns(10);
		
		JLabel lblNewLabel_9_2 = new JLabel("To:");
		lblNewLabel_9_2.setForeground(SystemColor.desktop);
		lblNewLabel_9_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_9_2.setBounds(41, 105, 246, 26);
		tab5.add(lblNewLabel_9_2);
		
		textField_EmailTitle = new JTextField();
		textField_EmailTitle.setBorder(new LineBorder(SystemColor.desktop));
		textField_EmailTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
		textField_EmailTitle.setForeground(SystemColor.desktop);
		textField_EmailTitle.setBounds(41, 194, 553, 35);
		tab5.add(textField_EmailTitle);
		textField_EmailTitle.setColumns(10);
		
		JLabel lblNewLabel_9_2_1 = new JLabel("Title:");
		lblNewLabel_9_2_1.setForeground(SystemColor.desktop);
		lblNewLabel_9_2_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_9_2_1.setBounds(41, 167, 246, 26);
		tab5.add(lblNewLabel_9_2_1);
		
		JTextArea textArea_Email_Content = new JTextArea();
		textArea_Email_Content.setForeground(SystemColor.desktop);
		textArea_Email_Content.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textArea_Email_Content.setBounds(40, 267, 553, 131);
		
		JScrollPane scrollPane_1 = new JScrollPane(textArea_Email_Content);
		scrollPane_1.setBorder(new LineBorder(SystemColor.desktop));
		scrollPane_1.setBounds(40, 254, 553, 131);
		tab5.add(scrollPane_1);
		
		JLabel lblNewLabel_9_2_1_1 = new JLabel("Content:");
		lblNewLabel_9_2_1_1.setForeground(SystemColor.desktop);
		lblNewLabel_9_2_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_9_2_1_1.setBounds(41, 228, 246, 26);
		tab5.add(lblNewLabel_9_2_1_1);
		
		textField_ListFile = new JTextField();
		textField_ListFile.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_ListFile.setBorder(new LineBorder(SystemColor.desktop));
		textField_ListFile.setColumns(10);
		textField_ListFile.setBounds(40, 395, 517, 35);
		tab5.add(textField_ListFile);
		
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
					}else {
						currentListFile += "-"+selectedFile.getAbsolutePath();
						textField_ListFile.setText(currentListFile);
					}
				}
			}
		});
		btn_fileAttachment.setFocusable(false);
		btn_fileAttachment.setBorderPainted(false);
		btn_fileAttachment.setBackground(new Color(240, 240, 240));
		btn_fileAttachment.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-attachment-24.png")));
		btn_fileAttachment.setBounds(560, 395, 33, 35);
		tab5.add(btn_fileAttachment);
		
		JButton btnSendMail = new JButton("SEND");
		btnSendMail.setBackground(new Color(17, 24, 39));
		btnSendMail.setBorder(new LineBorder(new Color(17, 24, 39), 2));
		btnSendMail.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnSendMail.setForeground(new Color(254, 245, 249));
		btnSendMail.setBounds(40, 441, 102, 30);
		tab5.add(btnSendMail);
		btnSendMail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnSendMail.setBackground(new Color(244,245,249));
            	btnSendMail.setForeground(new Color(17, 24, 39));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnSendMail.setBackground(new Color(17, 24, 39));
            	btnSendMail.setForeground(new Color(244,245,249));
            }
		});
		btnSendMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String emailSender = textField_EmailSender.getText().trim();
				String password = passwordEmailField.getText().trim();
				
				String reveiver = textField_EmailReceiver.getText().trim();
				String title = textField_EmailTitle.getText().trim();
				String content = textArea_Email_Content.getText();
				String paths = textField_ListFile.getText().trim();
				
				ArrayList<File> listFile = new ArrayList<>();
				String[] listPaths = paths.split("\\-");
				for (String path : listPaths) {
					File file = new File(path);
					listFile.add(file);
				}

				if (emailSender.isEmpty() || password.isEmpty() || reveiver.isEmpty()
				 || title.isEmpty() || content.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
					return;
				}
				
				if (emailSender.equals(reveiver)) {
					JOptionPane.showMessageDialog(null, "Email người gửi và người nhận không được trùng nhau");
					return;
				}
				
				try {
					new SendMail(emailSender, password, reveiver, title, content, listFile);
				} catch (UnsupportedEncodingException | MessagingException e1) {
					JOptionPane.showMessageDialog(null, "Lỗi ! \n Hãy kiểm tra lại tài khoản Gmail và mật khẩu \n Chú ý mật khẩu Gmail là mật khẩu ứng dụng");
					System.out.println(e1.getMessage());
					return;
				}
			}
		});

		JButton btnTab1 = new JButton("Khách hàng");
		btnTab1.setHorizontalAlignment(SwingConstants.LEFT);
		btnTab1.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-online-support-30.png")));
		btnTab1.setBackground(new Color(55, 65, 81));
		btnTab1.setForeground(new Color(244, 245, 249));
		btnTab1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTab1.setBorderPainted(false);
		btnTab1.setFocusable(false);
		btnTab1.setBounds(0, 155, 162, 42);
		panel.add(btnTab1);

		JButton btnTab2 = new JButton("DS Phòng");
		btnTab2.setHorizontalAlignment(SwingConstants.LEFT);
		btnTab2.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-room-30.png")));
		btnTab2.setBackground(new Color(17, 24, 39));
		btnTab2.setForeground(new Color(244, 245, 249));
		btnTab2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTab2.setFocusable(false);
		btnTab2.setBorderPainted(false);
		btnTab2.setBounds(0, 199, 162, 42);
		panel.add(btnTab2);

		JButton btnTab3 = new JButton("DS Hóa Đơn");
		btnTab3.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-bill-30.png")));
		btnTab3.setHorizontalAlignment(SwingConstants.LEFT);
		btnTab3.setBackground(new Color(17, 24, 39));
		btnTab3.setForeground(new Color(244, 245, 249));
		btnTab3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTab3.setFocusable(false);
		btnTab3.setBorderPainted(false);
		btnTab3.setBounds(0, 243, 162, 42);
		panel.add(btnTab3);

		JButton btnTab4 = new JButton("Chatting");
		btnTab4.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-communication-30.png")));
		btnTab4.setHorizontalAlignment(SwingConstants.LEFT);
		btnTab4.setForeground(new Color(244, 245, 249));
		btnTab4.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTab4.setBackground(new Color(17, 24, 39));
		btnTab4.setFocusable(false);
		btnTab4.setBorderPainted(false);
		btnTab4.setBounds(0, 287, 162, 42);
		panel.add(btnTab4);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-user-80.png")));
		lblNewLabel_6.setBounds(0, 0, 162, 90);
		panel.add(lblNewLabel_6);
		
		
		JLabel lblHello = new JLabel("Hello");
		lblHello.setHorizontalAlignment(SwingConstants.CENTER);
		lblHello.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblHello.setForeground(SystemColor.window);
		lblHello.setBounds(0, 86, 162, 25);
		panel.add(lblHello);
		
		label_EmployeeName = new JLabel(EmployeeName+":V");
		label_EmployeeName.setHorizontalAlignment(SwingConstants.CENTER);
		label_EmployeeName.setForeground(new Color(244, 245, 249));
		label_EmployeeName.setFont(new Font("Segoe UI", Font.BOLD, 14));
		label_EmployeeName.setBounds(0, 105, 162, 25);
		panel.add(label_EmployeeName);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(244, 245, 249));
		separator_2.setBackground(new Color(244, 245, 249));
		separator_2.setBounds(10, 145, 142, 1);
		panel.add(separator_2);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setForeground(new Color(244, 245, 249));
		separator_2_1.setBackground(new Color(244, 245, 249));
		separator_2_1.setBounds(10, 427, 142, 1);
		panel.add(separator_2_1);
		
		btnTab5 = new JButton("Email");
		btnTab5.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-gmail-30.png")));
		btnTab5.setHorizontalAlignment(SwingConstants.LEFT);
		btnTab5.setForeground(new Color(244, 245, 249));
		btnTab5.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTab5.setFocusable(false);
		btnTab5.setBorderPainted(false);
		btnTab5.setBackground(new Color(17, 24, 39));
		btnTab5.setBounds(0, 331, 162, 42);
		panel.add(btnTab5);
		
		JButton btnTab6 = new JButton("Profile");
		btnTab6.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-user-location-30.png")));
		btnTab6.setHorizontalAlignment(SwingConstants.LEFT);
		btnTab6.setForeground(new Color(244, 245, 249));
		btnTab6.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTab6.setFocusable(false);
		btnTab6.setBorderPainted(false);
		btnTab6.setBackground(new Color(17, 24, 39));
		btnTab6.setBounds(0, 375, 162, 42);
		panel.add(btnTab6);

		/* ================= ACTION LISTENER CHO BUTTON TAB =============== */
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
				btnTab5.setForeground(new Color(244, 245, 249));
				btnTab5.setBackground(new Color(17, 24, 39));
				btnTab6.setForeground(new Color(244, 245, 249));
				btnTab6.setBackground(new Color(17, 24, 39));
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
				btnTab5.setForeground(new Color(244, 245, 249));
				btnTab5.setBackground(new Color(17, 24, 39));
				btnTab6.setForeground(new Color(244, 245, 249));
				btnTab6.setBackground(new Color(17, 24, 39));
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
				btnTab5.setForeground(new Color(244, 245, 249));
				btnTab5.setBackground(new Color(17, 24, 39));
				btnTab6.setForeground(new Color(244, 245, 249));
				btnTab6.setBackground(new Color(17, 24, 39));
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
				btnTab5.setForeground(new Color(244, 245, 249));
				btnTab5.setBackground(new Color(17, 24, 39));
				btnTab6.setForeground(new Color(244, 245, 249));
				btnTab6.setBackground(new Color(17, 24, 39));
			}
		});
		
		btnTab5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
				btnTab5.setBackground(new Color(55, 65, 81));
				btnTab5.setForeground(new Color(244, 245, 249));
				btnTab4.setBackground(new Color(17, 24, 39));
				btnTab4.setForeground(new Color(244, 245, 249));
				btnTab3.setForeground(new Color(244, 245, 249));
				btnTab3.setBackground(new Color(17, 24, 39));
				btnTab2.setForeground(new Color(244, 245, 249));
				btnTab2.setBackground(new Color(17, 24, 39));
				btnTab1.setForeground(new Color(244, 245, 249));
				btnTab1.setBackground(new Color(17, 24, 39));
				btnTab6.setForeground(new Color(244, 245, 249));
				btnTab6.setBackground(new Color(17, 24, 39));
			}
		});
		
		btnTab6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(5);
				btnTab6.setBackground(new Color(55, 65, 81));
				btnTab6.setForeground(new Color(244, 245, 249));
				btnTab5.setBackground(new Color(17, 24, 39));
				btnTab5.setForeground(new Color(244, 245, 249));
				btnTab4.setForeground(new Color(244, 245, 249));
				btnTab4.setBackground(new Color(17, 24, 39));
				btnTab3.setForeground(new Color(244, 245, 249));
				btnTab3.setBackground(new Color(17, 24, 39));
				btnTab2.setForeground(new Color(244, 245, 249));
				btnTab2.setBackground(new Color(17, 24, 39));
				btnTab1.setForeground(new Color(244, 245, 249));
				btnTab1.setBackground(new Color(17, 24, 39));
			}
		});
		
		/* ================= ACTION LISTENER CHO BUTTON TAB =============== */
		
		/*================== MULTI-THREAD ==========================*/
//		Thread listen = new Thread(() -> {
//			while (true) {
//				try {
//					String message = this.reader.readLine();
//					
//					if (message != null) {
//						if (message.equals("SYSTEM_ADD_A_CUSTOMER")) {
//							updateBillTable();
//							updateCustomerTable();
//							updateRoomTable();
//						}
//						if (message.equals("SYSTEM_DELETE_A_CUSTOMER")) {
//							updateBillTable();
//							updateCustomerTable();
//							updateRoomTable();
//						}
//						if (message.equals("SYSTEM_UPDATE_A_CUSTOMER")) {
//							updateBillTable();
//							updateCustomerTable();
//							updateRoomTable();
//						}
//						if (message.equals("SYSTEM_ABATE_A_BILL")) {
//							updateBillTable();
//							updateCustomerTable();
//							updateRoomTable();
//						}
//					}
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//					System.out.println("Try-catch:"+e1.getMessage());
//				}
//			}
//		});
//		listen.start();
//		
//		JButton btnNewButton = new JButton("");
//		btnNewButton.setFocusable(false);
//		btnNewButton.setBorderPainted(false);
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int respone = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn đăng xuất ???");
//				if (respone == JOptionPane.OK_OPTION) {
//					setVisible(false);
//					new LoginGUI().setVisible(true);
//					listen.interrupt();
//					try {
//						EmployeeGUI.this.socket.close();
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
//			}
//		});
//		btnNewButton.setFocusable(false);
//		btnNewButton.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-log-out-48.png")));
//		btnNewButton.setBackground(new Color(17, 24, 39));
//		btnNewButton.setBounds(10, 428, 40, 42);
//		panel.add(btnNewButton);
		
		
		AtomicBoolean running = new AtomicBoolean(true);
		Thread listen = new Thread(() -> {
		    try {
		        while (running.get() && !Thread.currentThread().isInterrupted()) {
		            String message = this.reader.readLine();
		            if (message == null) break; // Dừng nếu đầu vào đã kết thúc.

//		            switch (message) {
//		                case "SYSTEM_ADD_A_CUSTOMER":
//		                case "SYSTEM_DELETE_A_CUSTOMER":
//		                case "SYSTEM_UPDATE_A_CUSTOMER":
//		                case "SYSTEM_ABATE_A_BILL":
//		                    updateBillTable();
//		                    updateCustomerTable();
//		                    updateRoomTable();
//		                    break;
//		               
//		            }
		            
		            if (message.equals("SYSTEM_ADD_A_CUSTOMER") || message.equals("SYSTEM_DELETE_A_CUSTOMER")
		             || message.equals("SYSTEM_UPDATE_A_CUSTOMER") || message.equals("SYSTEM_ABATE_A_BILL")) {
	                    updateBillTable();
	                    updateCustomerTable();
	                    updateRoomTable();
	                    try {
							Thread.sleep(1500);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	            		int totalBookingByDate = new BillBUS().getTotalBookingByDate(today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	            		label_TotalBookingThisDate.setText("Số lượt đặt phòng: "+totalBookingByDate);
					}
		            
		            if (message.startsWith("ONLINE_USERS:")) {
						System.out.println(message);
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								updateOnlinePanel(message.substring(13));
							}
						});
					}
		            if (message.equals("NEW_MESSAGE")) {
						String dateString = this.reader.readLine();
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						java.util.Date parsedDate = dateFormat.parse(dateString);
						Timestamp time = new Timestamp(parsedDate.getTime());
						String content = this.reader.readLine();
						String senderUserName = this.reader.readLine();
						String senderName = new ManagerBUS().getEmpNameByUserName(senderUserName);
						displayMessArea.append(time + "\n" +senderName +": "+content +"\n\n");
						displayMessArea.setCaretPosition(displayMessArea.getDocument().getLength());
					}
		        }
		    } catch (IOException | ParseException e) {
		        if (!running.get()) {
		            System.out.println("Thread is stopping because the flag was set to false.");
		        } else {
		            e.printStackTrace();
		            System.out.println("IO Exception: " + e.getMessage());
		        }
		    }
		});
		listen.start();

		// Cập nhật nút đăng xuất
		JButton btn_LogOut = new JButton("");
		btn_LogOut.setFocusable(false);
		btn_LogOut.setBorderPainted(false);
		btn_LogOut.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int response = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn đăng xuất ???");
		        if (response == JOptionPane.OK_OPTION) {
		            setVisible(false);
		            new LoginGUI().setVisible(true);
		            running.set(false); // Đặt cờ để dừng vòng lặp trong thread.
		            listen.interrupt(); // Yêu cầu dừng luồng.
		            try {
		                EmployeeGUI.this.socket.close();
		            } catch (IOException e1) {
		                e1.printStackTrace();
		            }
		        }
		    }
		});
		btn_LogOut.setFocusable(false);
		btn_LogOut.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-log-out-48.png")));
		btn_LogOut.setBackground(new Color(17, 24, 39));
		btn_LogOut.setBounds(10, 434, 40, 42);
		panel.add(btn_LogOut);
		
		loadMessage();
		displayMessArea.setCaretPosition(displayMessArea.getDocument().getLength());
		
		/* ===========  EMPLOYEE INFORMATION AREA  ================ */ 
		Employee info = new ManagerBUS().getEmployeeById(this.idEmp);
		
		JPanel tab6 = new JPanel();
		tab6.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tab6.setForeground(new Color(17, 24, 39));
		tab6.setBackground(new Color(244, 245, 249));
		tabbedPane.addTab("Tab6", null, tab6, null);
		tab6.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-user-96.png")));
		lblNewLabel_2.setBounds(25, 131, 96, 96);
		tab6.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Thông tin nhân viên");
		lblNewLabel_3.setForeground(SystemColor.desktop);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(0, 13, 797, 60);
		tab6.add(lblNewLabel_3);
		
		JLabel lblNewLabel_7 = new JLabel("Họ và tên");
		lblNewLabel_7.setForeground(SystemColor.desktop);
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_7.setBounds(169, 131, 83, 27);
		tab6.add(lblNewLabel_7);
		
		textFieldEmpName = new JTextField();
		textFieldEmpName.setDisabledTextColor(Color.DARK_GRAY);
		textFieldEmpName.setText(info.getName());
		textFieldEmpName.setForeground(SystemColor.desktop);
		textFieldEmpName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textFieldEmpName.setBorder(new LineBorder(new Color(17, 24, 39)));
		textFieldEmpName.setBounds(169, 159, 207, 35);
		tab6.add(textFieldEmpName);
		textFieldEmpName.setColumns(10);
		textFieldEmpName.setEnabled(false);
		
		JLabel label_id_info = new JLabel("ID:"+info.getId());
		label_id_info.setHorizontalAlignment(SwingConstants.CENTER);
		label_id_info.setForeground(SystemColor.desktop);
		label_id_info.setFont(new Font("Segoe UI", Font.BOLD, 16));
		label_id_info.setBounds(25, 222, 96, 27);
		tab6.add(label_id_info);
		
		JLabel lblNewLabel_7_2 = new JLabel("Ngày sinh");
		lblNewLabel_7_2.setForeground(SystemColor.desktop);
		lblNewLabel_7_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_7_2.setBounds(449, 131, 83, 27);
		tab6.add(lblNewLabel_7_2);
		
		textFieldBirthDateEmp = new JTextField();
		textFieldBirthDateEmp.setDisabledTextColor(Color.DARK_GRAY);
		textFieldBirthDateEmp.setText(info.getBirthDate().toString());
		textFieldBirthDateEmp.setForeground(SystemColor.desktop);
		textFieldBirthDateEmp.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textFieldBirthDateEmp.setBorder(new LineBorder(new Color(17, 24, 39)));
		textFieldBirthDateEmp.setColumns(10);
		textFieldBirthDateEmp.setBounds(449, 159, 207, 35);
		tab6.add(textFieldBirthDateEmp);
		textFieldBirthDateEmp.setEnabled(false);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(new Color(17, 24, 39));
		separator_3.setBackground(new Color(17, 24, 39));
		separator_3.setBounds(10, 98, 777, 2);
		tab6.add(separator_3);
		
		JLabel lblNewLabel_7_3 = new JLabel("Email");
		lblNewLabel_7_3.setForeground(SystemColor.desktop);
		lblNewLabel_7_3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_7_3.setBounds(169, 216, 83, 27);
		tab6.add(lblNewLabel_7_3);
		
		textField_EmailEmp = new JTextField();
		textField_EmailEmp.setDisabledTextColor(Color.DARK_GRAY);
		textField_EmailEmp.setText(info.getEmail());
		textField_EmailEmp.setForeground(SystemColor.desktop);
		textField_EmailEmp.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_EmailEmp.setColumns(10);
		textField_EmailEmp.setBorder(new LineBorder(new Color(17, 24, 39)));
		textField_EmailEmp.setBounds(169, 244, 207, 35);
		tab6.add(textField_EmailEmp);
		textField_EmailEmp.setEnabled(false);
		
		JLabel lblNewLabel_7_2_1 = new JLabel("Giới tính");
		lblNewLabel_7_2_1.setForeground(SystemColor.desktop);
		lblNewLabel_7_2_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_7_2_1.setBounds(449, 216, 83, 27);
		tab6.add(lblNewLabel_7_2_1);
		
		textField_usernameEmp = new JTextField();
		textField_usernameEmp.setDisabledTextColor(Color.DARK_GRAY);
		textField_usernameEmp.setText(info.getUserName());
		textField_usernameEmp.setForeground(SystemColor.desktop);
		textField_usernameEmp.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_usernameEmp.setColumns(10);
		textField_usernameEmp.setBorder(new LineBorder(new Color(17, 24, 39)));
		textField_usernameEmp.setBounds(169, 328, 207, 35);
		tab6.add(textField_usernameEmp);
		textField_usernameEmp.setEnabled(false);
		
		JLabel lblNewLabel_7_3_2 = new JLabel("Tên tài khoản");
		lblNewLabel_7_3_2.setForeground(SystemColor.desktop);
		lblNewLabel_7_3_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_7_3_2.setBounds(169, 300, 139, 27);
		tab6.add(lblNewLabel_7_3_2);
		
		JLabel lblNewLabel_7_3_2_1 = new JLabel("Mật khẩu");
		lblNewLabel_7_3_2_1.setForeground(SystemColor.desktop);
		lblNewLabel_7_3_2_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_7_3_2_1.setBounds(449, 300, 139, 27);
		tab6.add(lblNewLabel_7_3_2_1);
		
		textField_passwordEmp = new JTextField();
		textField_passwordEmp.setDisabledTextColor(Color.DARK_GRAY);
		textField_passwordEmp.setText(info.getPassword());
		textField_passwordEmp.setForeground(SystemColor.desktop);
		textField_passwordEmp.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		textField_passwordEmp.setColumns(10);
		textField_passwordEmp.setBorder(new LineBorder(new Color(17, 24, 39)));
		textField_passwordEmp.setBounds(449, 328, 207, 35);
		tab6.add(textField_passwordEmp);
		textField_passwordEmp.setEnabled(false);
		
		JButton btnEditEmpInfo = new JButton("EDIT");
		btnEditEmpInfo.setFocusable(false);
		btnEditEmpInfo.setBackground(new Color(244, 245, 249));
		btnEditEmpInfo.setBorder(new LineBorder(new Color(17, 24, 39), 2));
		btnEditEmpInfo.setForeground(new Color(17, 24, 39));
		btnEditEmpInfo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnEditEmpInfo.setBounds(449, 392, 89, 35);
		tab6.add(btnEditEmpInfo);
		
		JButton btnUpdateEmpInfo = new JButton("SAVE");
		btnUpdateEmpInfo.setFocusable(false);
		btnUpdateEmpInfo.setForeground(new Color(17, 24, 39));
		btnUpdateEmpInfo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnUpdateEmpInfo.setBorder(new LineBorder(new Color(17, 24, 39), 2));
		btnUpdateEmpInfo.setBackground(new Color(244, 245, 249));
		btnUpdateEmpInfo.setBounds(562, 392, 89, 35);
		tab6.add(btnUpdateEmpInfo);
		
		JRadioButton rdbtn_Man = new JRadioButton("Nam");
		rdbtn_Man.setForeground(SystemColor.desktop);
		rdbtn_Man.setFont(new Font("Segoe UI", Font.BOLD, 14));
		rdbtn_Man.setBackground(new Color(244, 245, 249));
		rdbtn_Man.setBounds(449, 244, 58, 35);
		tab6.add(rdbtn_Man);
		
		JRadioButton rdbtn_Woman = new JRadioButton("Nữ");
		rdbtn_Woman.setForeground(SystemColor.desktop);
		rdbtn_Woman.setFont(new Font("Segoe UI", Font.BOLD, 14));
		rdbtn_Woman.setBackground(new Color(244, 245, 249));
		rdbtn_Woman.setBounds(520, 244, 109, 35);
		tab6.add(rdbtn_Woman);
		
		btnGroupSex = new ButtonGroup();
		btnGroupSex.add(rdbtn_Man);
		btnGroupSex.add(rdbtn_Woman);
		
		if (info.isSex()) {
			rdbtn_Man.setSelected(true);
		} else {
			rdbtn_Woman.setSelected(true);
		}
		rdbtn_Man.setEnabled(false);
		rdbtn_Woman.setEnabled(false);
		
		/* ====== ACTIONLISTENER FOR BUTTON TAB6 ===========*/
		btnEditEmpInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldEmpName.setEnabled(true);
				textFieldBirthDateEmp.setEnabled(true);
				textField_EmailEmp.setEnabled(true);
				rdbtn_Man.setEnabled(true);
				rdbtn_Woman.setEnabled(true);
				textField_usernameEmp.setEnabled(true);
				textField_passwordEmp.setEnabled(true);
			}
		});
		
		btnUpdateEmpInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Lấy thông tin từ form
				String name = textFieldEmpName.getText().trim();
				Date birthdate = Date.valueOf(textFieldBirthDateEmp.getText().trim());
				String email = textField_EmailEmp.getText().trim();
				String username = textField_usernameEmp.getText().trim();
				String password = textField_passwordEmp.getText().trim();
				boolean sex = true;
				
				// Kiểm tra có mục nào để trống hay không
				if (name.isEmpty() || birthdate.toString().isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || (!rdbtn_Man.isSelected() && !rdbtn_Woman.isSelected())) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
					return;
				}
				
				// Kiểm tra định dạng ngày sinh theo yyyy-MM-dd
				if (!birthdate.toString().matches(birthDateRegex)) {
					JOptionPane.showMessageDialog(null, "Nhập ngày sinh theo định dạng yyyy-MM-dd");
					return;
				}
				
				// Kiểm tra định dạng email
				if (!email.matches(emailRegex)) {
					System.out.println(email);
					JOptionPane.showMessageDialog(null, "Định dạng email chưa đúng");
					return;
				}
				
				// Kiểm tra username có trùng với nhân viên khác hay không
				if (!username.equals(EmployeeGUI.this.username)) {
					if (isDuplicateUserName(username)) {
						JOptionPane.showMessageDialog(null, "Tên tài khoản đã được sử dụng trong hệ thống");
						return;
					}
				}
				
				if (password.length() < 8 || password.contains(" ")) {
					JOptionPane.showMessageDialog(null, "Mật khẩu không được ít hơn 8 kí tự và không chứa dấu cách");
					return;
				}
				
				if (rdbtn_Man.isSelected()) {
					sex = true;
				} else if (rdbtn_Woman.isSelected()) {
					sex = false;
				}
				
				// Tiến hành lưu thay đổi ở cơ sở dữ liệu
				new ManagerBUS().changeEmployeeInfo(EmployeeGUI.this.idEmp,name,birthdate,email,sex,username,password);
				
				// Disable form
				textFieldEmpName.setEnabled(false);
				textFieldBirthDateEmp.setEnabled(false);
				textField_EmailEmp.setEnabled(false);
				rdbtn_Man.setEnabled(false);
				rdbtn_Woman.setEnabled(false);
				textField_usernameEmp.setEnabled(false);
				textField_passwordEmp.setEnabled(false);
				
				// Thay đổi tên ở sidebar
				EmployeeName = new ManagerBUS().getEmpNameById(idEmp);
				label_EmployeeName.setText(EmployeeName + ":V");
				/*
				 * // Phần này giữ lại EmployeeName = new ManagerBUS().getEmpNameById(idEmp);
				 * label_EmployeeName.setText(EmployeeName + ":V");
				 * EmployeeGUI.this.setVisible(false); new LoginGUI().setVisible(true);
				 * running.set(false); // Đặt cờ để dừng vòng lặp trong thread.
				 * listen.interrupt(); // Yêu cầu dừng luồng. try {
				 * EmployeeGUI.this.socket.close(); } catch (IOException e1) {
				 * e1.printStackTrace(); }
				 */
				
				// TEST
				EmployeeGUI.this.writer.println("UPDATE_EMP_INFO");
				EmployeeGUI.this.writer.println(username);
				EmployeeGUI.this.username = username;
			}
		});
		
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

	private void loadMessage() {
		ArrayList<String> messages = new HistoryBUS().getMessages();
		for (String string : messages) {
			displayMessArea.append(string +"\n\n");
		}
	}

	protected void updateOnlinePanel(String users) {
		System.out.println("Updating online panel with users: "+users);
		panel_OnLineList.removeAll();
		String[] userList = users.split(",");
		int x = 5, y =5, width = 181, height = 28;
		for (String user : userList) {
			String name = new HistoryDAO().getNameByUserName(user);
			JLabel userLabel = new JLabel(name);
			userLabel.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-dot-20.png")));
			userLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
			userLabel.setForeground(new Color(SystemColor.DESKTOP));
			userLabel.setBounds(x, y, width, height);
			userLabel.setHorizontalAlignment(SwingConstants.LEFT);
			panel_OnLineList.add(userLabel);
			y = y + height + 10;
			System.out.println("Added user: "+user);
		}
		panel_OnLineList.revalidate();
		panel_OnLineList.repaint();
	}

	public void updateBillTable() {
		clearBillTable();
		ArrayList<Bill> bills = new BillBUS().getListBill();
		DefaultTableModel model = (DefaultTableModel) table_Bill.getModel();
		for (Bill bill : bills) {
			model.addRow(bill.toObject());
		}
	}

	public void clearBillTable() {
		DefaultTableModel model = (DefaultTableModel) table_Bill.getModel();
		int rowCount = model.getRowCount();
		
		for (int i = rowCount - 1; i >= 0 ; i--) {
			model.removeRow(i);
		}
	}

	public void updateRoomTable() {
		clearRoomTable();
		DefaultTableModel model = (DefaultTableModel) table_Room.getModel();
		ArrayList<Room> rooms = new RoomBUS().getListRooms();

		for (Room room : rooms) {
			model.addRow(room.toObjects());
			
		}
	}

	public void clearRoomTable() {
		DefaultTableModel model = (DefaultTableModel) table_Room.getModel();
		int rowCount = model.getRowCount();

		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}

	public void updateCustomerTable() {
		clearCustomerTable();
		DefaultTableModel model = (DefaultTableModel) table_Customer.getModel();
		ArrayList<Customer> customers = new EmployeeBUS().getListCustomer();
		for (Customer customer : customers) {
			model.addRow(customer.toObjects());
		}
	}

	public void clearCustomerTable() {
		DefaultTableModel model = (DefaultTableModel) table_Customer.getModel();
		int rowCount = model.getRowCount();

		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}

	public void setEmptyForm() {
		textField_Name.setText(null);
		textField_birthDate.setText(null);
		textField_CCCD.setText(null);
		textField_roomNumber.setText(null);
	}

	public void addCustomer() {
		String name = textField_Name.getText();
		String cccd = textField_CCCD.getText();
		String birthDateText = textField_birthDate.getText();
		String roomNumberText = textField_roomNumber.getText();
		if (name.isBlank() || cccd.isBlank() || birthDateText.isBlank() || roomNumberText.isBlank()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
			return;
		}
		
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) >= '0' && name.charAt(i) <= '9') {
				JOptionPane.showMessageDialog(null, "Tên không hợp lệ");
				return;
			}
		}
		
		if (!birthDateText.matches(birthDateRegex)) {
			JOptionPane.showMessageDialog(null, "Nhập ngày sinh theo định dạng yyyy-mm-yy");
			return;
		}
		
		if (!cccd.matches(regex_cccd)) {
			JOptionPane.showMessageDialog(null, "Số CCCD không đúng định dạng");
			return;
		}
		
		int roomNumber = Integer.parseInt(roomNumberText);
		Room room = new Room();
		
		if (new RoomBUS().isOrdered(roomNumber)) {
			JOptionPane.showMessageDialog(null, "Phòng " + roomNumber + " đã được đặt");
			return;
		}

		if (roomNumber < 1 || roomNumber > 40) {
			JOptionPane.showMessageDialog(null, "Số phòng không hợp lệ");
			return;
		}
		
		if (new EmployeeBUS().isDuplicateCCCD(cccd)) {
			// Nếu trùng số CCCD nhưng khác tên hoặc ngày sinh thì báo lỗi
			if (!new EmployeeBUS().isDuplicateName(name, cccd)
					|| !new EmployeeBUS().isDuplicateBirthDate(birthDateText, cccd)) {
				JOptionPane.showMessageDialog(null, "Số căn cước công dân bị trùng với người khác");
			} else {
				addCustomerAndBill(cccd, name, birthDateText, roomNumber);
			}
		}else {
			addCustomerAndBill(cccd, name, birthDateText, roomNumber);
		}
		updateBillTable();
		updateCustomerTable();
	}

	private void addCustomerAndBill(String cccd, String name, String birthDateText, int roomNumber) {
		Date birthDate = Date.valueOf(birthDateText);
//		Date dateOrder = new Date(System.currentTimeMillis();
		Timestamp dateOrder = new Timestamp(System.currentTimeMillis());
		
		Customer customer = new Customer(name, cccd, birthDate, idEmp);
		Bill bill = new Bill(roomNumber, cccd, dateOrder);

		new EmployeeBUS().addCustomer(customer);
		new BillBUS().addBill(bill);
		setEmptyForm();

		writer.println("THÊM");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int numberOfCustomerThisDay = new EmployeeDAO().getTotalCustomerThisDay(idEmp);
		lblBnPhc.setText("Bạn đã phục vụ: "+numberOfCustomerThisDay+" khách hàng");
		
		int totalBookingByDate = new BillBUS().getTotalBookingByDate(today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		System.out.println("Đã thêm 1 khách hàng, số lượt đặt phòng hiện tại "+totalBookingByDate);
		label_TotalBookingThisDate.setText("Số lượt đặt phòng: "+totalBookingByDate);
	}

	
	public void deleteCustomer() {
		DefaultTableModel model = (DefaultTableModel) table_Customer.getModel();
		int rowIndex = table_Customer.getSelectedRow();
		
		if (!table_Customer.isRowSelected(rowIndex)) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 khách hàng để xóa");
			return;
		}
		
		int id = Integer.parseInt((String) model.getValueAt(rowIndex, 0));
		new EmployeeBUS().deleteCustomer(id);
		updateBillTable();
		updateCustomerTable();
		
		writer.println("XÓA");
		int numberOfCustomerThisDay = new EmployeeDAO().getTotalCustomerThisDay(idEmp);
		lblBnPhc.setText("Bạn đã phục vụ: "+numberOfCustomerThisDay+" khách hàng");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int totalBookingByDate = new BillBUS().getTotalBookingByDate(today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		label_TotalBookingThisDate.setText("Có "+totalBookingByDate+" lượt đặt phòng");
	}

	public void setForm() {
		DefaultTableModel model = (DefaultTableModel) table_Customer.getModel();
		int rowIndex = table_Customer.getSelectedRow();
		
		if (!table_Customer.isRowSelected(rowIndex)) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 khách hàng");
			return;
		}
		int id = Integer.valueOf((String) model.getValueAt(rowIndex, 0));
		String name = (String) model.getValueAt(rowIndex, 1);
		Date birthDate = (Date) model.getValueAt(rowIndex, 3);
		String cccd = String.valueOf(model.getValueAt(rowIndex, 2));
		int roomNumber = new BillBUS().getRoomNumberByID(id);
		
		textField_Name.setText(name);
		textField_CCCD.setText(cccd);
		textField_birthDate.setText(birthDate+"");
		textField_roomNumber.setText(roomNumber+"");
	}

	public void updateCustomer() {
		DefaultTableModel model = (DefaultTableModel) table_Customer.getModel();
		int rowIndex = table_Customer.getSelectedRow();
		
		if (!table_Customer.isRowSelected(rowIndex)) {
			JOptionPane.showMessageDialog(null, "Hãy chọn 1 khách hàng để cập nhật");
			return;
		}
		
		if (textField_Name.getText().isBlank()||textField_CCCD.getText().isBlank()|| textField_birthDate.getText().isBlank()||textField_roomNumber.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
			return;
		}
		
		if (!textField_CCCD.getText().matches(regex_cccd)) {
			JOptionPane.showMessageDialog(null, "Số CCCD không đúng");
			return;
		}
		
		// Kiểm tra số CCCD
		String oldCCCD = String.valueOf(model.getValueAt(rowIndex, 2));
		String newCCCD = textField_CCCD.getText();
		
		// Nếu có sự thay đổi CCCD, kiểm tra sự trùng lặp 
		if (!newCCCD.equals(oldCCCD)) {
			// Nếu CCCD mới trùng với CCCD đã có trong danh sách, kiểm tra trùng tên
			if (new EmployeeBUS().isDuplicateCCCD(newCCCD)) {
				// Nếu trùng số CCCD mà khác tên hoặc ngày sinh thì báo lỗi
				if (new EmployeeBUS().isDuplicateName(textField_Name.getText(), newCCCD)
						|| new EmployeeBUS().isDuplicateBirthDate(textField_birthDate.getText(), newCCCD)) {
					JOptionPane.showMessageDialog(null, "Số CCCD bị trùng");
					return;
				}
			}
		}
		
		if (!textField_birthDate.getText().matches(birthDateRegex)) {
			JOptionPane.showMessageDialog(null, "Nhập ngày sinh theo định dạng yyyy-MM-dd");
			return;
		}
		
		int stt = Integer.valueOf((String) model.getValueAt(rowIndex, 0));
		int oldRoomNumber = new BillBUS().getRoomNumberByID(stt);
		int newRoomNumber = Integer.valueOf(textField_roomNumber.getText());
		
		// Thay đổi phòng, nếu khách đã trả phòng thì thay đổi tùy ý
		// Nếu khách chưa trả phòng thì kiểm tra phòng mới đã được đặt hay chưa
		
		// Nếu khách hàng đó chưa trả phòng mà thay đổi thông tin về phòng
		//--> kiểm tra
		if (!new BillBUS().isRoomReturned(stt)) {
			if (newRoomNumber != oldRoomNumber && new RoomBUS().isOrdered(newRoomNumber)) {
				JOptionPane.showMessageDialog(null, "Phòng "+newRoomNumber+" đã được đặt");
				return;
			}
		}
		
		Date birthDate = Date.valueOf(textField_birthDate.getText());
		Customer customer = new Customer(stt, textField_Name.getText(), newCCCD, birthDate, idEmp);
		Bill bill = new Bill(stt, newRoomNumber, newCCCD);
		new EmployeeBUS().updateCustomer(customer);
		new BillBUS().updateBill(bill);
		updateCustomerTable();
		updateBillTable();
		setEmptyForm();
		
		writer.println("CẬP NHẬT");
	}

	public void abateBill() {
		DefaultTableModel model = (DefaultTableModel) table_Bill.getModel();
		int rowIndex = table_Bill.getSelectedRow();
		
		if (!table_Bill.isRowSelected(rowIndex)) {
			JOptionPane.showMessageDialog(null, "Hãy chọn 1 khách hàng để thanh toán");
			return;
		}
		
		int id = Integer.valueOf((String)model.getValueAt(rowIndex, 0));
		boolean isAbated = new BillBUS().isBillAbated(id);
		
		if (isAbated) {
			JOptionPane.showMessageDialog(null, "Hóa đơn này đã được thanh toán, hãy chọn hóa đơn khác");
			return;
		}
		
		Timestamp dateOrder = (Timestamp) model.getValueAt(rowIndex, 3);
		Timestamp dateReturn = new Timestamp(System.currentTimeMillis());
		
		LocalDateTime start = dateOrder.toLocalDateTime();
		LocalDateTime end = dateReturn.toLocalDateTime();
		Duration duration = Duration.between(start, end);
		
		int roomNumber = Integer.valueOf((String) model.getValueAt(rowIndex, 2));
		String CCCD = String.valueOf(model.getValueAt(rowIndex, 1));
		
		double hours = duration.toHours();
		long price = (long) (hours*80000);
		
		new BillBUS().abateBill(id, price, dateReturn);
		updateBillTable();
		writer.println("THANH TOÁN");
	}

	public void showBill() {
		DefaultTableModel model = (DefaultTableModel) table_Bill.getModel();
		int rowIndex = table_Bill.getSelectedRow();
		
		if (!table_Bill.isRowSelected(rowIndex)) {
			JOptionPane.showMessageDialog(null, "Hãy chọn 1 khách hàng để in hóa đơn");
			return;
		}
		
		int id = Integer.valueOf((String)model.getValueAt(rowIndex, 0));
		boolean isAbated = new BillBUS().isBillAbated(id);
		
		if (!isAbated) {
			JOptionPane.showMessageDialog(null, "Hóa đơn này chưa được thanh toán, hãy chọn hóa đơn khác");
			return;
		}
		
		String CCCD = String.valueOf(model.getValueAt(rowIndex, 1));
		int roomNumber = Integer.valueOf((String) model.getValueAt(rowIndex, 2));
		Timestamp dateOrder = (Timestamp)model.getValueAt(rowIndex, 3);
		Timestamp dateReturn = (Timestamp) model.getValueAt(rowIndex, 4);
		long price = (Long)model.getValueAt(rowIndex, 5);
		
		LocalDateTime start = dateOrder.toLocalDateTime();
		LocalDateTime end = dateReturn.toLocalDateTime();
		Duration duration = Duration.between(start, end);
		
		double hours = duration.toHours();
		new BillGUI(id, roomNumber, CCCD, dateOrder, dateReturn, price, hours).setVisible(true);;
	}
	
	public void toExcel() throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		int option = fileChooser.showSaveDialog(EmployeeGUI.this);
		DefaultTableModel model = (DefaultTableModel) table_Bill.getModel();
		
		if (option == fileChooser.APPROVE_OPTION) {
			String filePath = fileChooser.getSelectedFile().getAbsolutePath();
			
			if (!filePath.toLowerCase().endsWith(".xlsx")) {
				filePath += ".xlsx";
			}
			
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("Bill List");
			
			Row headRow = sheet.createRow(0);
			
			for (int col = 0; col < model.getColumnCount(); col++) {
				Cell cell = headRow.createCell(col);
				cell.setCellValue(String.valueOf(model.getColumnName(col)));
			}
			
			for (int row = 0; row < model.getRowCount(); row++) {
				Row dataRow = sheet.createRow(row+1);
				for (int col = 0; col < model.getColumnCount(); col++) {
					Cell cell = dataRow.createCell(col);
					String data = String.valueOf(model.getValueAt(row, col));
					if (data == null) {
						data = "";
					}
					cell.setCellValue(String.valueOf(model.getValueAt(row, col)));
				}
			}
			
			FileOutputStream fileOutputStream = new FileOutputStream(filePath);
			workbook.write(fileOutputStream);
			workbook.close();
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSendMessage) {
			String message = typeArea.getText();
			if (!message.isEmpty()) {
				Timestamp time = new Timestamp(System.currentTimeMillis());
				writer.println("GỬI");
				writer.println(time);
				writer.println(message);
				typeArea.setText("");
//				String name = new HistoryBUS().getNameByUserName(EmployeeGUI.this.username);
				EmployeeName = new ManagerBUS().getEmpNameById(idEmp);
				displayMessArea.append(time + "\n" + EmployeeName + ": " + message + "\n\n");
				displayMessArea.setCaretPosition(displayMessArea.getDocument().getLength());
			}
		}
	}
}

