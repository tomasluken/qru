package nl.tomasluken.qru;

import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Luken on 15-9-2016.
 */
public class TestDES {
    @Test
    public void testDES() throws Exception {
        //SecretKey key = KeyGenerator.getInstance("DES").generateKey();
        //System.out.println(key);
        byte[] key = ("01234567").getBytes("UTF-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "DES");
        DES encrypter = new DES(secretKeySpec);
        String encrypted = encrypter.encrypt("Testbericht");
        System.out.println(encrypted);
        String decrypted = encrypter.decrypt(encrypted);
        System.out.println(decrypted);
    }
}
