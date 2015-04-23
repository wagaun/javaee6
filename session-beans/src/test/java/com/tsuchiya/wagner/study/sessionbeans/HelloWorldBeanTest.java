package com.tsuchiya.wagner.study.sessionbeans;

import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by wagner on 4/23/15.
 */
public class HelloWorldBeanTest {

    private final static EJBContainer ejbContainer = EJBContainer.createEJBContainer();

    @Test
    public void sharedInstances() throws Exception {
        final Context context = ejbContainer.getContext();
        final HelloWorldBean first = (HelloWorldBean) context.lookup("java:global/session-beans/HelloWorldBean");
        assertThat(first.helloWorld("Wagner"), equalTo("Hello world, Wagner"));
        final HelloWorldBean second = (HelloWorldBean) context.lookup("java:global/session-beans/HelloWorldBean");
        // The instances are not shared
        assertThat(second.helloWorld("Tsuchiya"), equalTo("Hello world, Tsuchiya"));
        // The instances are identical
        assertThat(second.getRandom(), equalTo(first.getRandom()));
        // BUT There is a pool of identical instances they are not the same
        assertFalse(first == second);
    }

    @AfterClass
    public static void closeEjbContainer() {
        ejbContainer.close();
    }

}