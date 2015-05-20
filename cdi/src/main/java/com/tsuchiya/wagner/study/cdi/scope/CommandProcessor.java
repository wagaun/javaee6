package com.tsuchiya.wagner.study.cdi.scope;

import com.tsuchiya.wagner.study.cdi.scope.application.ApplicationScopedCommand;
import com.tsuchiya.wagner.study.cdi.scope.request.RequestScopedCommand;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by wagner on 5/20/15.
 */
@Stateless
public class CommandProcessor {

    @Inject
    private CommandWrapper command;

    public ApplicationScopedCommand applicationScopedCommandToExecute(String commandName) {
        command.getApplicationCommand().setCommandName(commandName);
        return command.getApplicationCommand();
    }

    public ApplicationScopedCommand getPreviousApplicationScopedCommand() {
        return command.getApplicationCommand();
    }

    public RequestScopedCommand requestScopedCommandToExecute(String commandName) {
        command.getRequestCommand().setCommandName(commandName);
        return command.getRequestCommand();
    }

    public RequestScopedCommand getPreviousRequestScopedCommand() {
        return command.getRequestCommand();
    }

}
