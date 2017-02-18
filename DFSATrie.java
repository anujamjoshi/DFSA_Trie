import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeMap;


public class DFSATrie {

	/**
	 * @param args
	 */
	/*
	 * switch array is the array list that contains all the switch start 
	 */
	static int [] switchArray = new int[54];
	/*
	 * by using the TreeMap I am mapping the letters, underscore and dollar sign to indexes to the switch array 
	 */
	static TreeMap<String, Integer> alphabetTable = new TreeMap <String, Integer>();
	/*
	 * the symbol and next array contain the information for all the 
	 */
	static ArrayList<String>symbol = new ArrayList<String>();
	static ArrayList<Integer>next = new ArrayList<Integer>();
	public static void main(String[] args) {
		String fileName= "Proj2_Input1.txt";
		try{
			intializeArray(); 
			//			
			/*
			 * start file read using a buffered reader 
			 */
			BufferedReader br = new BufferedReader(new FileReader(fileName));



		}
		catch(Exception e){
			System.out.println("ERROR IN OUTER TRY");
		}

	}

	private static void intializeArray() {
		/*
		 * initalize switch table 
		 */
		createSwitchTable(); 


	}
	public static void addSymbol(char s){
		symbol.add(s+"");
		next.add(-1);
	}
	private static void printSymbolNextArray() {
		int size = symbol.size(); 
		int tempSymbol = 0; 
		int tempNext=0;
		
		while(size>0 && tempSymbol<size && tempNext<size){
			if (tempSymbol%20==0){
				System.out.print("\nSymbol:");
				System.out.printf("%5s", symbol.get(tempSymbol));
				tempSymbol++; 
			}
			while(tempSymbol%20!=0 && tempSymbol<size){
				System.out.printf("%5s", symbol.get(tempSymbol));
				tempSymbol++; 
			}
			if (tempNext%20==0){
				System.out.print("\n next: ");
				System.out.printf("%5s", next.get(tempNext));
				tempNext++; 
			}
			while(tempNext%20!=0 && tempNext<size){
				System.out.printf("%5s", next.get(tempNext));
				tempNext++; 
			}
			System.out.println();

		}



	}

	private static void createSwitchTable(){
		/*
		 * create the array Lists to store the switch and create a hash table to store the matching indexes for each alphabet  
		 */

		for (int i =0; i <switchArray.length; i++){
			switchArray[i]= -1; 
		}

		int i = 0;
		for(char c = 'A'; c <= 'Z'; c++) {
			alphabetTable.put(String.valueOf(c), i);
			i++;
		}
		for(char c = 'a'; c <= 'z'; c++) {
			alphabetTable.put(String.valueOf(c), i);
			i++;
		}
		alphabetTable.put("_", i);
		i++;
		alphabetTable.put("$", i);
	}
	private static void printSwitch() {
		System.out.printf("%8s"," ");
		for (char c = 'A'; c <='T'; c++){
			System.out.printf("%5s", c);
		}
		System.out.println();
		System.out.print("switch: ");
		for (char c = 'A'; c <='T'; c++){
			System.out.printf("%5s", switchArray[alphabetTable.get(c+"")]);
		}
		System.out.println();
		System.out.printf("%8s"," ");
		for (char c = 'U'; c <='Z'; c++){
			System.out.printf("%5s", c);
		}
		for (char c = 'a'; c <='n'; c++){
			System.out.printf("%5s", c);
		}
		System.out.println();
		System.out.print("switch: ");
		for (char c = 'U'; c <='Z'; c++){
			System.out.printf("%5s", switchArray[alphabetTable.get(c+"")]);
		}
		for (char c = 'a'; c <='n'; c++){
			System.out.printf("%5s", switchArray[alphabetTable.get(c+"")]);
		}
		System.out.println();
		System.out.printf("%8s"," ");
		for (char c = 'o'; c <='z'; c++){
			System.out.printf("%5s", c);
		}
		System.out.printf("%5s", "_");
		System.out.printf("%5s", "$");
		System.out.println();
		System.out.print("switch: ");

		for (char c = 'o'; c <='z'; c++){
			System.out.printf("%5s", switchArray[alphabetTable.get(c+"")]);
		}
		System.out.printf("%5s", switchArray[alphabetTable.get("_")]);
		System.out.printf("%5s", switchArray[alphabetTable.get("$")]);
		System.out.println();

	}

}
