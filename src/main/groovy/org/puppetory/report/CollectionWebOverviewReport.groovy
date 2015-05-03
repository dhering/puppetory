package org.puppetory.report

import org.puppetory.model.impl.StructuredFact;
import org.puppetory.model.impl.TextualReportTemplate

class CollectionWebOverviewReport extends TextualReportTemplate{

    StringWriter writer;
    def builder;

    CollectionWebOverviewReport(org.puppetory.model.api.Collection collection) {
        super(collection);
		writer = new StringWriter();
		builder = new groovy.xml.MarkupBuilder(writer);
    }

	@Override
    String toString() {
		
    	builder.div([class:"col-lg-12 col-md-12 col-sm-12 col-xs-12"]){
			collection.components.eachWithIndex { component, componentCount ->
				
				builder.div([class:"panel panel-primary"]){
					builder.div([class:"panel-heading"]){
						def nameFact = component.getFact("name");
						builder.yield("${nameFact.value}", true)
						builder.a([class:"btn btn-default btn-xs pull-right", href:"#", role:"button"]){
							yield("View details <span class=\"caret\"></span>", false)
						}
					}
				}
				
				div([class:"panel-body"]){
					table([class:"table table-striped"]){
						tr(){
							th(){
								yield("Fact", true)
							}
							th([colspan:2]){
								yield("Value", true)
							}
						}
						component.facts.eachWithIndex { fact, factCount ->							
							if(fact instanceof StructuredFact){
								
								fact.facts.eachWithIndex { subfact, subfactCount ->
									if(subfactCount == 0){
										builder.tr(){
											td([rowspan:fact.facts.size]){
												yield(fact.name, true)
											}
											td(){
												yield(subfact.name, true)
											}
											td(){
												yield(subfact.value, true)
											}
										}
									} else {
										builder.tr(){
											td(){
												yield(subfact.name, true)
											}
											td(){
												yield(subfact.value, true)
											}
										}
									}
								}
							} else {
								builder.tr(){
									td(){
										yield(fact.name, true)
									}
									td([colspan:2]){
										yield(fact.value, true)
									}
								}
							}
						}
					}
				}					
			}
		}
       
        return writer.toString();
    }
}
