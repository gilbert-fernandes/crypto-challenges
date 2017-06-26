import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Util {
	
	public static void checkArgs(String[] args) {
	    
		if(args.length == 0) {
			
			System.out.println("dxad (detect_xor_1char_and_decrypt)");
			System.out.println("usage: dxad <filename>");
			System.out.println("\n<filename> must be a utf-8 encoded text file with n lines of hex-encoded");
			System.out.println("strings. one of them is expected to be a sentence in English, encrypted");
			System.out.println("with XOR and a 1-char key.");
			System.out.println("\nprogram will brute-force search key for each line, calculate frequency");
			System.out.println("analysis scores for each line and output the highest-scoring line with");
			System.out.println("most probable key and decryption attempt with some totally useless stats");
			System.out.println("for the Hollywood effect.");
			System.out.println("\nnote : file contents are directly loaded into memory, don't feed this");
			System.out.println("a Gb of data. each line is checked and only even ones are kept for");
			System.out.println("analysis.");
			System.exit(EXITCODE.OK.ordinal());
		}
		
	}
	
	// ---- Parse text file for hex-encoded strings to attack -------------------------------------

	public static List<String> parse(String filename) {
		
		List<String> fromFile   = null;
		List<String> validLines = null;
		
		try
		{
		    fromFile = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
		}
		catch (IOException e)
		{
			System.out.println(EXITCODE.FILEOPEN_ERROR.toString());
			System.exit(EXITCODE.FILEOPEN_ERROR.getCode());
		}
		
		System.out.println("number of lines in file : " + fromFile.size());
		
		validLines = new ArrayList<String>();
		
		for(String s : fromFile)
		    if((s.length() % 2) == 0)
		        validLines.add(s);
		
		System.out.println("number of valid lines found : " + validLines.size());
		
		return validLines;
		
	}

}
