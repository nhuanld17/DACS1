package TEST;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LastSevenDays {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // In ra ngày hiện tại và 6 ngày trước đó
        for (int i = 0; i < 7; i++) {
            LocalDate date = today.minusDays(i);
            System.out.println(date.format(formatter));
        }
    }
}

