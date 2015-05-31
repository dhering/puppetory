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

    @Override
    public Filter and(Filter add) {
        return combind(add, "$and");
    }

    @Override
    public Filter or(Filter add) {
        return combind(add, "$or");
    }

    private Filter combind(Filter add, String operator){
        boolean current = isValid(this);
        boolean toAdd = isValid(add);

        if(current && toAdd){
            return new FilterImpl("{" + operator + ":[" +this.getQuery()+ ", " +add.getQuery()+ "]}");
        } else if (current) {
            return this;
        } else if (toAdd){
            return add;
        }

        return new FilterImpl("");
    }

    private boolean isValid(Filter filter){
        return !(filter == null || filter.getQuery() == null || filter.getQuery().isEmpty());
    }

    @Override
    public String toString() {
        return getQuery();
    }
}
