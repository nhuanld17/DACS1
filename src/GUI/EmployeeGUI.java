package GUI;

import static org.hamcrest.CoreMatchers.nullValue;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
import BUS.ManagerBUS;
import BUS.RoomBUS;
import CONTROLLER.EmployeeController;
import DAO.EmployeeDAO;
import DTO.Bill;
import DTO.Customer;
import DTO.Room;

public class EmployeeGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_Name;
	private JTextField textField_CCCD;
	private JTextField textField_birthDate;
	private JTextField textField_roomNumber;
	private JTable table_Customer;
	private String birthDateRegex = "^(?:(?:19|20)\\d\\d)-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01]|(?:0[1-9]|1\\d|2[0-8]))|(?:19|20)\\d\\d-(0[1-9]|1[0-2])-(29|30)(?:-0[1-9]|-1[0-9]|-2[0-8])|(?:19|20)(?:0[48]|[2468][048]|[13579][26])-02-29$";
	public String regex_cccd = "^(01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64)[0-9]{10}$";
	public int idEmp;
	private JTable table_Room;
	private JTable table_Bill;
	private JTextField textField_FindByName;
	private JTextField textField_FindByCCCD;
	private String EmployeeName;
	private JLabel label_TotalCustomerThisDay;
	private String username;

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
	 */
	public EmployeeGUI(int idEmp, String userName) {
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
		lblNewLabel.setBounds(260, 38, 255, 36);
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
		table_Customer.setFillsViewportHeight(true);

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
//		tab1.add(table_Customer);

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
					model.addRow(bill.toObject());
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
		btn_Abate.setBounds(275, 109, 114, 30);
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
		label_TotalCustomerThisDay = new JLabel("Hôm nay, bạn đã phục vụ: "+numberOfCustomerThisDay+" khách hàng");

		label_TotalCustomerThisDay.setForeground(SystemColor.desktop);
		label_TotalCustomerThisDay.setFont(new Font("Segoe UI", Font.BOLD, 14));
		label_TotalCustomerThisDay.setBounds(10, 354, 291, 22);
		tab3.add(label_TotalCustomerThisDay);
		
		JButton btn_CustomerChart = new JButton("XEM BIỂU ĐỒ");
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
		btn_CustomerChart.setBounds(294, 353, 114, 30);
		tab3.add(btn_CustomerChart);

		JPanel tab4 = new JPanel();
		tab4.setBackground(new Color(244, 245, 249));
		tabbedPane.addTab("Tab4", null, tab4, null);
		tab4.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Tab4");
		lblNewLabel_3.setBounds(0, 0, 81, 24);
		tab4.add(lblNewLabel_3);

		JButton btnTab1 = new JButton("Khách hàng");
		btnTab1.setHorizontalAlignment(SwingConstants.LEFT);
		btnTab1.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-online-support-30.png")));
		btnTab1.setBackground(new Color(55, 65, 81));
		btnTab1.setForeground(new Color(244, 245, 249));
		btnTab1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTab1.setBorderPainted(false);
		btnTab1.setFocusable(false);
		btnTab1.setBounds(0, 165, 162, 42);
		panel.add(btnTab1);

		JButton btnTab2 = new JButton("DS Phòng");
		btnTab2.setHorizontalAlignment(SwingConstants.LEFT);
		btnTab2.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-room-30.png")));
		btnTab2.setBackground(new Color(17, 24, 39));
		btnTab2.setForeground(new Color(244, 245, 249));
		btnTab2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTab2.setFocusable(false);
		btnTab2.setBorderPainted(false);
		btnTab2.setBounds(0, 209, 162, 42);
		panel.add(btnTab2);

		JButton btnTab3 = new JButton("DS Hóa Đơn");
		btnTab3.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-bill-30.png")));
		btnTab3.setHorizontalAlignment(SwingConstants.LEFT);
		btnTab3.setBackground(new Color(17, 24, 39));
		btnTab3.setForeground(new Color(244, 245, 249));
		btnTab3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTab3.setFocusable(false);
		btnTab3.setBorderPainted(false);
		btnTab3.setBounds(0, 253, 162, 42);
		panel.add(btnTab3);

		JButton btnTab4 = new JButton("Tab4");
		btnTab4.setHorizontalAlignment(SwingConstants.LEFT);
		btnTab4.setForeground(new Color(244, 245, 249));
		btnTab4.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTab4.setBackground(new Color(17, 24, 39));
		btnTab4.setFocusable(false);
		btnTab4.setBorderPainted(false);
		btnTab4.setBounds(0, 297, 162, 42);
		panel.add(btnTab4);

		JButton btnNewButton = new JButton("");
		btnNewButton.setFocusable(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respone = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn thoát ???");
				if (respone == JOptionPane.OK_OPTION) {
					setVisible(false);
					new LoginGUI().setVisible(true);
				}
			}
		});
		btnNewButton.setFocusable(false);
		btnNewButton.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-log-out-48.png")));
		btnNewButton.setBackground(new Color(17, 24, 39));
		btnNewButton.setBounds(10, 428, 40, 42);
		panel.add(btnNewButton);
		
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
		
		JLabel label_EmployeeName = new JLabel(EmployeeName+":V");
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
		separator_2_1.setBounds(10, 416, 142, 1);
		panel.add(separator_2_1);

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
		/* ================= ACTION LISTENER CHO BUTTON TAB =============== */
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
		int numberOfCustomerThisDay = new EmployeeDAO().getTotalCustomerThisDay(idEmp);
		label_TotalCustomerThisDay.setText("Hôm nay, bạn đã phục vụ: "+numberOfCustomerThisDay+" khách hàng");
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
}

