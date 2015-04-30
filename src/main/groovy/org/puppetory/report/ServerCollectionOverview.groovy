package org.puppetory.report

import org.puppetory.model.impl.TextualReportTemplate

class ServerCollectionOverview extends TextualReportTemplate{

    StringWriter writer = new StringWriter();
    def builder = new groovy.xml.MarkupBuilder(writer);

    ServerCollectionOverview(org.puppetory.model.api.Collection collection) {
        super(collection)
    }

    @Override
    String toString() {

        builder.div([class:"one"]){
            div([class:"two"]){
                p("class":"wohoo"){
                    yield("foo bar", true)
                }
            }
        }

        return writer.toString();
    }
}
