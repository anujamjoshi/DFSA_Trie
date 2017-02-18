import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeMap;


public class DFSATrie {
//	/**
//	 * String output is the output that the DFSA is going to add in for the begining 
//	 */
//	String output; 
	/**
	 * switch array is the array list that contains all the switch start 
	 */
	static int [] switchArray = new int[54];
	/**
	 * by using the TreeMap I am mapping the letters, underscore and dollar sign to indexes to the switch array 
	 */
	static TreeMap<String, Integer> alphabetTable = new TreeMap <String, Integer>();
	/**
	 * the symbol and next array contain the information for all the 
	 */
	static ArrayList<String>symbol = new ArrayList<String>();
	static ArrayList<Integer>next = new ArrayList<Integer>();
	/**
	 * a way of storing all known identifiers to allow for easy lookup / search 
	 */
	static ArrayList<String> identifiers = new ArrayList<String>();
	/**
	 * @param String s 
	 * @return true if we have already found this identifier in the files
	 */
	public static boolean hasIdentifier(String s){
		if (identifiers.contains(s)){
			return true;
		}
		return false;
	}
	/**
	 * @param char s 
	 * adds the char value to the symbol array and the next array 
	 */
	public static void addSymbol(char s){
		symbol.add(s+"");
		next.add(-1);
	}
	/**
	 * Prints the next and symbol array with proper format
	 */
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
	/**
	 * initializes the switch table with -1 and hash table to map the letters, and the _ and $ to indexes
	 */
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
	/**
	 * prints the switch table with proper formatting 
	 */
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
	/**
	 * if hasIdentifier(s) == false then we have not dealt with this identifier 
	 * Step 1: go to switch Table and check if switch[alphabetTable.get(s.getCharAt(0)] == -1 
	 * Step 2 a: if -1 then we haven't had an identifier that starts with this char
	 * Step 3: then change switch[alphabetTable.get(s.getCharAt(0)] to symbol.length
	 * Step 4: add the rest of the identifier into the symbol using the addSymbol() until the end 
	 * Step 4a: and add either a "*" if it is a reserved word 
	 * Step 4b: or add a "?" 
	 * Step 5: add to identifiers List 
	 * 
	 * Step 2b: if != -1 then we have found an identifier that starts with that character 
	 * Step 3: go to the character at the index given by symbol.get(switch[alphabetTable.get(s.getCharAt(0)])
	 * Step 4: if the characters are the same, then continue parsing through the symbol table 
	 * 
	 * Step 4a: until you hit either a character that is different 
	 * Step 5a: if you have reached a character that is different the set the next of that symbol to symbol.length 
	 * Step 6: then add the remaining characters to the symbol using the addSymbol() until the end 
	 * Step 7a: and add either a "*" if it is a reserved word 
	 * Step 7b: or add a "?" 
	 * 
	 * Step 4b: or you have finished all the characters within the identifier but there is no end for the identifier you are currently following 
	 * Step 5: Set the next to the symbol you are currently checking to symbol.length 
	 * Step 6: add either a * or a ? to the symbol list
	 * Step 7: add to identifiers List 
	 */
	/**
	 * else if hasIdentifier(s) == true then we have dealt with this identifier before
	 * Step 1: go to the character at the index given by symbol.get(switch[alphabetTable.get(s.getCharAt(0)])
	 * Step 2: if the characters are the same, then continue parsing through the symbol table
	 * Step 2a: until you hit either a character that is different 
	 * Step 3: if you have reached a character that is different then go to the next symbol and continue parsing 
	 * Step 3a: until you reach the end of the word, 
	 * Step 4: if you reach the end of the word and there is no end marker at the next spot, then use the next array to get the next index 
	 * Step 5: if the end marker is a * keep it otherwise change the '?' to a '@' symbol  
	 */
	/**
	 * after updating the arrays, add the identifier into our output list and store it 
	 */
	private static void addIdentifier(String s, char endSymbol){
		if (hasIdentifier(s) ==false){
		
			//we haven't dealt with this identifier 
			System.out.println(alphabetTable.get(s.charAt(0)+""));
			System.out.println(switchArray[alphabetTable.get(s.charAt(0)+"")]);
			if (switchArray[alphabetTable.get(s.charAt(0)+"")]== -1){
				// there isn't an identifier that starts with the letter so change the swtichArray to symbol.length
				switchArray[alphabetTable.get(s.charAt(0)+"")] = symbol.size();
				for (int characterIndex = 1; characterIndex <s.length(); characterIndex++ ){
					addSymbol(s.charAt(characterIndex));
				}
				addSymbol(endSymbol);
			}
			identifiers.add(s);
		}
		
	}
	public static void main(String[] args) {
		String fileName= "Proj2_Input1.txt";
		try{
			/*
			 * initalize switch table 
			 */
			createSwitchTable(); 
			addIdentifier("anuja", '*');
			printSwitch(); 
			printSymbolNextArray(); 
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

}
