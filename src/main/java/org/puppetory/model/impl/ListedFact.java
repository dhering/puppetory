package org.puppetory.model.impl;

import org.puppetory.model.FactListComparator;
import org.puppetory.model.api.Fact;

import java.util.Collections;
import java.util.List;

public class ListedFact extends StructuredFact {


    public ListedFact(String name, List<Fact> facts) {
        super(name, facts);
    }

    @Override
	public String getValue() {

		String out = "";

        List<Fact> facts = getFacts();
        Collections.sort(facts, FactListComparator.getInstance());

        for(int i=0; i<facts.size(); i++){
			Fact fact = facts.get(i);
			out += i>0 ? ", " + fact.toString() : fact.toString(); 
		}
		
		return "[" + out + "]";
	}
}
