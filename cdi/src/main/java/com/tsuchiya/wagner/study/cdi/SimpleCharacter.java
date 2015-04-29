package com.tsuchiya.wagner.study.cdi;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by wagner on 4/29/15.
 */
@Stateless
public class SimpleCharacter {

    @Inject
    private QuestBag questBag;

    private Quest quest;

    private String name;

    @PostConstruct
    public void init() {
        quest = new Quest("Destroy the ring");
        name = "A name";
    }

    public QuestBag getQuestBag() {
        return questBag;
    }

    public Quest getQuest() {
        return quest;
    }

    public String getName() {
        return name;
    }
}
