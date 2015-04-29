package com.tsuchiya.wagner.study.cdi;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wagner on 4/29/15.
 */
public class QuestBag {

    private List<String> stuff = new ArrayList<>();

    @PostConstruct
    public void init() {
        stuff.add("knife");
        stuff.add("food");
        stuff.add("ring");
    }

    public List<String> getStuff() {
        return stuff;
    }
}
