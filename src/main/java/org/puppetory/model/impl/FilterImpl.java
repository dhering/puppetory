package org.puppetory.model.impl;

import org.puppetory.model.api.Filter;

public class FilterImpl implements Filter {

	private String query;
	
	@Override
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
}
