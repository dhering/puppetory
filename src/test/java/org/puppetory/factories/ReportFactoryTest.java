package org.puppetory.factories;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.puppetory.dummies.CollectionDummies;
import org.puppetory.model.api.Filter;
import org.puppetory.model.api.Inventory;
import org.puppetory.model.api.Report;
import org.puppetory.model.impl.InventoryImpl;

import static org.mockito.Mockito.*;

public class ReportFactoryTest {
	
	private ReportFactory factory;
	
	@Before
	public void setup() throws Exception{
		Inventory inventory = mock(InventoryImpl.class);
		when(inventory.find(any(String.class), any(Filter.class))).thenReturn(CollectionDummies.getSimpleCollection());
		
		factory = new ReportFactory(inventory);
		factory.setFilterString("");
		factory.setReportClass("org.puppetory.report.TestOverviewReport");
		factory.setName("server");
		factory.afterPropertiesSet();
	}

	@Test
	public void tesGetObject() throws Exception {
		
		Report report = factory.getObject();
		
		assertEquals("[name: simplecollection: [name: simplecollection][test: fact]]", report.toString());
	}
	
	@Test
	public void tesGetObjectType() throws Exception {
		
		Class<?> objectType = factory.getObjectType();
		
		assertEquals("org.puppetory.report.TestOverviewReport", objectType.getName());
	}
	
}
