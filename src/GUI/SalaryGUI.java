package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
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

public class SalaryGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public SalaryGUI(int month, String name, int id, String position, int numberCustomer) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 740, 541);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("KHÁCH SẠN OPP JRT");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setForeground(SystemColor.desktop);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_1.setBackground(new Color(244, 245, 249));
		lblNewLabel_1.setBounds(290, 11, 201, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Uy tín & Chất lượng");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setForeground(SystemColor.desktop);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_2.setBackground(new Color(244, 245, 249));
		lblNewLabel_2.setBounds(290, 34, 201, 33);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SalaryGUI.class.getResource("/image/acac222a-c1ba-4e57-98cb-cf9d89ff6d89.jpg")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(244, 245, 249));
		lblNewLabel.setBounds(233, 11, 51, 51);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("BIÊN LAI TIỀN LƯƠNG THÁNG "+month);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel_3.setForeground(SystemColor.desktop);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(0, 69, 724, 39);
		contentPane.add(lblNewLabel_3);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.desktop);
		separator.setBackground(SystemColor.desktop);
		separator.setBounds(33, 119, 656, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(SystemColor.desktop);
		separator_1.setBackground(SystemColor.desktop);
		separator_1.setBounds(33, 119, 2, 252);
		contentPane.add(separator_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1.setForeground(SystemColor.desktop);
		separator_1_1.setBackground(SystemColor.desktop);
		separator_1_1.setBounds(687, 119, 2, 252);
		contentPane.add(separator_1_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(SystemColor.desktop);
		separator_2.setBackground(SystemColor.desktop);
		separator_2.setBounds(33, 166, 656, 2);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(SystemColor.desktop);
		separator_3.setBackground(SystemColor.desktop);
		separator_3.setBounds(33, 217, 656, 2);
		contentPane.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(SystemColor.desktop);
		separator_4.setBackground(SystemColor.desktop);
		separator_4.setBounds(33, 270, 656, 2);
		contentPane.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(SystemColor.desktop);
		separator_5.setBackground(SystemColor.desktop);
		separator_5.setBounds(33, 322, 656, 2);
		contentPane.add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(SystemColor.desktop);
		separator_6.setBackground(SystemColor.desktop);
		separator_6.setBounds(33, 371, 656, 2);
		contentPane.add(separator_6);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1_1.setForeground(SystemColor.desktop);
		separator_1_1_1.setBackground(SystemColor.desktop);
		separator_1_1_1.setBounds(361, 119, 2, 252);
		contentPane.add(separator_1_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("Tên nhân viên:");
		lblNewLabel_4.setForeground(SystemColor.desktop);
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(43, 128, 308, 33);
		contentPane.add(lblNewLabel_4);
		
		JLabel label_name = new JLabel(name);
		label_name.setForeground(SystemColor.desktop);
		label_name.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label_name.setBounds(369, 128, 308, 33);
		contentPane.add(label_name);
		
		JLabel lblNewLabel_4_2 = new JLabel("Mã nhân viên:");
		lblNewLabel_4_2.setForeground(SystemColor.desktop);
		lblNewLabel_4_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_4_2.setBounds(43, 176, 308, 33);
		contentPane.add(lblNewLabel_4_2);
		
		JLabel label_id = new JLabel(id+"");
		label_id.setForeground(SystemColor.desktop);
		label_id.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label_id.setBounds(369, 176, 308, 33);
		contentPane.add(label_id);
		
		JLabel lblNewLabel_4_2_2 = new JLabel("Vị trí:");
		lblNewLabel_4_2_2.setForeground(SystemColor.desktop);
		lblNewLabel_4_2_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_4_2_2.setBounds(43, 228, 308, 33);
		contentPane.add(lblNewLabel_4_2_2);
		
		JLabel label_position = new JLabel(position);
		label_position.setForeground(SystemColor.desktop);
		label_position.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label_position.setBounds(369, 228, 308, 33);
		contentPane.add(label_position);
		
		JLabel lblNewLabel_4_2_4 = new JLabel("Lương cơ bản:");
		lblNewLabel_4_2_4.setForeground(SystemColor.desktop);
		lblNewLabel_4_2_4.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_4_2_4.setBounds(43, 280, 308, 33);
		contentPane.add(lblNewLabel_4_2_4);
		
		double basicSalary = 6500000;
		double totalSalary = basicSalary + numberCustomer*100000;
		
		JLabel label_basic_salary = new JLabel("6500000 VNĐ");
		label_basic_salary.setForeground(SystemColor.desktop);
		label_basic_salary.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label_basic_salary.setBounds(369, 280, 308, 33);
		contentPane.add(label_basic_salary);
		
		JLabel lblNewLabel_4_2_6 = new JLabel("Số khách hàng đã phục vụ:");
		lblNewLabel_4_2_6.setForeground(SystemColor.desktop);
		lblNewLabel_4_2_6.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_4_2_6.setBounds(43, 330, 308, 33);
		contentPane.add(lblNewLabel_4_2_6);
		
		JLabel label_number_customer = new JLabel(numberCustomer+"");
		label_number_customer.setForeground(SystemColor.desktop);
		label_number_customer.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label_number_customer.setBounds(369, 330, 308, 33);
		contentPane.add(label_number_customer);
		
		JLabel lblNewLabel_4_2_4_1 = new JLabel("Thực lãnh: "+totalSalary+" VNĐ");
		lblNewLabel_4_2_4_1.setForeground(SystemColor.desktop);
		lblNewLabel_4_2_4_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_4_2_4_1.setBounds(43, 375, 335, 25);
		contentPane.add(lblNewLabel_4_2_4_1);
		
		LocalDate currentDate = LocalDate.now();
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, 'ngày' dd 'tháng' MM 'năm' yyyy", new Locale("vi", "VN"));
		
		String formattedDate = currentDate.format(dateFormatter);
		
		JLabel label_date = new JLabel("");
		label_date.setForeground(SystemColor.desktop);
		label_date.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label_date.setText(formattedDate);
		label_date.setHorizontalAlignment(SwingConstants.CENTER);
		label_date.setBounds(405, 378, 319, 33);
		contentPane.add(label_date);
		
		JLabel lblNewLabel_5 = new JLabel("Trưởng phòng");
		lblNewLabel_5.setForeground(SystemColor.desktop);
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(440, 408, 274, 25);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("Hồ Thanh Quảng");
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setForeground(SystemColor.desktop);
		lblNewLabel_5_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_5_1.setBounds(440, 435, 274, 25);
		contentPane.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Đã kí");
		lblNewLabel_5_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1_1.setForeground(SystemColor.desktop);
		lblNewLabel_5_1_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_5_1_1.setBounds(440, 458, 274, 33);
		contentPane.add(lblNewLabel_5_1_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusable(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBackground(SystemColor.text);
		btnNewButton.setIcon(new ImageIcon(SalaryGUI.class.getResource("/image/icons8-print-30.png")));
		btnNewButton.setBounds(10, 466, 81, 32);
		contentPane.add(btnNewButton);
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
					
					BufferedImage img = capturePanel(contentPane);
					try {
						createPdfWithImage(filePath, img);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}

	protected BufferedImage capturePanel(JPanel contentPane2) {
		// Tạo 1 đối tượng BufferedImage, sử dụng kích thước của Panel để thiết lập kích thước của hình ảnh
		// BufferImage.TYPE_INT_RGB : tạo ra loại ảnh có 3 thành phần màu là red, green, và blue
		BufferedImage image = new BufferedImage(contentPane2.getWidth(), contentPane2.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		// Gọi phương thức paint của JPanel, truyền đối tượng Graphics của BufferImages
		// phương thức paint() vẽ nội dung hiện tại của JPanel vào đối tượng Graphics được cung cấp
		// trong trường hợp này là Graphics của BufferedImage. Giúp chụp nội dung hiển thị lên JPanel
		contentPane2.paint(image.getGraphics());
		
		 // Trả về đối tượng BufferedImage đã chứa hình ảnh của JPanel.
		return image;
	}

	protected void createPdfWithImage(String pdfPath, BufferedImage panelImage) throws IOException{
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
