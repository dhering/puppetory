package org.puppetory.model.impl;

import org.junit.Test;
import org.puppetory.dummies.FactDummies;

import static junit.framework.Assert.assertEquals;

public class ListedFactTest {

    @Test
    public void testToString(){
        ListedFact fact = new ListedFact("foo", FactDummies.getTwoFacts());

        assertEquals("foo: [foo: 'bar', name: 'dummyFact']",fact.toString());
    }
}
