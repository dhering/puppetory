package org.puppetory.model.impl;

import org.puppetory.model.api.Fact;

public class MultipleValueFact implements Fact{

    private String name;
    private String[] values;

    public MultipleValueFact(String name, String[] values) {
        this.name = name;
        this.values = values.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {

        StringBuffer out = new StringBuffer();

        for(int i=0; i<values.length; i++){
            if(i>0){
                out.append(", ");
            }
            out.append('\'');
            out.append(values[i]);
            out.append('\'');
        }

        return "[" + out.toString() + "]";
    }

    public String[] getValues() {
        return values.clone();
    }

    public void setValues(String[] values) {
        this.values = values.clone();
    }

    @Override
	public String toString() {
		return getName() + ": " + getValue();
	}
}
