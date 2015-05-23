package org.puppetory.model.impl;

import org.puppetory.model.api.Fact;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 26.04.15
 * Time: 14:50
 * To change this template use File | Settings | File Templates.
 */
public class StructuredFact implements Fact{

    private String name;
    private List<Fact> facts;

    public StructuredFact(String name, List<Fact> facts) {
        this.name = name;
        this.facts = facts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public List<Fact> getFacts() {
		return facts;
	}

	public void setFacts(List<Fact> facts) {
		this.facts = facts;
	}

	@Override
	public String getValue() {

		String out = "";
		
		for(int i=0; i<facts.size(); i++){
			Fact fact = facts.get(i);
			out += i>0 ? ", " + fact.toString() : fact.toString(); 
		}
		
		return "{" + out + "}";
	}

	@Override
	public String toString() {
		return getName() + ": " + getValue();
	}
}
