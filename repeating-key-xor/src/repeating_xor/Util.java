package repeating_xor;

/*
 * Util.java
 * /repeating-key-xor/src/Util.java
 * Public domain - free to use
 * 
 */

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Util {
	
	static final byte[] XOR_KEY_BYTES = RepeatingKeyXOR.XOR_KEY.getBytes();
	
	Util() {
		// constructor
	}

	public static void parse(String[] args) {
		
		System.out.println("repeating key xor (using key \"ICE\")");
		System.out.println("public domain - gilbert.fernandes@orange.fr");
		System.out.println("sources @ https://github.com/gilbert-fernandes/");
		System.out.println();
		
		if(args.length == 0) {
			System.out.println("usage : rkXOR [filename]");
			System.exit(0);
		}
		
	}

	public static byte[] grabFileContents(String path) {
		
		Path p = Paths.get(path);
		byte[] fileContents = null;
		try {
			fileContents = Files.readAllBytes(p);
		} catch (IOException e) {
			System.out.println("could not load file contents : " + e.getMessage());
			System.out.println(">> EXITING <<");
			System.exit(0);
		}
		return fileContents;
		
	}

	public static void dumpToBinaryFile(String binaryOutput, byte[] appliedXOR) {
		
		try (FileOutputStream fos = new FileOutputStream(binaryOutput)) {
			   fos.write(appliedXOR);
	    } catch (IOException e) {
	    	System.out.println("failed to save file : " + e.getMessage());
		}
		
	}

	public static void dumpToPrettyPrintFile(String prettyOutput, byte[] appliedXOR) {
		
		try (FileWriter fw = new FileWriter(prettyOutput)) {
			   fw.write(convertByteArrayToHexPrettyPrint(appliedXOR));
	    } catch (IOException e) {
	    	System.out.println("failed to save file : " + e.getMessage());
		}
		
	}
	
	private static String convertByteArrayToHexPrettyPrint(byte[] theBytes) {
		StringBuilder sb = new StringBuilder();
	    for (byte b : theBytes) {
	        sb.append(String.format("%02x", b)); // use X for uppercase
	    }
	    return sb.toString();
	}
	
	static byte[] rkXOR(byte[] content) {
		
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
