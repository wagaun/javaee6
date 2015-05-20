package com.tsuchiya.wagner.study.cdi.scope.application;

import com.tsuchiya.wagner.study.cdi.scope.CommandProcessor;
import com.tsuchiya.wagner.study.cdi.scope.CommandWrapper;
import org.apache.webbeans.proxy.OwbNormalScopeProxy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by wagner on 5/20/15.
 */
public class ApplicationScopedCommandProcessorTest {

    private EJBContainer container;

    @EJB
    private CommandProcessor commandProcessor;

    @Before
    public void startContainer() throws Exception {
        container = EJBContainer.createEJBContainer();
        container.getContext().bind("inject", this);
    }

    @Test
    public void processCommands(){
        ApplicationScopedCommand firstCommand = commandProcessor.applicationScopedCommandToExecute("a command");
        ApplicationScopedCommand previous = commandProcessor.getPreviousApplicationScopedCommand();
        // Two different instances
        assertEquals(firstCommand.getCommandName(), previous.getCommandName());
    }

    @After
    public void closeContainer() throws Exception {
        container.close();
    }
}
