package br.edu.ufba.softvis.visminerweb.enums;

public enum TDType {
	CODE,
	DESIGN;
	
	public static String toString(TDType tdType) {
		String str = "Code";
		
		if (tdType == TDType.DESIGN) {
			str = "Design";
		} 

		return str;
	}

}
