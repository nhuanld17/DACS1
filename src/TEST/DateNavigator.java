package TEST;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DateNavigator {
    private LocalDate currentDate;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DateNavigator() {
        this.currentDate = LocalDate.now(); // Ngày hiện tại
    }

    private void printWeek() {
        LocalDate startOfWeek = currentDate.minusDays(6); // Bắt đầu từ 6 ngày trước ngày hiện tại
        for (int i = 0; i < 7; i++) {
            System.out.println(startOfWeek.plusDays(i).format(formatter));
        }
    }

    public void navigateWeeks() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        printWeek(); // In tuần ban đầu

        while (!input.equals("exit")) {
            System.out.println("Enter '<<' to go back 7 days, '>>' to go forward 7 days, or 'exit' to quit:");
            input = scanner.nextLine().trim();
            switch (input) {
                case "<<":
                    currentDate = currentDate.minusDays(7); // Dịch chuyển 7 ngày về trước
                    printWeek();
                    break;
                case ">>":
                    currentDate = currentDate.plusDays(7); // Dịch chuyển 7 ngày về sau
                    printWeek();
                    break;
                case "exit":
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        DateNavigator app = new DateNavigator();
        app.navigateWeeks();
    }
}
