import java.util.ArrayList;
import java.util.TreeMap;


public class DFSATrie {
	/** 
	 *  this is the output for the file reading 
	 */
	public String output ="";
	/**
	 * switch array is the array list that contains all the switch start 
	 */
	int [] switchArray = new int[54];
	/**
	 * by using the TreeMap I am mapping the letters, underscore and dollar sign to indexes to the switch array 
	 */
	TreeMap<String, Integer> alphabetTable = new TreeMap <String, Integer>();
	/**
	 * the symbol and next array contain the information for all the 
	 */
	ArrayList<String>symbol = new ArrayList<String>();
	ArrayList<Integer>next = new ArrayList<Integer>();
	/**
	 * a way of storing all known identifiers to allow for easy lookup / search 
	 */
	ArrayList<String> identifiers = new ArrayList<String>();
	ArrayList<String> reservedWords = new ArrayList<String>();
	/**
	 * @param String s 
	 * @return true if we have already found this identifier in the files
	 */
	public  boolean hasIdentifier(String s){
		if (identifiers.contains(s)){
			return true;
		}
		return false;
	}
	public boolean isReserved (String s){
		if (reservedWords.contains(s)){
			return true;
		}
		return false;
	}
	/**
	 * @param char s 
	 * adds the char value to the symbol array and the next array 
	 */
	public void addSymbol(char s){
		symbol.add(s+"");
		next.add(-1);
	}
	/**
	 * Prints the next and symbol array with proper format
	 */
	void printSymbolNextArray() {
		int size = symbol.size(); 
		int tempSymbol = 0; 
		int tempNext=0;
		int tempIndex =0; 

		while(size>0 && tempSymbol<size && tempNext<size){
			if (tempIndex %20 ==0){
				System.out.print("\nIndex: ");
				System.out.printf("%5s", tempIndex );
				tempIndex++; 
			}
			while(tempIndex%20!=0 && tempIndex<size){
				System.out.printf("%5s", tempIndex);
				tempIndex++; 
			}
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
	void createSwitchTable(){
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
	void printSwitch() {
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
	 * this method is for the first input file 
	 * @param s
	 */
	void processReserved(String s){
		if (hasIdentifier(s) ==false && isReserved(s)==false){
			addNewIdentifier(s, '*' );
			reservedWords.add(s);
		}

	}
	/**
	 *  this method is for the second input file 
	 */
	void processProgram(String s ){
		if (reservedWords.contains(s)){
			// this is a reserved word, it's in the file just add it to the output 
		//	System.out.println("Reserved");
			output+= s +"*";
		}
		else{ 
			/*
			 * check to see if it is a new identifier. if it is then add it to the arrays and to the output as well 
			 */
			if (hasIdentifier(s) == false){
			//	System.out.println("New Identifier ");
				addNewIdentifier(s, '?');
				identifiers.add(s);
				output+= s+"?";
			}
//			else{
//				/*
//				 * parse through the list and change the ? to a @ or make sure it is an @
//				 */
//				System.out.println("Old Identifier");
//				int index = switchArray[alphabetTable.get(s.charAt(0)+"")];
//				for (int charIndex = 1; charIndex <s.length(); charIndex++){
//					
//					
//				}
//					System.out.println(index + "HIFJ");
//					symbol.set(index, "@");
//					output+= s +"@";
//			}
		}
		
	}
	/**
	 * This method takes in the identifier that has not been processed,
	 *  and adds it into the array list with the end symbol of c 
	 * @param s
	 * @param c
	 */
	
	private void addNewIdentifier(String s, char c) {
		//we haven't dealt with this identifier 
				if (switchArray[alphabetTable.get(s.charAt(0)+"")]== -1){
					// there isn't an identifier that starts with the letter so change the swtichArray to symbol.length
					switchArray[alphabetTable.get(s.charAt(0)+"")] = symbol.size();
					// add the rest of the symbols to the end of the symbol and next list
					for (int characterIndex = 1; characterIndex <s.length(); characterIndex++ ){
						addSymbol(s.charAt(characterIndex));
					}
					addSymbol(c);
				}
				else{
					
					// there is an identifier that starts with this letter 
					int index = switchArray[alphabetTable.get(s.charAt(0)+"")];
					for (int charIndex=1; charIndex<s.length(); charIndex++){
						if (index !=-1){
							// we need to parse the symbol array 
							if (symbol.get(index).equals(s.charAt(charIndex)+"") && index<symbol.size()){
								index++;
							}
							else{
								int tempIndex = index;
								index = next.get(index);
								if (index == -1){
									next.set(tempIndex, next.size());
									
								}
								charIndex --; 
							}
						}
						else { 					
							addSymbol(s.charAt(charIndex));
							
						}
					}
					if (index>=0){
						next.set(index, next.size());
						
					}
						addSymbol(c);
				}
				
				identifiers.add(s);		
	}

}
