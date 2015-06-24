import java.io.File;
import java.io.FileOutputStream;
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
	Map<String, byte[]> translation;
	boolean encode = true;
	
	@Override
	public File encode(File message, File key, File encoded) {
		
		try {
			Path path = Paths.get(key.getPath());
			byte[] data = Files.readAllBytes(path);
			getTranslation(data);
			
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

private void getTranslation(byte[] allBytes){
		
		translation = new HashMap<String, byte[]>();
		int nbBytes = 1;
		int indiceLettre = 0;
		for(int i = 0; i<allBytes.length; i+=nbBytes){
			if(allBytes[0] != 0){
				nbBytes = allBytes[i];
				byte [] tabByteLetter = new byte[nbBytes];
				int indiceTabByteLetter = 0;
				for(int j = i+1; j<nbBytes; j++){
					tabByteLetter[indiceTabByteLetter] = allBytes[j];
					indiceTabByteLetter++;
				}
				translation.put(String.valueOf(lettresAlphabet.charAt(indiceLettre)), tabByteLetter);
			}
			indiceLettre++;
		}
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
		
		String keyHomophonic = "";
		for(int j = 0; j<lettresAlphabet.length(); j++){
			byte[] valuesLetter = mapCaractereListByte.get(lettresAlphabet.charAt(j));
			int nbByte = valuesLetter.length;
			for(int k = 0; k<valuesLetter.length; k++){
				keyHomophonic += nbByte+valuesLetter[k];
			}
		}
		keyHomophonic += "0";
		
		try {
		    FileOutputStream fos = new FileOutputStream(myKey);
		    fos.write( keyHomophonic.getBytes()); 
		    fos.close(); 
		} catch(Exception e) {
		    e.printStackTrace();
		}
		
		
	}

	

}
