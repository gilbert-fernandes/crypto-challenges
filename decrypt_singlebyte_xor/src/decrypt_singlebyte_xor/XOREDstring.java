package decrypt_singlebyte_xor;

public class XOREDstring {
	
	private int bestScore;
	private int bestKey;
	private int maxScore;
	
	private final byte[] hexDecoded;
	
	private int[] bestScoresByKey;
	private int[] bestFreqPerLetter;
	
	public enum frequencies {
		Q, J, Z, X, V, K, W, Y, F, B, G, H, M, P, D, U, C, L, S, N, T, O, I, R, A, E, SPACE
	}
	
	// ---- Constructor ---------------------------------------------------------------------------
	
	public XOREDstring(final String s) {
		bestScore = 0;
		bestKey   = 0;
		maxScore  = 0;
		
		hexDecoded = byteFromHexString(s);
		
		bestScoresByKey   = new int[256];
		bestFreqPerLetter = new int[27];  // 26 letters + SPACE
	}
	
	// ---- byteFromHexString ---------------------------------------------------------------------
	
	static byte[] byteFromHexString(final String encoded) {
	    if ((encoded.length() % 2) != 0)
	        throw new IllegalArgumentException("input string has an odd number of characters");

	    final byte result[] = new byte[encoded.length()/2];
	    final char    enc[] = encoded.toCharArray();
	    
	    for (int i = 0; i < enc.length; i += 2) {
	        StringBuilder sb = new StringBuilder(2);
	        sb.append(enc[i]).append(enc[i + 1]);
	        result[i/2] = (byte) Integer.parseInt(sb.toString(), 16);
	    }
	    
	    return result;
	}
	
	// ---- calculateScores -----------------------------------------------------------------------
	
	/*
	 * Of the 128 possible ASCII 7-bit characters, 95 are printable ones :
	 * 
 	 *  !"#$%&'()*+,-./
	 * 0123456789:;<=>?
	 * @ABCDEFGHIJKLMNO
	 * PQRSTUVWXYZ[\]^_
	 * `abcdefghijklmno
	 * pqrstuvwxyz{|}~
	 *  
	 * (mind the space at the beginning)
	 * 
	 * Frequency of the 26 letters extracted from the Oxford Concise dictionary :
	 * EARIOTNSLCUDPMHGBFYWKVXZJQ
	 * 
	 * This order is not only the words find in the dictionary but also carries the frequency of word
	 * use. Without it we would get : EARIOTNSLCUDPMHGBFYWKVXZJQ
	 * 
	 * Robert Lewand "Cryptological Mathematics" uses the following :
	 * ETAOINSHRDLCUMWFGYPBVKJXQZ
	 * 
	 * This seems to be quite close to the ETAOIN SHRDLU the challenge hints about :
	 * ETAOINSHRDLC (Lewand)
	 * ETAOINSHRDLU (Cryptopals)
	 * 
	 * The one from Cryptopals comes from the order of letters on the type-casting Linotype/Intertype
	 * machines arranged by letter frequency and that were used in the press printing.
	 * 
	 * I will here use the frequencies from the Oxford dictionary :
	 * (score is reverse-position number counting from right to left)
	 * 
	 * Letter | Frequency | Score
	 *  SPACE |     ?     |  27     (slightly higher than 12.702 but I don't have the value)
	 *    E   |  12.702 % |  26
	 *    A   |   8.167 % |  25
	 *    R   |   5.987 % |  24
	 *    I   |   6.966 % |  23
	 *    O   |   7.507 % |  22
	 *    T   |   9.056 % |  21
	 *    N   |   6.749 % |  20
	 *    S   |   6.327 % |  19
	 *    L   |   4.025 % |  18
	 *    C   |   2.782 % |  17
	 *    U   |   2.758 % |  16
	 *    D   |   4.253 % |  15
	 *    P   |   1.929 % |  14
	 *    M   |   2.406 % |  13
	 *    H   |   6.094 % |  12
	 *    G   |   2.015 % |  11
	 *    B   |   1.492 % |  10
	 *    F   |   2.228 % |   9
	 *    Y   |   1.974 % |   8
	 *    W   |   2.360 % |   7
	 *    K   |   0.772 % |   6
	 *    V   |   0.978 % |   5
	 *    X   |   0.150 % |   4
	 *    Z   |   0.074 % |   3
	 *    J   |   0.153 % |   2
	 *    Q   |   0.095 % |   1
	 *    
	 * Note : on my first attempt, I was not considering spaces but only letters A-Z.
	 *        the decryption failed. The Wikipedia page from character frequencies :
	 *        https://en.wikipedia.org/wiki/Letter_frequency
	 *        did not list the SPACE probability but said that :
	 *        "In English, the space is slightly more frequent than the top letter (e)"  
	 * 
	 *        So... I added to my Enum SPACE at the end, to give it the highest possible
	 *        score and tadaaaaa ! The test hex string from Cryptopals deciphered itself :)
	 * 
	 */

