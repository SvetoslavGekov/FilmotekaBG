package validation;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class Supp {
	public static final int RNG(int max) {
		Random r = new Random();
		return r.nextInt(max);
	}

	public static  final int RNG(int min, int max) {
		Random r = new Random();
		return r.nextInt(max - min) + min;
	}
	
	public static final boolean validStr(String str) {
		return str != null && !str.trim().isEmpty();
	}
	
	public static final boolean validEmail(String str){
		Pattern ptr = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		return ptr.matcher(str).matches();
	}
	
	public static final boolean validPassword(String str){
		Pattern ptr = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\ S+$).{8,}$");
		return ptr.matcher(str).matches();
	}
	
	public static final boolean validPhoneNumber(String str){
		return str.length() == 10 && str.charAt(0) == '0' && str.matches("[0-9]+");
	}

	
	public static String inputString() {
		Scanner scan = new Scanner(System.in);
		boolean isValid = false;
		String input = "";
		do {
			input = scan.nextLine().trim();
			isValid = validStr(input);
			if(!isValid) {
				System.out.print("\nPlease enter a valid string: ");
			}
		}while(!isValid);
		return input;
	}
	
	public static int getPositiveNumber() {
		Scanner scan = new Scanner(System.in);
		boolean isValid = false;
		int number = -1;
		while(!isValid) {
			while(!scan.hasNextInt()) {
				System.out.println("Please enter a valid positive integer.");
				scan.nextLine();
			}
			number = scan.nextInt();
			if(number > 0) {
				isValid = true;
			}
			else {
				System.out.println("You've entered an negative integer. Try again.");
			}
		}
		return number;
	}
}

