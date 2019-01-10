package repeating_xor;

/*
 * RepeatingKeyXOR.java
 * /repeating-key-xor/src/RepeatingKeyXOR.java
 * Public domain - free to use
 * 
 */

public class RepeatingKeyXOR {
	
	static final String XOR_KEY = "ICE";
	
	

	public static void main(String[] args) {
		
		Util.parse(args);
		
		final byte[] fileContents = Util.grabFileContents(args[0]);

		final byte[] appliedXOR = Util.rkXOR(fileContents);
		
		final String binaryOutput = args[0] + ".xor";
		final String prettyOutput = binaryOutput + ".asc";
		
		Util.dumpToBinaryFile(binaryOutput, appliedXOR);
		Util.dumpToPrettyPrintFile(prettyOutput, appliedXOR);
		
		System.out.println("done, written "               + appliedXOR.length + " bytes to '" + binaryOutput + "'");
		System.out.println("(ascii hex pretty print -> '" + prettyOutput      + "')");
		
	}

}
