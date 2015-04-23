package com.tsuchiya.wagner.study.sessionbeans;

import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * Created by wagner on 4/23/15.
 */
public class FirstAccessTest {
    private final static EJBContainer ejbContainer = EJBContainer.createEJBContainer();

    @Test
    public void instancesAreNotShared() throws Exception {

        final Context context = ejbContainer.getContext();

        final FirstAccess first = (FirstAccess) context.lookup("java:global/session-beans/FirstAccess");
        Long firstMillis = first.getFirstAcessMillis();
        Thread.sleep(1);
        // Should be the same at first state
        assertThat(first.getFirstAcessMillis(), equalTo(firstMillis));
        final FirstAccess second = (FirstAccess) context.lookup("java:global/session-beans/FirstAccess");
        Long secondMillis = second.getFirstAcessMillis();
        // The instances are not shared
        assertThat(firstMillis, not(equalTo(secondMillis)));
    }

    @AfterClass
    public static void closeEjbContainer() {
        ejbContainer.close();
    }

}