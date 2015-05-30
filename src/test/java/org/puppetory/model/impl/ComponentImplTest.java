package org.puppetory.model.impl;

import org.junit.Test;
import org.puppetory.dummies.FactDummies;
import org.puppetory.model.api.Component;

import static junit.framework.Assert.assertEquals;

public class ComponentImplTest {

    @Test
    public void testToString(){

        Component component = new ComponentImpl(FactDummies.getDeepStructuredFacts());

        assertEquals("ComponentImpl(" +
                        "{foo: 'bar', " +
                        "list: [" +
                        "{name: 'name1', value: 'value1'}, " +
                        "{name: 'name2', value: 'value2'}" +
                        "], " +
                        "name: 'dummyFact', " +
                        "subfacts: " +
                        "{foo: 'bar', name: 'dummyFact'}" +
                        "})", component.toString());
    }
}
