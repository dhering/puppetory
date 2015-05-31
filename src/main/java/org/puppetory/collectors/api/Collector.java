package org.puppetory.collectors.api;

/**
 * Collectors are worker classes to run via a scheduler, collects inventory data and
 * store it into the database.
 */
public interface Collector {
    /**
     * call this method to start the collecting process
     */
    public void collect();
}
