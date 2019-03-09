import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.security.MessageDigest;

public class Cert {
	public static void main(String[] args) {
		try {
			File fichier = new File("email1.txt");//email
			Reader reader = new FileReader(fichier);
			BufferedReader br = new BufferedReader(reader);
			MessageDigest fonctionDeHachage = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[2048];
			
			/*String secret = "Alain Turin";
			buffer = secret.getBytes();
			byte[] secretMD5 = fonctionDeHachage.digest(buffer);
			System.out.print("Le résumé MD5 du secret \"Alain Turin\" vaut: 0x");
			for(byte k: secretMD5)
                System.out.print(String.format("%02x", k));
            System.out.println();*/
			
			
			String auth = "X-AUTH:";
			int nbLines = 0;
			int nbOctetsLus = 0;
			System.out.println("Lecture début");
			String line = br.readLine(); // Lecture du premier morceau
			boolean isEnTete = true;                   // Lecture du premier morceau
			while (line != null) {
				if(line.isEmpty()) { isEnTete = false;}
				if(!isEnTete && !line.isEmpty()) {
					System.out.println(line);
					line+="\r\n";
					buffer = line.getBytes();
					System.out.println(buffer.length + " " + line.length());
					fonctionDeHachage.update(buffer, 0, buffer.length); // Digestion du morceau
				}
				line = br.readLine();// Lecture du morceau suivant
				
			}
			reader.close();
			
			String S = "c5dcb78732e1f3966647655229729843\r\n";
			System.out.println(S.getBytes().length);
			fonctionDeHachage.update(S.getBytes(), 0, S.getBytes().length);
			
            /*System.out.println(secretMD5.length);
            fonctionDeHachage.update(secretMD5, 0, secretMD5.length);*/
			
			byte[] resumeMD5 = fonctionDeHachage.digest();
			System.out.print("Le champ \"X-AUTH:\" vaut: 0x");
			for (byte k : resumeMD5) {
				auth += String.format("%02x", k);
				System.out.print(String.format("%02x", k));
			}
			System.out.println();
			System.out.println("Champ " + auth);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
