import java.util.List;

public class DetectXOR {

	public static void main(String[] args) {
		
		Util.checkArgs(args);
		List<String> lines = Util.parse(args[0]);
		
		final int nb = lines.size();
		
		XOREDstring bestFound = null;
		
		System.out.println("\n     Line | Progress |    Score |");
		
		long start = System.currentTimeMillis();
		
		for(int i=0; i<=nb-1; i++)
		{
		    XOREDstring x = new XOREDstring(lines.get(i));
		    
		      System.out.print(String.format("%9s", i+1) + " |");
		      System.out.print(String.format("%9s", String.format("%.1f",(float)((i+1)*100)/nb) + " %") + " |");
		    System.out.println(String.format("%9s", x.getScore()) + " |");
		    
		    if(bestFound == null || x.getScore() > bestFound.getScore())
		        bestFound = x;
		}
		
        System.out.println("\nfrequency analysis for best key : ");
        System.out.println("\n" + bestFound.EnglishFrequency(bestFound.decrypt().toCharArray()));
        System.out.println("best score found : " + bestFound.getScore());
        System.out.println(String.format("best key guess : 0x%x", bestFound.getKey()));
        System.out.println("decryption : " + bestFound.decrypt());
        
        System.out.println("decryption time : " + (System.currentTimeMillis() - start) + " ms");
		
	}

}
