import java.io.File;


public interface ICipher {

	File encode(File message, File key, File encoded);
	File decode(File crypted, File key, File decoded);
	void generateKey(File myKey);
}
