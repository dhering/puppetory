package org.puppetory.factories;

import org.puppetory.model.api.Collection;
import org.puppetory.model.api.Filter;
import org.puppetory.model.api.Inventory;
import org.puppetory.model.api.Report;
import org.puppetory.model.impl.FilterImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Constructor;

public class ReportFactory{

	private Inventory inventory;
	
	private String name;
	private String collection;
	private String filterString;
	
	private Class<?> beanClass;
	private Constructor<?> beanConstructor;

    public ReportFactory(String reportClass) throws ClassNotFoundException, NoSuchMethodException {
        beanClass = Class.forName(reportClass);
        this.beanConstructor = beanClass.getConstructor(Collection.class);

        this.inventory = null;
    }

	public Inventory getInventory() {
		return inventory;
	}

    @Autowired
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public String getFilterString() {
		return filterString;
	}

	public void setFilterString(String filterString) {
		this.filterString = filterString;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

    public Report createReport() throws Exception {
        return createReport(new FilterImpl(filterString));
    }

	public Report createReport(Filter filter) throws Exception {
        Filter combinedFilter = new FilterImpl(filterString).and(filter);

		Collection result = inventory.find(collection, combinedFilter);

		Report report =  (Report) this.beanConstructor.newInstance(new Object[] { result });
		report.setName(name);
		
		return report;
	}
}
