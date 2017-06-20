package fixed_xor;

public class fixed_xor {

	public static void main(String[] args) {
		
		Util.argcheck(args.length);
		
//		long start = System.currentTimeMillis();
//		
//		for(int i=0; i < (args.length-1); i+=2)
//			System.out.println(new FixedXor(args[i], args[i+1]).getXor());
//		
//		System.out.println("new code execution time : " + (System.currentTimeMillis() - start) + "ms");
//		
//		start = System.currentTimeMillis();
//		
//		for(int i=0; i < (args.length-1); i+=2)
//			System.out.println(new FixedXor(args[i], args[i+1]).getXorOld());
//		
//		System.out.println("old code execution time : " + (System.currentTimeMillis() - start) + "ms");
		
		
		for(int i=0; i < (args.length-1); i+=2)
			System.out.println(new FixedXor(args[i], args[i+1]).getXor());

	}

}
