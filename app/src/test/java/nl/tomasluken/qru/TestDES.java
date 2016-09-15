package nl.tomasluken.qru;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Luken on 15-9-2016.
 */
public class TestDES {
    @Test
    public void testDES() throws Exception {
        //SecretKey key = KeyGenerator.getInstance("DES").generateKey();
        //System.out.println(key);

        DES encrypter = new DES();
        String encryptedAsHex = encrypter.encrypt("DitIsEen", "01234567");
        System.out.println(encryptedAsHex);
        assertEquals("E6D12A258CB75E54", encryptedAsHex);

        String decryptedAsHex = encrypter.decrypt("E6D12A258CB75E54", "01234567");
        System.out.println(decryptedAsHex);
        assertEquals("DitIsEen", decryptedAsHex);

        encryptedAsHex = encrypter.encrypt("bioscoop", "canadees");
        System.out.println(encryptedAsHex);
        assertEquals("DCCD38F9137EE79B", encryptedAsHex);

        decryptedAsHex = encrypter.decrypt("DCCD38F9137EE79B", "canadees");
        System.out.println(decryptedAsHex);
        assertEquals("bioscoop", decryptedAsHex);
    }
}
