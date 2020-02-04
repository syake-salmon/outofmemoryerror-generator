package com.examples.oomeg.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.examples.oomeg.testutil.PrintStreamStub;

public class OOMEGeneratorTest {

    @Test
    public void testGenerateOOME() throws Exception {
        /* SETUP */
        PrintStreamStub out = new PrintStreamStub();
        System.setOut(out);

        /* INVOCATION */
        try {
            OOMEGenerator.generateOOME(1);
        } catch (OutOfMemoryError e) {}
        String actual = out.getLastOutput();

        /* ASSERTION */
        assertEquals("Catching java.lang.OutOfMemoryError. Generating OOME is succeeded.", actual);
    }
}
