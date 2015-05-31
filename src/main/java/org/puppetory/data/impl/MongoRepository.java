package org.puppetory.data.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import org.bson.Document;
import org.puppetory.data.api.DbModelMapper;
import org.puppetory.data.api.Repository;
import org.puppetory.model.api.Collection;
import org.puppetory.model.api.Component;
import org.puppetory.model.api.Filter;
import org.puppetory.model.impl.CollectionImpl;
import org.puppetory.model.impl.FilterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * mongoDB repository implementation
 */
@Service("repository")
public class MongoRepository implements Repository{

    private MongoDatabase database;
    private DbModelMapper mapper;

    @Autowired
    public MongoRepository(@Qualifier("database") MongoDatabase database, DbModelMapper dbModelMapper) {
        this.database = database;
        this.mapper = dbModelMapper;
    }

    @Override
    public Collection find(String collectionName) {
        MongoCollection<Document> mongoCollection = database.getCollection(collectionName);

        FindIterable<Document> documents = mongoCollection.find();

        List<Component> components = new LinkedList<Component>();

        for(Document document : documents){
            components.add(mapper.getComponentFromDocument(document));
        }

        return new CollectionImpl(components, new FilterImpl());
    }

    @Override
    public Collection find(String collectionName, Filter filter) {

        MongoCollection<Document> mongoCollection = database.getCollection(collectionName);
        FindIterable<Document> documents;

        if(filter.getQuery() == null || filter.getQuery().isEmpty()){
            documents = mongoCollection.find();
        } else {
            Document mongoFilter = Document.parse(filter.getQuery());
            documents = mongoCollection.find(mongoFilter);
        }

        List<Component> components = new LinkedList<Component>();

        for(Document document : documents){
            components.add(mapper.getComponentFromDocument(document));
        }

        return new CollectionImpl(components, filter);
    }

    @Override
    public void insert(String collectionName, Component component) {
        Document document = mapper.getDocumentFromComponent(component);

        database.getCollection(collectionName).insertOne(document);
    }

    @Override
    public void upsert(String collectionName, Component component, Filter filter) {
        Document document = mapper.getDocumentFromComponent(component);
        Document docFilter = Document.parse(filter.getQuery());

        database.getCollection(collectionName).findOneAndUpdate(docFilter, new Document("$set", document), new FindOneAndUpdateOptions().upsert(true));
    }
}
