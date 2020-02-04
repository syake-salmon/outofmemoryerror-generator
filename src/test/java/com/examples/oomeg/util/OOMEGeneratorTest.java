package com.examples.oomeg.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OOMEGeneratorTest {

    @Test
    public void testGenerateOOME() throws Exception {
        /* SETUP */

        /* INVOCATION */
        boolean condition = false;
        try {
            OOMEGenerator.generateOOME(1);
        } catch (OutOfMemoryError e) {
            condition = true;
        }

        /* ASSERTION */
        assertTrue(condition);
    }
}
