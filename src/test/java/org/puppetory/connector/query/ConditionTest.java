package org.puppetory.connector.query;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConditionTest {

    @Test
    public void testWhereCondition(){
        Condition condition = new Condition();
        condition.where("name").equals("test");

        assertEquals(condition.toString(),"name=test");
    }
}
