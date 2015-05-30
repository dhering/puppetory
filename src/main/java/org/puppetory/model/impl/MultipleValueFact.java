package org.puppetory.model.impl;

import org.puppetory.model.api.Fact;

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
            if(i>0){
                out += ", ";
            }
            out += '\'' + values[i] + "\'";
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
