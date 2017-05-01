/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * A tester for the CryptoUtils class.
 *
 * @author www.codejava.net
 *
 */
public class crypByByte {

    private ErrorClass ERROR = new ErrorClass();
    private String ALGORITHM = "";
    private String TRANSFORMATION = "";

    private boolean useAES = true;
    private boolean useDES = true;
    private boolean useTDES = true;

    private int bitrate = 0;
    private int desde = 0;
    private int aes = 0;
    private int des = 0;
    private int desdeT = 0;
    private int aesT = 0;
    private int desT = 0;

    private SecretKeySpec desdeKey;
    private SecretKeySpec aesKey;
    private SecretKeySpec desKey;
    private String[] kPart1 = new String[6];
    private String[] kPart2 = new String[26];
    private String[] kPart3 = new String[2];
    private byte[] decrycontent;
    private String keyPart;

    protected String Number[] = {"yQgnuAych9QqMGiZiJ5pdmJXKB9n87FtWOAn9iHykzQFo4oVbi", "tuPBiZW2zJ5edMwSvHyHPqdgFToSIILUgmHnSX21rejvDB8vY2", "1lf1gX4HmHMXXwiaoopOTFkFYR3tyYU84AdoNYAUcVmu4F3UYl", "9BSrzAmm5jpcqmxTWmvpg1tJmf7b2APMP777V8Q0XxDi2NnZMo", "KjUEk2toqeY15kaBvTDGREuEytR9D2wQRNLKrsCocTqphIQ6qL", "ulX0JgJbf7chLQWjQfiwVj8Zd3ajSUhEM3vQQMOnBoN0yks2wV", "yQgnuAych9QqMGiZiJ5pdmJXKB9n87FtWOAn9iHykzQFo4oVbi", "tuPBiZW2zJ5edMwSvHyHPqdgFToSIILUgmHnSX21rejvDB8vY2", "1lf1gX4HmHMXXwiaoopOTFkFYR3tyYU84AdoNYAUcVmu4F3UYl", "9BSrzAmm5jpcqmxTWmvpg1tJmf7b2APMP777V8Q0XxDi2NnZMo", "KjUEk2toqeY15kaBvTDGREuEytR9D2wQRNLKrsCocTqphIQ6qL", "ulX0JgJbf7chLQWjQfiwVj8Zd3ajSUhEM3vQQMOnBoN0yks2wV"};
    protected String salat[] = {"yhLurcHJHF8kDrDgZoTPyrOa6wv4WkWjzQvlLDqBqeDmN9M2rB", "1rIyE9DuWEGW9pcz4wcOqhBwVY3rgFYuMhUQrWGLBjoWJNlZcB", "1fFnVKcyBVrER2TtBnSAX8TBnkE433TuQdCMajA8EsFSfwAwzM", "Er2dYQx1ZkuihmCL9siFnuuAUwgGh1sMtpIZK3aO2731lppIHw", "0327EwzfLJybZELpWfi1nzZAXjkvkxLm151Kakf5EZmwMmP5HD", "d1e0HBRf2PDSsLhtgaq4CitFevRqKTPVuetQ3szcER9MI1rzui", "TeCgFmPjEPmqThSgOa66NhOPlArAHrA1qMhKpwmmPQVsxVDgE9", "TeCgFmPjEPmqThSgOa66NhOPlArAHrA1qMhKpwmmPQVsxVDgE9", "mZkgeaptb1qKLY9W1fhrXrJWs9t8AIDEZ4vfwsl1KJbtTqcJQZ"};
    protected String date[] = {"d1e0HBRf2PDSsLhtgaq4CitFevRqKTPVuetQ3szcER9MI1rzui", "TeCgFmPjEPmqThSgOa66NhOPlArAHrA1qMhKpwmmPQVsxVDgE9", "TeCgFmPjEPmqThSgOa66NhOPlArAHrA1qMhKpwmmPQVsxVDgE9", "mZkgeaptb1qKLY9W1fhrXrJWs9t8AIDEZ4vfwsl1KJbtTqcJQZ", "yQgnuAych9QqMGiZiJ5pdmJXKB9n87FtWOAn9iHykzQFo4oVbi", "tuPBiZW2zJ5edMwSvHyHPqdgFToSIILUgmHnSX21rejvDB8vY2", "1lf1gX4HmHMXXwiaoopOTFkFYR3tyYU84AdoNYAUcVmu4F3UYl", "9BSrzAmm5jpcqmxTWmvpg1tJmf7b2APMP777V8Q0XxDi2NnZMo", "KjUEk2toqeY15kaBvTDGREuEytR9D2wQRNLKrsCocTqphIQ6qL", "ulX0JgJbf7chLQWjQfiwVj8Zd3ajSUhEM3vQQMOnBoN0yks2wV"};
    protected String example[] = {"JpkCtGLOYQJIaEpvVht8Z2JTlTSKXQCNu4ja9BhrYwe0xtC5V0", "nDiTtGyz6GIhE5RPMYPY9Hgyj1eeHOmUc0hllL4gVdQrbZi8O1", "xcQfNPjUTxsssng8Ghpco4uKacuWOAjNDCyQtFwXDiBUacNN3y", "n5gQy7tJmsJnCSOsT0Z6f1WI5LlfED2fSWDpHfjci3gUyHkhfm", "xXlJMrQWBq2AFeZp8UTdaehKaAYBj7o2u0FGWbgNcDajOONTlH", "jjp6ax36dN8tDMEnn0QgcJpbeepv0iJI16hzyJmrKcLv5XvMFg", "JpkCtGLOYQJIaEpvVht8Z2JTlTSKXQCNu4ja9BhrYwe0xtC5V0", "nDiTtGyz6GIhE5RPMYPY9Hgyj1eeHOmUc0hllL4gVdQrbZi8O1", "xcQfNPjUTxsssng8Ghpco4uKacuWOAjNDCyQtFwXDiBUacNN3y", "n5gQy7tJmsJnCSOsT0Z6f1WI5LlfED2fSWDpHfjci3gUyHkhfm", "xXlJMrQWBq2AFeZp8UTdaehKaAYBj7o2u0FGWbgNcDajOONTlH", "jjp6ax36dN8tDMEnn0QgcJpbeepv0iJI16hzyJmrKcLv5XvMFg"};

