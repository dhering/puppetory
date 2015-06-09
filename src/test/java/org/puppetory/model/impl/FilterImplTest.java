package org.puppetory.model.impl;

import org.junit.Test;
import org.puppetory.model.api.Filter;

import static org.junit.Assert.assertEquals;

public class FilterImplTest {

    Filter filter1 = new FilterImpl("{name:\"foo\"}");
    Filter filter2 = new FilterImpl("{value:\"bar\"}");

    @Test
    public void testAndTwoValid(){
        Filter combined = filter1.and(filter2);

        assertEquals("{$and:[{name:\"foo\"}, {value:\"bar\"}]}", combined.getQuery());
    }

    @Test
    public void testAndFirstValid(){
        Filter combined = filter1.and(null);

        assertEquals("{name:\"foo\"}", combined.getQuery());
    }

    @Test
    public void testAndSecondValid(){
        Filter combined = new FilterImpl("").and(filter2);

        assertEquals("{value:\"bar\"}", combined.getQuery());
    }

    @Test
    public void testAndNoneValid(){
        Filter combined = new FilterImpl("").and(new FilterImpl(""));

        assertEquals("", combined.getQuery());
    }

    @Test
    public void testOrTwoValid(){
        Filter combined = filter1.or(filter2);

        assertEquals("{$or:[{name:\"foo\"}, {value:\"bar\"}]}", combined.getQuery());
    }

    @Test
    public void testOrFirstValid(){
        Filter combined = filter1.or(null);

        assertEquals("{name:\"foo\"}", combined.getQuery());
    }

    @Test
    public void testOrSecondValid(){
        Filter combined = new FilterImpl("").or(filter2);

        assertEquals("{value:\"bar\"}", combined.getQuery());
    }

    @Test
    public void testOrNoneValid(){
        Filter combined = new FilterImpl("").or(new FilterImpl(""));

        assertEquals("", combined.getQuery());
    }
}
