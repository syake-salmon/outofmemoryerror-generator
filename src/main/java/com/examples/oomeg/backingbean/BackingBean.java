package com.examples.oomeg.backingbean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.examples.oomeg.listener.ServletContextListenerImpl;
import com.examples.oomeg.util.OOMEGenerator;

/**
 * Requestscoped bean for index.xhtml, named as "BB".<br />
 * 
 * <h1>Initialize</h1> Get value of context-param(
 * {@linkplain ServletContextListenerImpl#CONTXTPRM_GENERATE_OOME_INTERVAL_MILSEC})
 * . If param is null or empty, will load alternative
 * {@linkplain ServletContextListenerImpl#DEFAULT_GENERATE_OOME_INTERVAL_MILSEC}.
 */
@RequestScoped
@Named("BB")
public class BackingBean {
    /**
     * Interval millisec of generating {@linkplain OutOfMemoryError}.
     */
    private Long interval = ServletContextListenerImpl.DEFAULT_GENERATE_OOME_INTERVAL_MILSEC;

    @PostConstruct
    private void init() {
        String intervalStr = FacesContext.getCurrentInstance()
                .getExternalContext().getInitParameter(
                        ServletContextListenerImpl.CONTXTPRM_GENERATE_OOME_INTERVAL_MILSEC);
        if (intervalStr != null && !intervalStr.isEmpty()) {
            interval = Long.valueOf(intervalStr);
        }
    }

    /**
     * @return {@linkplain BackingBean#interval}
     * 
     * @see ServletContextListenerImpl#CONTXTPRM_GENERATE_OOME_INTERVAL_MILSEC
     * @see ServletContextListenerImpl#DEFAULT_GENERATE_OOME_INTERVAL_MILSEC
     */
    public long getInterval() {
        return interval;
    }

    /**
     * Set interval millisec of generating {@linkplain OutOfMemoryError}.
     * 
     * @param interval new interval millisec
     */
    public void setInterval(final long interval) {
        this.interval = interval;
    }

    /**
     * {@linkplain OOMEGenerator#generateOOME(long)}
     */
    public void generateOOME() {
        OOMEGenerator.generateOOME(interval);
    }
}
