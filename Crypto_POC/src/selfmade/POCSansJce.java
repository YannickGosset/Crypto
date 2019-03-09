package selfmade;
import java.math.BigInteger;
import java.security.SecureRandom;

public class POCSansJce {

	private static final int LG_MAX = 16;
	static byte[] k;
	static byte[] iv;
	private static byte[] encodage;	// C’est le tableau à encoder
	private static byte[] decodage = new byte[LG_MAX];			// Ce sera le tableau décodé
	private static BigInteger code, codeChiffre;
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Un seul argument autorisé (le nom de fichier de destination)!");
			return;
		} 
		k = new byte[16];
		SecureRandom rand = new SecureRandom();
		rand.nextBytes(k);
		iv = new byte[16];
		rand.nextBytes(iv);
		
		System.out.println("Clef utilisée: 0x" + toHex(k));
		System.out.println("Vecteur d'initialisation: 0x" + toHex(iv));
		
		BigInteger e = new BigInteger(
				"44bb1ff6c2b674798e09075609b7883497ae2e2d7b06861ef9850e26d1456280523319021062c8743544877923fe65f85111792a98e4b887de8ffd13aef18ff7f6f736c821cfdad98af051e7caaa575d30b54ed9a6ee901bb0ffc17e25d444f8bfc5922325ee2ef94bd4ee15bede2ea12eb623ad507d6b246a1f0c3cc419f155",
				16);
		BigInteger n = new BigInteger(
				"94f28651e58a75781cfe69900174b86f855f092f09e3da2ad86b4ed964a84917e5ec60f4ee6e3adaa13962884e5cf8dae2e0d29c6168042ec9024ea11176a4ef031ac0f414918b7d13513ca1110ed80bd2532f8a7aab0314bf54fcaf621eda74263faf2a5921ffc515097a3c556bf86f2048a3c159fccfee6d916d38f7f23f21",
				16);
		encodage = k;
		//Bourrage pkcs1
		PKCS1.os2ip(code, encodage);
		codeChiffre = code.modPow(e, n);//code = m
		System.out.println("Code chiffré      : " + codeChiffre);
		PKCS1.ip2os(code, decodage);
		//clef étendu en AES... + PKCS5

		
	}
	

	private static String toHex(byte[] bytes) {
		String s = "";
		for (byte b : bytes) {
			s += String.format("%02X ", b);
		}
		return s;
	}
	
	
	
}
