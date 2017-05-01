/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author CHILSE
 */
public class ownReBuild {

    private ErrorClass ERROR = new ErrorClass();

    public SecretKeySpec puKo(String enryptFormat) {
        int keylength = 0;
        switch (enryptFormat) {
            case "DESede":
                keylength = 24;
                break;
            case "AES":
                keylength = 16;
                break;
            default:
                keylength = 8;
                break;
        }
        try {
            byte[] key = (new String(randomCharArray(100))).getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);
            key = Arrays.copyOf(key, keylength); //192 bit
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, enryptFormat);
            return secretKeySpec;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            ERROR.exceptioninfo(ex);
            return null;
        }
    }

    public String puKoEN(String enryptFormat) {
        int keylength = 0;
        switch (enryptFormat) {
            case "DESede":
                keylength = 24;
                break;
            case "AES":
                keylength = 16;
                break;
            default:
                keylength = 8;
                break;
        }
        try {
            byte[] key = (new String(randomCharArray(100))).getBytes("UTF-8");
            key = Arrays.copyOf(key, keylength); //192 bit
            return new String(key);
        } catch (UnsupportedEncodingException ex) {
            ERROR.exceptioninfo(ex);
            return null;
        }
    }

    public SecretKeySpec buildByOwn(String enryptFormat, String keyRaw) {
        int keylength = 0;
        switch (enryptFormat) {
            case "DESede":
                keylength = 24;
                break;
            case "AES":
                keylength = 16;
                break;
            default:
                keylength = 8;
                break;
        }
        try {
            byte[] key = keyRaw.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);
            key = Arrays.copyOf(key, keylength); //192 bit
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, enryptFormat);
            return secretKeySpec;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            ERROR.exceptioninfo(ex);
            return null;
        }
    }

    

    private char[] randomCharArray(int length) {
        char[] charArray = new char[length];
        for (int i = 0; i < length; i++) {
            charArray[i] = randomChar();
        }
        return charArray;
    }

    private char randomChar() {
        int rnd = randomInteger(47, 123);
        char value;
        if ((rnd >= 48 && rnd <= 57) || (rnd >= 65 && rnd <= 90) || (rnd >= 97 && rnd <= 122)) {
            value = (char) rnd;
            return value;
        }

        return randomChar();
    }

    private int randomInteger(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

}
