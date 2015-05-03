package org.puppetory.model.api;

import java.util.List;

public interface Collection {
    public List<Component> getComponents();
    public Filter getFilter();
}
