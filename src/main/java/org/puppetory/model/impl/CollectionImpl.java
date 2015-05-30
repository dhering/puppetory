package org.puppetory.model.impl;

import org.puppetory.model.api.Collection;
import org.puppetory.model.api.Component;
import org.puppetory.model.api.Filter;

import java.util.List;

public class CollectionImpl implements Collection {

	private List<Component> components;
	private Filter filter;
	
	public CollectionImpl(List<Component> components, Filter filter) {
		this.components = components;
		this.filter = filter;
	}

	@Override
	public List<Component> getComponents() {
		return components;
	}

	@Override
	public Filter getFilter() {
		return filter;
	}

    @Override
    public String toString() {
        String out = "";

        for(int i=0; i<components.size(); i++){
            Component component = components.get(i);
            out += i>0 ? ", " + component.toString() : component.toString();
        }

        return "CollectionImpl([" + out + "])";
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
