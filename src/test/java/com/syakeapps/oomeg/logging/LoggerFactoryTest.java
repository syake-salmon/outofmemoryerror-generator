package com.syakeapps.oomeg.logging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.slf4j.cal10n.LocLogger;

import ch.qos.cal10n.IMessageConveyor;

public class LoggerFactoryTest {

    @Test
    public void test_getLogger() throws Exception {
        /* SETUP */
        Locale expected = Locale.getDefault();

        /* INVOCATION */
        LocLogger logger = LoggerFactory.getLogger(LoggerFactoryTest.class);
        IMessageConveyor imc = (IMessageConveyor) PowerMockito
                .field(logger.getClass(), "imc").get(logger);
        Locale actual = (Locale) PowerMockito.field(imc.getClass(), "locale")
                .get(imc);

        /* ASSERTION */
        assertNotNull(logger);
        assertEquals(expected, actual);
    }
}
