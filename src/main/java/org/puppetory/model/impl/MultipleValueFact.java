package org.puppetory.model.impl;

import org.puppetory.model.api.Fact;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 26.04.15
 * Time: 14:50
 * To change this template use File | Settings | File Templates.
 */
public class MultipleValueFact implements Fact{

    private String name;
    private String[] values;

    public MultipleValueFact(String name, String[] values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {

        String out = "";

        for(int i=0; i<values.length; i++){
            out += i>0 ? ", " + values[i] : values[i];
        }

        return "[" + out + "]";
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    @Override
	public String toString() {
		return getName() + ": " + getValue();
	}
}
