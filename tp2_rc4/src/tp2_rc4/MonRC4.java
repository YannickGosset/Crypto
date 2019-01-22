package tp2_rc4;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;


//-*- coding: utf-8 -*-

public class MonRC4
{     
 private static int LG_FLUX = 10;
 // Ce programme ne produira que les 10 premiers octets de le clef longue.

 //static int[] clef = {1, 2, 3, 4, 5};
 static int[] clef = {0x4B, 0x59, 0x4F, 0x54, 0x4F}; // C'est "KYOTO"

 static int lg = clef.length;
 
 static int[] state = new int[256];       // Ces int sont <256.
 static int i = 0, j = 0;                 // Ils représentent un octet.

 public static void main(String[] args)
 {
     initialisation();
     System.out.print("Premiers octets de la clef longue : ");
     for (int k = 0; k < LG_FLUX; k++) {
         System.out.print(String.format("0x%02X ", production()));
         // Affichage d'un octet généré
     }
     System.out.print("\n");
     try {
     File fichier = new File("butokuden.jpg");//change to confidentiel.jpg to decrypt
     //Lecture morceau/morceau + rapide, + élégante (récupérer lecture sur resume.java du HMAC)
     byte[] fileToByte = Files.readAllBytes(fichier.toPath());
     byte[] byteToFile = new byte[fileToByte.length];
     for(int i = 0; i < fileToByte.length; ++i) {
    	 byteToFile[i] = (byte) (fileToByte[i] ^ production());
     }
     File fichier2 = new File("confidentiel.jpg");
     Files.write(fichier2.toPath(), byteToFile,  StandardOpenOption.CREATE);
     }catch(Exception e) {
    	 e.printStackTrace();
     }
     System.out.println("Terminé");
 }
      
 private static void echange(int k, int l)
 {	
     int temp = state[k]; 
     state[k] = state[l]; 
     state[l] = temp; 
 }
 
 private static void initialisation()
 {	
     System.out.print("Clef courte utilisée : ");
     for (int k = 0; k < lg ; k++ )
         System.out.print(String.format("0x%02X ", clef[k]));
     System.out.println("\nLongueur de la clef courte : " + lg);
     for (i=0; i < 256; i++) state[i] = i;
     j = 0;
     for (int i=0; i < 256; i++) {
         j = (j + state[i] + clef[i % lg]) % 256;
         echange(i,j);                // Echange des octets en i et j
     }
     i = 0;
     j = 0;
 }
 
 private static int production()
 {
     i = (i + 1) % 256;            // Incrémentation de i modulo 256
     j = (j + state[i]) % 256;     // Déplacement de j
     echange(i,j);                 // Echange des octets en i et j
     return state[(state[i] + state[j]) % 256];
 }     
 
 public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
 }
}

/* 1er test avec la clef 12345
$ make
javac *.java 
$ java MonRC4
Clef courte utilisée : 0x01 0x02 0x03 0x04 0x05 
Longueur de la clef courte : 5
Premiers octets de la clef longue : 0xB2 0x39 0x63 0x05 0xF0 0x3D 0xC0 0x27 0xCC 0xC3 
*/

/* 2nd test avec la clef "KYOTO"
$ make
javac *.java 
$ java MonRC4
Clef courte utilisée : 0x4B 0x59 0x4F 0x54 0x4F 
Longueur de la clef courte : 5
Premiers octets de la clef longue : 0x8C 0xE5 0x01 0xB4 0xD3 0xDF 0x6B 0xA7 0x41 0x0D 
*/

/*
Pour trouver le codage de "KYOTO" en hexadécimal:
$ echo -n "KYOTO" | od -t x1
0000000    4b  59  4f  54  4f                                            
0000005
*/
