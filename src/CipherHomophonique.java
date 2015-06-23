import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class CipherHomophonique implements ICipher{

	private String alphabet =  "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,;:\"'";
	
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
		
		List<Integer> lstBytesUse = new ArrayList<Integer>();
		
		byte[][] keys = new byte[alphabet.length()][7];
		for(int i = 0; i<alphabet.length(); i++){
			for(int j=0; j<keys[i].length; j++){
				Integer randomNum = (int) (Math.random() * 255);
			}
		}
		
	}

	

}
