package org.puppetory.model.impl;

import org.junit.Test;
import org.puppetory.model.impl.TextualFact;

import static junit.framework.Assert.assertEquals;

public class TextualFactTest {

    @Test
    public void testToString(){
        TextualFact fact = new TextualFact("foo","bar");

        assertEquals("foo: 'bar'",fact.toString());
    }
}
