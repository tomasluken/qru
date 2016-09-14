package nl.tomasluken.qru;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestPojos {
    @Test
    public void TestOne() throws Exception {
        AES aes = new AES();
        aes.DoIt("Deze tekst is een stuk langer, en dient ook versleuteld te worden...", "0123456789123456");
    }
    @Test
    public void TestEncrypt() throws Exception {
        AES aes = new AES();
        assertEquals("670A94867A44412995F92B980EFE337E92B7585AC56CDD9E0A36CCF415C47A613A3799A91D2A0B25203DEC54DD106D47BCBC1DF6D2398F032A262E3F4FF8A75E22721558354929565526709D670A51B8", aes.Encrypt("Deze tekst is een stuk langer, en dient ook versleuteld te worden...", "0123456789123456"));
    }
}