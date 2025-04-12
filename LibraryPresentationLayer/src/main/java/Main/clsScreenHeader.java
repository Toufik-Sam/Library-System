package Main;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class clsScreenHeader {
	public static void PrintScreenHeader(String title) {
		int width = 50; // Total width of the header
        String border = "*".repeat(width); // Top and bottom border
        int padding = (width - title.length() - 2) / 2; // Calculate padding for centering

        // Get current date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = LocalDateTime.now().format(formatter);

        // Print the header
        System.out.println(border);
        System.out.printf("*%s%s%s*\n", " ".repeat(padding), title, " ".repeat(width - padding - title.length() - 2));
        System.out.println(border);
        System.out.println(currentTime);
	}
}
