package com.syakeapps.oomeg.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import com.syakeapps.oomeg.testutil.HttpServletRequestStub;
import com.syakeapps.oomeg.testutil.HttpServletResponseStub;

public class XHTMLProtectFilterTest {

    private XHTMLProtectFilter testTarget;

    @Before
    public void doBefore() {
        testTarget = new XHTMLProtectFilter();
    }

    @Test
    public void test_init() throws Exception {
        /* SETUP */

        /* INVOCATION */
        boolean isError = false;
        try {
            testTarget.init(null);
        } catch (Exception | Error e) {
            isError = true;
        }

        /* ASSERTION */
        assertFalse(isError);
    }

    @Test
    public void test_doFilter_ForbiddenPath() throws Exception {
        /* SETUP */
        HttpServletRequestStub request = new HttpServletRequestStub();
        request.requestInfoMap.put(HttpServletRequestStub.KEY_SERVLET_PATH,
                "/hoge.xhtml");
        HttpServletResponseStub response = new HttpServletResponseStub();

        /* INVOCATION */
        testTarget.doFilter(request, response, null);
        int actual = response.statusCode;

        /* ASSERTION */
        assertEquals(HttpServletResponse.SC_FORBIDDEN, actual);
    }

    @Test
    public void test_doFilter_SafePath() throws Exception {
        /* SETUP */
        HttpServletRequestStub request = new HttpServletRequestStub();
        request.requestInfoMap.put(HttpServletRequestStub.KEY_SERVLET_PATH,
                "/hoge.faces");
        HttpServletResponseStub response = new HttpServletResponseStub();
        FilterChain chain = PowerMockito.mock(FilterChain.class);

        /* INVOCATION */
        testTarget.doFilter(request, response, chain);
        int actual = response.statusCode;

        /* ASSERTION */
        assertEquals(0, actual);
    }

    @Test
    public void test_destroy() {
        /* SETUP */

        /* INVOCATION */
        boolean isError = false;
        try {
            testTarget.destroy();
        } catch (Exception | Error e) {
            isError = true;
        }

        /* ASSERTION */
        assertFalse(isError);
    }

}
