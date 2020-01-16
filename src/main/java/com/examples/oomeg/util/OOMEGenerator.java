package com.examples.oomeg.util;

import com.examples.oomeg.listener.ServletContextListenerImpl;

public class OOMEGenerator {

    public static void generateOOME(long interval) {
        int dummyArraySize = 15;
        System.out.println("Max JVM memory: " + Runtime.getRuntime().maxMemory());
        long memoryConsumed = 0;
        try {
            long[] memoryAllocated = null;
            for (int loop = 0; loop < Integer.MAX_VALUE; loop++) {
                memoryAllocated = new long[dummyArraySize];
                memoryAllocated[0] = 0;
                memoryConsumed += dummyArraySize * Long.SIZE;
                System.out.println("Memory Consumed till now: " + memoryConsumed);
                dummyArraySize *= dummyArraySize * 2;
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    // にぎりつぶす
                    e.printStackTrace();
                }
            }
        } catch (OutOfMemoryError err) {
            System.out.println("Catching " + OutOfMemoryError.class.getName() + ". Generating OOME is succeeded.");
            throw err;
        }
    }

    public static void main(String... args) {
        Long interval = null;
        if (args.length > 0) {
            interval = Long.valueOf(Long.valueOf(args[0]));
        }

        if (interval == null) {
            generateOOME(ServletContextListenerImpl.DEFAULT_GENERATE_OOME_INTERVAL_MILSEC);
        } else {
            generateOOME(interval);
        }
    }
}
