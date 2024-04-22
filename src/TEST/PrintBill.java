package TEST;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
import com.itextpdf.layout.properties.HorizontalAlignment;

public class PrintBill extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrintBill frame = new PrintBill();
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
	public PrintBill() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(0, 0, 495, 538);
		contentPane.add(contentPane_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(244, 245, 249));
		panel.setBounds(0, 0, 495, 497);
		contentPane_1.add(panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PrintBill.class.getResource("/image/acac222a-c1ba-4e57-98cb-cf9d89ff6d89.jpg")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(244, 245, 249));
		lblNewLabel.setBounds(132, 11, 51, 51);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("HOTEL OPP JRT");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setForeground(SystemColor.desktop);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_1.setBackground(new Color(244, 245, 249));
		lblNewLabel_1.setBounds(193, 11, 155, 25);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Prestige & Quality");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setForeground(SystemColor.desktop);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_2.setBackground(new Color(244, 245, 249));
		lblNewLabel_2.setBounds(193, 36, 201, 33);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Invoice to:");
		lblNewLabel_3.setForeground(SystemColor.desktop);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_3.setBackground(new Color(244, 245, 249));
		lblNewLabel_3.setBounds(23, 168, 113, 25);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("BILL");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(SystemColor.desktop);
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel_4.setBackground(new Color(244, 245, 249));
		lblNewLabel_4.setBounds(0, 74, 492, 51);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_3_1 = new JLabel("Bill No.:");
		lblNewLabel_3_1.setForeground(SystemColor.desktop);
		lblNewLabel_3_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_3_1.setBackground(new Color(244, 245, 249));
		lblNewLabel_3_1.setBounds(294, 173, 70, 25);
		panel.add(lblNewLabel_3_1);
		
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
		lblNewLabel_5.setForeground(new Color(200, 158, 82));
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_5.setBounds(23, 250, 125, 30);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("PRICE");
		lblNewLabel_5_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setForeground(new Color(200, 158, 82));
		lblNewLabel_5_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_5_1.setBounds(336, 250, 113, 30);
		panel.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_1_2 = new JLabel("Room");
		lblNewLabel_5_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1_2.setForeground(SystemColor.desktop);
		lblNewLabel_5_1_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_5_1_2.setBounds(23, 295, 51, 30);
		panel.add(lblNewLabel_5_1_2);
		
		JLabel lblNewLabel_5_1_3 = new JLabel("80,000 VNĐ / 1H");
		lblNewLabel_5_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1_3.setForeground(SystemColor.desktop);
		lblNewLabel_5_1_3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_5_1_3.setBounds(328, 295, 131, 30);
		panel.add(lblNewLabel_5_1_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(244, 245, 249));
		panel_1.setBounds(0, 494, 495, 33);
		contentPane_1.add(panel_1);
		
		JButton btnNewButton = new JButton("In ");
		btnNewButton.addActionListener(e -> {
		    JFileChooser fileChooser = new JFileChooser();
		    fileChooser.setDialogTitle("Chọn vị trí để lưu file PDF");
		    fileChooser.setAcceptAllFileFilterUsed(false);
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf");
		    fileChooser.addChoosableFileFilter(filter);

		    int userSelection = fileChooser.showSaveDialog(null);

		    if (userSelection == JFileChooser.APPROVE_OPTION) {
		        try {
		            File fileToSave = fileChooser.getSelectedFile();
		            String filePath = fileToSave.getAbsolutePath();
		            // Đảm bảo rằng file có phần mở rộng là .pdf
		            if (!filePath.endsWith(".pdf")) {
		                filePath += ".pdf";
		            }

		            BufferedImage img = capturePanel(panel); // Chụp ảnh của JPanel
		            createPdfWithImage(filePath, img); // Tạo PDF với ảnh
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		});

		btnNewButton.setFocusable(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(244, 245, 249));
		btnNewButton.setBounds(445, 3, 45, 30);
		panel_1.add(btnNewButton);
	}
	
	public BufferedImage capturePanel(JPanel panel) {
		BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
		panel.paint(image.getGraphics());
		return image;
	}
	
	public void createPdfWithImage(String pdfPath, BufferedImage panelImage) throws Exception {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(panelImage, "png", baos);  // Ghi BufferedImage thành output stream
	    byte[] imageBytes = baos.toByteArray();
	    
	    PdfWriter writer = new PdfWriter(pdfPath);
	    PdfDocument pdfDoc = new PdfDocument(writer);

	    // Thiết lập kích thước trang PDF với kích thước của JPanel
	    PageSize pageSize = new PageSize(panelImage.getWidth(), panelImage.getHeight());
	    pdfDoc.setDefaultPageSize(pageSize);
	    
	    Document doc = new Document(pdfDoc);
	    
	    ImageData imageData = ImageDataFactory.create(imageBytes);
	    Image pdfImage = new Image(imageData);

	    // Đặt ảnh để nó không vượt quá kích thước trang
	    pdfImage.setAutoScale(true);
	    
	    doc.add(pdfImage);
	    
	    doc.close();
	    System.out.println("PDF created.");
	}


}
