package EncDec;

import javax.crypto.*;
import org.apache.commons.net.util.Base64;

public class Blowfish {
	KeyGenerator keygenerator;
	SecretKey secretkey;
	static Cipher cipher;
	private static Base64 base64; 
	public Blowfish() throws Exception {
	    // create a key generator based upon the Blowfish cipher
	    this.keygenerator = KeyGenerator.getInstance("Blowfish");

	    // create a key
	    this.secretkey = keygenerator.generateKey();
		// create a cipher based upon Blowfish
		this.cipher = Cipher.getInstance("Blowfish");
		this.base64 = new Base64(true);
	}
	
	public static String encryption(String inputText, SecretKey secretkey) throws Exception {
		// create a cipher based upon Blowfish
			cipher = Cipher.getInstance("Blowfish");
		    // initialise cipher to with secret key
		    cipher.init(Cipher.ENCRYPT_MODE, secretkey);
		    base64 = new Base64(true);
		 // encrypt message
		    byte[] encrypted = cipher.doFinal(inputText.getBytes());
		    //return encrypted;
		    return base64.encodeToString(cipher.doFinal(inputText.getBytes("UTF8")));
		
	}
	public SecretKey getBlowfishKey() {
		return secretkey;
	}
	
	public static String decryption(String encrypted, SecretKey secretkey) throws Exception {
		byte[] encryptedData = base64.decodeBase64(encrypted);
		// create a cipher based upon Blowfish
			cipher = Cipher.getInstance("Blowfish");
			base64 = new Base64(true);
		// re-initialise the cipher to be in decrypt mode
	    cipher.init(Cipher.DECRYPT_MODE, secretkey);

	    // decrypt message
	    byte[] decrypted = cipher.doFinal(encryptedData);
		return new String(decrypted);
		
	}
	
	

  public static void main(String[] args) throws Exception {
	  Blowfish encryption = new Blowfish();
	  encryption.getBlowfishKey();
	  System.out.println("KEY : " + encryption.getBlowfishKey());
	  String line = "hola chico como estas!!";
	  String enc = encryption.encryption(line,encryption.secretkey);
	  String dec = encryption.decryption(enc,encryption.secretkey);
	  
	  System.out.println("ENCRYPTED : "+ enc);
	  System.out.println("DECRYPTED : "+ dec);
  }
}
