package TEST;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MailSender extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField email_Field;
	private JPasswordField passwordField;
	private JTextField emailReceiver;
	private JTextField title_tf;
	private JTextField textField_ListFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MailSender frame = new MailSender();
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
	public MailSender() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(70, 155, 524, 155);
		contentPane.add(textArea);
		
		email_Field = new JTextField();
		email_Field.setBounds(70, 11, 253, 37);
		contentPane.add(email_Field);
		email_Field.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(341, 11, 253, 37);
		contentPane.add(passwordField);
		
		emailReceiver = new JTextField();
		emailReceiver.setBounds(341, 59, 253, 37);
		contentPane.add(emailReceiver);
		emailReceiver.setColumns(10);
		
		title_tf = new JTextField();
		title_tf.setBounds(70, 107, 524, 37);
		contentPane.add(title_tf);
		title_tf.setColumns(10);
		
		textField_ListFile = new JTextField();
		textField_ListFile.setColumns(10);
		textField_ListFile.setBounds(70, 321, 524, 37);
		contentPane.add(textField_ListFile);
		
		JButton btnNewButton_1 = new JButton("Choose file");
		btnNewButton_1.addActionListener(new ActionListener() {
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
		btnNewButton_1.setBounds(604, 321, 137, 37);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String emailSender = email_Field.getText().trim();
				String password = passwordField.getText().trim();
				
				String receiver = emailReceiver.getText().trim();
				String title = title_tf.getText().trim();
				String content = textArea.getText();
				String paths = textField_ListFile.getText().trim();
				
				ArrayList<File> listFile = new ArrayList<>();
				String[] listPath = paths.split("\\-");
				if (listPath.length != 0) {
					for (String path : listPath) {
						File file = new File(path);
						listFile.add(file);
					}
				}
				if (emailSender.isEmpty() || password.isEmpty() || receiver.isEmpty() || title.isEmpty() || content.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin");
					return;
				}
				
				try {
					new SendMail(emailSender, password, receiver, title, content, listFile);
				} catch (UnsupportedEncodingException | MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Lỗi ! Hãy kiểm tra lại tài khoản và mật khẩu \n"+"Chú ý mật khẩu là mật khẩu ứng dụng");
				}
			}
		});
		btnNewButton.setBounds(505, 384, 89, 23);
		contentPane.add(btnNewButton);
		
		
	}
}
