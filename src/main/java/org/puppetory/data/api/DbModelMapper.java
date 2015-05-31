package org.puppetory.data.api;

import org.bson.Document;
import org.puppetory.model.api.Component;
import org.puppetory.model.api.Fact;

import java.util.List;

/**
 * mapper to get a {@link Component} or {@link Fact}s from a {@link Document} or backwards.
 */
public interface DbModelMapper {
    /**
     * map the given {@link Component} to a {@link Document}
     *
     * @param component
     * @return the mapped document
     */
    Document getDocumentFromComponent(Component component);

    /**
     * map the given {@link Document} to a {@link Component}
     *
     * @param document
     * @return the mapped component
     */
    Component getComponentFromDocument(Document document);

    /**
     * map a list of {@link Fact}s to a {@link Document}
     *
     * @param facts
     * @return the mapped document
     */
    Document getDocumentFromFacts(List<Fact> facts);

    /**
     * map the given {@link Document} to a list of {@link Fact}
     *
     * @param document
     * @return a list of facts
     */
    List<Fact> getFactsFromDocument(Document document);

    /**
     * read a specified fact by the given name from the given document
     *
     * @param document
     * @param factName
     * @return specified fact from document
     */
    Fact getFactFromDocument(Document document, String factName);
}
