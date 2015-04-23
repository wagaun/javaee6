package com.tsuchiya.wagner.study.sessionbeans;

import org.hamcrest.CoreMatchers;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import java.util.concurrent.CountDownLatch;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Created by wagner on 4/16/15.
 */
public class RuntimePropertiesTest {

    private final static EJBContainer ejbContainer = EJBContainer.createEJBContainer();

    @Test
    public void unsafeChangeShouldReturnIncorrectValue() throws NamingException, InterruptedException {
        final Context context = ejbContainer.getContext();

        final RuntimeProperties one = (RuntimeProperties) context.lookup("java:global/session-beans/RuntimeProperties");
        final RuntimeProperties two = (RuntimeProperties) context.lookup("java:global/session-beans/RuntimeProperties");

        // Synchronize two threads
        CountDownLatch latch = new CountDownLatch(2);
        final String input1 = "Hello I am number 1";
        ChangeRuntimeProperty task1 = new ChangeRuntimeProperty(latch, one) {
            @Override
            protected String doSomething() {
                return runtimeProperties.changeWaitAndGetUnsafeMessage(input1);
            }
        };
        new Thread(task1).start();
        final String input2 = "Hello I am number 2";
        ChangeRuntimeProperty task2 = new ChangeRuntimeProperty(latch, two) {
            @Override
            protected String doSomething() {
                return runtimeProperties.changeWaitAndGetUnsafeMessage(input2);
            }
        };
        new Thread(task2).start();
        latch.await();
        assertFalse(task1.getOutput().equals(input1) && task2.getOutput().equals(input2));
    }

    @Test
    public void safeChangeShouldReturnIncorrectValue() throws NamingException, InterruptedException {
        final Context context = ejbContainer.getContext();

        final RuntimeProperties one = (RuntimeProperties) context.lookup("java:global/session-beans/RuntimeProperties");
        final RuntimeProperties two = (RuntimeProperties) context.lookup("java:global/session-beans/RuntimeProperties");

        // Synchronize two threads
        CountDownLatch latch = new CountDownLatch(2);
        final String input1 = "Hello I am number 1";
        ChangeRuntimeProperty task1 = new ChangeRuntimeProperty(latch, one) {
            @Override
            protected String doSomething() {
                return runtimeProperties.changeWaitAndGetSafeMessage(input1);
            }
        };
        new Thread(task1).start();
        final String input2 = "Hello I am number 2";
        ChangeRuntimeProperty task2 = new ChangeRuntimeProperty(latch, two) {
            @Override
            protected String doSomething() {
                return runtimeProperties.changeWaitAndGetSafeMessage(input2);
            }
        };
        new Thread(task2).start();
        latch.await();
        assertThat(task1.getOutput(), equalTo(input1));
        assertThat(task2.getOutput(), equalTo(input2));
    }

    @AfterClass
    public static void closeEjbContainer() {
        ejbContainer.close();
    }

    private abstract static class ChangeRuntimeProperty implements Runnable {

        private final CountDownLatch countDownLatch;

        protected final RuntimeProperties runtimeProperties;


        private String output;

        private ChangeRuntimeProperty(CountDownLatch countDownLatch, RuntimeProperties runtimeProperties) {
            this.countDownLatch = countDownLatch;
            this.runtimeProperties = runtimeProperties;
        }

        @Override
        public void run() {
            output = doSomething();
            countDownLatch.countDown();
        }

        abstract protected String doSomething();

        public String getOutput() {
            return output;
        }
    }
}
