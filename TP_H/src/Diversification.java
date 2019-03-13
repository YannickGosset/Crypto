
public class Diversification {

	public static byte[] SBox = {
			  0x63,(byte) 0x7C,(byte) 0x77,(byte) 0x7B,(byte) 0xF2,(byte) 0x6B,(byte) 0x6F,(byte) 0xC5,(byte) 0x30,(byte) 0x01,(byte) 0x67,(byte) 0x2B,(byte) 0xFE,(byte) 0xD7,(byte) 0xAB,(byte) 0x76,(byte) 
			  0xCA,(byte) 0x82,(byte) 0xC9,(byte) 0x7D,(byte) 0xFA,(byte) 0x59,(byte) 0x47,(byte) 0xF0,(byte) 0xAD,(byte) 0xD4,(byte) 0xA2,(byte) 0xAF,(byte) 0x9C,(byte) 0xA4,(byte) 0x72,(byte) 0xC0,(byte) 
			  0xB7,(byte) 0xFD,(byte) 0x93,(byte) 0x26,(byte) 0x36,(byte) 0x3F,(byte) 0xF7,(byte) 0xCC,(byte) 0x34,(byte) 0xA5,(byte) 0xE5,(byte) 0xF1,(byte) 0x71,(byte) 0xD8,(byte) 0x31,(byte) 0x15,(byte) 
			  0x04,(byte) 0xC7,(byte) 0x23,(byte) 0xC3,(byte) 0x18,(byte) 0x96,(byte) 0x05,(byte) 0x9A,(byte) 0x07,(byte) 0x12,(byte) 0x80,(byte) 0xE2,(byte) 0xEB,(byte) 0x27,(byte) 0xB2,(byte) 0x75,(byte) 
			  0x09,(byte) 0x83,(byte) 0x2C,(byte) 0x1A,(byte) 0x1B,(byte) 0x6E,(byte) 0x5A,(byte) 0xA0,(byte) 0x52,(byte) 0x3B,(byte) 0xD6,(byte) 0xB3,(byte) 0x29,(byte) 0xE3,(byte) 0x2F,(byte) 0x84,(byte) 
			  0x53,(byte) 0xD1,(byte) 0x00,(byte) 0xED,(byte) 0x20,(byte) 0xFC,(byte) 0xB1,(byte) 0x5B,(byte) 0x6A,(byte) 0xCB,(byte) 0xBE,(byte) 0x39,(byte) 0x4A,(byte) 0x4C,(byte) 0x58,(byte) 0xCF,(byte) 
			  0xD0,(byte) 0xEF,(byte) 0xAA,(byte) 0xFB,(byte) 0x43,(byte) 0x4D,(byte) 0x33,(byte) 0x85,(byte) 0x45,(byte) 0xF9,(byte) 0x02,(byte) 0x7F,(byte) 0x50,(byte) 0x3C,(byte) 0x9F,(byte) 0xA8,(byte) 
			  0x51,(byte) 0xA3,(byte) 0x40,(byte) 0x8F,(byte) 0x92,(byte) 0x9D,(byte) 0x38,(byte) 0xF5,(byte) 0xBC,(byte) 0xB6,(byte) 0xDA,(byte) 0x21,(byte) 0x10,(byte) 0xFF,(byte) 0xF3,(byte) 0xD2,(byte) 
			  0xCD,(byte) 0x0C,(byte) 0x13,(byte) 0xEC,(byte) 0x5F,(byte) 0x97,(byte) 0x44,(byte) 0x17,(byte) 0xC4,(byte) 0xA7,(byte) 0x7E,(byte) 0x3D,(byte) 0x64,(byte) 0x5D,(byte) 0x19,(byte) 0x73,(byte) 
			  0x60,(byte) 0x81,(byte) 0x4F,(byte) 0xDC,(byte) 0x22,(byte) 0x2A,(byte) 0x90,(byte) 0x88,(byte) 0x46,(byte) 0xEE,(byte) 0xB8,(byte) 0x14,(byte) 0xDE,(byte) 0x5E,(byte) 0x0B,(byte) 0xDB,(byte) 
			  0xE0,(byte) 0x32,(byte) 0x3A,(byte) 0x0A,(byte) 0x49,(byte) 0x06,(byte) 0x24,(byte) 0x5C,(byte) 0xC2,(byte) 0xD3,(byte) 0xAC,(byte) 0x62,(byte) 0x91,(byte) 0x95,(byte) 0xE4,(byte) 0x79,(byte) 
			  0xE7,(byte) 0xC8,(byte) 0x37,(byte) 0x6D,(byte) 0x8D,(byte) 0xD5,(byte) 0x4E,(byte) 0xA9,(byte) 0x6C,(byte) 0x56,(byte) 0xF4,(byte) 0xEA,(byte) 0x65,(byte) 0x7A,(byte) 0xAE,(byte) 0x08,(byte) 
			  0xBA,(byte) 0x78,(byte) 0x25,(byte) 0x2E,(byte) 0x1C,(byte) 0xA6,(byte) 0xB4,(byte) 0xC6,(byte) 0xE8,(byte) 0xDD,(byte) 0x74,(byte) 0x1F,(byte) 0x4B,(byte) 0xBD,(byte) 0x8B,(byte) 0x8A,(byte) 
			  0x70,(byte) 0x3E,(byte) 0xB5,(byte) 0x66,(byte) 0x48,(byte) 0x03,(byte) 0xF6,(byte) 0x0E,(byte) 0x61,(byte) 0x35,(byte) 0x57,(byte) 0xB9,(byte) 0x86,(byte) 0xC1,(byte) 0x1D,(byte) 0x9E,(byte) 
			  0xE1,(byte) 0xF8,(byte) 0x98,(byte) 0x11,(byte) 0x69,(byte) 0xD9,(byte) 0x8E,(byte) 0x94,(byte) 0x9B,(byte) 0x1E,(byte) 0x87,(byte) 0xE9,(byte) 0xCE,(byte) 0x55,(byte) 0x28,(byte) 0xDF,(byte) 
			  0x8C,(byte) 0xA1,(byte) 0x89,(byte) 0x0D,(byte) 0xBF,(byte) 0xE6,(byte) 0x42,(byte) 0x68,(byte) 0x41,(byte) 0x99,(byte) 0x2D,(byte) 0x0F,(byte) 0xB0,(byte) 0x54,(byte) 0xBB,(byte) 0x16};
	
	
	public static byte[] Rcon = { 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, (byte)0x80, 0x1b, 0x36 } ;
	
