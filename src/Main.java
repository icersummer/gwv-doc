import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	private static Questionaire _questionaire = new Questionaire();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Fragebogen wird durchlaufen
		System.out.println("Bitte beantworten Sie alle Fragen mit 'Ja' oder 'Nein'." +
				"Falls Sie die Frage nicht beantworten können, bestätigen Sie bitte nur mit der Eingabetaste.\n");
		for (Question question : _questionaire.get_questionaire()) {

			// Frage wird gestellt
			System.out.println(question.get_question());
			
			// Eingabe wird eingelesen
			String eingabezeile = "";
			BufferedReader eingabe = new BufferedReader(new InputStreamReader(
	                System.in));
	        try {
	            eingabezeile = eingabe.readLine();
	        } catch (IOException exc) {
	            System.out.println(exc.getMessage());
	        }
	        
	        // Eingabeüberprüfung ob "ja" oder "nein"
			String yes = "ja";
			String no = "nein";	
			if (eingabezeile.equals(yes)) {
				question.set_answer(true);
				question.set_answered(true);
			}
			if (eingabezeile.equals(no)){
				question.set_answer(false);
				question.set_answered(true);
			}									
		}
		
		// Antworten werden an den Bayescalculator übergeben.
		Bayescalc calculator = new Bayescalc(_questionaire);
		
		// Bayescalculator berechnet die Wahrscheinlichkeit.
		System.out.println("Sie haben mit einer Wahrscheinlichkeit von " +
				calculator.get_influenza() + " eine Influenza-Erkrankung");
	}

}