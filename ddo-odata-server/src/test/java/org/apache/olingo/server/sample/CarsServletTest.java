package org.apache.olingo.server.sample;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CarsServletTest {
    @Mock
    private HttpSession mockHttpSession;
    private Map<String, Object> attributes = new HashMap<String, Object>();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Ignore("Deferring this test for triage.  JettyServletTest may be a good alternative")
    public void testServlet() throws Exception {

        Mockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                String key = (String) invocation.getArguments()[0];
                return attributes.get(key);
            }
        }).when(mockHttpSession).getAttribute(anyString());

        Mockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                String key = (String) invocation.getArguments()[0];
                Object value = invocation.getArguments()[1];
                attributes.put(key, value);
                return null;
            }
        }).when(mockHttpSession).setAttribute(anyString(), any());

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("username")).thenReturn("me");
        when(request.getParameter("password")).thenReturn("secret");
        when(request.getSession(true)).thenReturn(mockHttpSession);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new CarsServlet().service(request, response);

        verify(request, atLeast(1)).getParameter("username"); // only if you want to verify username was called...
        writer.flush(); // it may not have been flushed yet...
        assertTrue(stringWriter.toString().contains("My expected string"));
    }


}