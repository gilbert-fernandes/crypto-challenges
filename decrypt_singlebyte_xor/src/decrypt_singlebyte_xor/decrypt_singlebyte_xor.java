package decrypt_singlebyte_xor;

import java.util.ArrayList;
import java.util.List;

public class decrypt_singlebyte_xor {

	public static void main(String[] args) {
		
		List<String> encodedStrings = new ArrayList<String>();
		Util.checkArg(args, encodedStrings);
		
		for(String s : encodedStrings) {
			
			XOREDstring x = new XOREDstring(s);
			
			System.out.println("\nencoded string : " + s);
			System.out.print("using letter frequency : ");
			System.out.println(java.util.Arrays.asList(XOREDstring.frequencies.values()));
			
			System.out.println("\nhighest score found : " + x.getScore());
			System.out.println(String.format("best key guess : 0x%x", x.getKey()));
			
			System.out.println("\nkey scores :");
			System.out.println("\n" + x.getKeyScores());
			
			System.out.println("frequency analysis : ");
			System.out.println("\n" + x.EnglishFrequency(x.decrypt().toCharArray()));
			
			System.out.println("\nbest decryption :");
			System.out.println(x.decrypt());
			
		}
		
	}

}
