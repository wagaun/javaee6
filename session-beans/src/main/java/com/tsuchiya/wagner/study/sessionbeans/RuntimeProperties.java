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
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.anUnsafeMessage;
    }

    @Lock(LockType.WRITE)
    public String changeWaitAndGetSafeMessage(String aSafeMessage) {
        this.aSafeMessage = aSafeMessage;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return this.aSafeMessage;
    }
}
