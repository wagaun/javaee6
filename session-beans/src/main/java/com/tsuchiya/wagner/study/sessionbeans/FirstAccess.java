package com.tsuchiya.wagner.study.sessionbeans;

import javax.ejb.Stateful;
import java.security.Timestamp;
import java.util.Date;

/**
 * Created by wagner on 4/23/15.
 */
@Stateful
public class FirstAccess {

    private Long firstAcessMillis = null;

    public Long getFirstAcessMillis() {
        if(firstAcessMillis == null) {
            firstAcessMillis = System.currentTimeMillis();
        }
        return firstAcessMillis;
    }
}
