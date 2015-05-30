package org.puppetory.model.impl;

import org.junit.Test;
import org.puppetory.model.impl.PersistentTextualFact;

import static junit.framework.Assert.assertEquals;

public class PersistentTextualFactTest {

    @Test
    public void testToString(){
        PersistentTextualFact fact = new PersistentTextualFact("foo", "bar");

        assertEquals("{name: 'foo', value: 'bar'}",fact.toString());
    }
}