    public crypByByte(byte[] decrycontent, int bitrate, String[] p1, String[] p2, String[] p3) {
        this.bitrate = bitrate;
        this.decrycontent = decrycontent;

        Number = p1;
        salat = p2;
        date = p3;
        example = p2;

        kryLog keyLong = new kryLog();
        keyPart = keyLong.kryLog(Number, salat, date, example);
        keyLong = null;
        byte[] key;
        for (int i = 0; i < kPart1.length; i++) {
            key = keyPart.getBytes();
            key = Arrays.copyOf(key, 24);
            keyPart = keyPart.replace(new String(key), "");
            kPart1[i] = new String(key);
        }
        for (int i = 0; i < kPart2.length; i++) {
            key = keyPart.getBytes();
            key = Arrays.copyOf(key, 16);
            keyPart = keyPart.replace(new String(key), "");
            kPart2[i] = new String(key);
        }
        for (int i = 0; i < kPart3.length; i++) {
            key = keyPart.getBytes();
            key = Arrays.copyOf(key, 8);
            keyPart = keyPart.replace(new String(key), "");
            kPart3[i] = new String(key);
        }
        keyPart = null;
        //calc();
        keyHandle();

    }

    public crypByByte(byte[] decrycontent, int bitrate) {
        this.bitrate = bitrate;
        this.decrycontent = decrycontent;

        kryLog keyLong = new kryLog();
        keyPart = keyLong.kryLog(Number, salat, date, example);
        keyLong = null;
        byte[] key;
        for (int i = 0; i < kPart1.length; i++) {
            key = keyPart.getBytes();
            key = Arrays.copyOf(key, 24);
            keyPart = keyPart.replace(new String(key), "");
            kPart1[i] = new String(key);
        }
        for (int i = 0; i < kPart2.length; i++) {
            key = keyPart.getBytes();
            key = Arrays.copyOf(key, 16);
            keyPart = keyPart.replace(new String(key), "");
            kPart2[i] = new String(key);
        }
        for (int i = 0; i < kPart3.length; i++) {
            key = keyPart.getBytes();
            key = Arrays.copyOf(key, 8);
            keyPart = keyPart.replace(new String(key), "");
            kPart3[i] = new String(key);
        }
        keyPart = null;
        //calc();
        keyHandle();

    }

    public boolean setupAlgorythm(boolean AES, boolean DES, boolean TDES) {
        if (!AES && !DES && !TDES) {
            return false;
        }
        useAES = AES;
        useDES = DES;
        useTDES = TDES;
        calc();
        return true;
    }

