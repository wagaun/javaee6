package com.tsuchiya.wagner.study.sessionbeans;

import org.hamcrest.CoreMatchers;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by wagner on 4/16/15.
 */
public class ApplicationConfigTest {

    private final static EJBContainer ejbContainer = EJBContainer.createEJBContainer();

    @Test
    public void oneInstancePerMultipleReferences() throws Exception {

        final Context context = ejbContainer.getContext();

        final ApplicationConfig one = (ApplicationConfig) context.lookup("java:global/session-beans/ApplicationConfig");
        final ApplicationConfig two = (ApplicationConfig) context.lookup("java:global/session-beans/ApplicationConfig");

        // Should not be null, so it is initialized
        assertThat(one.getIndex(), equalTo(0));
        // The same
        assertThat(two.getIndex(), equalTo(0));
        one.incrementIndex();
        // As expected
        assertThat(one.getIndex(), equalTo(1));
        // If they are singleton two should be the same
        assertThat(two.getIndex(), equalTo(1));
    }

    @Test
    public void shouldSayHello() throws NamingException {
        final Context context = ejbContainer.getContext();

        final ApplicationConfig config = (ApplicationConfig) context.lookup("java:global/session-beans/ApplicationConfig");
        assertThat(config.getMyProperty(), equalTo("Hello world!"));
    }

    @AfterClass
    public static void closeEjbContainer() {
        ejbContainer.close();
    }
}
