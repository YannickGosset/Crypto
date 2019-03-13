

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class OAEP {
	 	private static int LG_BLOC = 107;
	    
	    private static final byte[] lhash = hexStringToByteArray("DA39A3EE5E6B4B0D3255BFEF95601890AFD80709");
	    
	    public static void main(String[] args) throws Exception {
	        //------------------------------------------------------------------
	        //  Construction et affichage de la clef et du message clair
	        //------------------------------------------------------------------
	        BigInteger n = new BigInteger(
	                                      "00af7958cb96d7af4c2e6448089362"+
	                                      "31cc56e011f340c730b582a7704e55"+
	                                      "9e3d797c2b697c4eec07ca5a903983"+
	                                      "4c0566064d11121f1586829ef6900d"+
	                                      "003ef414487ec492af7a12c34332e5"+
	                                      "20fa7a0d79bf4566266bcf77c2e007"+
	                                      "2a491dbafa7f93175aa9edbf3a7442"+
	                                      "f83a75d78da5422baa4921e2e0df1c"+
	                                      "50d6ab2ae44140af2b", 16);
	        BigInteger e = BigInteger.valueOf(0x10001);
	        BigInteger d = new BigInteger(
	                                      "35c854adf9eadbc0d6cb47c4d11f9c"+
	                                      "b1cbc2dbdd99f2337cbeb2015b1124"+
	                                      "f224a5294d289babfe6b483cc253fa"+
	                                      "de00ba57aeaec6363bc7175fed20fe"+
	                                      "fd4ca4565e0f185ca684bb72c12746"+
	                                      "96079cded2e006d577cad2458a5015"+
	                                      "0c18a32f343051e8023b8cedd49598"+
	                                      "73abef69574dc9049a18821e606b0d"+
	                                      "0d611894eb434a59", 16);

			System.out.println("Module          (N): " + n);
			System.out.println("Exposant public (E): " + e);
			System.out.println("Exposant privé  (D): " + d);

			byte[] message = { 0x41, 0x6C, 0x66, 0x72, 0x65, 0x64 };
			System.out.println("Message clair      : " + toHex(message));
			System.out.println("Message clair      : " + new String(message));

			// ------------------------------------------------------------------
			// Du "message" au "bloc" (partie à modifier conformément à OAEP)
			// ------------------------------------------------------------------

			byte[] mm = blocMM(message);
			System.out.println("Le bloc mm = " + toHex(mm));

			// ------------------------------------------------------------------
			// Encodage, chiffrement et déchiffrement du "bloc"
			// ------------------------------------------------------------------

			BigInteger m = new BigInteger(1, mm);
			System.out.println("m = " + m);
			System.out.println("m = 0x" + String.format("%0256X", m));
			BigInteger chiffre = m.modPow(e, n);
			System.out.println("m^e mod n = " + chiffre);
			BigInteger dechiffre = chiffre.modPow(d, n);
			System.out.println("(m^e)^d mod n = " + dechiffre);
		}

		public static void fabrique(byte[] message, byte[] bloc) {

			byte[] PS = new byte[LG_BLOC - message.length - lhash.length - 1];
			byte octet = 0x01;

			System.arraycopy(lhash, 0, bloc, 0, lhash.length);
			System.arraycopy(PS, 0, bloc, lhash.length, PS.length);
			bloc[lhash.length + PS.length] = octet;
			System.arraycopy(message, 0, bloc, lhash.length + PS.length + 1, message.length);
		}

		public static byte[] blocMM(byte[] message) throws NoSuchAlgorithmException {
			byte[] bloc = new byte[LG_BLOC];
			fabrique(message, bloc);

			byte g[] = new byte[20];
			SecureRandom.getInstanceStrong().nextBytes(g);
			
			byte masque[] = MGF(g, 107);

			byte blocMasque[] = new byte[LG_BLOC];
			for (int i = 0; i < 107; i++) {
				blocMasque[i] = (byte) (bloc[i] ^ masque[i]);
			}

			byte masque2[] = MGF(blocMasque, 20);
			
			byte graineMasque[] = new byte[g.length];
			for (int i = 0; i < masque2.length; i++) {
				graineMasque[i] = (byte) (g[i] ^ masque2[i]);
			}

			byte mm[] = new byte[128];
			mm[0] = 0;
			System.arraycopy(graineMasque, 0, mm, 1, graineMasque.length);
			System.arraycopy(blocMasque, 0, mm, 1 + graineMasque.length, blocMasque.length);

			return mm;
		}

		public static byte[] MGF(byte[] g, int k) throws NoSuchAlgorithmException {
			byte[] masque = new byte[k];

			byte[] tab = { 00, 00, 00, 00 };

			for (int i = 0; i < (k / 20); i++) {
				tab[3] = (byte) i;
				byte[] tabAHasher = new byte[g.length + tab.length];

				System.arraycopy(g, 0, tabAHasher, 0, g.length);
				System.arraycopy(tab, 0, tabAHasher, g.length, tab.length);

				MessageDigest fonctionDeHachage = MessageDigest.getInstance("SHA1");
				byte[] resumeSHA1 = fonctionDeHachage.digest(tabAHasher);

				System.arraycopy(resumeSHA1, 0, masque, i * 20, resumeSHA1.length);
			}

			if (k % 20 != 0) {
				tab[3]++;

				byte[] tabAHasher = new byte[24];

				System.arraycopy(g, 0, tabAHasher, 0, g.length);
				System.arraycopy(tab, 0, tabAHasher, g.length, tab.length);

				MessageDigest fonctionDeHachage = MessageDigest.getInstance("SHA1");
				byte[] resumeSHA1 = fonctionDeHachage.digest(tabAHasher);

				System.arraycopy(resumeSHA1, 0, masque, (k / 20) * 20, k % 20);
			}
			
			return masque;
		}

		public static String toHex(byte[] donnees) {
			return "0x" + javax.xml.bind.DatatypeConverter.printHexBinary(donnees);
		}

		public static byte[] hexStringToByteArray(String s) {
			int len = s.length();
			byte[] data = new byte[len / 2];
			for (int i = 0; i < len; i += 2) {
				data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
			}
			return data;
		}
	}
