package TEST;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HotelRevenueAndBookingsChart extends ApplicationFrame {

    public HotelRevenueAndBookingsChart(String title) {
        super(title);

        // Create dataset
        XYSeriesCollection dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Hotel Revenue and Bookings Over Time",
                "Date",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Customize the chart
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        // Customize series lines and shapes
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.BLUE);

        plot.setRenderer(renderer);

        // Set the date axis format
        DateAxis dateAxis = new DateAxis("Date");
        dateAxis.setDateFormatOverride(new SimpleDateFormat("dd-MM-yyyy"));
        plot.setDomainAxis(dateAxis);

        // Set the background color for the chart
        plot.setBackgroundPaint(Color.WHITE);

        // Add the chart to a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private XYSeriesCollection createDataset() {
        XYSeries revenueSeries = new XYSeries("Revenue (Million VND)");
        XYSeries bookingsSeries = new XYSeries("Bookings");

        // Add data to series (example data)
        revenueSeries.add(new Day(1, 5, 2024).getMiddleMillisecond(), 50);
        revenueSeries.add(new Day(2, 5, 2024).getMiddleMillisecond(), 75);
        revenueSeries.add(new Day(3, 5, 2024).getMiddleMillisecond(), 60);

        bookingsSeries.add(new Day(1, 5, 2024).getMiddleMillisecond(), 30);
        bookingsSeries.add(new Day(2, 5, 2024).getMiddleMillisecond(), 45);
        bookingsSeries.add(new Day(3, 5, 2024).getMiddleMillisecond(), 40);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(revenueSeries);
        dataset.addSeries(bookingsSeries);

        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HotelRevenueAndBookingsChart example = new HotelRevenueAndBookingsChart("Hotel Revenue and Bookings Chart");
            example.setSize(800, 600);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}

