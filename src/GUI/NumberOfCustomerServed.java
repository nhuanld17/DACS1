package GUI;

import java.awt.EventQueue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NumberOfCustomerServed extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private LocalDate currentDate;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate[] date = new LocalDate[7];

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NumberOfCustomerServed frame = new NumberOfCustomerServed();
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
    public NumberOfCustomerServed() {
    	this.currentDate = LocalDate.now();
    	printWeek();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 343, 408);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 327, 369);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JButton btnNewButton = new JButton(">>");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	currentDate = currentDate.plusDays(7);
            	printWeek();
            }
        });
        btnNewButton.setBounds(180, 117, 89, 23);
        panel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("<<");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	currentDate = currentDate.minusDays(7);
            	printWeek();
            }
        });
        btnNewButton_1.setBounds(58, 117, 89, 23);
        panel.add(btnNewButton_1);
    }

	private void printWeek() {
		LocalDate  start = currentDate.minusDays(6);
		for (int i = 0; i < 7; i++) {
			date[i] = start.plusDays(i);
		}
		
		for (LocalDate localDate : date) {
			System.out.println(localDate.format(formatter));
		}
		
		System.out.println("============");
	}
}
