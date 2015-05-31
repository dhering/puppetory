package org.puppetory.data.impl;

import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;
import org.puppetory.dummies.FactDummies;
import org.puppetory.model.api.Component;
import org.puppetory.model.impl.ComponentImpl;

public class PuppetMongoMapperTest {

    private final Component referenceComponent = new ComponentImpl(FactDummies.getDeepStructuredFacts());
    private final Document referenceDocument = Document.parse("{name: 'dummyFact', foo: 'bar', " +
            "subfacts: {name: 'dummyFact', foo: 'bar'}, " +
            "list: [{name: 'name1', value: 'value1'},{name: 'name2', value: 'value2'}]}");

    private PuppetMongoMapper mapper = new PuppetMongoMapper();

    @Test
    public void testComponentFromDocument() {

        Component component = mapper.getComponentFromDocument(referenceDocument);

        Assert.assertEquals(referenceComponent, component);
    }

    @Test
    public void testDocumentFromComponent() {

        Document document = mapper.getDocumentFromComponent(referenceComponent);

        Assert.assertEquals(referenceDocument, document);
    }
}
