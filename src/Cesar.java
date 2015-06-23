import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Cesar implements ICipher {

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
		
		return null;
		//for 
		
	}

	@Override
	public File decode(File crypted, File key, File decoded) {
		// TODO Auto-generated method stub
		return null;
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

}
