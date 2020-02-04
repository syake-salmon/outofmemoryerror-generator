package com.examples.oomeg.util;

import javax.jms.IllegalStateException;

import com.examples.oomeg.listener.ServletContextListenerImpl;

/**
 * {@linkplain OutOfMemoryError} generator.
 */
public final class OOMEGenerator {

    private OOMEGenerator() throws IllegalStateException {
        throw new IllegalStateException(
                this.getClass().getName() + " is utility class.");
    }

    /**
     * Generate {@linkplain OutOfMemoryError} because of the lack of java heap.
     * 
     * @param interval {@linkplain ServletContextListenerImpl#CONTXTPRM_GENERATE_OOME_INTERVAL_MILSEC}
     */
    public static void generateOOME(final long interval) {
        int dummyArraySize = 15;
        System.out
                .println("Max JVM memory: " + Runtime.getRuntime().maxMemory());
        long memoryConsumed = 0;
        try {
            long[] memoryAllocated = null;
            for (int loop = 0; loop < Integer.MAX_VALUE; loop++) {
                memoryAllocated = new long[dummyArraySize];
                memoryAllocated[0] = 0;
                memoryConsumed += dummyArraySize * Long.SIZE;
                System.out
                        .println("Memory Consumed till now: " + memoryConsumed);
                dummyArraySize *= dummyArraySize * 2;
                sleep(interval);
            }
        } catch (OutOfMemoryError err) {
            System.out.println("Catching " + OutOfMemoryError.class.getName()
                    + ". Generating OOME is succeeded.");
            throw err;
        }
    }

    private static void sleep(final long interval) {
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
