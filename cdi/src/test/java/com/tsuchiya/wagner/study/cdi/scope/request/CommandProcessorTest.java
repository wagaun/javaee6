package com.tsuchiya.wagner.study.cdi.scope.request;

import com.tsuchiya.wagner.study.cdi.scope.CommandWrapper;
import com.tsuchiya.wagner.study.cdi.scope.CommandProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by wagner on 5/20/15.
 */
public class CommandProcessorTest {

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
        RequestScopedCommand firstCommand = commandProcessor.requestScopedCommandToExecute("a command");
        // FIXME how to start another request here?
        RequestScopedCommand secondCommand = commandProcessor.requestScopedCommandToExecute("another");
        RequestScopedCommand previous = commandProcessor.getPreviousRequestScopedCommand();
        // Two different instances
        assertNotEquals(firstCommand.getCommandName(), secondCommand.getCommandName());
        assertNotEquals(secondCommand.getCommandName(), previous.getCommandName());
    }

    @After
    public void closeContainer() throws Exception {
        container.close();
    }
}
