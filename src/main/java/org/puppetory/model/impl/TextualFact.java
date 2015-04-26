package org.puppetory.model.impl;

import org.puppetory.model.api.Fact;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 26.04.15
 * Time: 14:50
 * To change this template use File | Settings | File Templates.
 */
public class TextualFact implements Fact{

    private String name;
    private String value;

    public TextualFact(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
