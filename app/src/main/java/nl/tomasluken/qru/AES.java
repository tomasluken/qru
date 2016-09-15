package nl.tomasluken.qru;

import android.util.Base64;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Luken on 14-9-2016.
 */
public class AES {
    public void EncryptAndDecrypt(String theMessage, String theKey) throws Exception
    {
        String plainText = theMessage;
        System.out.println("Original Text:" + plainText);

        SecretKey secKey = getSecretEncryptionKey(theKey);
        System.out.println("AES Key (Hex Form):"+bytesToHex(secKey.getEncoded()));

        byte[] cipherText = encryptText(plainText, secKey);
        System.out.println("Encrypted Text (Hex Form):"+bytesToHex(cipherText));

        secKey = getSecretEncryptionKey(theKey);
        String decryptedText = decryptText(cipherText, secKey);
        System.out.println("Descrypted Text:"+decryptedText);
    }

    public String Encrypt(String theMessage, String theKey) throws Exception
    {
        SecretKey secKey = getSecretEncryptionKey(theKey);
        System.out.println("AES Key (Hex Form):"+bytesToHex(secKey.getEncoded()));
        byte[] cipherText = encryptText(theMessage, secKey);
        System.out.println("Encrypted Text (Hex Form):"+bytesToHex(cipherText));
        return bytesToHex(cipherText);
    }

    public String Decrypt(String theMessage, String theKey) throws Exception
    {
        SecretKey secKey = getSecretEncryptionKey(theKey);
        System.out.println("AES Key (Hex Form):"+bytesToHex(secKey.getEncoded()));
        System.out.println("Encrypted Text (Hex Form):"+theMessage);
        byte[] encryptedText = hexStringToByteArray(theMessage);
        String decryptedText = decryptText(encryptedText, secKey);
        return decryptedText;
    }

    private static SecretKey getSecretEncryptionKey(String theKey) throws Exception{
        //SecretKeyFactory factory = SecretKeyFactory.getInstance("AES");
        byte[] key = (theKey).getBytes("UTF-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        //KeyGenerator generator = KeyGenerator.getInstance("AES");
        //generator.init(128); // The AES key size in number of bits
        //SecretKey secKey = generator.generateKey();
        return secretKeySpec;
    }

    private static byte[] encryptText(String plainText,SecretKey secKey) throws Exception{
        // AES defaults to AES/ECB/PKCS5Padding in Java 7
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aesCipher.init(Cipher.ENCRYPT_MODE, secKey, generateIV());
        byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
        return byteCipherText;
    }

    private static String decryptText(byte[] byteCipherText, SecretKey secKey) throws Exception {
        // AES defaults to AES/ECB/PKCS5Padding in Java 7
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aesCipher.init(Cipher.DECRYPT_MODE, secKey, generateIV());
        byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
        System.out.println("Decrypted (HEX):"+bytesToHex(bytePlainText));
        return new String(bytePlainText);
    }
    private static IvParameterSpec generateIV() {
        IvParameterSpec IV;
        SecureRandom r = new SecureRandom();
        byte[] newSeed = r.generateSeed(16);
        r.setSeed(newSeed);

        byte[] byteIV = new byte[16];
        r.nextBytes(byteIV);
        IV = new IvParameterSpec(byteIV);
        return IV;
    }
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}