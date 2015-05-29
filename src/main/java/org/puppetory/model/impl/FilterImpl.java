package org.puppetory.model.impl;

import org.puppetory.model.api.Filter;

public class FilterImpl implements Filter {

	private String query;

    public FilterImpl() {
        query = "";
    }

    public FilterImpl(String query) {
        this.query = query;
    }

    @Override
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
}
