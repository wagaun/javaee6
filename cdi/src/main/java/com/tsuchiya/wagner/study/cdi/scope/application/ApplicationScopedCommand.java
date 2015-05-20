package com.tsuchiya.wagner.study.cdi.scope.application;

import com.tsuchiya.wagner.study.cdi.scope.CommandWrapper;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by wagner on 5/20/15.
 */
@ApplicationScoped
public class ApplicationScopedCommand {
    private String command;

    public void setCommandName(String command) {
        this.command = command;
    }

    public String getCommandName() {
        return command;
    }
}
