package org.puppetory.model.impl;

import org.puppetory.model.api.Fact;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 26.04.15
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public class HierarchicalFact implements Fact {

    String name;
    Set<Fact> facts;

    public HierarchicalFact(String name, Set<Fact> facts) {
        this.name = name;
        this.facts = facts;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {

        String value = "";

        for(Fact fact : facts){

            if(value.length() > 0){
                value += ", ";
            }

            value += fact.getName() + ": " + fact.getValue();
        }

        return "[" + value + "]";
    }

    public Set<Fact> getFacts() {
        return facts;
    }
}
