import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CipherHomophonique implements ICipher{

	private String frequence = " EAISRNTLUODCPMBHVFG,.'QYXJKWZ;:\"";
	private String lettresAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,;:\"'";
	
	Map<String, byte[]> translationEncode = new HashMap<String, byte[]>();
	Map<Integer, String> translationDecode = new HashMap<Integer, String>();
	
	//Map<String, Integer>
	boolean encode = true;
	
	@Override
	public File encode(File message, File key, File encoded) {
		
		try {
			Path pathKey = Paths.get(key.getPath());
			byte[] dataKey = Files.readAllBytes(pathKey);
			getTranslation(dataKey);
			
			if(encode){
			String messageLine= "";
			BufferedReader br = new BufferedReader(new FileReader(message));
		
			FileOutputStream fos = new FileOutputStream(encoded);
				while((messageLine = br.readLine()) != null){
					messageLine = messageLine.toUpperCase();
				 
						for(int j = 0; j<messageLine.length(); j++){	
							int indice = (int) (Math.random() * (translationEncode.get(String.valueOf(messageLine.charAt(j))).length-1));
							byte valuesLetter = translationEncode.get(String.valueOf(messageLine.charAt(j)))[indice];
							fos.write(valuesLetter);	
						}
					
				
				}
			
				fos.close(); 
				br.close();
			}
			else{
				Path pathFileEncoded = Paths.get(message.getPath());
				byte[] dataMessageEncoded = Files.readAllBytes(pathFileEncoded);
				
				String messageDecoded = "";
				for(int j = 0; j<dataMessageEncoded.length; j++){
					messageDecoded += translationDecode.get((byte) dataMessageEncoded[j] & 0xff);
				}
				
				FileWriter fw = new FileWriter(encoded.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				
				bw.write(messageDecoded);	
				
				bw.close();
			}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			return encoded;		
	}

	@Override
	public File decode(File crypted, File key, File decoded) {
		encode = false;
		return encode(crypted, key, decoded);
	}

private void getTranslation(byte[] allBytes){
		
		
		int nbBytes = 1;
		int indiceLettre = 0;
		
		for(int i = 0; i<allBytes.length; i+=(nbBytes+1)){
			if(allBytes[i] != 0){
				
				nbBytes = (byte) allBytes[i] & 0xff;
				byte [] tabByteLetter = new byte[nbBytes];
				int indiceTabByteLetter = 0;
				for(int j = i+1; j<(nbBytes+i); j++){
					tabByteLetter[indiceTabByteLetter] = allBytes[j];
					indiceTabByteLetter++;
				}
				//System.out.println(lettresAlphabet.charAt(indiceLettre));
				translationEncode.put(String.valueOf(lettresAlphabet.charAt(indiceLettre)), tabByteLetter);
				for(int k=0; k<tabByteLetter.length; k++){
					translationDecode.put((byte) tabByteLetter[k] & 0xff, String.valueOf(lettresAlphabet.charAt(indiceLettre)));
				}
				
			}
			indiceLettre++;
		}
		
		System.out.println(translationDecode.get(0));
		System.out.println(translationDecode.get(1));
		System.out.println(translationDecode.get(2));
	}

	@Override
	public void generateKey(File myKey) {
		
		List<Integer> lstBytes = new ArrayList<Integer>();
		Map<Character, byte[]> mapCaractereListByte = new HashMap<Character, byte[]>();
		
		for(int i = 0; i<256; i++){
			lstBytes.add(i);
		}
		
		// 30*8 + 3*4
		byte[][] keys = new byte[frequence.length()][] ;
		for(int i = 0; i<frequence.length(); i++){
			if(lstBytes.size() > 15)
				keys[i] = new byte[8];
			else
				keys[i] = new byte[4];

			for(int j=0; j<keys[i].length; j++){
				int randomNum = (int) (Math.random() * (lstBytes.size()-1));
				keys[i][j] = lstBytes.get(randomNum).byteValue();
				lstBytes.remove(randomNum);
			}
			mapCaractereListByte.put(frequence.charAt(i), keys[i]);
		}
		
		try{
		 FileOutputStream fos = new FileOutputStream(myKey);
		 
		for(int j = 0; j<lettresAlphabet.length(); j++){
			byte[] valuesLetter = mapCaractereListByte.get(lettresAlphabet.charAt(j));
			int nbByte = valuesLetter.length;
			fos.write(nbByte);
			fos.write(valuesLetter);
		}
		
		fos.write(0);
		fos.close(); 
		
		} catch(Exception e) {
		    e.printStackTrace();
		}	
		
	}

	

}
