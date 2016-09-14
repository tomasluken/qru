package nl.tomasluken.qru;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Luken on 12-9-2016.
 */
public class TextEncryptor {
    public String Decrypt(String encryptedString, String key) {
        try {
            String stringDec = "Hi there";
            SecretKey aesKey = new SecretKeySpec(new byte[16], "AES");

            Cipher cipher = null;

            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, aesKey);

            // no encoding given, don't use getBytes() without a Charset.forName("UTF-8")
            byte[] data = cipher.doFinal(stringDec.getBytes());
            byte[] iv = cipher.getIV();

            // doesn't do anything
            AlgorithmParameters.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, aesKey, new IvParameterSpec(iv));
            byte[] decrypted = cipher.doFinal(data);
            System.out.println(new String(decrypted));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return "asdsad";
    }
}