	public static byte K[] = {                          // Une clef courte a une longueur maximale de 32 octets
			  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
			  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
			  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
			  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 } ;
	
	public static byte W[] = new byte[240];
	
	static int longueur_de_la_clef;

	static int longueur_de_la_clef_etendue;

	static int Nk;
	static int Nr;
	
	public static void affiche_la_clef(byte clef[])
	{
	  for (int i=0; i<clef.length; i++) {
		  System.out.print(String.format("%02X ", clef[i]));  
	}
	  System.out.println("\n");
	}
	
	private static void calcule_la_clef_courte(String string) {
		int indexCle = 0;
		for(int i=0; i<longueur_de_la_clef*2; i+=2) {
			int value = Integer.parseInt(string.substring(i, i+2), 16);
			K[indexCle] = (byte) value;
			indexCle++;
		}
	}
	
	public static void calcule_la_clef_etendue()
	{
	  if (longueur_de_la_clef == 16){
	    Nr = 10; Nk = 4;
	  } else if (longueur_de_la_clef == 24){
	    Nr = 12; Nk = 6;
	  } else {
	    Nr = 14; Nk = 8; }
	  longueur_de_la_clef_etendue = 4*(4*(Nr+1));

	  for(int i=0; i<longueur_de_la_clef; i++) {
		  W[i] = K[i];
	  }
	  
	  for(int i=Nk; i<4*(Nr+1); i++) {
		  byte[] tmp = new byte[4];
		  tmp[0] = W[(i-1)*4];
		  tmp[1] = W[(i-1)*4 + 1];
		  tmp[2] = W[(i-1)*4 + 2];
		  tmp[3] = W[(i-1)*4 + 3];
		  
		  if(i%Nk == 0) {
			  tmp = rotWord(tmp);
			//  System.out.println("oui : " + String.format("%02X ", tmp[0]) + " " + tmp[1] + " " + tmp[2] + " " + tmp[3]);
			  tmp = subWord(tmp);
			  byte[] rCon = rCon(i/Nk);
			  for(int j=0; j<4; j++) {
				  tmp[j] = (byte) (tmp[j] ^ rCon[j]);
			  }
		  }
		  else if(Nk > 6 && i%Nk == 4)
			  tmp = subWord(tmp);
		  
		  byte[] wIMoins1 = new byte[4];
		  wIMoins1[0] = W[(i-Nk)*4];
		  wIMoins1[1] = W[(i-Nk)*4 + 1];
		  wIMoins1[2] = W[(i-Nk)*4 + 2];
		  wIMoins1[3] = W[(i-Nk)*4 + 3];
		   
		  for(int y=0; y<4; y++) {
			  tmp[y] = (byte) (tmp[y] ^ wIMoins1[y]);
		  }
		  
		  W[i*4] = tmp[0];
		  W[i*4 + 1] = tmp[1];
		  W[i*4 + 2] = tmp[2];
		  W[i*4 + 3] = tmp[3];
	  }

	}

