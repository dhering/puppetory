package org.puppetory.connector.query;

import org.puppetory.connector.api.PuppetQuery;
import org.puppetory.connector.config.PuppetUrlManager;

class SimpleQuery implements PuppetQuery {

    private String query;
    private PuppetUrlManager puppetUrlManager;

    public SimpleQuery(String query, PuppetUrlManager puppetUrlManager) {
        this.query = query;
        this.puppetUrlManager = puppetUrlManager;
    }

    @Override
    public String getQuery() {
        return puppetUrlManager.getQueryBaseUrl() + query;
    }
}
