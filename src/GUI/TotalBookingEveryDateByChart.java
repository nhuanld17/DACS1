package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import DAO.BillDAO;

public class TotalBookingEveryDateByChart extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LocalDate currentDate;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private LocalDate[] date = new LocalDate[7];
	private CategoryDataset dataset;
	private JFreeChart chart;
	private ChartPanel chartPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TotalBookingEveryDateByChart frame = new TotalBookingEveryDateByChart();
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
	public TotalBookingEveryDateByChart() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 775, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.currentDate = LocalDate.now();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 762, 432);
		contentPane.add(panel);
		
		dataset = createDataSet();
		
		chart = createBarChart(dataset);
		chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(0, 11, 762, 383);
		chartPanel.setBackground(new Color(244, 245, 249));
		chartPanel.setForeground(SystemColor.desktop);
		chartPanel.setPreferredSize(new Dimension(450, 300));
		panel.add(chartPanel);
		
		panel.setLayout(null);
		chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(0, 11, 762, 383);
		chartPanel.setBackground(new Color(244, 245, 249));
		chartPanel.setForeground(SystemColor.desktop);
		chartPanel.setPreferredSize(new Dimension(450, 300));
		panel.add(chartPanel);

		
		JButton btnNewButton = new JButton(">>");
		btnNewButton.setFocusable(false);
		btnNewButton.setForeground(new Color(244, 245, 249));
		btnNewButton.setBackground(new Color(244,245,249));
		btnNewButton.setBorder(new LineBorder(new Color(17, 24, 39),2));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        currentDate = currentDate.plusDays(7);
		        dataset = createDataSet();
		        chart = createBarChart(dataset);
		        chartPanel.setChart(chart);
			}
		});
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		btnNewButton.setForeground(SystemColor.desktop);
		btnNewButton.setBounds(663, 395, 89, 30);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("<<");
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setForeground(new Color(244, 245, 249));
		btnNewButton_1.setBorder(new LineBorder(new Color(17, 24, 39),2));
		btnNewButton_1.setBackground(new Color(244, 245, 249));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        currentDate = currentDate.minusDays(7);
		        dataset = createDataSet();
		        chart = createBarChart(dataset);
		        chartPanel.setChart(chart);
			}
		});
		btnNewButton_1.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton_1.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		btnNewButton_1.setForeground(SystemColor.desktop);
		btnNewButton_1.setBounds(564, 395, 89, 30);
		panel.add(btnNewButton_1);
		

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private JFreeChart createBarChart(CategoryDataset dataset2) {
	    return ChartFactory.createBarChart(
		        "Số lượt đặt phòng hằng ngày",
		        "Ngày",
		        "Số lượt",
		        dataset2,
		        PlotOrientation.VERTICAL,
		        true,
		        true,
		        false
		    );
	}

	private CategoryDataset createDataSet() {
		LocalDate start = currentDate.minusDays(6);
		for (int i = 0; i < 7; i++) {
			date[i] = start.plusDays(i);
		}
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ArrayList<Integer> totalBooking = new BillDAO().getDailyTotalBooking(date);
		
		for (int i = 0; i < totalBooking.size(); i++) {
			dataset.addValue(totalBooking.get(i), "Lượt đặt phòng", date[i]);
		}
		return dataset;
	}

}
