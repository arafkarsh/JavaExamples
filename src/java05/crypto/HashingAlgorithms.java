/**
 * Copyright (c) 2017 Araf Karsh Hamid

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.

 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation

 *   or (per the licensee's choosing)

 * under the terms of the Apache 2 License version 2.0
 * as published by the Apache Software Foundation.
 */
package java05.crypto;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Hashing Algorithms usage.
 *
 * Compute the hash for String using Standard Algorithms like MD5, SHA-1, SHA-256, SHA-386, SHA-512
 *
 * HashAlgorithms algo = HashAlgorithms.getInstance();
 * 
 * String hashValue = algo.computeHash("Secret Code", HashAlgorithms.SHA_1);
 * OR
 * String hashValue = algo.computeHash("Secret Code", HashAlgorithms.SHA_1, "UTF-16");
 *
 * The above code will return the computed Hash value of "Secret Code" using SHA-1
 * (Secure Hash Algorithm).
 *
 * Hash Algorithms Available
 * 
 * Message Digest			= MD5 		(128 bit)
 * Secure Hash Algorithm 	= SHA-1 	(160 bit)
 * Secure Hash Algorithm 	= SHA-256
 * Secure Hash Algorithm	= SHA-384
 * Secure Hash Algorithm	= SHA-512
 *
 * @author:  Araf Karsh Hamid
 * @version: 1.0
 * @seriel:	 20170627131239
 */

public final class HashingAlgorithms {
    
	// Algorithms Definitions
	public final static int	MD5				= 1;	// Default Algorithm
	public final static int	SHA_1			= 2;
	public final static int	SHA_256			= 3;	
	public final static int	SHA_384			= 4;
	public final static int	SHA_512			= 5;

	// Algorithm lookup codes
	private final static String[] algos		= {"", "MD5", "SHA-1", "SHA-256", "SHA-384", "SHA-512" };
	private int DEFAULT_ALGO 				= 5;
    private int CURRENT_ALGO 	        	= DEFAULT_ALGO;

	// Singleton Instance
	private final static HashingAlgorithms hashAlgo	= new HashingAlgorithms();

	/**
	 * Private Constructor used to make this as a Singleton instance.
	 */

	private HashingAlgorithms() {
            try {
                CURRENT_ALGO    = Integer.parseInt(System.getProperty("HASHALGO"));
            } catch (Exception ignored) {}
        }

	/**
	 * Returns the single instance of HashAlgorithms Object
	 *
	 * @return HashAlgorithms
	 */

	public static HashingAlgorithms getInstance() { 	return hashAlgo;	}

	/**
	 * Returns the default Algorithm code int value. Following are the Algorithm options
	 *
	 * Algorithm options
	 * 
	 * 1 MD5		(128 bit)
	 * 2 SHA-1		(160 bit) 
	 * 3 SHA-256	(256 bit Strong hash - check US export controls)
	 * 4 SHA-384	(384 bit Strong hash - check US export controls)
	 * 5 SHA-512	(512 bit Strong hash - check US export controls)
	 *
	 * @return int algo_code
	 */

	public int getDefaultAlgorithm() {
		return CURRENT_ALGO;
	}

    /**
	 * computeHash() method will create a hash of the input message. This method uses UTF-8 for message
	 * encoding.
	 *
	 * Algorithm options
	 * 
	 * 1 MD5		(128 bit)
	 * 2 SHA-1		(160 bit)
	 * 3 SHA-256	(256 bit Strong hash - check US export controls)
	 * 4 SHA-384	(384 bit Strong hash - check US export controls)
	 * 5 SHA-512	(512 bit Strong hash - check US export controls)
	 *
	 * @param _message
	 * @return String (computed hash value)
	 * @throws Exception
	 */

	public final String createHash(final String _message) throws Exception {
		return createHash(_message, getDefaultAlgorithm(), "UTF-8");
	}

	/**
	 * computeHash() method will create a hash of the input message. This method uses UTF-8 for message
	 * encoding.
	 *
	 * Algorithm options
	 * 
	 * 1 MD5		(128 bit)
	 * 2 SHA-1		(160 bit)
	 * 3 SHA-256	(256 bit Strong hash - check US export controls)
	 * 4 SHA-384	(384 bit Strong hash - check US export controls)
	 * 5 SHA-512	(512 bit Strong hash - check US export controls)
	 *
	 * @param  _message, int _algo
	 * @return String (computed hash value)
	 * @throws Exception
	 */

	public final String createHash(final String _message, final int _algo) throws Exception {
		return createHash(_message, _algo, "UTF-8");
	}

	/**
	 * computeHash() method will create a hash of the input message.
	 *
	 * Algorithm options
	 * 
	 * 1 MD5		(128 bit)
	 * 2 SHA-1		(160 bit)
	 * 3 SHA-256	(256 bit Strong hash - check US export controls)
	 * 4 SHA-384	(384 bit Strong hash - check US export controls)
	 * 5 SHA-512	(512 bit Strong hash - check US export controls)
	 *
	 * String Encoding = UTF-8, UTF-16 etc
	 *
	 * @param  _message, int _algo, String _encoding
	 * @return String (computed hash)
	 * @throws Exception
	 */

