package org.puppetory.factories;

import java.lang.reflect.Constructor;

import org.puppetory.model.api.Collection;
import org.puppetory.model.api.Inventory;
import org.puppetory.model.api.Report;
import org.puppetory.model.impl.FilterImpl;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class ReportFactory implements FactoryBean<Report>, InitializingBean{

	private Inventory inventory;
	
	private String name;
	private String collection;
	private String filterString;
	private String reportClass;
	
	private Class<?> beanClass;
	private Constructor<?> beanConstructor;
		
	@Autowired
	public ReportFactory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public String getFilterString() {
		return filterString;
	}

	public void setFilterString(String filterString) {
		this.filterString = filterString;
	}

	public String getReportClass() {
		return reportClass;
	}

	public void setReportClass(String reportClass) {
		this.reportClass = reportClass;
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

	@Override
	public Report getObject() throws Exception {
		Collection result = inventory.find(collection, new FilterImpl());
		
		Report report =  (Report) this.beanConstructor.newInstance(new Object[] { result });
		report.setName(name);

		
		return report;
	}

	@Override
	public Class<?> getObjectType() {
		final Class<?> clazz = this.beanClass;
		return clazz;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		beanClass = Class.forName(reportClass);
		this.beanConstructor = beanClass.getConstructor(Collection.class);
	}
}
