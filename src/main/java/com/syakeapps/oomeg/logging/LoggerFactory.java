package com.syakeapps.oomeg.logging;

import java.util.Locale;

import org.slf4j.cal10n.LocLogger;
import org.slf4j.cal10n.LocLoggerFactory;

import ch.qos.cal10n.IMessageConveyor;
import ch.qos.cal10n.MessageConveyor;

/**
 * {@link LocLogger} provider.
 */
public final class LoggerFactory {

    private LoggerFactory() {
        throw new IllegalStateException("Unsupported feature.");
    }

    /**
     * Get a new {@link LocLogger} instance by class. The returned
     * {@link LocLogger} will be named after the class. The {@link LocLogger}
     * will be localized by {@link Locale#getDefault()}.
     *
     * @param clazz {@link Class} for logger name
     * @return {@link LocLogger} instance by class
     */
    public static LocLogger getLogger(final Class<?> clazz) {
        return getLogger(clazz, Locale.getDefault());
    }

    private static LocLogger getLogger(final Class<?> clazz, final Locale loc) {
        IMessageConveyor conveyor = new MessageConveyor(loc);
        return new LocLoggerFactory(conveyor).getLocLogger(clazz);
    }
}
