package com.tsuchiya.wagner.study.cdi.scope;

import com.tsuchiya.wagner.study.cdi.scope.application.ApplicationScopedCommand;
import com.tsuchiya.wagner.study.cdi.scope.request.RequestScopedCommand;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by wagner on 5/20/15.
 */
@Stateless
public class CommandWrapper {

    @Inject
    private RequestScopedCommand requestCommand;

    @Inject
    private ApplicationScopedCommand applicationCommand;

    public RequestScopedCommand getRequestCommand() {
        return requestCommand;
    }

    public ApplicationScopedCommand getApplicationCommand() {
        return applicationCommand;
    }
}
