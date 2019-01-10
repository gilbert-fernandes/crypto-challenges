package decrypt_multiple_xor;

/*
 * DecryptMultipleXOR.java
 * /decrypt_multiple_xor/src/decrypt_multiple_xor/DecryptMultipleXOR.java
 * Public domain - free to use
 * 
 */

// https://cryptopals.com/sets/1/challenges/6

public class DecryptMultipleXOR {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * 1. Let KEYSIZE be the guessed length of the key; try values from 2 to (say) 40.
		 *  
		 * 2. Write a function to compute the edit distance/Hamming distance between two strings.
		 *    The Hamming distance is just the number of differing bits.
		 *    The distance between "this is a test"and "wokka wokka!!!" is 37 
		 *    
		 * 3. For each KEYSIZE, take the first KEYSIZE worth of bytes, and the second KEYSIZE
		 *    worth of bytes, and find the edit distance between them. Normalize this result by
		 *    dividing by KEYSIZE. 
		 * 
		 * 4. The KEYSIZE with the smallest normalized edit distance is probably the key.
		 *    You could proceed perhaps with the smallest 2-3 KEYSIZE values. Or take 4 KEYSIZE
		 *    blocks instead of 2 and average the distances.
		 *    
		 * 5. Now that you probably know the KEYSIZE: break the ciphertext into blocks of KEYSIZE length.
		 * 
		 * 6. Now transpose the blocks: make a block that is the first byte of every block, and a block
		 *    that is the second byte of every block, and so on.
		 *    
		 * 7. Solve each block as if it was single-character XOR. You already have code to do this. 
		 * 
		 * 8. For each block, the single-byte XOR key that produces the best looking histogram is the
		 *    repeating-key XOR key byte for that block. Put them together and you have the key. 
		 * 
		 */
		
		/*
		 * Note : distinguer le poids de Hamming (bits <> 0) de la distance de Hamming (bits diff�rents
		 *        entre deux cha�nes prises bit � bit) ; voir PDF dans r�pertoire doc (cours sur les codes
		 *        du Master de Bordeaux)
		 * 
		 */
		
	}

}
