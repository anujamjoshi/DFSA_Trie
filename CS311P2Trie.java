import java.io.BufferedReader;
import java.io.FileReader;

/**
* CS 311 Formal Languages and Automata
* Project #2 -dynamic finite state automaton
* Winter Quarter 2017
* Professor Dr. Daisy Sang
* @author Anuja Joshi 
*
* Project Description: In this project we created a FSA that will be able to identify 
* words described in the language and recognize the difference between reserved words 
* and other identifiers as well 
*  
*
* How to compile, link, and run this program:
*   1) make sure that Proj2_Input1.txt and Proj2_Input2.txt are within the correct directory before compilation 
*   2) javac DFSATrie.java
*   3) javac CS311P2Trie.java
*   3) java CS311P2Trie
*/
public class CS311P2Trie {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DFSATrie trie = new DFSATrie();
		String fileName= "Proj2_Input1.txt";
		try{
			/**
			 * initalize switch table 
			 */
			trie.createSwitchTable(); 		
			/**
			 * start file read  for all Reserved Words using a buffered reader 
			 */
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while (br.ready()){
				String [] input = br.readLine().split(" ") ;
				for (String s: input){
					trie.processReserved(s);
				}
			}
			
			/**
			 * start reading the program and processing it 
			 */
			fileName = "Proj2_Input2.txt";
			br = new BufferedReader(new FileReader(fileName));
			while (br.ready()){
				String line = br.readLine();
				String [] identifiers = line.split("[\\p{Punct}\\s]+");
				for (String s: identifiers){
					if (!s.equals("") && !Character.isDigit(s.charAt(0))){
						trie.processProgram(s);
					}
				}
				if (identifiers.length > 0){
					trie.addNewLine();
				}
				
				
			}
			trie.printSwitch();
			trie.printSymbolNextArray();
			System.out.println(trie.getOutput());



		}
		catch(Exception e){
			System.out.println("ERROR IN OUTER TRY");
		}

	}

}
