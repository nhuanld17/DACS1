package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import BUS.BillBUS;
import BUS.EmployeeBUS;

import java.awt.SystemColor;
import java.sql.Timestamp;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class BillGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					BillGUI frame = new BillGUI();
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
	public BillGUI(int ID, int roomNumber, String CCCD, Timestamp dateOrder, Timestamp dateReturn, long price, double hours) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 511, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		String name = new EmployeeBUS().getNameByID(ID);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(244, 245, 249));
		panel.setBounds(0, 0, 495, 497);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(244, 245, 249));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(BillGUI.class.getResource("/image/acac222a-c1ba-4e57-98cb-cf9d89ff6d89.jpg")));
		lblNewLabel.setBounds(132, 11, 51, 51);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("HOTEL OPP JRT");
		lblNewLabel_1.setBackground(new Color(244, 245, 249));
		lblNewLabel_1.setForeground(SystemColor.desktop);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_1.setBounds(193, 11, 155, 25);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Prestige & Quality");
		lblNewLabel_2.setBackground(new Color(244, 245, 249));
		lblNewLabel_2.setForeground(SystemColor.desktop);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_2.setBounds(193, 36, 201, 33);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Invoice to:");
		lblNewLabel_3.setBackground(new Color(244, 245, 249));
		lblNewLabel_3.setForeground(SystemColor.desktop);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_3.setBounds(23, 168, 113, 25);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("BILL");
		lblNewLabel_4.setBackground(new Color(244, 245, 249));
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel_4.setForeground(SystemColor.desktop);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(0, 74, 492, 51);
		panel.add(lblNewLabel_4);
		
		JLabel label_CustomerName = new JLabel(name);
		label_CustomerName.setBackground(new Color(244, 245, 249));
		label_CustomerName.setFont(new Font("Segoe UI", Font.BOLD, 18));
		label_CustomerName.setForeground(SystemColor.desktop);
		label_CustomerName.setBounds(23, 191, 180, 33);
		panel.add(label_CustomerName);
		
		JLabel lblNewLabel_3_1 = new JLabel("Bill No.:");
		lblNewLabel_3_1.setForeground(SystemColor.desktop);
		lblNewLabel_3_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_3_1.setBackground(new Color(244, 245, 249));
		lblNewLabel_3_1.setBounds(294, 173, 70, 25);
		panel.add(lblNewLabel_3_1);
		
		JLabel label_BillID = new JLabel(ID+"");
		label_BillID.setForeground(SystemColor.desktop);
		label_BillID.setFont(new Font("Segoe UI", Font.BOLD, 18));
		label_BillID.setBackground(new Color(244, 245, 249));
		label_BillID.setBounds(360, 168, 125, 33);
		panel.add(label_BillID);
		
		JLabel label_CCCD = new JLabel(CCCD);
		label_CCCD.setForeground(SystemColor.desktop);
		label_CCCD.setFont(new Font("Segoe UI", Font.BOLD, 18));
		label_CCCD.setBackground(new Color(244, 245, 249));
		label_CCCD.setBounds(360, 196, 125, 33);
		panel.add(label_CCCD);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("ID Card:");
		lblNewLabel_3_1_1.setForeground(SystemColor.desktop);
		lblNewLabel_3_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_3_1_1.setBackground(new Color(244, 245, 249));
		lblNewLabel_3_1_1.setBounds(294, 201, 60, 25);
		panel.add(lblNewLabel_3_1_1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(200, 158, 82));
		separator.setBackground(new Color(200, 158, 82));
		separator.setBounds(23, 291, 454, 3);
		panel.add(separator);
		
		JLabel lblNewLabel_5 = new JLabel("ROOM NUMBER");
		lblNewLabel_5.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_5.setForeground(new Color(200, 158, 82));
		lblNewLabel_5.setBounds(23, 250, 125, 30);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("PRICE");
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_5_1.setForeground(new Color(200, 158, 82));
		lblNewLabel_5_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_5_1.setBounds(336, 250, 113, 30);
		panel.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("TOTAL(VNĐ):");
		lblNewLabel_5_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_5_1_1.setForeground(new Color(200, 158, 82));
		lblNewLabel_5_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_5_1_1.setBounds(23, 452, 113, 30);
		panel.add(lblNewLabel_5_1_1);
		
		JLabel lblNewLabel_5_1_2 = new JLabel("Room");
		lblNewLabel_5_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_2.setForeground(SystemColor.desktop);
		lblNewLabel_5_1_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_5_1_2.setBounds(23, 295, 51, 30);
		panel.add(lblNewLabel_5_1_2);
		
		JLabel label_RoomNumber = new JLabel(roomNumber+"");
		label_RoomNumber.setHorizontalAlignment(SwingConstants.LEFT);
		label_RoomNumber.setForeground(SystemColor.desktop);
		label_RoomNumber.setFont(new Font("Segoe UI", Font.BOLD, 16));
		label_RoomNumber.setBounds(71, 295, 51, 30);
		panel.add(label_RoomNumber);
		
		JLabel lblNewLabel_5_1_3 = new JLabel("80,000 VNĐ / 1H");
		lblNewLabel_5_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1_3.setForeground(SystemColor.desktop);
		lblNewLabel_5_1_3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_5_1_3.setBounds(328, 295, 131, 30);
		panel.add(lblNewLabel_5_1_3);
		
		JLabel lblNewLabel_5_2 = new JLabel("CHECK-IN TIME:");
		lblNewLabel_5_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_5_2.setForeground(new Color(200, 158, 82));
		lblNewLabel_5_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_5_2.setBounds(23, 336, 137, 30);
		panel.add(lblNewLabel_5_2);
		
		JLabel label_Checkin = new JLabel(dateOrder+"");
		label_Checkin.setHorizontalAlignment(SwingConstants.CENTER);
		label_Checkin.setVerticalAlignment(SwingConstants.BOTTOM);
		label_Checkin.setForeground(SystemColor.desktop);
		label_Checkin.setFont(new Font("Segoe UI", Font.BOLD, 16));
		label_Checkin.setBounds(302, 336, 190, 30);
		panel.add(label_Checkin);
		
		JLabel lblNewLabel_5_2_2 = new JLabel("CHECK-OUT DATE:");
		lblNewLabel_5_2_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_5_2_2.setForeground(new Color(200, 158, 82));
		lblNewLabel_5_2_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_5_2_2.setBounds(23, 375, 149, 30);
		panel.add(lblNewLabel_5_2_2);
		
		JLabel label_CheckOut = new JLabel(dateReturn+"");
		label_CheckOut.setVerticalAlignment(SwingConstants.BOTTOM);
		label_CheckOut.setHorizontalAlignment(SwingConstants.CENTER);
		label_CheckOut.setForeground(SystemColor.desktop);
		label_CheckOut.setFont(new Font("Segoe UI", Font.BOLD, 16));
		label_CheckOut.setBounds(302, 377, 190, 30);
		panel.add(label_CheckOut);
		
		JLabel lblNewLabel_5_2_2_1 = new JLabel("USED TIME (hours):");
		lblNewLabel_5_2_2_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_5_2_2_1.setForeground(new Color(200, 158, 82));
		lblNewLabel_5_2_2_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_5_2_2_1.setBounds(23, 411, 149, 30);
		panel.add(lblNewLabel_5_2_2_1);
		
		JLabel label_UsedTime = new JLabel(hours+"");
		label_UsedTime.setVerticalAlignment(SwingConstants.BOTTOM);
		label_UsedTime.setHorizontalAlignment(SwingConstants.CENTER);
		label_UsedTime.setForeground(SystemColor.desktop);
		label_UsedTime.setFont(new Font("Segoe UI", Font.BOLD, 16));
		label_UsedTime.setBounds(315, 418, 162, 30);
		panel.add(label_UsedTime);
		
		
		JLabel label_Total = new JLabel(price+"");
		label_Total.setVerticalAlignment(SwingConstants.BOTTOM);
		label_Total.setHorizontalAlignment(SwingConstants.CENTER);
		label_Total.setForeground(SystemColor.desktop);
		label_Total.setFont(new Font("Segoe UI", Font.BOLD, 16));
		label_Total.setBounds(315, 452, 162, 30);
		panel.add(label_Total);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(244, 245, 249));
		panel_1.setBounds(0, 494, 495, 33);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				// Vô hiệu hóa lựa chọn "All Files" để người dùng không thể chọn bất kì loại file nào khác
				fileChooser.setAcceptAllFileFilterUsed(false);
				
				// Thiết lập bộ lọc chỉ cho phép lưu file dưới dạng PDF
				FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf");
				fileChooser.addChoosableFileFilter(filter);
				
				int userSelection = fileChooser.showSaveDialog(null);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser.getSelectedFile();
					String filePath = fileToSave.getAbsolutePath();
					
					if (!filePath.endsWith(".pdf")) {
						filePath += ".pdf";
					}
					
					BufferedImage img = capturePanel(panel);
					try {
						createPdfWithImage(filePath, img);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setFocusable(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setIcon(new ImageIcon(BillGUI.class.getResource("/image/icons8-print-30.png")));
		btnNewButton.setBackground(new Color(244, 245, 249));
		btnNewButton.setBounds(445, 3, 45, 30);
		panel_1.add(btnNewButton);
	}
	
	public BufferedImage capturePanel(JPanel panel) {
		// Tạo 1 đối tượng BufferedImage, sử dụng kích thước của Panel để thiết lập kích thước của hình ảnh
		// BufferImage.TYPE_INT_RGB : tạo ra loại ảnh có 3 thành phần màu là red, green, và blue
		BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		// Gọi phương thức paint của JPanel, truyền đối tượng Graphics của BufferImages
		// phương thức paint() vẽ nội dung hiện tại của JPanel vào đối tượng Graphics được cung cấp
		// trong trường hợp này là Graphics của BufferedImage. Giúp chụp nội dung hiển thị lên JPanel
		panel.paint(image.getGraphics());
		
		 // Trả về đối tượng BufferedImage đã chứa hình ảnh của JPanel.
		return image;
	}
	
	public void createPdfWithImage(String pdfPath, BufferedImage panelImage) throws IOException {
		// tạo 1 ByteArrayOutputStream để lưu trữ dữ liệu hình ảnh
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		// Ghi ảnh từ BufferedImage vào ByteArrayOutputStream ở định dạng PNG
		ImageIO.write(panelImage, "png", baos);
		
		// Chuyển đổi ByteArrayOutputStream thành 1 mảng Byte
		byte[] imageBytes = baos.toByteArray();
		
		// Khởi tạo một PdfWriter để viết tệp PDF, sử dụng đường dẫn được cung cấp.
		PdfWriter pdfWriter = new PdfWriter(pdfPath);
		
		// Tạo một đối tượng PdfDocument mới với PdfWriter đã khởi tạo.
		PdfDocument pdfDoc = new PdfDocument(pdfWriter);
		
		 // Thiết lập kích thước trang PDF bằng với kích thước của ảnh BufferedImage.
	    PageSize pageSize = new PageSize(panelImage.getWidth() - 10, panelImage.getHeight() - 10);
	    pdfDoc.setDefaultPageSize(pageSize);
	    
	    // Tạo một đối tượng Document mới để thêm nội dung vào tài liệu PDF.
	    Document doc = new Document(pdfDoc);
	    
	    // Tạo một đối tượng ImageData từ mảng byte của ảnh, để có thể thêm vào PDF.
	    ImageData imageData = ImageDataFactory.create(imageBytes);
	    
	    // Tạo một đối tượng Image từ ImageData.
	    Image pdfImage = new Image(imageData);

	    // Đặt ảnh để nó không vượt quá kích thước trang
	    pdfImage.setAutoScale(true);
	    
	    doc.add(pdfImage);
	    
	    doc.close();
	    System.out.println("PDF created.");
	}
}
