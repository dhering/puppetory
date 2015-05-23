package org.puppetory.model.impl;

import org.puppetory.model.api.Component;
import org.puppetory.model.api.Fact;

import java.util.List;

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

    @Override
    public String toString() {
        String out = "";

        for(int i=0; i<facts.size(); i++){
            Fact fact = facts.get(i);
            out += i>0 ? ", " + fact.toString() : fact.toString();
        }

        return "ComponentImpl({" + out + "})";
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }
}
