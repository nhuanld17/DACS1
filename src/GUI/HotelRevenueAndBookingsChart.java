package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import BUS.BillBUS;

public class HotelRevenueAndBookingsChart extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private XYSeriesCollection dataset;
    private JFreeChart chart;

    /**
     * Create the frame.
     */
    public HotelRevenueAndBookingsChart() {
    	setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 869, 502);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        setContentPane(contentPane);

        dataset = createDataset();

        chart = ChartFactory.createXYLineChart(
                "Hotel Revenue and Bookings Over Time",
                "Date",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        // Enable tooltips
        renderer.setSeriesToolTipGenerator(0, new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("MM-yyyy"), 
                new java.text.DecimalFormat("0.00")
        ));
        
        renderer.setSeriesToolTipGenerator(1, new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("MM-yyyy"), 
                new java.text.DecimalFormat("0.00")
        ));

        plot.setRenderer(renderer);
        
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.GRAY);

        // Set the date axis format
        DateAxis dateAxis = new DateAxis("Date");
        dateAxis.setDateFormatOverride(new SimpleDateFormat("MM-yyyy"));
        plot.setDomainAxis(dateAxis);

        // Set the background color for the chart
        plot.setBackgroundPaint(Color.WHITE);

        // Add the chart to a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(664, 384)); // Match the size of the panel
        chartPanel.setBounds(0, 0, 853, 463);

        contentPane.add(chartPanel);
    }

    private XYSeriesCollection createDataset() {
        XYSeries revenueSeries = new XYSeries("Revenue (Million VND)");
        XYSeries bookingsSeries = new XYSeries("Bookings");

        double[] revenues = new BillBUS().getRevenueEachMonth();
        double[] bookings = new BillBUS().getBookingEachMonth();
        
        for (int i = 0; i < revenues.length; i++) {
			revenues[i] = revenues[i] / 1000000;
		}

        for (int month = 1; month <= 12; month++) {
            revenueSeries.add(new Month(month, 2024).getFirstMillisecond(), revenues[month - 1]);
            bookingsSeries.add(new Month(month, 2024).getFirstMillisecond(), bookings[month - 1]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(revenueSeries);
        dataset.addSeries(bookingsSeries);

        return dataset;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HotelRevenueAndBookingsChart frame = new HotelRevenueAndBookingsChart();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
