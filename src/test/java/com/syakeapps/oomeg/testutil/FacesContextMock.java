package com.syakeapps.oomeg.testutil;

import java.util.Iterator;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseStream;
import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;

import org.powermock.api.mockito.PowerMockito;

public class FacesContextMock extends FacesContext {

    public static FacesContext mock() {
        FacesContext context = PowerMockito.mock(FacesContext.class);
        setCurrentInstance(context);
        return context;
    }

    @Override
    public Application getApplication() {
        return null;
    }

    @Override
    public Iterator<String> getClientIdsWithMessages() {
        return null;
    }

    @Override
    public ExternalContext getExternalContext() {
        return null;
    }

    @Override
    public Severity getMaximumSeverity() {
        return null;
    }

    @Override
    public Iterator<FacesMessage> getMessages() {
        return null;
    }

    @Override
    public Iterator<FacesMessage> getMessages(String clientId) {
        return null;
    }

    @Override
    public RenderKit getRenderKit() {
        return null;
    }

    @Override
    public boolean getRenderResponse() {
        return false;
    }

    @Override
    public boolean getResponseComplete() {
        return false;
    }

    @Override
    public ResponseStream getResponseStream() {
        return null;
    }

    @Override
    public void setResponseStream(ResponseStream responseStream) {}

    @Override
    public ResponseWriter getResponseWriter() {
        return null;
    }

    @Override
    public void setResponseWriter(ResponseWriter responseWriter) {}

    @Override
    public UIViewRoot getViewRoot() {
        return null;
    }

    @Override
    public void setViewRoot(UIViewRoot root) {}

    @Override
    public void addMessage(String clientId, FacesMessage message) {}

    @Override
    public void release() {}

    @Override
    public void renderResponse() {}

    @Override
    public void responseComplete() {}

}
