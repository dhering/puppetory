package org.puppetory.dummies;

import java.util.Arrays;
import java.util.List;

import org.puppetory.model.api.Collection;
import org.puppetory.model.api.Component;
import org.puppetory.model.api.Fact;
import org.puppetory.model.impl.CollectionImpl;
import org.puppetory.model.impl.ComponentImpl;
import org.puppetory.model.impl.FilterImpl;
import org.puppetory.model.impl.TextualFact;

public class CollectionDummies {
	
	public static Collection getSimpleCollection(){
		
		Component component = new ComponentImpl(Arrays.asList(new Fact[] {
				new TextualFact("name","simplecollection"),
				new TextualFact("test","fact")
		}));
		
		List<Component> components = Arrays.asList(new Component[]{component});
		
		return new CollectionImpl(components, new FilterImpl());
	}

}
