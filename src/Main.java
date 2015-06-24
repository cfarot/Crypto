import java.io.File;


public class Main {
	public static void main(String[] args) {
		
		/*ICipher cipher = new MonoCipher();
		MonoEncodedAttack attack = new MonoEncodedAttack();
		
		File keyMonoCipher = new File("maCle.txt");
		File messageMonoCipher = new File("message.txt");
		File encoded = new File("encoded.txt");
		File decoded = new File("decoded.txt");
		File keyFound = new File("keyFound.txt");
		File decodeWithKeyFound = new File("decodeHack.txt");
		
		
		cipher.generateKey(keyMonoCipher);
		cipher.encode(messageMonoCipher, keyMonoCipher, encoded);
		cipher.decode(encoded, keyMonoCipher, decoded);
		
		attack.findKey(encoded, keyFound);
		cipher.decode(encoded, keyFound, decodeWithKeyFound);
		
		// Cesar
		File keyCesar = new File("keyCesar.txt");
		File messageCesar = new File("messageCesar.txt");
		File encodedCesar = new File("encodedCesar.txt");
		File decodedCesar = new File("decodedCesar.txt");
		
		Cesar c = new Cesar();
		c.generateKey(keyCesar);
		c.encode(messageCesar, keyCesar, encodedCesar);
		c.decode(encodedCesar, keyCesar, decodedCesar);
		c.brutForce(encodedCesar);*/
		
		File keyHomophonique = new File("keyHomophonique.txt");
		
		ICipher homophonique = new CipherHomophonique();
		homophonique.generateKey(keyHomophonique);
		
	}
}
