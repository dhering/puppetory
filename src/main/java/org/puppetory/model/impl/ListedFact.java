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
public class ListedFact extends StructuredFact {


    public ListedFact(String name, List<Fact> facts) {
        super(name, facts);
    }

    @Override
	public String getValue() {

		String out = "";

        List<Fact> facts = getFacts();

        for(int i=0; i<facts.size(); i++){
			Fact fact = facts.get(i);
			out += i>0 ? ", " + fact.toString() : fact.toString(); 
		}
		
		return "[" + out + "]";
	}
}