	private static byte[] rotWord(byte[] tmp) {
		byte valeurTmp = tmp[0];
		tmp[0] = tmp[1];
		tmp[1] = tmp[2];
		tmp[2] = tmp[3];
		tmp[3] = valeurTmp;
		
		return tmp;
	}
	
	private static byte[] subWord(byte[] tmp) {
		tmp[0] = SBox[(tmp[0]+256) % 256];
		tmp[1] = SBox[(tmp[1]+256) % 256];
		tmp[2] = SBox[(tmp[2]+256) % 256];
		tmp[3] = SBox[(tmp[3]+256) % 256];
		return tmp;
	}
	
	private static byte[] rCon(int j) {
		byte rCon[] = new byte[4];
		switch(j) {
		case 1:
			rCon[0] = (byte)1;
			break;
		case 2:
			rCon[0] = (byte)2;
			break;
		case 3:
			rCon[0] = (byte)4;
			break;
		case 4:
			rCon[0] = (byte)8;
			break;
		case 5:
			rCon[0] = (byte)0x10;
			break;
		case 6:
			rCon[0] = (byte)0x20;
			break;
		case 7:
			rCon[0] = (byte)0x40;
			break;
		case 8:
			rCon[0] = (byte)0x80;
			break;
		case 9:
			rCon[0] = (byte)0x1b;
			break;
		case 10:
			rCon[0] = (byte)0x36;
			break;
		}
		rCon[1] = rCon[2] = rCon[3] = 0;
		return rCon;
	}

	public static void main(String[] args) {
	  if(args.length != 1){
	    System.out.println("Usage: java Diversification <clef en hexadécimal>");
	    System.exit(1);
	  }
	  longueur_de_la_clef = args[0].length() >> 1; // Il faut 2 caractères hexadécimaux pour un octet
	  if((longueur_de_la_clef != 16) && (longueur_de_la_clef != 24) && (longueur_de_la_clef != 32)){
		  System.out.println("Usage: clef en hexadécimal (formée de 128, 192 ou 256 bits)");
		  System.out.println("\t Une clef AES est formée de 32, 48, ou 64 caractères hexadécimaux,\n");
		  System.out.println("\t c'est-à-dire 128, 192, ou 256 bits.\n");
		  System.exit(1);
	  }
	  
	  System.out.println("La clé est de longueur : " + longueur_de_la_clef);

	  calcule_la_clef_courte(args[0]);            // Fonction à écrire pour récupérer la clef courte
	  System.out.println("Clé courte :");
	  affiche_la_clef(K);
	  
	  calcule_la_clef_etendue();                    // Fonction à écrire pour compléter le programme

	  affiche_la_clef(W);
	}
}
