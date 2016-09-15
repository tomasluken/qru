package nl.tomasluken.qru;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestPojos {
    @Test
    public void TestEncryptAndDecrypt() throws Exception {
        AES aes = new AES();
        aes.EncryptAndDecrypt("Testbericht", "0123456789123456");
    }
    @Test
    public void TestEncrypt() throws Exception {
        AES aes = new AES();
        assertEquals("1AD1E71D59A182C6AA91E0FFD5D04172", aes.Encrypt("Testbericht", "0123456789123456"));
    }
    @Test
    public void TestDecrypt() throws Exception {
        AES aes = new AES();
        assertEquals("Testbericht", aes.Decrypt("1AD1E71D59A182C6AA91E0FFD5D04172", "0123456789123456"));
    }
}