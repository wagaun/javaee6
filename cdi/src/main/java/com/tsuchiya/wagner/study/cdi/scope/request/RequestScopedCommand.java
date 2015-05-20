package com.tsuchiya.wagner.study.cdi.scope.request;

import com.tsuchiya.wagner.study.cdi.scope.CommandWrapper;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * Created by wagner on 5/20/15.
 */
@RequestScoped
public class RequestScopedCommand {

    private String command;

    public void setCommandName(String command) {
        this.command = command;
    }

    public String getCommandName() {
        return command;
    }
}
