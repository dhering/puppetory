package org.puppetory.data.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.puppetory.data.api.DbModelMapper;
import org.puppetory.data.api.Repository;
import org.puppetory.model.api.Collection;
import org.puppetory.model.api.Component;
import org.puppetory.model.api.Filter;
import org.puppetory.model.impl.CollectionImpl;
import org.puppetory.model.impl.FilterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 18.05.15
 * Time: 23:09
 * To change this template use File | Settings | File Templates.
 */

//@Profile("production")
@Service("repository")
public class MongoRepository implements Repository{

    private MongoDatabase database;
    private DbModelMapper mapper;

    @Autowired
    public MongoRepository(MongoDatabase database, DbModelMapper dbModelMapper) {
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
}
