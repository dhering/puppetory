package org.puppetory.data.api;

import org.puppetory.model.api.Collection;
import org.puppetory.model.api.Filter;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 18.05.15
 * Time: 23:08
 * To change this template use File | Settings | File Templates.
 */
public interface Repository {
    public Collection find(String collection);
    public Collection find(String collection, Filter filter);
}
