package org.puppetory.model.impl;

import org.puppetory.data.api.Repository;
import org.puppetory.model.api.Collection;
import org.puppetory.model.api.Component;
import org.puppetory.model.api.Filter;
import org.puppetory.model.api.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("inventory")
public class InventoryImpl implements Inventory {

    private Repository repository;

    @Autowired
    public InventoryImpl(Repository repository) {
        this.repository = repository;
    }

	@Override
	public Collection find(String collection) {
		return repository.find(collection);
	}

	@Override
	public Collection find(String collection, Filter filter) {
		return repository.find(collection, filter);
	}

    @Override
    public void insert(String collectionName, Component component) {
        repository.insert(collectionName, component);
    }

    @Override
    public void upsert(String collectionName, Component component, Filter filter) {
        repository.upsert(collectionName, component, filter);
    }

}
