import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class CS311P2Trie {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DFSATrie trie = new DFSATrie();
		String fileName= "Proj2_Input1.txt";
		try{
			/*
			 * initalize switch table 
			 */
			trie.createSwitchTable(); 
			//			
			/*
			 * start file read  for all Reserved Words using a buffered reader 
			 */
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while (br.ready()){
				String [] input = br.readLine().split(" ") ;
				for (String s: input){
					trie.processReserved(s);
				}
			}
			trie.printSwitch();
			trie.printSymbolNextArray();
			
////			/**
////			 * start reading the program and processing it 
////			 */
////			fileName = "Proj2_Input2.txt";
////			br = new BufferedReader(new FileReader(fileName));
////			while (br.ready()){
////				String line = br.readLine();
////				//ArrayList <String> identifiers = new ArrayList<String>();
////				String [] identifiers = line.split("[\\p{Punct}\\s]+");
////				for (String s: identifiers){
////					if (!s.equals("") && !Character.isDigit(s.charAt(0))){
////						System.out.println("___________________\n"+s);
////						trie.processIdentifier(s, '?', true);
////					}
////				}
//				//trie.addNewLine();
//				
//			}
			//System.out.println(trie.getOutput());



		}
		catch(Exception e){
			System.out.println("ERROR IN OUTER TRY");
		}

	}

}
