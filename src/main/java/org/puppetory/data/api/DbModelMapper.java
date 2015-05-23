package org.puppetory.data.api;

import org.bson.Document;
import org.puppetory.model.api.Component;

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
}
