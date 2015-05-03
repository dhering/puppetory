package org.puppetory.model.mockup;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.puppetory.mockups.InventoryMockup;
import org.puppetory.model.api.Collection;
import org.puppetory.model.api.Inventory;

public class InventoryTest {

    Inventory inventory;

    @Before
    public void before(){
        inventory = new InventoryMockup();
    }

    @Test
    public void findAllTest(){
        Collection components = inventory.find("");

        assertEquals(components.getComponents().size(), 2);
    }

    @Ignore
    @Test
    public void findTest(){
        //List<Component> components = inventory.find("", new Filer());
        // assertEquals(components.size(), 1);
    }
}
