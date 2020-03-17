package com.syakeapps.oomeg.util;

import org.junit.Test;

import junit.framework.TestCase;

public class OOMEGeneratorTest extends TestCase {

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
