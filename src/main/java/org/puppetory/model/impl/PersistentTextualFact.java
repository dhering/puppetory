package org.puppetory.model.impl;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 23.05.15
 * Time: 18:14
 * To change this template use File | Settings | File Templates.
 */
public class PersistentTextualFact extends TextualFact {

    public PersistentTextualFact(String name, String value) {
        super(name, value);
    }

    @Override
    public String toString() {
        return "{" +
                "name: '" + getName() + "', " +
                "value: '" + getValue() + "'" +
               "}";
    }
}
