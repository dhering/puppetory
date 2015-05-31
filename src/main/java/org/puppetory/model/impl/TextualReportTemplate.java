package org.puppetory.model.impl;

import org.puppetory.model.api.Collection;
import org.puppetory.model.api.Report;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 30.04.15
 * Time: 18:25
 * To change this template use File | Settings | File Templates.
 */
public abstract class TextualReportTemplate implements Report {

    protected Collection collection;
    protected String name;
    
    public TextualReportTemplate(Collection collection){
    	this.collection = collection;
    	this.name = this.getClass().getSimpleName();
    }

    public TextualReportTemplate(Collection collection, String name){
    	this.collection = collection;
    	this.name = name;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public Collection getCollection() {
        return collection;
    }
}
