package decrypt_singlebyte_xor;

import java.util.List;

public class Util {
	
	// ---- checkArg ------------------------------------------------------------------------------

	public static void checkArg(final String[] args, List<String> encodedStrings) {

		// 1. we must have one arg or more
		// 2. each arg must have an even number of chars
		// 3. return string to decrypt in encodedStrings
		
		if(args.length == 0) {
			System.out.println("decrypt_singlebyte_xor {encoded string} [...]");
			System.out.println();
			System.out.println("use : analyze and decrypt a single-char XORed encoded string");
			System.out.println("      each possible char is tried, character frequency is calculated");
			System.out.println("      outputs the highest score for english plaintext");
			System.out.println("      clear text is supposed to be ASCII-encoded");
			System.exit(0);
		}
		
		for(String s : args) {
			if(s.length() % 2 != 0) {
				System.out.print("ignored (invalid length) : ");
				System.out.println(s);
			}
			else {
				encodedStrings.add(s);
			}
		}
		
	}

}
