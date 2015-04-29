package com.tsuchiya.wagner.study.cdi;

import org.junit.Before;
import org.junit.Test;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import static org.junit.Assert.*;

/**
 * Created by wagner on 4/29/15.
 */
public class SimpleCharacterTest {

    @EJB
    private SimpleCharacter simpleCharacter;

    @Before
    public void setUp() throws Exception {
        EJBContainer.createEJBContainer().getContext().bind("inject", this);
    }

    @Test
    public void simpleCharacterInjectionTest() {

        // Was the EJB injected?
        assertTrue(simpleCharacter != null);

        // Was the SimpleCharacter @PostConstruct called?
        assertNotNull(simpleCharacter.getName());
        final Quest quest = simpleCharacter.getQuest();
        assertTrue(quest != null);
        assertEquals(quest.getName(), "Destroy the ring");

        // Was a QuestBag instance injected into SimpleCharacter?
        final QuestBag questBag = simpleCharacter.getQuestBag();
        assertTrue(questBag != null);

        // Was the @PostConstruct called on QuestBag?
        assertEquals(questBag.getStuff().size(), 3);
        assertTrue(questBag.getStuff().contains("knife"));
        assertTrue(questBag.getStuff().contains("food"));
        assertTrue(questBag.getStuff().contains("ring"));
    }

}