	public void calculateScore() {
		
		for(int key=0; key<=255; key++) {
			
			byte[] decodeAttempt = new byte[hexDecoded.length];
			
			for(int i=0; i<=hexDecoded.length-1; i++)
				decodeAttempt[i] = (byte)((int) hexDecoded[i] ^ (int) key);
			
			int score = scoreValue(decodeAttempt);
			
			bestScoresByKey[key] = score;
			
			if(score > bestScore) {
				bestScore = score;
				bestKey = key;
			}
			
			if(score > maxScore)
				maxScore = score;
		    
		}
	}
	
	// ---- scoreValue ----------------------------------------------------------------------------
	
	private int scoreValue(byte[] buffer) {
		
		int score = 0;
		
		for(int i=0; i<=buffer.length-1; i++) {
			switch((int)(buffer[i])) {
			
				case 32 :
					score += frequencies.SPACE.ordinal();
					break;
				
				case  65 :
				case  97 :
					score += frequencies.A.ordinal();
					break;
					
				case  66 :
				case  98 :
					score += frequencies.B.ordinal();
					break;
					
				case  67 :
				case  99 :
					score += frequencies.C.ordinal();
					break;
					
				case  68 :
				case 100 :
					score += frequencies.D.ordinal();
					break;
					
				case  69 :
				case 101 :
					score += frequencies.E.ordinal();
					break;
					
				case  70 :
				case 102 :
					score += frequencies.F.ordinal();
					break;
					
				case  71 :
				case 103 :
					score += frequencies.G.ordinal();
					break;
					
				case  72 :
				case 104 :
					score += frequencies.H.ordinal();
					break;
					
				case  73 :
				case 105 :
					score += frequencies.I.ordinal();
					break;
					
				case  74 :
				case 106 :
					score += frequencies.J.ordinal();
					break;
					
				case  75 :
				case 107 :
					score += frequencies.K.ordinal();
					break;
					
				case  76 :
				case 108 :
					score += frequencies.L.ordinal();
					break;
					
				case  77 :
				case 109 :
					score += frequencies.M.ordinal();
					break;
					
				case  78 :
				case 110 :
					score += frequencies.N.ordinal();
					break;
					
				case  79 :
				case 111 :
					score += frequencies.O.ordinal();
					break;
					
				case  80 :
				case 112 :
					score += frequencies.P.ordinal();
					break;
					
				case  81 :
				case 113 :
					score += frequencies.Q.ordinal();
					break;
					
				case  82 :
				case 114 :
					score += frequencies.R.ordinal();
					break;
					
				case  83 :
				case 115 :
					score += frequencies.S.ordinal();
					break;
					
				case  84 :
				case 116 :
					score += frequencies.T.ordinal();
					break;
					
				case  85 :
				case 117 :
					score += frequencies.U.ordinal();
					break;
					
				case  86 :
				case 118 :
					score += frequencies.V.ordinal();
					break;
					
				case  87 :
				case 119 :
					score += frequencies.W.ordinal();
					break;
					
				case  88 :
				case 120 :
					score += frequencies.X.ordinal();
					break;
					
				case  89 :
				case 121 :
					score += frequencies.Y.ordinal();
					break;
					
				case  90 :
				case 122 :
					score += frequencies.Z.ordinal();
					break;
					
			}
		}
		
		return score;
		
	}

	// ---- getScore ------------------------------------------------------------------------------

	public int getScore() {
		return bestScore;
	}
	
	// ---- getKey --------------------------------------------------------------------------------

	public int getKey() {
		return bestKey;
	}
	
	// ---- getKeyScores --------------------------------------------------------------------------

