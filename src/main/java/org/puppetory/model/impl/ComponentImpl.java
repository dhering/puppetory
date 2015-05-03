package org.puppetory.model.impl;

import java.util.List;

import org.puppetory.model.api.Component;
import org.puppetory.model.api.Fact;

public class ComponentImpl implements Component {

	List<Fact> facts;

    public ComponentImpl(List<Fact> facts) {
        this.facts = facts;
    }

    public List<Fact> getFacts() {
        return facts;
    }

	@Override
	public Fact getFact(String name) {
		
		for(Fact fact : facts){
			if(fact.getName().equals(name)){
				return fact;
			}
		}
		
		return null;
	}
}
