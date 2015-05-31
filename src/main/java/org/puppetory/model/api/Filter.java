package org.puppetory.model.api;

public interface Filter {
    public String getQuery();
    public Filter and(Filter add);
    public Filter or(Filter add);
}
