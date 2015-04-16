package com.tsuchiya.wagner.study.sessionbeans;

import javax.ejb.*;

/**
 * Created by wagner on 4/16/15.
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.READ)
public class RuntimeProperties {

    private String anUnsafeMessage;

    private String aSafeMessage;

    @Lock(LockType.READ)
    public String changeWaitAndGetUnsafeMessage(String anUnsafeMessage) {
        this.anUnsafeMessage = anUnsafeMessage;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        return anUnsafeMessage;
    }

    @Lock(LockType.WRITE)
    public String changeWaitAndGetSafeMessage(String aSafeMessage) {
        this.aSafeMessage = aSafeMessage;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        return aSafeMessage;
    }
}
