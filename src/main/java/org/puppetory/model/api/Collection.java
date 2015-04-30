package org.puppetory.model.api;

import java.util.Set;

public interface Collection {
    public Set<Component> getComponents();
    public Filter getFilter();
}
