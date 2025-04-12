package Utility;

import java.util.Scanner;

public class clsUtil {
    public static boolean compareStrings(String str1, String str2) {
        // Check if both strings are not null
        if (str1 == null || str2 == null) {
            return false;
        }

        // Check if both strings contain only letters
        if (!str1.matches("[a-zA-Z]+") || !str2.matches("[a-zA-Z]+")) {
            return false;
        }

        // Compare the strings ignoring case
        return str1.equalsIgnoreCase(str2);
    }
	public static short ReadShortNumberBetween(short From,short To) {
	     Scanner scanner = new Scanner(System.in);
	        short validShort = 0;
	        boolean isValid = false;

	        while (!isValid) {
	            System.out.print("Choose What You want To Do ["+From+" to"+ To+"]? ");
	            if (scanner.hasNextShort()) { // Check if the input is a valid short
	                validShort = scanner.nextShort();
	                if(validShort<From || validShort>To) {
	                	System.out.println("Number out of Range!");
	                	continue;
	                }
	                isValid = true; // Input is valid, exit the loop
	            } else {
	                System.out.println("Invalid input! That's not a short number.");
	                scanner.next(); // Consume the invalid input to avoid infinite loop
	            }
	        }

	        return validShort;
	}
	public static short ReadShortNumber() {
		Scanner scanner = new Scanner(System.in);
        short validShort = 0;
        boolean isValid = false;

        while (!isValid) {
            if (scanner.hasNextShort()) { // Check if the input is a valid short
                validShort = scanner.nextShort();
                isValid = true; // Input is valid, exit the loop
            } else {
                System.out.println("Invalid input! That's not a short number.");
                scanner.next(); // Consume the invalid input to avoid infinite loop
            }
        }

        return validShort;
	}
	public static String ReadString() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		return input;
	}
	public static void ClearScreen() {
		 for (int i = 0; i < 50; i++) {
	            System.out.println();
	        }
	}
	 public static String wrapText(String text, int maxWidth) {
	        StringBuilder wrappedText = new StringBuilder();
	        String[] words = text.split(" ");
	        StringBuilder currentLine = new StringBuilder();

	        for (String word : words) {
	            if (currentLine.length() + word.length() + 1 > maxWidth) {
	                // Append the current line to the result and start a new line
	                wrappedText.append(currentLine).append("\n");
	                currentLine.setLength(0); // Clear the current line
	            }
	            if (currentLine.length() > 0) {
	                currentLine.append(" ");
	            }
	            currentLine.append(word);
	        }

	        // Append the last line
	        wrappedText.append(currentLine);

	        return wrappedText.toString();
	    }
}
