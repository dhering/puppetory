package org.puppetory.model.mockup;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.puppetory.model.api.Component;
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
        List<Component> components = inventory.find("");

        assertEquals(components.size(), 1);
    }

    @Ignore
    @Test
    public void findTest(){
        //List<Component> components = inventory.find("", new Filer());
        // assertEquals(components.size(), 1);
    }
}
