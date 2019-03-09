package selfmade;

import java.math.BigInteger;

public class PKCS1 {
	byte[] m;
	private static final int LG_MAX = 16;
	
	public static byte[] bourrer(byte[] m, BigInteger n) {
		byte[] mm = {0};
		return mm;
	}
	
	
	public static void os2ip(BigInteger code, byte[] encodage) {
		code = BigInteger.ZERO;
		for(int i = 0; i < encodage.length; i++) {	// On utilise la méthode de Horner
			code = code.multiply(BigInteger.valueOf(256));
			int chiffre = encodage[i];				// chiffre peut être négatif!
			if (chiffre < 0) chiffre +=256;			// car les Bytes sont signés en Java!
			// int chiffre = encodage[i] & 0xFF		// Equivalent plus simple!
			code = code.add(BigInteger.valueOf(chiffre));
		}
	}
	
	public static void ip2os(BigInteger code, byte[] decodage) {
		int longueur = LG_MAX;		//C'est la taille du tableau décodage
		BigInteger tmp = code;
		for(int i = longueur -1; i>=0; i--) {
			decodage[i] = (byte) tmp.mod(BigInteger.valueOf(256)).intValue();
			tmp = tmp.divide(BigInteger.valueOf(256));
		}
	}

}
