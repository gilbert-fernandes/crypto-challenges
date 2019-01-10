package fixed_xor;

/*
 * Util.java
 * /fixed_xor/src/fixed_xor/Util.java
 * Public domain - free to use
 * 
 */

public class Util {

	public static void argcheck(final int nb) {
		
		// usage on no-arg otherwise must be a pair of 2 input/against values
		
		if(nb == 0) {
			System.out.println("fixed_xor {input} {against}");
			System.out.println();
			System.out.println("use : does a fixed XOR between two equal-length byte[] buffers");
			System.out.println("      accepts any <long> number of input/against groups");
			System.out.println("      specify hex values (like 1c01f0...) as input");
			System.exit(0);
		}
		
		if(nb % 2 != 0) {
			System.out.println("invalid number or args (use pairs of 2 values)");
			System.exit(0);
		}
		
	}

}
