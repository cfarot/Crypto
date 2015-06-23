import java.io.File;


public class Main {
	public static void main(String[] args) {
		
		ICipher cipher = new MonoCipher();
		MonoEncodedAttack attack = new MonoEncodedAttack();
		
		//File key = new File("maCle.txt");
		File message = new File("mess.txt");
		File encoded = new File("encoded.txt");
		File encodedB = new File("encodedb.txt");
		File decoded = new File("decoded.txt");
		File keyFound = new File("keyFound.txt");
		File decodeWithKeyFound = new File("decodeHack.txt");
		
	/*	cipher.generateKey(key);
		cipher.encode(message, key, encoded);
		cipher.decode(encoded, key, decoded);*/
		
	//	attack.findKey(encoded, keyFound);
	//	cipher.decode(encoded, keyFound, decodeWithKeyFound);
		
		File key = new File("key.txt");
		
		Cesar c = new Cesar();
		c.generateKey(key);
		c.encode(message, key, encoded);
		c.decode(encoded, key, decoded);
	}
}
