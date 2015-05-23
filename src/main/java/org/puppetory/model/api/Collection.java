package org.puppetory.model.api;

import java.util.List;

/**
 * Collections are a subset of the {@link Inventory} which is a filtered list of {@link Component}s.
 */
public interface Collection {
    /**
     *
     * @return empty list, if no results
     */
    public List<Component> getComponents();

    /**
     *
     *
     * @return used filter
     */
    public Filter getFilter();
}
