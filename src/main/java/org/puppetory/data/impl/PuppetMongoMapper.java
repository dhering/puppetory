package org.puppetory.data.impl;

import org.bson.BsonArray;
import org.bson.BsonString;
import org.bson.Document;
import org.puppetory.data.api.DbModelMapper;
import org.puppetory.model.api.Component;
import org.puppetory.model.api.Fact;
import org.puppetory.model.impl.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 18.05.15
 * Time: 23:17
 * To change this template use File | Settings | File Templates.
 */
@Service("dbModelMapper")
public class PuppetMongoMapper implements DbModelMapper {

    public PuppetMongoMapper(){}

    @Override
    public Document getDocumentFromComponent(Component component) {
        if(component == null){
            return new Document();
        }

        return getDocumentFromFacts(component.getFacts());
    }

    @Override
    public Component getComponentFromDocument(Document document) {

        return new ComponentImpl(getFactsFromDocument(document));
    }

    @Override
    public List<Fact> getFactsFromDocument(Document document) {

        List<Fact> facts = new ArrayList<Fact>();

        if(document != null){
            for(String factName : document.keySet()){

                Fact fact = getFactFromDocument(document, factName);
                if (fact != null) {
                    facts.add(fact);
                }
            }
        }

        return facts;
    }

    @Override
    public Fact getFactFromDocument(Document document, String factName){
        Object value = document.get(factName);
        Fact fact = null;

        if(value instanceof Document){
            fact = new StructuredFact(factName, getFactsFromDocument((Document) value));
        } else if (value instanceof List){

            List<Object> values =  (List<Object>) value;

            String[] allStringValues = getAllStrings(values);

            if(allStringValues != null){
                fact = new MultipleValueFact(factName, allStringValues);
            } else {
                fact = new ListedFact(factName, getFactsFromDocuments((List<Document>) value));
            }
        } else {
            fact = new TextualFact(factName, value.toString());
        }

        return fact;
    }

    public List<Fact> getFactsFromDocuments(List<Document> documents) {

        List<Fact> facts = new ArrayList<Fact>(documents.size());

        for(Document document : documents){
            String name = document.getString("name");
            String value = document.getString("value");

            if(document.size() == 2 && name != null && value != null){
                facts.add(new PersistentTextualFact(name, value));
            } else {
                facts.addAll(getFactsFromDocument(document));
            }
        }

        return facts;
    }

    private String[] getAllStrings(List<Object> values){
        String[] array = new String[values.size()];

        for(int i=0; i<values.size(); i++){
            Object o = values.get(i);
            if(o instanceof Document || o instanceof List){
                return null;
            } else {
                array[i] = o.toString();
            }
        }

        return array;
    }

    @Override
    public Document getDocumentFromFacts(List<Fact> facts){

        Document document = new Document();

        if(facts != null && facts.isEmpty() == false){
            for(Fact fact : facts){
                addFactToDocument(fact, document);
            }
        }

        return document;
    }

    public void addFactToDocument(Fact fact, Document document){

        if(fact instanceof ListedFact){
            document.put(fact.getName(), getDocumentListFromFacts(((ListedFact) fact).getFacts()));
        } else if(fact instanceof StructuredFact){
            document.put(fact.getName(), getDocumentFromFacts(((StructuredFact) fact).getFacts()));
        }else if(fact instanceof PersistentTextualFact){
            document.put("name", fact.getName());
            document.put("value", fact.getValue());
        }else if(fact instanceof MultipleValueFact){
            BsonArray factList = new BsonArray();
            for(String value : ((MultipleValueFact) fact).getValues()){
                factList.add(new BsonString(value));
            }
            document.put(fact.getName(), factList);
        } else {
            document.put(fact.getName(), fact.getValue());
        }
    }

    public List<Document> getDocumentListFromFacts(List<Fact> facts){

        List<Document> list = new LinkedList<Document>();

        for(Fact fact : facts){
            Document document = new Document();
            addFactToDocument(fact, document);
            list.add(document);
        }

        return list;
    }
}
