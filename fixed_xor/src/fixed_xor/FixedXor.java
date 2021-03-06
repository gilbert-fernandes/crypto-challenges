package fixed_xor;

/*
 * FixedXor.java
 * /fixed_xor/src/fixed_xor/FixedXor.java
 * Public domain - free to use
 * 
 */

public class FixedXor {
	
	private final byte[] firstValue;
	private final byte[] secondValue;
	
	// ---- FixedXor ------------------------------------------------------------------------------

	public FixedXor(final String value1, final String value2) {
		
		if(value1.length() != value2.length())
			throw new IllegalArgumentException("each pair of arguments must have the same size");
		
		this.firstValue  = byteFromHexString(value1);
		this.secondValue = byteFromHexString(value2);
	}
	
	// ---- getXor --------------------------------------------------------------------------------

	public String getXor() {
		byte[] xored = new byte[firstValue.length];
		
		for(int i=0; i<firstValue.length; i++)
			xored[i] = (byte)((int) firstValue[i] ^ (int) secondValue[i]);
		
		return hexFromByteArray(xored);
		
	}
	
	// ---- getXorOld -----------------------------------------------------------------------------

	public String getXorOld() {
		byte[] xored = new byte[firstValue.length];
		
		for(int i=0; i<firstValue.length; i++)
			xored[i] = (byte)((int) firstValue[i] ^ (int) secondValue[i]);
		
		return hexFromByteArray_Old(xored);
		
	}
	
	// ---- byteFromHexString ---------------------------------------------------------------------
	
	private byte[] byteFromHexString(final String encoded) {
	    if ((encoded.length() % 2) != 0)
	        throw new IllegalArgumentException("input string has an odd number of characters");

	    final byte[] result = new byte[encoded.length()/2];
	    final char[] enc    = encoded.toCharArray();
	    
	    for (int i=0; i < enc.length; i+=2) {
	    	
	        StringBuilder sb = new StringBuilder(2);
	        sb.append(enc[i]).append(enc[i+1]);
	        
	        result[i/2] = (byte) Integer.parseInt(sb.toString(), 16);
	    }
	    
	    return result;
	}
	
	// ---- hexFromByteArray ----------------------------------------------------------------------
	
	public static String hexFromByteArray(final byte[] bytes) {
		
		char[] hexChars  = new char[bytes.length * 2];
		final char[] hexValues = "0123456789ABCDEF".toCharArray();
	    
	    for (int i=0; i < bytes.length; i++) {
	    	
	        int v = bytes[i] & 0xFF;
	        
	        hexChars[i * 2]   = hexValues[v >>> 4];
	        hexChars[i * 2+1] = hexValues[v & 0x0F];
	        
	    }
	    return new String(hexChars);
	}
	
	// ---- previous hex->pretty print ------------------------------------------------------------
	
	public String hexFromByteArray_Old(final byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		
		for(byte b : bytes)
			sb.append(String.format("%02x", b));
		
		return sb.toString();
	}

}
