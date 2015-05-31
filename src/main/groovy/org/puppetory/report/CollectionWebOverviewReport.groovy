package org.puppetory.report

import org.puppetory.model.FactListComparator
import org.puppetory.model.api.Fact
import org.puppetory.model.impl.MultipleValueFact
import org.puppetory.model.impl.StructuredFact
import org.puppetory.model.impl.TextualReportTemplate

class CollectionWebOverviewReport extends TextualReportTemplate{

    StringWriter writer;
    def builder;
    def toSkip = ["sshdsakey","sshrsakey","sshecdsakey","sshfp_dsa","sshfp_ecdsa","sshfp_rsa"];

    def tabs = [
            OS: [
                    operatingsystem:"OS name",
                    operatingsystemrelease:"OS Version",
                    system_uptime: "uptime",
                    architecture: "architecture"
            ],
            Hardware: [
                    serialnumber: "serial number",
                    productname: "product name",
                    processor: "processor",
                    memorysize:"memory",
                    memoryfree:"free memory",
                    swapsize: "swap",
                    puppetory_pci: "PCI devices",
                    type: "type"
            ],
            Software: [
                    puppetory_servicesoftware: "service software",
                    puppetory_software: "software"
            ],
            Organisation: [
                    puppetory_server_anlagennummer: "Anlagenummer",
                    puppetory_server_kostenstelle: "Kostenstelle",
                    puppetory_server_lieferdatum: "Lieferdatum",
                    puppetory_server_status: "Status",
                    puppetory_server_verwendungszweck: "Verwendungszweck"
            ]
            ]

    CollectionWebOverviewReport(org.puppetory.model.api.Collection collection) {
        super(collection);
		writer = new StringWriter();
		builder = new groovy.xml.MarkupBuilder(writer);
    }

	@Override
    String toString() {
    	builder.div([class:"col-lg-12 col-md-12 col-sm-12 col-xs-12"]){
            builder.h1(){
                yield(getName(), false)
            }

			collection.components.eachWithIndex { component, componentCount ->

                String uuid = component.getFact("uuid").value;
                String componentName = component.getFact("hostname").value;

				builder.div([class:"panel panel-default", id:componentName]){
					div([class:"panel-heading"]){
						yield("${componentName}", true)
						a([class:"btn btn-default btn-xs pull-right toggle-details", href:"#"+componentName, role:"button"]){
							yield("View details <span class=\"caret\"></span>", false)
						}
					}

                    builder.div([class:"panel-body hide"]){
                        div([role:"tabpanel"]){
                            ul([class:"nav nav-tabs", role:"tablist"]){
                                tabs.eachWithIndex { tab, index ->
                                    def id = uuid + "--" + tab.key;
                                    def classes = [role:"presentation"];
                                    if(index==0) classes["class"] = "active";
                                    builder.li(classes){
                                        a([href:"#"+id, "aria-controls":id, role:"tab", "data-toggle":"tab"]){
                                            yield(tab.key, true);
                                        }
                                    }
                                }

                                li([role:"presentation"]){
                                    def id = uuid + "--All";
                                    a([href:"#"+id, "aria-controls":id, role:"tab", "data-toggle":"tab"]){
                                        yield("All", true);
                                    }
                                }
                            }

                            div([class:"tab-content"]){
                                tabs.eachWithIndex { tab, index ->
                                    def id = uuid + "--" + tab.key;
                                    def classes = [class:"tab-pane",role:"tabpanel", id:id];
                                    if(index==0) classes["class"] = "tab-pane active";
                                    builder.div(classes){
                                        printTable(builder, component.facts, tab.value);
                                    }
                                }

                                div([class:"tab-pane", role:"tabpanel", id:uuid + "--All"]){
                                    printTable(builder, component.facts, null);
                                }
                            }
                        }
                    }
				}
			}
		}
       
        return writer.toString();
    }

    void printTable(def builder, def facts, def nameMapping){
        builder.table([class:"table table-striped"]){
            tr(){
                th(){
                    yield("Fact", true)
                }
                th([colspan:2]){
                    yield("Value", true)
                }
            }

            def selectedFacts = [];
            if(nameMapping == null){
                selectedFacts = facts;
            } else {
                nameMapping.each { key, name ->
                    facts.each { fact ->
                        if(fact.name == key){
                            selectedFacts.add(fact);
                        }
                    }
                }
            }
            Collections.sort(selectedFacts, FactListComparator.getInstance());

            selectedFacts.eachWithIndex { fact, factCount ->
                if(toSkip.contains(fact.name)){

                } else if(fact instanceof StructuredFact){

                    Collections.sort(fact.facts, FactListComparator.getInstance());
                    fact.facts.eachWithIndex { subfact, subfactCount ->
                        if(subfactCount == 0){
                            builder.tr(){
                                td([rowspan:fact.facts.size]){
                                    yield(mapFactName(fact.name, nameMapping), true)
                                }
                                td(){
                                    yield(subfact.name, true)
                                }
                                td(){
                                    printFactValue(builder, subfact);
                                }
                            }
                        } else {
                            builder.tr(){
                                td(){
                                    yield(subfact.name, true)
                                }
                                td(){
                                    printFactValue(builder, subfact);
                                }
                            }
                        }
                    }
                } else {
                    builder.tr(){
                        td(){
                            yield(mapFactName(fact.name, nameMapping), true)
                        }
                        td([colspan:2]){
                            printFactValue(builder, fact);
                        }
                    }
                }
            }
        }
    }

    String mapFactName(String key, def nameMapping){

        if(nameMapping == null){
            return key;
        }

        String name = nameMapping[key];

        return name == null || name.empty ? key : name;
    }

    void printFactValue(def builder, Fact fact){
        if(fact instanceof MultipleValueFact){
            builder.ul(){
                ((MultipleValueFact) fact).values.each { value ->
                   builder.li(){
                       builder.yield(value, true);
                   }
                }
            }
        } else if (fact instanceof StructuredFact){
            builder.ul(){
                ((StructuredFact) fact).facts.each { structFact ->
                    builder.li(){
                        builder.yield(structFact.name + ": ", true);
                        printFactValue(builder, structFact);
                    }
                }
            }
        } else if (fact.value.indexOf(';') > 0){
            def lines = fact.value.split(';');

            for (int i=0; i<lines.size(); i++){
                if(i>0){
                    builder.yield("<br />", false);
                }
                builder.yield(lines[i], true);
            }


        } else {
            builder.yield(fact.value, true);
        }
    }
}
