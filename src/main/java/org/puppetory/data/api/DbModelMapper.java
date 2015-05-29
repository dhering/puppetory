package org.puppetory.data.api;

import org.bson.Document;
import org.puppetory.model.api.Component;
import org.puppetory.model.api.Fact;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 21.05.15
 * Time: 22:32
 * To change this template use File | Settings | File Templates.
 */
public interface DbModelMapper {
    Document getDocumentFromComponent(Component component);
    Component getComponentFromDocument(Document document);
    Document getDocumentFromFacts(List<Fact> facts);
    List<Fact> getFactsFromDocument(Document document);

    Fact getFactFromDocument(Document document, String factName);
}
