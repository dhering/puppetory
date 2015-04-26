package org.puppetory.model.impl;

import org.puppetory.model.api.Component;
import org.puppetory.model.api.Fact;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 26.04.15
 * Time: 14:53
 * To change this template use File | Settings | File Templates.
 */
public class ComponentImpl implements Component {

    Set<Fact> facts;

    public ComponentImpl(Set<Fact> facts) {
        this.facts = facts;
    }

    public Set<Fact> getFacts() {
        return facts;
    }
}
