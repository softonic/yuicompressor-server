package com.github.kpacha.yuicompressorserver.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A simple BufferedReader content hasher
 * 
 * @author kpacha
 */
public class Md5Hasher {
    private static final String ALGORITHM = "md5";
    private MessageDigest md;

    /**
     * The default constructor sets up the internal message digester with the
     * md5 algorithm
     * 
     * @throws NoSuchAlgorithmException
     */
    public Md5Hasher() throws NoSuchAlgorithmException {
	md = MessageDigest.getInstance(ALGORITHM);
    }

    /**
     * Hash the received String
     * 
     * @param in
     * @param charset
     * @return
     * @throws IOException
     */
    public String getHash(String in, String charset) throws IOException {
	return getDigest(in, charset);
    }

    private String getDigest(String message, String charset)
	    throws UnsupportedEncodingException {
	return getDigest(message.getBytes(charset));
    }

    private String getDigest(byte[] message)
	    throws UnsupportedEncodingException {
	md.reset();
	return byteArrayToString(md.digest(message));
    }

    private String byteArrayToString(byte[] result) {
	StringBuffer sb = new StringBuffer();
	for (int i = 0; i < result.length; i++) {
	    sb.append(Integer.toHexString((result[i] & 0xFF) | 0x100)
		    .substring(1, 3));
	}
	return sb.toString();
    }
}