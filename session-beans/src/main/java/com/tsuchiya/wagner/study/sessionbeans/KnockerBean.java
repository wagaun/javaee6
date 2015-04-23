package com.tsuchiya.wagner.study.sessionbeans;

/**
 * Created by wagner on 4/23/15.
 */
public class KnockerBean implements RemoteKnocker, LocalKnocker {
    @Override
    public String knock() {
        return "Who is there?";
    }

    @Override
    public String remotelyKnock() {
        return "Send email: " + knock();
    }
}
