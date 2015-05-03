package org.puppetory.mockups;

import java.util.ArrayList;
import java.util.List;

import org.puppetory.model.api.Collection;
import org.puppetory.model.api.Component;
import org.puppetory.model.api.Fact;
import org.puppetory.model.api.Filter;
import org.puppetory.model.api.Inventory;
import org.puppetory.model.impl.CollectionImpl;
import org.puppetory.model.impl.ComponentImpl;
import org.puppetory.model.impl.FilterImpl;
import org.puppetory.model.impl.StructuredFact;
import org.puppetory.model.impl.TextualFact;
import org.springframework.stereotype.Service;

@Service
public class InventoryMockup implements Inventory{

    Collection collection;

    public InventoryMockup() {
		List<Component> components = new ArrayList<Component>();
		
		List<Fact> facts = new ArrayList<Fact>();
    	facts.add(new TextualFact("name", "testserver"));
    	facts.add(new TextualFact("ip", "127.0.0.1"));
    	facts.add(new TextualFact("os", "debian"));
    	facts.add(new TextualFact("core", "linux"));
    	
    	List<Fact> softwareFacts = new ArrayList<Fact>();
    	softwareFacts.add(new TextualFact("httpd", "2.0.5"));
    	softwareFacts.add(new TextualFact("curl", "1.1.0"));
    	softwareFacts.add(new TextualFact("tomcat", "7.0"));
    	
    	facts.add(new StructuredFact("software", softwareFacts));
    	    	
    	Component component = new ComponentImpl(facts); 
    	
    	components.add(component);
    	components.add(component);
    	
    	this.collection = new CollectionImpl(components, new FilterImpl());
	}

	@Override
	public Collection find(String collection) {
		return this.collection;
	}

	@Override
	public Collection find(String collection, Filter filter) {
		return this.collection;
	}
}
