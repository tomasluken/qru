package nl.tomasluken.qru;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Luken on 15-9-2016.
 */
public class DES {
    //Cipher ecipher;
    //Cipher cipher;
    //DES(SecretKey key) throws Exception {
        //ecipher = Cipher.getInstance("DES/ECB/NoPadding");
        //dcipher = Cipher.getInstance("DES/ECB/NoPadding");
        //ecipher.init(Cipher.ENCRYPT_MODE, key);
        //dcipher.init(Cipher.DECRYPT_MODE, key);
    //}
    public String encrypt(String str, String sKey) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        byte[] key = (sKey).getBytes("UTF-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        // Encode the string into bytes using utf-8
        byte[] utf8 = str.getBytes("UTF8");

        // Encrypt
        byte[] enc = cipher.doFinal(utf8);

        // Encode bytes to HEX to get a string
        return toHex(enc);
    }

    public String decrypt(String str, String sKey) throws Exception {

        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        byte[] key = (sKey).getBytes("UTF-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        byte[] dec = hexStringToByteArray(str);

        byte[] utf8 = cipher.doFinal(dec);

        // Decode using utf-8
        return new String(utf8, "UTF8");
    }
    private static String toHex(byte[] bytes)
    {
        char[] hexArray = "0123456789ABCDEF".toCharArray();
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
