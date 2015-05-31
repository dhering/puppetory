package org.puppetory.data.api;

import org.puppetory.model.api.Collection;
import org.puppetory.model.api.Component;
import org.puppetory.model.api.Filter;

/**
 * central interface for any data access and manipulation
 */
public interface Repository {
    /**
     * find all {@link Component}s of a collection by the given collections name
     *
     * @param collection
     * @return a {@link Collection}
     */
    public Collection find(String collection);

    /**
     * find all {@link Component}s of a collection by the given collections name
     * which are matching the given filer
     *
     * @param collection
     * @return a {@link Collection}
     */
    public Collection find(String collection, Filter filter);

    /**
     * insert a given {@link Component} to the specified collection
     *
     * @param collectionName
     * @param component
     */
    void insert(String collectionName, Component component);

    /**
     * insert a new or update a known {@link Component}, but use the given {@link Filter}
     * to identify if a {@link Component} already exists.
     *
     * @param collectionName
     * @param component
     * @param filter
     */
    void upsert(String collectionName, Component component, Filter filter);
}
