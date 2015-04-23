package com.tsuchiya.wagner.study.sessionbeans;

import javax.ejb.Stateless;
import java.util.Random;

/**
 * Created by wagner on 4/23/15.
 *
 * Important:
 * Stateless session beans are session beans whose instances have no conversational state. This means that all bean instances are equivalent when they are not involved in servicing a client-invoked method. The term 'stateless' signifies that an instance has no state for a specific client.
 */
@Stateless
public class HelloWorldBean {

    public String helloWorld(String name) {
        return "Hello world, " + name;
    }

    // This code is just for testing. It breaks the semantics of statelessness
    private Long random = null;

    public Long getRandom()
    {
        if(random == null) {
            random = new Random().nextLong();
        }
        return random;
    }
}
