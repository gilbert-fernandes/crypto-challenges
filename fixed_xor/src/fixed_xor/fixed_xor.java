package fixed_xor;

public class fixed_xor {

	public static void main(String[] args) {
		
		Util.argcheck(args.length);
		
		for(int i=0; i < (args.length-1); i+=2)
			System.out.println(new FixedXor(args[i], args[i+1]).getXor());

	}

}
