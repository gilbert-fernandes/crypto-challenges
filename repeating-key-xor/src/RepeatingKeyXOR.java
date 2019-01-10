/*
 * RepeatingKeyXOR.java
 * /repeating-key-xor/src/RepeatingKeyXOR.java
 * Public domain - free to use
 * 
 */

public class RepeatingKeyXOR {
	
	static final String XOR_KEY = "ICE";
	
	static final byte[] XOR_KEY_BYTES = XOR_KEY.getBytes();

	public static void main(String[] args) {
		
		Util.parse(args);
		
		final byte[] fileContents = Util.grabFileContents(args[0]);

		final byte[] appliedXOR = rkXOR(fileContents);
		
		final String binaryOutput = args[0] + ".xor";
		final String prettyOutput = binaryOutput + ".asc";
		
		Util.dumpToBinaryFile(binaryOutput, appliedXOR);
		Util.dumpToPrettyPrintFile(prettyOutput, appliedXOR);
		
		System.out.println("done, written " + appliedXOR.length + " bytes to '" + binaryOutput + "'");
		System.out.println("(ascii hex pretty print -> '" + prettyOutput + "')");
		
	}
	
	private static byte[] rkXOR(byte[] content) {
		
		short currentByteKey = 0;
		
		for(int i = 0; i<content.length; i++) {
			
			/*
			 * next lines used during development because I hate tracing execution from within eclipse...
			 * final String toBeConverted = String.format("%02x ", content[i]);
			 * final String onceConvered = String.format("%02x ", (byte)((int) content[i] ^ (int) XOR_KEY[currentByteKey]));
			 * System.out.println(i+1 + " : " + toBeConverted + " -" + currentByteKey + "-> " + onceConvered);
			 */	
			
			content[i] = (byte)((int) content[i] ^ (int) XOR_KEY_BYTES[currentByteKey]);
			currentByteKey = incrementByteKey(currentByteKey);
		}
		
		return content;
		
	}
	
	// loop from 0 to (XOR_KEY-1) values
	private static short incrementByteKey(short currentByteKey) {
		return (++currentByteKey > XOR_KEY_BYTES.length-1) ? 0 : currentByteKey;
	}

}
