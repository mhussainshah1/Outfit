package com.example.demo.business.util;
/**
 * The following class will provide you with a static method that returns the hex format md5 of an input string:
 * <p>
 * This class can then be used to return the MD5 hash of an email address (make sure you lower case it first!) like this:
 *
 * <ol>
 * <li>
 * String email = "someone@somewhere.com";
 * </li>
 * <li>
 * String hash = MD5Util.md5Hex(email);
 * </li>
 * </ol>
 * <p>
 * With the hex string that is returned, you can construct your gravatar URL.
 */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    private static String hex(byte[] array) {
        var sb = new StringBuffer();
        for (var byt : array) {
            sb.append(Integer.toHexString((byt & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString();
    }

    public static String md5Hex(String message) {
        try {
            var messageDigest = MessageDigest.getInstance("MD5");
            return hex(messageDigest.digest(message.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}