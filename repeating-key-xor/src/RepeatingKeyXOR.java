import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RepeatingKeyXOR {
	
	final static String XOR_KEY = "ICE";
	final static byte[] XOR_KEY_BYTES = XOR_KEY.getBytes();

	public static void main(String[] args) {
		System.out.println("repeating key xor (using key \"ICE\")");
		System.out.println("public domain - gilbert.fernandes@orange.fr");
		System.out.println("sources @ https://github.com/gilbert-fernandes/");
		if(args.length == 0) {
			System.out.println("usage : rkXOR [filename]");
			return;
		}
		
		byte[] fileContents = grabFileContents(args[0]);
		if(fileContents == null) {
			System.out.println(">> EXITING <<");
			return;
		}

		final byte[] appliedXOR = rkXOR(fileContents);
		
		final String outputFileName = args[0] + ".xor";
		try (FileOutputStream fos = new FileOutputStream(outputFileName)) {
			   fos.write(appliedXOR);
	    } catch (IOException e) {
	    	System.out.println("failed to save file : " + e.getMessage());
		}
		System.out.println("done, written " + appliedXOR.length + " bytes to '" + outputFileName + "'");
		
	}

	// file is read as one string directly even if we have n lines in it
	private static byte[] grabFileContents(final String path) {
		Path p = Paths.get(path);
		try {
			return Files.readAllBytes(p);
		} catch (IOException e) {
			System.out.println("could not load file contents : " + e.getMessage());
			return null;
		}
	}
	
	private static byte[] rkXOR(byte[] content) {
		short currentByteKey = 0;
		for(int i = 0; i<content.length; i++) {
			// next lines used during development because I hate tracing execution from within eclipse...
//			final String toBeConverted = String.format("%02x ", content[i]);
//			final String onceConvered = String.format("%02x ", (byte)((int) content[i] ^ (int) XOR_KEY[currentByteKey]));
//			System.out.println(i+1 + " : " + toBeConverted + " -" + currentByteKey + "-> " + onceConvered);
			content[i] = (byte)((int) content[i] ^ (int) XOR_KEY_BYTES[currentByteKey]);
			currentByteKey = incrementByteKey(currentByteKey);
		}
		return content;
	}
	
	// loop from 0 to (XOR_KEY-1) values
	private static short incrementByteKey(short currentByteKey) {
		return (++currentByteKey > XOR_KEY_BYTES.length-1) ? 0 : currentByteKey;
	}
	
	private static String convertByteArrayToHexPrettyPrint(byte[] theBytes) {
		StringBuilder sb = new StringBuilder();
	    for (byte b : theBytes) {
	        sb.append(String.format("%02x", b)); // use X for uppercase
	    }
	    return sb.toString();
	}

}
