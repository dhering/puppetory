package org.puppetory.model.api;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 26.04.15
 * Time: 14:33
 * To change this template use File | Settings | File Templates.
 */
public interface Inventory {
	public Collection find(String collection);
	public Collection find(String collection, Filter filter);

    public void insert(String collectionName, Component component);
    public void upsert(String collectionName, Component component, Filter filter);
}
