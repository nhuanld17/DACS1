package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

import BUS.BillBUS;
import BUS.EmployeeBUS;
import BUS.RoomBUS;
import CONTROLLER.EmployeeController;
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
	 */
	public EmployeeGUI(int idEmp) {
		this.idEmp = idEmp;
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
		lblNewLabel.setBounds(264, 11, 255, 36);
		tab1.add(lblNewLabel);

		JLabel lblNewLabel_4 = new JLabel("Họ và tên");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_4.setForeground(SystemColor.desktop);
		lblNewLabel_4.setBounds(10, 97, 73, 20);
		tab1.add(lblNewLabel_4);

		textField_Name = new JTextField();
		textField_Name.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField_Name.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		textField_Name.setForeground(SystemColor.desktop);
		textField_Name.setBounds(93, 94, 176, 28);
		tab1.add(textField_Name);
		textField_Name.setColumns(10);

		JLabel lblNewLabel_4_1 = new JLabel("CCCD");
		lblNewLabel_4_1.setForeground(SystemColor.desktop);
		lblNewLabel_4_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_4_1.setBounds(10, 146, 73, 20);
		tab1.add(lblNewLabel_4_1);

		textField_CCCD = new JTextField();
		textField_CCCD.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		textField_CCCD.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField_CCCD.setForeground(SystemColor.desktop);
		textField_CCCD.setColumns(10);
		textField_CCCD.setBounds(93, 143, 176, 28);
		tab1.add(textField_CCCD);

		JLabel lblNewLabel_4_2 = new JLabel("Ngày sinh");
		lblNewLabel_4_2.setForeground(SystemColor.desktop);
		lblNewLabel_4_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_4_2.setBounds(292, 96, 83, 25);
		tab1.add(lblNewLabel_4_2);

		textField_birthDate = new JTextField();
		textField_birthDate.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		textField_birthDate.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField_birthDate.setForeground(SystemColor.desktop);
		textField_birthDate.setColumns(10);
		textField_birthDate.setBounds(375, 94, 176, 28);
		tab1.add(textField_birthDate);

		JLabel lblNewLabel_4_3 = new JLabel("Số phòng");
		lblNewLabel_4_3.setForeground(SystemColor.desktop);
		lblNewLabel_4_3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_4_3.setBounds(292, 143, 83, 26);
		tab1.add(lblNewLabel_4_3);

		textField_roomNumber = new JTextField();
		textField_roomNumber.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		textField_roomNumber.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField_roomNumber.setForeground(SystemColor.desktop);
		textField_roomNumber.setColumns(10);
		textField_roomNumber.setBounds(375, 143, 176, 28);
		tab1.add(textField_roomNumber);

		JButton btn_Them = new JButton("THÊM");
		btn_Them.addActionListener(actionListener);
		btn_Them.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-add-24.png")));
		btn_Them.setForeground(SystemColor.desktop);
		btn_Them.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_Them.setFocusable(false);
		btn_Them.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		btn_Them.setBackground(new Color(46, 204, 113));
		btn_Them.setBounds(568, 93, 100, 30);
		tab1.add(btn_Them);

		JButton btn_Xoa = new JButton("XÓA");
		btn_Xoa.addActionListener(actionListener);
		btn_Xoa.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-add-24.png")));
		btn_Xoa.setForeground(SystemColor.desktop);
		btn_Xoa.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_Xoa.setFocusable(false);
		btn_Xoa.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		btn_Xoa.setBackground(new Color(231, 76, 60));
		btn_Xoa.setBounds(687, 93, 100, 30);
		tab1.add(btn_Xoa);

		JButton btn_Sua = new JButton("SỬA");
		btn_Sua.addActionListener(actionListener);
		btn_Sua.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-edit-30.png")));
		btn_Sua.setForeground(SystemColor.desktop);
		btn_Sua.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_Sua.setFocusable(false);
		btn_Sua.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		btn_Sua.setBackground(new Color(255, 165, 0));
		btn_Sua.setBounds(568, 142, 100, 30);
		tab1.add(btn_Sua);

		JButton btn_CapNhat = new JButton("CẬP NHẬT");
		btn_CapNhat.addActionListener(actionListener);
		btn_CapNhat.setForeground(SystemColor.desktop);
		btn_CapNhat.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_CapNhat.setFocusable(false);
		btn_CapNhat.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		btn_CapNhat.setBackground(new Color(255, 165, 0));
		btn_CapNhat.setBounds(687, 142, 100, 30);
		tab1.add(btn_CapNhat);

		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.desktop);
		separator.setBounds(10, 191, 777, 2);
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
		scrollPane_Customer.setBounds(10, 235, 777, 231);
		tab1.add(scrollPane_Customer);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.desktop);
		separator_1.setBackground(SystemColor.desktop);
		separator_1.setBounds(10, 76, 777, 2);
		tab1.add(separator_1);

		JPanel tab2 = new JPanel();
		tab2.setForeground(SystemColor.desktop);
		tab2.setBackground(new Color(244, 245, 249));
		tabbedPane.addTab("Tab2", null, tab2, null);
		tab2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Tab2");
		lblNewLabel_1.setBounds(0, 0, 64, 21);
		tab2.add(lblNewLabel_1);

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

		JPanel tab3 = new JPanel();
		tab3.setForeground(SystemColor.desktop);
		tab3.setBackground(new Color(244, 245, 249));
		tabbedPane.addTab("Tab3", null, tab3, null);
		tab3.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Tab3");
		lblNewLabel_2.setBounds(0, 0, 187, 22);
		tab3.add(lblNewLabel_2);

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
		scrollPane_Bill.setBounds(10, 134, 777, 209);
		tab3.add(scrollPane_Bill);

		JPanel tab4 = new JPanel();
		tab4.setBackground(new Color(244, 245, 249));
		tabbedPane.addTab("Tab4", null, tab4, null);
		tab4.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Tab4");
		lblNewLabel_3.setBounds(0, 0, 81, 24);
		tab4.add(lblNewLabel_3);

		JButton btnTab1 = new JButton("Khách hàng");
		btnTab1.setHorizontalAlignment(SwingConstants.LEFT);
		btnTab1.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-admin-30.png")));
		btnTab1.setBackground(new Color(55, 65, 81));
		btnTab1.setForeground(new Color(244, 245, 249));
		btnTab1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTab1.setBorderPainted(false);
		btnTab1.setFocusable(false);
		btnTab1.setBounds(0, 118, 162, 42);
		panel.add(btnTab1);

		JButton btnTab2 = new JButton("DS Phòng");
		btnTab2.setHorizontalAlignment(SwingConstants.LEFT);
		btnTab2.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-room-30.png")));
		btnTab2.setBackground(new Color(17, 24, 39));
		btnTab2.setForeground(new Color(244, 245, 249));
		btnTab2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTab2.setFocusable(false);
		btnTab2.setBorderPainted(false);
		btnTab2.setBounds(0, 162, 162, 42);
		panel.add(btnTab2);

		JButton btnTab3 = new JButton("DS Hóa Đơn");
		btnTab3.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-bill-30.png")));
		btnTab3.setHorizontalAlignment(SwingConstants.LEFT);
		btnTab3.setBackground(new Color(17, 24, 39));
		btnTab3.setForeground(new Color(244, 245, 249));
		btnTab3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTab3.setFocusable(false);
		btnTab3.setBorderPainted(false);
		btnTab3.setBounds(0, 206, 162, 42);
		panel.add(btnTab3);

		JButton btnTab4 = new JButton("Tab4");
		btnTab4.setHorizontalAlignment(SwingConstants.LEFT);
		btnTab4.setForeground(new Color(244, 245, 249));
		btnTab4.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTab4.setBackground(new Color(17, 24, 39));
		btnTab4.setFocusable(false);
		btnTab4.setBorderPainted(false);
		btnTab4.setBounds(0, 250, 162, 42);
		panel.add(btnTab4);

		JButton btnNewButton = new JButton("");
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
		btnNewButton.setIcon(new ImageIcon(EmployeeGUI.class.getResource("/image/icons8-off-40.png")));
		btnNewButton.setBackground(new Color(17, 24, 39));
		btnNewButton.setBounds(42, 409, 76, 61);
		panel.add(btnNewButton);

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
		Date dateOrder = new Date(System.currentTimeMillis());
		
		Customer customer = new Customer(name, cccd, birthDate, idEmp);
		Bill bill = new Bill(roomNumber, cccd, dateOrder);

		new EmployeeBUS().addCustomer(customer);
		new BillBUS().addBill(bill);
		setEmptyForm();
	}

	
	public void deleteCustomer() {
		DefaultTableModel model = (DefaultTableModel) table_Customer.getModel();
		int rowIndex = table_Customer.getSelectedRow();
		
		if (!table_Customer.isRowSelected(rowIndex)) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 khách hàng để xóa");
			return;
		}
		
		int id = Integer.parseInt((String) model.getValueAt(rowIndex, 0));
		
	}
	
}

