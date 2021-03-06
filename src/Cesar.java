import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Cesar implements ICipher {

	private String lettresAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,;:\"'";
	Map<String, String> translation;
	private boolean encode = true;
	
	@Override
	public File encode(File message, File key, File encoded) {
		
		String keyStr = "";
		BufferedReader br = null;
		
		try {

			br = new BufferedReader(new FileReader(key));
			keyStr = br.readLine();
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		String listLettreKey[] = new String [lettresAlphabet.length()];
		boolean keyExist = false;
		int keyIdx=0;
		int i = 0;
		
		while(!keyExist && i<lettresAlphabet.length()){
			if(keyStr.equals(String.valueOf(lettresAlphabet.charAt(i)))){
				keyExist = true;
				keyIdx = i;
			}
			i++;
		}
		
//		System.out.println(keyStr);
//		System.out.println(keyIdx);
		
		listLettreKey[keyIdx] = String.valueOf(lettresAlphabet.charAt(lettresAlphabet.length()-1));
		
		for (i=keyIdx-1; i>=0; i--){
			listLettreKey[i] = String.valueOf(lettresAlphabet.charAt(lettresAlphabet.length()-1-i));
		}
		
		for (int j=keyIdx+1; j<listLettreKey.length; j++){
			listLettreKey[j] = String.valueOf(lettresAlphabet.charAt(j-(keyIdx+1)));
		}
		
		String result = "";		
		for(i=0; i<listLettreKey.length; i++){
			result += listLettreKey[i];
		}		
		
//		System.out.println(result);
		
		getTranslation(result);
		
		String messageLine= "";
		
		try {
			FileWriter fw;
			br = new BufferedReader(new FileReader(message));
			fw = new FileWriter(encoded.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			while((messageLine = br.readLine()) != null){
				messageLine = messageLine.toUpperCase();
				    String messageLineEncoded = "";
					
					for(int j = 0; j<messageLine.length(); j++){
						if(translation.get(String.valueOf(messageLine.charAt(j))) != null)
							messageLineEncoded += translation.get(String.valueOf(messageLine.charAt(j)));
						else
							messageLineEncoded += messageLine.charAt(j);
					}
					//System.out.println(messageLineEncoded);
					bw.write(messageLineEncoded+"\n");		
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		
		return null;
	}

	@Override
	public File decode(File crypted, File key, File decoded) {
		encode = false;
		return encode(crypted, key, decoded);
	}

	@Override
	public void generateKey(File myKey) {
		String myKeyStr;
		String keysPossible = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int randomNum = (int) (Math.random() * keysPossible.length());
		myKeyStr = String.valueOf(keysPossible.charAt(randomNum));
		FileWriter fw;
		try {
			fw = new FileWriter(myKey.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(myKeyStr);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void brutForce(File encoded){
		
		File myKey = new File("keyTry.txt");
		File brutForce = new File("brutForceHackCesar.txt");
		String allBrutForceDecode = "";
		String keysPossible = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		FileWriter fw;
		for(int i=0; i<keysPossible.length(); i++){
			String keySelect = String.valueOf(keysPossible.charAt(i));
			try {
				fw = new FileWriter(myKey.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(keySelect);
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			decode(encoded, myKey, brutForce);
			
			String decodeBrutForce = "";
			BufferedReader br = null;
			try {

				br = new BufferedReader(new FileReader(brutForce));
				decodeBrutForce = br.readLine();
				br.close();

			} catch (IOException e) {
				e.printStackTrace();
			} 
			allBrutForceDecode += "with key'"+keySelect+"': "+decodeBrutForce+"\n";
		}
		
		try {
			fw = new FileWriter(brutForce.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(allBrutForceDecode);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getTranslation(String key){
		
		translation = new HashMap<String, String>();
		if (key.trim().length() > 0) {
			if(encode){
				for (int i = 0; i < lettresAlphabet.length(); i++) {
					translation.put(String.valueOf(lettresAlphabet.charAt(i)), String.valueOf(key.charAt(i)));
				}
			}
			else{
				for (int i = 0; i < lettresAlphabet.length(); i++) {
					translation.put(String.valueOf(key.charAt(i)), String.valueOf(lettresAlphabet.charAt(i)));
				}
			}
		}
	}
}
