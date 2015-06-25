import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VigenerCipher implements ICipher{
	
	private String lettresAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	Map<String, Integer> lettresAlphabetMap;
	Map<String, String> translation;

	@Override
	public File encode(File message, File key, File encoded) {
		String keyStr = "";
		String messageLine= "";
		BufferedReader br = null;
		
		try {

			br = new BufferedReader(new FileReader(key));
			keyStr = br.readLine();
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < lettresAlphabet.length(); i++) {
			lettresAlphabetMap.put(String.valueOf(lettresAlphabet.charAt(i)), i);
		}
		
		
		translation = new HashMap<String, String>();
		if (keyStr.trim().length() > 0) {
			for (int i = 0; i < keyStr.length(); i++) {
				
			}
		}
		
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void generateKey(File myKey) {
		

		String myKeyStr = "";

		List<Integer> indiceAleatoireCle = new ArrayList<Integer>();

		while (indiceAleatoireCle.size() != lettresAlphabet.length()-23) {
			Integer randomNum = (int) (Math.random() * lettresAlphabet.length());
			if (!indiceAleatoireCle.contains(randomNum))
				indiceAleatoireCle.add(randomNum);
		}

		for (int i = 0; i < indiceAleatoireCle.size(); i++) {

			int indiceLettre = indiceAleatoireCle.get(i);
			myKeyStr += lettresAlphabet.charAt(indiceLettre);
		}

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

}
