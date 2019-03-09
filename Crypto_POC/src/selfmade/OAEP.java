package selfmade;

import java.math.BigInteger;

public class OAEP {
	private static int LG_BLOC = 128;
	private static byte[] g = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

	public static void main(String[] args) throws Exception {
		// ------------------------------------------------------------------
		// Construction et affichage de la clef et du message clair
		// ------------------------------------------------------------------
		BigInteger n = new BigInteger("00af7958cb96d7af4c2e6448089362" + "31cc56e011f340c730b582a7704e55"
				+ "9e3d797c2b697c4eec07ca5a903983" + "4c0566064d11121f1586829ef6900d" + "003ef414487ec492af7a12c34332e5"
				+ "20fa7a0d79bf4566266bcf77c2e007" + "2a491dbafa7f93175aa9edbf3a7442" + "f83a75d78da5422baa4921e2e0df1c"
				+ "50d6ab2ae44140af2b", 16);
		BigInteger e = BigInteger.valueOf(0x10001);
		BigInteger d = new BigInteger("35c854adf9eadbc0d6cb47c4d11f9c" + "b1cbc2dbdd99f2337cbeb2015b1124"
				+ "f224a5294d289babfe6b483cc253fa" + "de00ba57aeaec6363bc7175fed20fe" + "fd4ca4565e0f185ca684bb72c12746"
				+ "96079cded2e006d577cad2458a5015" + "0c18a32f343051e8023b8cedd49598" + "73abef69574dc9049a18821e606b0d"
				+ "0d611894eb434a59", 16);

		System.out.println("Module          (N): " + n);
		System.out.println("Exposant public (E): " + e);
		System.out.println("Exposant privé  (D): " + d);

		byte[] message = { 0x41, 0x6C, 0x66, 0x72, 0x65, 0x64 };
		System.out.println("Message clair      : " + toHex(message));

		// ------------------------------------------------------------------
		// Du "message" au "bloc" (partie à modifier conformément à OAEP)
		// ------------------------------------------------------------------
		byte[] bloc = new byte[LG_BLOC];
		
		/*for (int i = 0; i < LG_BLOC; i++)
			bloc[i] = 0;
		for (int i = 0; i < message.length; i++) {
			bloc[128 - message.length + i] = message[i];
		}*/
		buildBlock(message, bloc);
		doMGF(g,107);
		System.out.println("Bloc du message    : " + toHex(bloc));

		// ------------------------------------------------------------------
		// Encodage, chiffrement et déchiffrement du "bloc"
		// ------------------------------------------------------------------
		BigInteger m = new BigInteger(1, bloc);
		System.out.println("m = " + m);
		System.out.println("m = 0x" + String.format("%0256X", m));
		BigInteger chiffre = m.modPow(e, n);
		System.out.println("m^e mod n = " + chiffre);
		BigInteger dechiffre = chiffre.modPow(d, n);
		System.out.println("(m^e)^d mod n = " + dechiffre);
	}

	private static String toHex(byte[] bytes) {
		String s = "";
		for (byte b : bytes) {
			s += String.format("%02X ", b);
		}
		return s;
	}
	
	public static void buildBlock(byte[] m, byte[] bloc) {
		byte[] IHash = ResumeSHA1.resumer("");
		byte one = 1;
		int bytesLeft = 86-(m.length);
		byte[] PS = new byte[bytesLeft];
		for(int i = 0; i < bytesLeft; ++i) {
			PS[i] = 0;
		}
		System.arraycopy(IHash, 0, bloc, 21, IHash.length);
		System.arraycopy(PS, 0, bloc, 21+IHash.length, PS.length);
		bloc[21+IHash.length+PS.length] = one;
		System.arraycopy(m, 0, bloc, 21+IHash.length+PS.length+1, m.length);
		//System.out.println("block = " + toHex(bloc));
	}
	
	public static byte[] doMGF(byte[] g, int length) {
		byte[] MGF = new byte[length];
		byte[] bytes = {0,1,2};
		int accu = 0;
		for(int i = 0; i < g.length; ++i) {
			String msg = String.valueOf(g) + String.valueOf(bytes[i%3]);
			byte[] bytemsg = msg.getBytes();
			for(int j = 0; j < bytemsg.length; ++j) {
				bytemsg[j] = (byte) (bytemsg[j] ^ bytes[i%3]);
			}
			msg = String.valueOf(bytemsg);
			byte[] resume = ResumeSHA1.resumer(msg);
			if(accu + resume.length > length) {
				int trunc = length - accu;
				System.arraycopy(resume, 0, MGF, accu, trunc-1);
			}		
			else
				System.arraycopy(resume, 0, MGF, accu, resume.length);
			accu += resume.length;
			if(accu > length) break;
		}
		System.out.println("MGF = " + toHex(MGF));
		return MGF;
	}
}

/*
$ make
javac *.java 
$ java OAEP
Module          (N): 1232220410961060140022027618443990735890055007290...99
Exposant public (E): 65537
Exposant privé  (D): 3776738543872135592508425587329972673729883109000...09
Message clair      : 0x416C66726564
Bloc du message    : 0x000000000000000000000000000000000...0000416C66726564
m = 71933831046500
m = 0x00000000000000000000000000000000000000000000000000...0000416C66726564
m^e mod n = 7332035087841460148221217550312624636765396722834952425725...41
(m^e)^d mod n = 71933831046500
*/

