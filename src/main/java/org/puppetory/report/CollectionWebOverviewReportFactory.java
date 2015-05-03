package org.puppetory.report;

import org.puppetory.model.api.Collection;
import org.puppetory.model.api.Filter;
import org.puppetory.model.api.Inventory;
import org.puppetory.model.api.Report;
import org.springframework.beans.factory.annotation.Autowired;

public class CollectionWebOverviewReportFactory {
	
	private Inventory inventory;
	private String collection;
	private Filter filter;

	@Autowired
	public CollectionWebOverviewReportFactory(Inventory inventory) {	
		this.inventory = inventory;
	}

	public Report createReport(){		
		Collection result = inventory.find(collection, filter);
		return new TestOverviewReport(result);
	}

}