	public final String createHash(final String _message, final int _algo, final String _encoding) throws Exception {

		if(_message == null) {							// Check Input Message
			throw new Exception("Invalid message for hashing");
		}

		int algo_number = this.getDefaultAlgorithm();
 		if( (_algo >0 ) && (_algo <= algos.length)) {		// Check Input Algorithm
			algo_number = _algo;
		}

		String encoding = _encoding;
		if(encoding == null) {								// Check Input Encoding
			encoding = "UTF-8";
		}

		// Input validation over -------------------------------------------------------------------------------
		MessageDigest mesgDigest = null;
		try {
			mesgDigest = MessageDigest.getInstance(algos[algo_number]); 	// Load the Algorithm
		} catch(NoSuchAlgorithmException e) { throw e; }
		try {
			mesgDigest.update(_message.getBytes(encoding)); 				// Updates the digest
		} catch(UnsupportedEncodingException e) { throw e; }

		byte raw[] = mesgDigest.digest(); 									// Hash Computation and reset
		
		return base64Encoder(raw); 											// Convert raw data in Base64

		// Hash computing over -------------------------------------------------------------------------------
	}

	
	/**
	 * Base 64 Encoder
	 * 
	 * @param raw
	 * @return String
	 */
	public String base64Encoder(byte raw[]) {
		return Base64.getEncoder().encodeToString(raw);
	}

	/**
	 * Base 64 Encoder
	 * 
	 * @param s
	 * @return String
	 */
	public String base64Encoder(String s) {
		return Base64.getEncoder().encodeToString(s.getBytes());
	}
	
	/**
	 * To Convert bytes to Hexa String
	 * 
	 * @param raw
	 * @return String
	 */
	
	public String hexEncoder(byte raw[]) {
		char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();

		StringBuilder sb = new StringBuilder(raw.length * 2);
		for (byte b : raw) {
		    sb.append(HEX_CHARS[(b & 0xF0) >> 4]);
		    sb.append(HEX_CHARS[b & 0x0F]);
		}
		String hexValue = sb.toString();
		System.out.println("HEX="+hexValue);
		return hexValue;
	}
	
	/**
	 * Use this method to test the code in the command line.
	 */

	public static void main(String[] args) throws Exception {
		
		// Usage
		if(args.length == 0) {
			System.out.println("\n0 - Print all the Algorithms to create Hash.\n");
			System.out.println("1 - MD5   (128 bit)");
			System.out.println("2 - SHA_1 (160 bit)");
			System.out.println("3 - SHA_256");
			System.out.println("4 - SHA_384");
			System.out.println("5 - SHA_512\n");
			System.out.println("java HashingAlgorithms string algorithm_number (default is 5 = SHA_512, use 0 to print all Hash values)");
			
			System.out.println("\nTesting with default Password = MyC0mp13xPa$$w0rd -----------------------\n");
			
			long ll = System.currentTimeMillis();
			int xx = 9999999;
			System.out.println("TimeInMillis = "+ll);
			System.out.println("Hex Value    = "+Long.toHexString(ll).toUpperCase());
			System.out.println("Base64 Value = "+HashingAlgorithms.getInstance().base64Encoder(""+ll));
			System.out.println("Running No.  = "+xx);
			System.out.println("Hex Value    = "+Integer.toHexString(xx).toUpperCase());
			
			System.out.println("\n");

			args = new String[2];
			args[0]	=	new String("MyC0mp13xPa$$w0rd");
			args[1]	=	"0";

		}
		/**		
		MD5     	Password = ( MyC0mp13xPa$$w0rd ) { FdI/f4FwX9G77bWsXvPdNA== }
		SHA-1     	Password = ( MyC0mp13xPa$$w0rd ) { 1efAOuOs0D6NOqispKWGOV4hPyk= }
		SHA-256     Password = ( MyC0mp13xPa$$w0rd ) { Q0RVvX7GKq2vShiNF8VPv9A770eGY/ZvluRpQ+sFcUg= }
		SHA-384     Password = ( MyC0mp13xPa$$w0rd ) { mXlHG4Nrw+e7Z/si20aBzm02Mpadj1AzZ9TBBg86UBjD9hvmytvCogWO5BleQJax }
		SHA-512     Password = ( MyC0mp13xPa$$w0rd ) { XR/g4iAU1mGrGAVj6XUNfsfptbg+9cenyCtb8z2NdIlJZfm1dpET61fnc34hYUa2FuEUZealj4UCla16+rpaXA== }
		*/
		String passwordHash = "";
		String password 	= args[0];
		int algo 			= HashingAlgorithms.getInstance().getDefaultAlgorithm();
		try {
			algo = Integer.parseInt(args[1]);
		} catch (Exception ignored) {}

		// Print all the algorithms computed hash value of the input message if the algo code == ZERO
		if (algo == 0) {
			for(int x=0; x< (algos.length-1); x++) {
				passwordHash = HashingAlgorithms.getInstance().createHash(password, x+1);
				System.out.println(algos[x+1]+"     \tPassword = ( "+password+" )"+" { "+passwordHash+" }");
			}
		} else {
			passwordHash = HashingAlgorithms.getInstance().createHash(password, algo);
			System.out.println("ALGOS="+algos+" \tPassword = ( "+password+" )"+" { "+passwordHash+" }");
		}
	}

}
