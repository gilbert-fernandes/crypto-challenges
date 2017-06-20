package fixed_xor;

public class FixedXor {
	
	final private byte[] firstValue;
	final private byte[] secondValue;
	
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
	
	// ---- byteFromHexString ---------------------------------------------------------------------
	
	private byte[] byteFromHexString(final String encoded) {
	    if ((encoded.length() % 2) != 0)
	        throw new IllegalArgumentException("input string has an odd number of characters");

	    final byte result[] = new byte[encoded.length()/2];
	    final char enc[]    = encoded.toCharArray();
	    
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

}
