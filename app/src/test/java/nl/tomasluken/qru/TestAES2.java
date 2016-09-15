package nl.tomasluken.qru;

import org.junit.Test;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Luken on 15-9-2016.
 */
public class TestAES2 {
    @Test
    public void Run() throws Exception {

        byte[] plainText = "Achtachttientien".getBytes("UTF8");
        //
        // get a DES private key
        System.out.println( "\nStart generating DES key" );
        //KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        //keyGen.init(56);
        //Key key = keyGen.generateKey();
        byte[] key = ("01234567").getBytes("UTF-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "DES");
        System.out.println( "Finish generating DES key" );
        //
        // get a DES cipher object and print the provider
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        System.out.println( "\n" + cipher.getProvider().getInfo() );
        //
        // encrypt using the key and the plaintext
        System.out.println( "\nStart encryption" );
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] cipherText = cipher.doFinal(plainText);
        System.out.println( "Finish encryption: " );
        System.out.println( new String(cipherText, "UTF8") );
        //System.out.println(DES.toHex(cipherText));

        //
        // decrypt the ciphertext using the same key
        System.out.println( "\nStart decryption" );
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] newPlainText = cipher.doFinal(cipherText);
        System.out.println( "Finish decryption: " );

        System.out.println( new String(newPlainText, "UTF8") );

    }
}
