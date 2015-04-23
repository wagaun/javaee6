package com.tsuchiya.wagner.study.sessionbeans;

import org.junit.*;

import javax.ejb.Local;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * FIXME This test is named Simple because it needs to run after others. I think it is because the container created by
 * initial context is not disposed after test.
 *
 * Created by wagner on 4/23/15.
 */
public class SimpleKnockerBeanTest {

    private static InitialContext initialContext;

    @BeforeClass
    public static void setUp() throws Exception {
        Properties properties = new Properties();
        properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.core.LocalInitialContextFactory");

        initialContext = new InitialContext(properties);
    }

    /**
     * Lookup the Calculator bean via its remote home interface
     *
     * @throws Exception
     */
    @Test
    public void testCalculatorViaRemoteInterface() throws Exception {
        Object object = initialContext.lookup("KnockerImplRemote");

        assertNotNull(object);
        assertTrue(object instanceof RemoteKnocker);
        RemoteKnocker knocker = (RemoteKnocker) object;
        assertEquals("Send email: Who is there?", knocker.remotelyKnock());
    }

    /**
     * Lookup the Calculator bean via its local home interface
     *
     * @throws Exception
     */
    @Test
    public void testCalculatorViaLocalInterface() throws Exception {
        Object object = initialContext.lookup("KnockerImplLocal");

        assertNotNull(object);
        assertTrue(object instanceof LocalKnocker);
        LocalKnocker knocker = (LocalKnocker) object;
        assertEquals("Who is there?", knocker.knock());
    }

    @AfterClass
    public static void closeContext() throws NamingException {
        initialContext.close();
        initialContext = null;
    }
}