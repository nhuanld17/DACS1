package GUI;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import DAO.EmployeeDAO;
import DTO.CustomerServedChart;

public class NumberOfCustomerServedChart extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LocalDate currentDate;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private LocalDate[] date = new LocalDate[7];
	private int idEmp;


	/**
	 * Create the frame.
	 */
	public NumberOfCustomerServedChart(int idEmp) {
		setBounds(new Rectangle(0, 0, 800, 800));
		this.idEmp = idEmp;
		this.currentDate = LocalDate.now();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 776, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 762, 388);
		contentPane.add(panel);
		
		CategoryDataset dataset = createDataSet();
		
		JFreeChart chart = ChartFactory.createBarChart(
				"Số lượng khách hàng đã phục vụ hàng ngày",
				"Ngày",
				"Số lượng",
				dataset,
				PlotOrientation.VERTICAL,
				true,
				true,
				false
		);
		panel.setLayout(null);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setForeground(SystemColor.desktop);
		chartPanel.setBounds(0, 0, 762, 377);
		chartPanel.setPreferredSize(new Dimension(450, 300));
		panel.add(chartPanel);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private CategoryDataset createDataSet() {
		LocalDate start = currentDate.minusDays(6);
		for (int i = 0; i < 7; i++) {
			date[i] = start.plusDays(i);
		}
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ArrayList<CustomerServedChart> list = new EmployeeDAO().getDailyCustomerServed(date, idEmp);
		
		for (CustomerServedChart customerServedChart : list) {
			dataset.addValue(customerServedChart.getTotalCustomer(), "Số lượng", customerServedChart.getDate());
		}
		return dataset;
	}
}
