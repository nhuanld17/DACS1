package TEST;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;

public class PieChartExample extends ApplicationFrame {

    public PieChartExample(String title) {
        super(title);
        // Tạo biểu đồ tròn
        JFreeChart pieChart = createChart(createDatasetGioiTinh());

        // Tạo ChartPanel và thêm vào JFrame
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private DefaultPieDataset createDatasetGioiTinh() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        int maleNumber = 7;  // Số liệu nam
        int femaleNumber = 1;  // Số liệu nữ

        dataset.setValue("Nam", maleNumber);
        dataset.setValue("Nữ", femaleNumber);

        return dataset;
    }

    private JFreeChart createChart(DefaultPieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Tỉ lệ nam nữ",   // Tiêu đề biểu đồ
                dataset,               // Tập dữ liệu
                false,                  // Hiển thị legend
                true,
                false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Nam", new Color(255, 127, 14));
        plot.setSectionPaint("Nữ", new Color(31, 119, 180));
        plot.setBackgroundPaint(new Color(254, 245, 249));
        chart.setBackgroundPaint(new Color(254, 245, 249));

        // Đổi font cho title
        TextTitle title = chart.getTitle();
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        return chart;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PieChartExample example = new PieChartExample("Pie Chart Example");
            example.setSize(800, 600);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}


