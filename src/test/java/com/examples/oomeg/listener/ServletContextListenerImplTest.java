package com.examples.oomeg.listener;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.servlet.ServletContextEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.examples.oomeg.testutil.OutOfMemoryErrorMock;
import com.examples.oomeg.testutil.ServletContextStub;
import com.examples.oomeg.util.OOMEGenerator;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ OOMEGenerator.class })
public class ServletContextListenerImplTest {

    private ServletContextListenerImpl testTarget;

    @Before
    public void doBefore() {
        testTarget = new ServletContextListenerImpl();
    }

    @Test
    public void testContextInitialized_flagIsTrueAndIntervalIsSet() throws Exception {
        /* SETUP */
        ServletContextEvent sce = PowerMockito.mock(ServletContextEvent.class);
        ServletContextStub sc = PowerMockito.mock(ServletContextStub.class);
        PowerMockito.when(sc.getInitParameter(ServletContextListenerImpl.CONTXTPRM_GENERATE_OOME_ON_BOOT))
                .thenReturn("true");
        PowerMockito.when(sc.getInitParameter(ServletContextListenerImpl.CONTXTPRM_GENERATE_OOME_INTERVAL_MILSEC))
                .thenReturn("5000");
        PowerMockito.when(sce.getServletContext()).thenReturn(sc);
        PowerMockito.mockStatic(OOMEGenerator.class);
        PowerMockito.doThrow(new OutOfMemoryErrorMock()).when(OOMEGenerator.class, "generateOOME", 5000L);

        /* INVOCATION */
        boolean condition = false;
        try {
            testTarget.contextInitialized(sce);
        } catch (OutOfMemoryErrorMock e) {
            condition = true;
        }

        /* ASSERTION */
        assertTrue(condition);
    }

    @Test
    public void testContextInitialized_flagIsTrueAndIntervalIsNull() throws Exception {
        /* SETUP */
        ServletContextEvent sce = PowerMockito.mock(ServletContextEvent.class);
        ServletContextStub sc = PowerMockito.mock(ServletContextStub.class);
        PowerMockito.when(sc.getInitParameter(ServletContextListenerImpl.CONTXTPRM_GENERATE_OOME_ON_BOOT))
                .thenReturn("true");
        PowerMockito.when(sc.getInitParameter(ServletContextListenerImpl.CONTXTPRM_GENERATE_OOME_INTERVAL_MILSEC))
                .thenReturn(null);
        PowerMockito.when(sce.getServletContext()).thenReturn(sc);
        PowerMockito.mockStatic(OOMEGenerator.class);
        PowerMockito.doThrow(new OutOfMemoryErrorMock()).when(OOMEGenerator.class, "generateOOME", 1000L);

        /* INVOCATION */
        boolean condition = false;
        try {
            testTarget.contextInitialized(sce);
        } catch (OutOfMemoryErrorMock e) {
            condition = true;
        }

        /* ASSERTION */
        assertTrue(condition);
    }

    @Test
    public void testContextInitialized_flagIsTrueAndIntervalIsEmpty() throws Exception {
        /* SETUP */
        ServletContextEvent sce = PowerMockito.mock(ServletContextEvent.class);
        ServletContextStub sc = PowerMockito.mock(ServletContextStub.class);
        PowerMockito.when(sc.getInitParameter(ServletContextListenerImpl.CONTXTPRM_GENERATE_OOME_ON_BOOT))
                .thenReturn("true");
        PowerMockito.when(sc.getInitParameter(ServletContextListenerImpl.CONTXTPRM_GENERATE_OOME_INTERVAL_MILSEC))
                .thenReturn("");
        PowerMockito.when(sce.getServletContext()).thenReturn(sc);
        PowerMockito.mockStatic(OOMEGenerator.class);
        PowerMockito.doThrow(new OutOfMemoryErrorMock()).when(OOMEGenerator.class, "generateOOME", 1000L);

        /* INVOCATION */
        boolean condition = false;
        try {
            testTarget.contextInitialized(sce);
        } catch (OutOfMemoryErrorMock e) {
            condition = true;
        }

        /* ASSERTION */
        assertTrue(condition);
    }

    @Test
    public void testContextInitialized_flagIsFalse() throws Exception {
        /* SETUP */
        ServletContextEvent sce = PowerMockito.mock(ServletContextEvent.class);
        ServletContextStub sc = PowerMockito.mock(ServletContextStub.class);
        PowerMockito.when(sc.getInitParameter(ServletContextListenerImpl.CONTXTPRM_GENERATE_OOME_ON_BOOT))
                .thenReturn("false");
        PowerMockito.when(sce.getServletContext()).thenReturn(sc);

        /* INVOCATION */
        boolean condition = false;
        try {
            testTarget.contextInitialized(sce);
        } catch (OutOfMemoryError e) {
            condition = true;
        }

        /* ASSERTION */
        assertFalse(condition);
    }

    @Test
    public void testContextDestroyed() {
        /* SETUP */
        ServletContextEvent sce = PowerMockito.mock(ServletContextEvent.class);

        /* INVOCATION */
        boolean condition = false;
        try {
            testTarget.contextDestroyed(sce);
        } catch (Exception e) {
            condition = false;
        }

        /* ASSERTION */
        assertFalse(condition);
    }
}