	public String getKeyScores() {
		StringBuilder sb = new StringBuilder();
		
		for(int key=0; key <=255; key++) {
			int freq = bestScoresByKey[key];
			int stars = (freq * 64) / maxScore;
			
			sb.append(String.format("0x%02x : ", key));
			
			for(int j=0; j<= stars; j++) {
				sb.append("*");
			}
			
			sb.append("\n");
			
		}
		
		return sb.toString();
	}
	
	// ---- EnglishFrequency ----------------------------------------------------------------------

	public String EnglishFrequency(char[] decodedChars) {
		
		for(int i=0; i<decodedChars.length; i++) {
			switch(Character.toUpperCase(decodedChars[i])) {
			
				case ' ' :
					bestFreqPerLetter[frequencies.SPACE.ordinal()]++;
					break;
					
				case 'A' :
					bestFreqPerLetter[frequencies.A.ordinal()]++;
					break;
					
				case 'B' :
					bestFreqPerLetter[frequencies.B.ordinal()]++;
					break;
					
				case 'C' :
					bestFreqPerLetter[frequencies.C.ordinal()]++;
					break;
					
				case 'D' :
					bestFreqPerLetter[frequencies.D.ordinal()]++;
					break;
					
				case 'E' :
					bestFreqPerLetter[frequencies.E.ordinal()]++;
					break;
					
				case 'F' :
					bestFreqPerLetter[frequencies.F.ordinal()]++;
					break;
					
				case 'G' :
					bestFreqPerLetter[frequencies.G.ordinal()]++;
					break;
					
				case 'H' :
					bestFreqPerLetter[frequencies.H.ordinal()]++;
					break;
					
				case 'I' :
					bestFreqPerLetter[frequencies.I.ordinal()]++;
					break;
					
				case 'J' :
					bestFreqPerLetter[frequencies.J.ordinal()]++;
					break;
					
				case 'K' :
					bestFreqPerLetter[frequencies.K.ordinal()]++;
					break;
					
				case 'L' :
					bestFreqPerLetter[frequencies.L.ordinal()]++;
					break;
					
				case 'M' :
					bestFreqPerLetter[frequencies.M.ordinal()]++;
					break;
					
				case 'N' :
					bestFreqPerLetter[frequencies.N.ordinal()]++;
					break;
					
				case 'O' :
					bestFreqPerLetter[frequencies.O.ordinal()]++;
					break;
					
				case 'P' :
					bestFreqPerLetter[frequencies.P.ordinal()]++;
					break;
					
				case 'Q' :
					bestFreqPerLetter[frequencies.Q.ordinal()]++;
					break;
					
				case 'R' :
					bestFreqPerLetter[frequencies.R.ordinal()]++;
					break;
					
				case 'S' :
					bestFreqPerLetter[frequencies.S.ordinal()]++;
					break;
					
				case 'T' :
					bestFreqPerLetter[frequencies.T.ordinal()]++;
					break;
					
				case 'U' :
					bestFreqPerLetter[frequencies.U.ordinal()]++;
					break;
					
				case 'V' :
					bestFreqPerLetter[frequencies.V.ordinal()]++;
					break;
					
				case 'W' :
					bestFreqPerLetter[frequencies.W.ordinal()]++;
					break;
					
				case 'X' :
					bestFreqPerLetter[frequencies.X.ordinal()]++;
					break;
					
				case 'Y' :
					bestFreqPerLetter[frequencies.Y.ordinal()]++;
					break;
					
				case 'Z' :
					bestFreqPerLetter[frequencies.Z.ordinal()]++;
					break;
					
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(frequencies cf : frequencies.values()) {
			int charfreq = bestFreqPerLetter[frequencies.valueOf(cf.toString()).ordinal()];
			sb.append(cf.toString() + "(" + charfreq + ") : ");
			
			for(int i=0; i<charfreq; i++)
				sb.append("*");
			
			sb.append("\n");
		}
		
		return sb.toString();
		
	}
	
	// ---- decrypt -------------------------------------------------------------------------------

	public String decrypt() {
		
		byte[] bestDecode = new byte[hexDecoded.length];
		
		for(int i=0; i<=hexDecoded.length-1; i++)
			bestDecode[i] = (byte)((int)(hexDecoded[i]) ^ (int)(bestKey));
		
		return new String(bestDecode);
		
	}

}
