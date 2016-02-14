package br.edu.ufba.softvis.visminerweb.enums;

public enum TDView {
	
	EVOLUTION,
	MANAGEMENT,
	EVALUATION;

	public static String toString(TDView tdView) {
		String str = "Evolution";
		
		if (tdView == TDView.MANAGEMENT) {
			str = "Management";
		} 
		else if (tdView == TDView.EVALUATION) {
			str = "Evaluation";
		}
		
		return str;
	}

}
