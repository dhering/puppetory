package org.puppetory.model.impl;

import org.junit.Test;
import org.puppetory.model.impl.MultipleValueFact;

import static junit.framework.Assert.assertEquals;

public class MultipleValueFactTest {

    @Test
    public void testToString(){
        MultipleValueFact fact = new MultipleValueFact("foo", new String[]{"bar","woo"});

        assertEquals("foo: ['bar', 'woo']",fact.toString());
    }
}
