package com.syakeapps.oomeg.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.syakeapps.oomeg.util.OOMEGenerator;

/**
 * Implementation class of ServletContextListener to generate
 * {@linkplain OutOfMemoryError} on boot.
 */
@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    /**
     * Enable generationg OOME on boot.
     * 
     * <h1>web.xml example</h1>
     * 
     * <code><pre>
     * &lt;context-param&gt;
     *   &lt;param-name&gt;GENERATE_OOME_ON_BOOT&lt;/param-name&gt;
     *   &lt;param-value&gt;FALSE&lt;/param-value&gt;
     * &lt;/context-param&gt;
     * </pre></code>
     */
    public static final String CONTXTPRM_GENERATE_OOME_ON_BOOT = "GENERATE_OOME_ON_BOOT";

    /**
     * Interval millisec of generationg OOME.
     * 
     * <h1>web.xml example</h1>
     * 
     * <code><pre>
     * &lt;context-param&gt;
     *   &lt;param-name&gt;GENERATE_OOME_INTERVAL_MILLISEC&lt;/param-name&gt;
     *   &lt;param-value&gt;1000&lt;/param-value&gt;
     * &lt;/context-param&gt;
     * </pre></code>
     */
    public static final String CONTXTPRM_GENERATE_OOME_INTERVAL_MILSEC = "GENERATE_OOME_INTERVAL_MILLISEC";

    /**
     * Default value of
     * {@linkplain ServletContextListenerImpl#CONTXTPRM_GENERATE_OOME_INTERVAL_MILSEC}.
     */
    public static final long DEFAULT_GENERATE_OOME_INTERVAL_MILSEC = 1000;

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        boolean flg = Boolean.parseBoolean(sce.getServletContext()
                .getInitParameter(CONTXTPRM_GENERATE_OOME_ON_BOOT));
        if (flg) {
            String intervalStr = sce.getServletContext()
                    .getInitParameter(CONTXTPRM_GENERATE_OOME_INTERVAL_MILSEC);
            if (intervalStr == null || intervalStr.isEmpty()) {
                OOMEGenerator
                        .generateOOME(DEFAULT_GENERATE_OOME_INTERVAL_MILSEC);
            } else {
                long interval = Long.parseLong(intervalStr);
                OOMEGenerator.generateOOME(interval);
            }
        }
    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
        // do nothing.
    }
}