    private void calc() {
        int tempbit = bitrate;

        if (useTDES && bitrate / 192 >= 1) {
            desde = bitrate / 192;
            if (desde > 5) {
                desde = 5;
            }
            tempbit = bitrate - (desde * 192);
        }

        if (useAES && tempbit / 128 >= 1) {
            aes = tempbit / 128;
            tempbit = tempbit - aes * 128;
        }

        if (useDES && tempbit / 64 >= 1) {
            des = tempbit / 64;
            tempbit = tempbit - des * 64;
        }
        //System.out.println("TDES keys " + desde);
        //System.out.println("AES keys " + aes);
        //System.out.println("DES keys " + des);
        desdeT = 0;
        aesT = 0;
        desT = 0;

    }

    public void updateContent(byte[] content) {
        decrycontent = content;
        calc();
    }

    public byte[] startEncryption() {
        return encryptFiles(decrycontent);
    }

    public byte[] startDecryption() {
        return decryptFiles(decrycontent);
    }

    private void keyHandle() {
        ownReBuild keys = new ownReBuild();
        aesKey = keys.buildByOwn("AES", kPart2[aesT]);
        desKey = keys.buildByOwn("DES", kPart3[desT]);
        desdeKey = keys.buildByOwn("DESede", kPart1[desdeT]);
    }

    private byte[] encryptFiles(byte[] fileContent) {

        try {
            SecretKeySpec key = null;
            if (des != 0) {
                desT = des;
                keyHandle();
                key = desKey;
                ALGORITHM = "DES";
                TRANSFORMATION = "DES";
                des--;
            } else if (aes != 0) {
                aesT = aes;
                keyHandle();
                key = aesKey;
                ALGORITHM = "AES";
                TRANSFORMATION = "AES";
                aes--;
            } else if (desde != 0) {
                desdeT = desde;
                keyHandle();
                key = desdeKey;
                ALGORITHM = "DESede";
                TRANSFORMATION = "DESede";
                desde--;
            }

            fileContent = encrypt(key, fileContent);
            System.gc();

            if (desde == 0 && aes == 0 && des == 0) {
                calc();
                return fileContent;
            } else {
                return encryptFiles(fileContent);
            }
        } catch (CryptoException ex) {
            ERROR.exceptioninfo(ex);
        }
        return null;
    }

    private byte[] decryptFiles(byte[] fileContent) {
        //decrypt

        SecretKeySpec key = null;
        //encrypt fName
        if (desde != 0) {
            desdeT++;
            keyHandle();
            key = desdeKey;
            ALGORITHM = "DESede";
            TRANSFORMATION = "DESede";
            desde--;
        } else if (aes != 0) {
            aesT++;
            keyHandle();
            key = aesKey;
            ALGORITHM = "AES";
            TRANSFORMATION = "AES";
            aes--;
        } else if (des != 0) {
            desT++;
            keyHandle();
            key = desKey;
            ALGORITHM = "DES";
            TRANSFORMATION = "DES";
            des--;
        }
        //System.out.println(new String(key.getEncoded()));
        try {

            fileContent = decrypt(key, fileContent);

            System.gc();
        } catch (CryptoException ex) {
            ERROR.exceptioninfo(ex);
        }

        if (desde == 0 && aes == 0 && des == 0) {
            calc();
            return fileContent;
        } else {
            return decryptFiles(fileContent);
        }
    }

    private byte[] encrypt(SecretKeySpec key, byte[] input)
            throws CryptoException {
        return doCrypto(Cipher.ENCRYPT_MODE, key, input);
    }

    private byte[] decrypt(SecretKeySpec key, byte[] input)
            throws CryptoException {

        return doCrypto(Cipher.DECRYPT_MODE, key, input);
    }

    private byte[] doCrypto(int cipherMode, SecretKeySpec key, byte[] input) throws CryptoException {
        try {
            SecretKeySpec secretKey = key;
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);

            byte[] inputBytes = input;

            byte[] outputBytes = cipher.doFinal(inputBytes);

            return outputBytes;
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException ex) {
            ERROR.exceptioninfo(ex);
        }
        return null;
    }

    public class CryptoException extends Exception {

        public CryptoException() {
        }

        public CryptoException(String message, Throwable throwable) {
            super(message, throwable);
        }
    }

}
