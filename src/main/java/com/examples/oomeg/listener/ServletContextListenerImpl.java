package com.examples.oomeg.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.examples.oomeg.util.OOMEGenerator;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    public static final String CONTXTPRM_GENERATE_OOME_ON_BOOT = "GENERATE_OOME_ON_BOOT";
    public static final String CONTXTPRM_GENERATE_OOME_INTERVAL_MILSEC = "GENERATE_OOME_INTERVAL_MILLISEC";
    public static final long DEFAULT_GENERATE_OOME_INTERVAL_MILSEC = 1000;

    public void contextInitialized(ServletContextEvent sce) {
        boolean flg = Boolean.valueOf(sce.getServletContext().getInitParameter(CONTXTPRM_GENERATE_OOME_ON_BOOT));
        if (flg) {
            String intervalStr = sce.getServletContext().getInitParameter(CONTXTPRM_GENERATE_OOME_INTERVAL_MILSEC);
            if (intervalStr == null || intervalStr.isEmpty()) {
                OOMEGenerator.generateOOME(DEFAULT_GENERATE_OOME_INTERVAL_MILSEC);
            } else {
                long interval = Long.valueOf(intervalStr);
                OOMEGenerator.generateOOME(interval);
            }
        }
    }
}
