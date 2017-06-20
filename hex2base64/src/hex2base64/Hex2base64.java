package hex2base64;

public class Hex2base64 {

	public static void main(String[] args) {

		if(args.length == 0) {
			System.out.println();
			System.out.println("hex -> base64 (v.1.0)");
			System.out.println("public domain - gilbert.fernandes@orange.fr");
			System.out.println("sources @ https://github.com/gilbert-fernandes/");
			System.out.println("this is my implementation of set 1 challenge 1 of cryptopals challenges");
			System.out.println("enjoy :-)");
			return;
		}
		
		for(String s : args)
			System.out.println(new Hex(s).toBase64());
		
	}

}
