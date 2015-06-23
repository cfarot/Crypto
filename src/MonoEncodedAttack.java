import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class MonoEncodedAttack {

	//private String frequence = "EAISNRTOLUDCMP GBVHF,.'QYXJKWZ;:\"";
	private String frequence = "EASINRTOLUDCMP GBVHF,.'QYXJKWZ;:\"";
	
	private String lettresAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,;:\"'";
	
	public void findKey(File encoded, File foundKey){
		
		Map<String, Integer> occurenceLetterFileEncoded = new HashMap<String, Integer>();
		
		String allText = "";
		String messageLine;
		BufferedReader br = null;
		try {
			
			br = new BufferedReader(new FileReader(encoded));
			while((messageLine = br.readLine()) != null){
				allText += messageLine;
			}
			
			for(int j=0; j<lettresAlphabet.length(); j++){
				String lettre = String.valueOf(lettresAlphabet.charAt(j));
				if(lettresAlphabet.contains(lettre))
						occurenceLetterFileEncoded.put(lettre, countOccurence(allText, lettre));
			}
				
			List<Entry<String, Integer>> sortedEntries = new ArrayList<Entry<String,Integer>>(occurenceLetterFileEncoded.entrySet());
				// sequence par ordre frequence du fichier encodé
				 Collections.sort(sortedEntries, 
				            new Comparator<Entry<String, Integer>>() {
				                @Override
				                public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
				                    return e2.getValue().compareTo(e1.getValue());
				                }
				            }
				    );
					System.out.println(sortedEntries);
				
				String lstLettreKey[] = new String [lettresAlphabet.length()];
				 for(int k = 0; k<sortedEntries.size(); k++){
					 String lettreAlphabetWithFrequence =  String.valueOf(frequence.charAt(k));
					 for(int j=0; j<lettresAlphabet.length(); j++){ 
						 if(lettreAlphabetWithFrequence.equals(String.valueOf(lettresAlphabet.charAt(j)))){
							 lstLettreKey[j] = sortedEntries.get(k).getKey();
						 }
					 }
				 }
				
				 String key = "";
				 for(int p = 0; p<lstLettreKey.length; p++){
					 key += lstLettreKey[p];
				 }
				 
				 FileWriter fw;
					try {
						fw = new FileWriter(foundKey.getAbsoluteFile());
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(key);
						bw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	private int countOccurence(String texte, String lettre){
		int counter = 0;
		for(int i = 0; i<texte.length(); i++){
			if(String.valueOf(texte.charAt(i)).equals(lettre))
				counter ++;
		}
		return counter;
	}

}
