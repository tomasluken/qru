package nl.tomasluken.helloworld3;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestPojos {
    @Test
    public void testYourNameIs() throws Exception {
        MyAppCore core = new MyAppCore();
        String result = core.YourNameIs("Tomas");
        assertEquals("Hi, Tomas", result);
    }
}