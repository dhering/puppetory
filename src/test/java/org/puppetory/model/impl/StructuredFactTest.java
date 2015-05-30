package org.puppetory.model.impl;

import org.junit.Test;
import org.puppetory.dummies.FactDummies;
import org.puppetory.model.impl.StructuredFact;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 30.05.15
 * Time: 09:32
 * To change this template use File | Settings | File Templates.
 */
public class StructuredFactTest {

    @Test
    public void testToString(){
        StructuredFact fact = new StructuredFact("foo", FactDummies.getTwoFacts());

        assertEquals("foo: {foo: 'bar', name: 'dummyFact'}",fact.toString());
    }
}
