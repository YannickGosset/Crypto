package selfmade;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ResumeSHA1 {
	
	public static byte[] resumer(String message) {
		byte[] resumeSHA1 = null;
		MessageDigest fonctionDeHachage;
		try {
			fonctionDeHachage = MessageDigest.getInstance("SHA1");
			resumeSHA1 = fonctionDeHachage.digest(message.getBytes());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resumeSHA1;
	}
	
	
	public static void main(String[] args) throws Exception {
		byte[] resumeSHA1;
		String message = "Alain Turin";
		System.out.println("Message à hacher: \"" + message + "\"");

		MessageDigest fonctionDeHachage = MessageDigest.getInstance("SHA1");
		resumeSHA1 = fonctionDeHachage.digest(message.getBytes());

		System.out.print("Le résumé SHA1 de cette chaîne est: 0x");
		for (byte octet : resumeSHA1)
			System.out.print(String.format("%02X", octet));
		System.out.println();
	}
}

/*
 * $ javac ResumeSHA1.java $ java ResumeSHA1 Message à hacher: "Alain Turin" Le
 * résumé SHA1 de cette chaîne est: 0x9B682F2CA6F44CB60493288A686DE5D81ECA6B6D
 */
