package SideBarGUI_TEST;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class SideBar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTab;
	private JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SideBar frame = new SideBar();
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
	public SideBar() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 943, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel SideBarPanel = new JPanel();
		SideBarPanel.setBounds(0, 0, 162, 457);
		SideBarPanel.setBackground(new Color(33, 33, 33));
		contentPane.add(SideBarPanel);
		SideBarPanel.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(160, 0, 767, 457);
		contentPane.add(tabbedPane);
		
		JPanel Tab1 = new JPanel();
		tabbedPane.addTab("New tab", null, Tab1, null);
		Tab1.setLayout(null);
		
		txtTab = new JTextField();
		txtTab.setBounds(338, 5, 86, 20);
		txtTab.setText("Tab1");
		Tab1.add(txtTab);
		txtTab.setColumns(10);
		
		JPanel Tab2 = new JPanel();
		tabbedPane.addTab("New tab", null, Tab2, null);
		Tab2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tab2");
		lblNewLabel.setBounds(304, 5, 61, 34);
		Tab2.add(lblNewLabel);
		
		JPanel Tab3 = new JPanel();
		tabbedPane.addTab("New tab", null, Tab3, null);
		Tab3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tab3");
		lblNewLabel_1.setBounds(304, 5, 61, 21);
		Tab3.add(lblNewLabel_1);
		
		JPanel Tab4 = new JPanel();
		tabbedPane.addTab("New tab", null, Tab4, null);
		Tab4.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Tab4");
		lblNewLabel_2.setBounds(293, 5, 92, 39);
		Tab4.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Tab1");
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setBackground(new Color(33, 33, 33));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				btnNewButton.setBackground(new Color(160, 50, 50));
			}
		});
		btnNewButton.setBounds(0, 102, 162, 34);
		btnNewButton.setBorder(new LineBorder(new Color(33, 33, 33)));
		SideBarPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Tab2");
		btnNewButton_1.setBackground(new Color(33, 33, 33));
		btnNewButton_1.setForeground(SystemColor.text);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnNewButton_1.setBorder(new LineBorder(new Color(33, 33, 33)));
		btnNewButton_1.setBounds(0, 136, 162, 34);
		SideBarPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Tab3");
		btnNewButton_2.setBackground(new Color(33, 33, 33));
		btnNewButton_2.setForeground(SystemColor.text);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnNewButton_2.setBorder(new LineBorder(new Color(33, 33, 33)));
		btnNewButton_2.setBounds(0, 170, 162, 34);
		SideBarPanel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Tab4");
		btnNewButton_3.setBackground(new Color(33, 33, 33));
		btnNewButton_3.setForeground(SystemColor.text);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			
			}
		});
		btnNewButton_3.setBorder(new LineBorder(new Color(33, 33, 33)));
		btnNewButton_3.setBounds(0, 204, 162, 34);
		SideBarPanel.add(btnNewButton_3);
		

	}
}
