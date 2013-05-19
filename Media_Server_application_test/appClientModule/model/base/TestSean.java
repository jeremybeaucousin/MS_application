package model.base;

import java.util.Hashtable;

public class TestSean {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Hashtable<String, String> testTable =  new Hashtable<String, String>();
		
		testTable.put("colonne1", "valeur1");
		testTable.put("colonne2", "valeur2");
		testTable.put("colonne3", "valeur3");
		testTable.put("colonne4", "valeur4");
		System.out.println(DatabaseActionsImplemented.insert("table", testTable));
	}

}
