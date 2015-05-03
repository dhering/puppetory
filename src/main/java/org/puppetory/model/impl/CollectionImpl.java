package org.puppetory.model.impl;

import java.util.List;

import org.puppetory.model.api.Collection;
import org.puppetory.model.api.Component;
import org.puppetory.model.api.Filter;
import org.puppetory.model.api.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
