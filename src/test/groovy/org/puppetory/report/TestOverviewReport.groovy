package org.puppetory.report

import org.puppetory.model.impl.StructuredFact;
import org.puppetory.model.impl.TextualReportTemplate

class TestOverviewReport extends TextualReportTemplate{

    TestOverviewReport(org.puppetory.model.api.Collection collection) {
        super(collection);
    }

	@Override
    String toString() {
			def out = "";
			
			collection.components.eachWithIndex { component, componentCount ->
				
			if(componentCount > 0){
				out += ",\n";
			}
			
			def nameFact = component.getFact("name");
			
			out += "[${nameFact}: ";
			
			component.facts.eachWithIndex { fact, factCount ->							
				out += "[${fact.name}: ${fact.value}]";
			}	
			
			out += "]";
		}
       
        return out;
    }
}
