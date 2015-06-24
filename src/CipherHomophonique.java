import java.awt.AlphaComposite;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CipherHomophonique implements ICipher{

	private String frequence = " EAISRNTLUODCPMBHVFG,.'QYXJKWZ;:\"";
	private String lettresAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,;:\"'";
	
	@Override
	public File encode(File message, File key, File encoded) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File decode(File crypted, File key, File decoded) {
		// TODO Auto-generated method stub
		return null;
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
		
		/*try {
		  
		    FileOutputStream fos = new FileOutputStream(myKey);
		    fos.write( stringOut.getBytes() ); //On parse le contenu de la chaîne qu'on converti d'abord en variable de type byte
		    fos.close(); //Fermeture du fichier
		} catch(Exception e) {
		    e.printStackTrace();
		}*/
		System.out.println(mapCaractereListByte);
		
	}

	

}
