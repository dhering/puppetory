package org.puppetory.model.mockup;

import org.puppetory.model.api.Component;
import org.puppetory.model.api.Fact;
import org.puppetory.model.api.Filter;
import org.puppetory.model.api.Inventory;
import org.puppetory.model.impl.ComponentImpl;
import org.puppetory.model.impl.TextualFact;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class InventoryMockup implements Inventory{

    List<Component> components;

    public InventoryMockup() {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("puppet-data.json");

        JsonReader jsonReader = Json.createReader(input);
        JsonArray array = jsonReader.readArray();

        Set<Fact> facts = new HashSet<Fact>();

        for (int i=0; i<array.size(); i++){
            JsonObject fact = array.getJsonObject(i);
            String name = fact.getString("name");
            String value = fact.getString("value");
            facts.add(new TextualFact(name, value));

            if(name.endsWith("_count")){
                System.out.println(name);
            }
        }

        Component component = new ComponentImpl(facts);

        components = new ArrayList<>(1);
        components.add(component);
    }

    @Override
    public List<Component> find() {
        return components;
    }

    @Override
    public List<Component> find(Filter filter) {
        return components;
    }
}
