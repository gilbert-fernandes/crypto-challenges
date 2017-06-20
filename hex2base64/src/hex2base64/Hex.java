package hex2base64;

import java.util.Base64;

public class Hex {

	final private byte[] byteData;
	
	// ---- Constructeur --------------------------------------------------------------------------
	
	public Hex(final String s) {
		byteData = byteFromHexString(s);
	}
	
	// ---- Accesseur byteData --------------------------------------------------------------------
	
	public String getBytes() {
		StringBuilder sb = new StringBuilder();
		
		for(byte b : byteData)
			sb.append(String.format("%02x", b));
		
		return sb.toString();
	}
	
	// ---- Conversion en base64 ------------------------------------------------------------------

	public final String toBase64() {
		Base64.Encoder encoder = Base64.getEncoder();
		return (new String(encoder.encode(byteData)));
	}
	
	// ---- byteFromHexString ---------------------------------------------------------------------
	
	private byte[] byteFromHexString(final String encoded) {
	    if ((encoded.length() % 2) != 0)
	        throw new IllegalArgumentException("input string has an odd number of characters");

	    final byte result[] = new byte[encoded.length()/2];
	    final char enc[] = encoded.toCharArray();
	    
	    for (int i = 0; i < enc.length; i += 2) {
	        StringBuilder sb = new StringBuilder(2);
	        sb.append(enc[i]).append(enc[i + 1]);
	        result[i/2] = (byte) Integer.parseInt(sb.toString(), 16);
	    }
	    
	    return result;
	}

}
