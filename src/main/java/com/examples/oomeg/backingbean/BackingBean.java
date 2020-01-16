package com.examples.oomeg.backingbean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.examples.oomeg.listener.ServletContextListenerImpl;
import com.examples.oomeg.util.OOMEGenerator;

@RequestScoped
@Named("BB")
public class BackingBean {
    private Long interval = ServletContextListenerImpl.DEFAULT_GENERATE_OOME_INTERVAL_MILSEC;

    @PostConstruct
    private void init() {
        String intervalStr = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter(ServletContextListenerImpl.CONTXTPRM_GENERATE_OOME_INTERVAL_MILSEC);
        if (intervalStr != null && !intervalStr.isEmpty()) interval = Long.valueOf(intervalStr);
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void generateOOME() {
        OOMEGenerator.generateOOME(interval);
    }
}
