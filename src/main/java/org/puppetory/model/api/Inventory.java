package org.puppetory.model.api;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 26.04.15
 * Time: 14:33
 * To change this template use File | Settings | File Templates.
 */
public interface Inventory {
    List<Component> find(String collection);
    List<Component> find(String collection, Filter filter);
}
