package parser;

public class LexerHelper {
	
	public static int lexemeToInt(String str) {
		try {
			return Integer.parseInt(str);
		}
		catch(NumberFormatException e) {
			System.out.println(e);
		}
		return -1;
	}

	public static char lexemeToChar(String str) {
		str = str.substring(1, str.length() - 1); //Quito comillas
		if (str.equals("\\n")) {
			return '\n';
		} else if (str.equals("\\t")) {
			return '\t';
		} else if (str.startsWith("\\")) {
			try {
				int asciiCode = Integer.parseInt(str.substring(1));
				return (char) asciiCode;
			} catch (NumberFormatException e) {
				return ' ';
			}
		}
		if (str.length() == 1) {
			return str.charAt(0);
		}
		return ' ';
	}

	public static double lexemeToReal(String str) {
		try{
			return Double.parseDouble(str);
		}
		catch(NumberFormatException e) {
			System.out.println(e);
		}
		return -1;
	}
}
