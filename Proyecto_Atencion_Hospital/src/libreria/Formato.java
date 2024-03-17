package libreria;

import java.util.Locale;

public class Formato {
	
	//  Métodos static que retornan valor (con parámetros)
	public static String ajusteA(double real) {
		return String.format(Locale.US, "%2.2f", real);
	}
	public static String ajusteB(double real) {
		return String.format(Locale.US, "%7.2f", real);
	}	
}
