package org.puppetory.dummies;

import org.puppetory.model.api.Collection;
import org.puppetory.model.api.Component;
import org.puppetory.model.impl.CollectionImpl;
import org.puppetory.model.impl.ComponentImpl;
import org.puppetory.model.impl.FilterImpl;

import java.util.Arrays;
import java.util.List;

public class CollectionDummies {
	
	public static Collection getSimpleCollection(){

        Component component = new ComponentImpl(FactDummies.getTwoFacts());
		
		List<Component> components = Arrays.asList(new Component[]{component});
		
		return new CollectionImpl(components, new FilterImpl());
	}

    public static Collection getCollectionWithStructuredFact(){

        Component component = new ComponentImpl(FactDummies.getStructuredFacts());

        List<Component> components = Arrays.asList(new Component[]{component});

        return new CollectionImpl(components, new FilterImpl());
    }


    public static Collection getCollectionWithTwoEntries(){

        Component component = new ComponentImpl(FactDummies.getStructuredFacts());
        Component component2 = new ComponentImpl(FactDummies.getDeepStructuredFacts());

        List<Component> components = Arrays.asList(new Component[]{component, component2});

        return new CollectionImpl(components, new FilterImpl());
    }
}
