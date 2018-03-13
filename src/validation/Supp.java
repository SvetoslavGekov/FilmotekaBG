package validation;

import java.util.Random;
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

}
