package org.puppetory.factories;

import org.junit.Before;
import org.junit.Test;
import org.puppetory.dummies.CollectionDummies;
import org.puppetory.model.api.Filter;
import org.puppetory.model.api.Inventory;
import org.puppetory.model.api.Report;
import org.puppetory.model.impl.FilterImpl;
import org.puppetory.model.impl.InventoryImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ReportFactoryTest {
	
	private ReportFactory factory;
	
	@Before
	public void setup() throws Exception{
		Inventory inventory = mock(InventoryImpl.class);
		when(inventory.find(any(String.class), any(Filter.class))).thenReturn(CollectionDummies.getSimpleCollection());
		
		factory = new ReportFactory("org.puppetory.report.TestOverviewReport");
		factory.setFilterString("");
        factory.setInventory(inventory);
		factory.setName("server");
	}

	@Test
	public void tesGetObject() throws Exception {
		
		Report report = factory.createReport();
		
		assertEquals("[name: 'dummyFact': [name: dummyFact][foo: bar]]", report.toString());
	}
	
	@Test
	public void tesGetObjectType() throws Exception {

        Report report = factory.createReport(new FilterImpl(""));

        assertEquals("[name: 'dummyFact': [name: dummyFact][foo: bar]]", report.toString());
	}
	
}
