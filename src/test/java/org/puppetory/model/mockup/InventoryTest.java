package org.puppetory.model.mockup;

import org.junit.Before;
import org.junit.Test;
import org.puppetory.model.api.Component;
import org.puppetory.model.api.Filter;
import org.puppetory.model.api.Inventory;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class InventoryTest {

    Inventory inventory;

    @Before
    public void before(){
        inventory = new InventoryMockup();
    }

    @Test
    public void findAllTest(){
        List<Component> components = inventory.find();

        assertEquals(components.size(), 1);
    }

    @Test
    public void findTest(){
        List<Component> components = inventory.find(new Filter() {
            @Override
            public String getQuery() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        assertEquals(components.size(), 1);
    }
}
