package nl.tomasluken.helloworld3;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestPojos {
    @Test
    public void testTestsCanBeRun() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void testTestsCanFail() throws Exception {
        assertEquals(4, 2 + 1);
    }
}