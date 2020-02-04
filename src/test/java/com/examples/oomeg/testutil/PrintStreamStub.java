package com.examples.oomeg.testutil;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.powermock.api.mockito.PowerMockito;

public class PrintStreamStub extends PrintStream {

    private List<String> outputs = new ArrayList<>();

    public PrintStreamStub() {
        this(PowerMockito.mock(OutputStream.class));
    }

    private PrintStreamStub(OutputStream out) {
        super(out);
    }

    @Override
    public void println(String output) {
        outputs.add(output);
    }

    public List<String> getOutputs() {
        return outputs;
    }

    public String getLastOutput() {
        return outputs.get(outputs.size() - 1);
    }
}
