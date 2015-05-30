package org.puppetory.model.impl;

import org.junit.Test;
import org.puppetory.dummies.CollectionDummies;
import org.puppetory.model.api.Collection;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 30.05.15
 * Time: 09:45
 * To change this template use File | Settings | File Templates.
 */
public class CollectionImplTest {

    @Test
    public void testToString(){

        Collection collection = CollectionDummies.getCollectionWithTwoEntries();

        assertEquals("CollectionImpl(" +
                        "[ComponentImpl(" +
                            "{foo: 'bar', name: 'dummyFact', " +
                                "subfacts: {foo: 'bar', name: 'dummyFact'}" +
                            "}), " +
                        "ComponentImpl(" +
                            "{foo: 'bar', " +
                            "list: [" +
                                "{name: 'name1', value: 'value1'}, " +
                                "{name: 'name2', value: 'value2'}" +
                            "], " +
                            "name: 'dummyFact', " +
                            "subfacts: " +
                                "{foo: 'bar', name: 'dummyFact'}" +
                            "}" +
                          ")])", collection.toString());
    }
}
