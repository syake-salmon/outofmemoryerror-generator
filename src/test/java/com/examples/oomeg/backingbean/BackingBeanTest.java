package com.examples.oomeg.backingbean;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.jboss.weld.junit4.WeldInitiator;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.examples.oomeg.listener.ServletContextListenerImpl;
import com.examples.oomeg.testutil.ExceptionUtil;
import com.examples.oomeg.testutil.FacesContextMock;
import com.examples.oomeg.testutil.OutOfMemoryErrorMock;
import com.examples.oomeg.util.OOMEGenerator;

import junit.framework.TestCase;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ OOMEGenerator.class })
public class BackingBeanTest extends TestCase {

    @Rule
    public WeldInitiator weld = WeldInitiator.from(BackingBean.class)
            .activate(RequestScoped.class).build();

    private BackingBean testTarget;

    @Before
    public void doBefore() {
        testTarget = weld.select(BackingBean.class).get();
    }

    @After
    public void doAfter() {
        if (testTarget != null) {
            weld.destroy(BackingBean.class);
        }
    }

    @Test
    public void testInit_paramIs9999() throws Exception {
        /* SETUP */
        FacesContext context = FacesContextMock.mock();
        ExternalContext exContext = PowerMockito.mock(ExternalContext.class);
        PowerMockito.when(context.getExternalContext()).thenReturn(exContext);
        PowerMockito.when(exContext.getInitParameter(
                ServletContextListenerImpl.CONTXTPRM_GENERATE_OOME_INTERVAL_MILSEC))
                .thenReturn("9999");

        /* INVOCATION */
        long actual = testTarget.getInterval();

        /* ASSERTION */
        assertEquals(9999, actual);
    }

    @Test
    public void testInit_paramIsNotLongValue() throws Exception {
        /* SETUP */
        FacesContext context = FacesContextMock.mock();
        ExternalContext exContext = PowerMockito.mock(ExternalContext.class);
        PowerMockito.when(context.getExternalContext()).thenReturn(exContext);
        PowerMockito.when(exContext.getInitParameter(
                ServletContextListenerImpl.CONTXTPRM_GENERATE_OOME_INTERVAL_MILSEC))
                .thenReturn("This is not long value!");

        /* INVOCATION */
        Class<?> actual = null;
        try {
            testTarget.getInterval();
        } catch (Exception | Error e) {
            actual = ExceptionUtil.findRootCause(e).getClass();
        }

        /* ASSERTION */
        assertEquals(NumberFormatException.class, actual);
    }

    @Test
    public void testInit_paramIsEmpty() throws Exception {
        /* SETUP */
        FacesContext context = FacesContextMock.mock();
        ExternalContext exContext = PowerMockito.mock(ExternalContext.class);
        PowerMockito.when(context.getExternalContext()).thenReturn(exContext);
        PowerMockito.when(exContext.getInitParameter(
                ServletContextListenerImpl.CONTXTPRM_GENERATE_OOME_INTERVAL_MILSEC))
                .thenReturn("");

        /* INVOCATION */
        long actual = testTarget.getInterval();

        /* ASSERTION */
        assertEquals(1000, actual);
    }

    @Test
    public void testInit_paramIsNull() throws Exception {
        /* SETUP */
        FacesContext context = FacesContextMock.mock();
        ExternalContext exContext = PowerMockito.mock(ExternalContext.class);
        PowerMockito.when(context.getExternalContext()).thenReturn(exContext);
        PowerMockito.when(exContext.getInitParameter(
                ServletContextListenerImpl.CONTXTPRM_GENERATE_OOME_INTERVAL_MILSEC))
                .thenReturn(null);

        /* INVOCATION */
        long actual = testTarget.getInterval();

        /* ASSERTION */
        assertEquals(1000, actual);
    }

    @Test
    public void testSetInterval() throws Exception {
        /* SETUP */
        FacesContext context = FacesContextMock.mock();
        ExternalContext exContext = PowerMockito.mock(ExternalContext.class);
        PowerMockito.when(context.getExternalContext()).thenReturn(exContext);

        /* INVOCATION */
        long expected = 9999;
        testTarget.setInterval(expected);
        long actual = testTarget.getInterval();

        /* ASSERTION */
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateOOME() throws Exception {
        /* SETUP */
        FacesContext context = FacesContextMock.mock();
        ExternalContext exContext = PowerMockito.mock(ExternalContext.class);
        PowerMockito.when(context.getExternalContext()).thenReturn(exContext);
        PowerMockito.mockStatic(OOMEGenerator.class);
        PowerMockito.doThrow(new OutOfMemoryErrorMock())
                .when(OOMEGenerator.class, "generateOOME", 1000L);

        /* INVOCATION */
        boolean condition = false;
        try {
            testTarget.generateOOME();
        } catch (OutOfMemoryError e) {
            condition = true;
        }

        /* ASSERTION */
        assertTrue(condition);
    }
}
