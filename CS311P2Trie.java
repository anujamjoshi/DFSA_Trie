import java.io.BufferedReader;
import java.io.FileReader;


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
//					System.out.println(s);
					trie.processIdentifier(s, '*', false);
				}
			}
			trie.printSwitch();
			trie.printSymbolNextArray();



		}
		catch(Exception e){
			System.out.println("ERROR IN OUTER TRY");
		}

	}

}
